package com.jack12324.eop.packet;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.IButtonUse;
import com.jack12324.eop.machine.TETickingMachine;
import com.jack12324.eop.util.Coord4D;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketHandler {
	public static final List<IDataHandler> DATA_HANDLERS = new ArrayList<IDataHandler>();
	public static SimpleNetworkWrapper NETWORK;
	public static ModGuiHandler modGuiHandler = new ModGuiHandler();
	public static final IDataHandler TILE_ENTITY_HANDLER = new IDataHandler() {
		@Override
		@SideOnly(Side.CLIENT)
		public void handleData(NBTTagCompound compound, MessageContext context) {
			World world = Minecraft.getMinecraft().world;
			if (world != null) {
				TileEntity tile = world.getTileEntity(
						new BlockPos(compound.getInteger("X"), compound.getInteger("Y"), compound.getInteger("Z")));
				if (tile instanceof TETickingMachine) {
					((TETickingMachine) tile).readSyncableNBT(compound.getCompoundTag("Data"),
							TETickingMachine.NBTType.SYNC);
				}
			}
		}
	};

	public static final IDataHandler GUI_TOGGLE_BUTTON = new IDataHandler() {
		@Override
		// @SideOnly(Side.SERVER)
		public void handleData(NBTTagCompound compound, MessageContext context) {
			World world = Minecraft.getMinecraft().world;
			if (world != null) {
				TileEntity tile = world.getTileEntity(
						new BlockPos(compound.getInteger("X"), compound.getInteger("Y"), compound.getInteger("Z")));
				if (tile != null && tile instanceof IButtonUse) {
					((IButtonUse) tile).onButtonPress(compound.getInteger("buttonID"));
				}
			}
		}
	};

	public static final IDataHandler GUI_UPGRADE_BUTTON = new IDataHandler() {
		@Override
		public void handleData(NBTTagCompound compound, MessageContext context) {
			EntityPlayer player = ExtremeOreProcessing.proxy.getPlayer(context);

			Coord4D coord4D = Coord4D.read(compound);
			System.out.println(coord4D.getPos());

			if (!player.world.isRemote) {
				EntityPlayerMP playerMP = (EntityPlayerMP) player;
				World worldServer = FMLCommonHandler.instance().getMinecraftServerInstance()
						.worldServerForDimension(coord4D.dimensionId);
				System.out.println((worldServer == null) + " worldServer null Check");
				if (worldServer != null && coord4D.getTileEntity(worldServer) instanceof TETickingMachine) {
					System.out.println("worldServer notnull");
					playerMP.closeContainer();
					playerMP.getNextWindowId();

					int window = playerMP.currentWindowId;

					compound.setInteger("window", window);

					NETWORK.sendTo(new PacketServerToClient(compound, PacketHandler.GUI_UPGRADE_BUTTON), playerMP);

					playerMP.openContainer = modGuiHandler.getServerGuiElement(compound.getInteger("guiID"), playerMP,
							player.world, coord4D.getPos());
					playerMP.openContainer.windowId = window;
					playerMP.openContainer.addListener(playerMP);
				}
			} else {
				System.out.println("cli");
				FMLCommonHandler.instance().showGuiScreen(modGuiHandler
						.getClientGuiElement(compound.getInteger("guiID"), player, player.world, coord4D.getPos()));
				player.openContainer.windowId = compound.getInteger("window");
			}
		}

	};

	public static final void init() {
		NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(ExtremeOreProcessing.modID);
		NETWORK.registerMessage(PacketServerToClient.Handler.class, PacketServerToClient.class, 0, Side.CLIENT);
		NETWORK.registerMessage(PacketClientToServer.Handler.class, PacketClientToServer.class, 1, Side.SERVER);

		DATA_HANDLERS.add(TILE_ENTITY_HANDLER);
		DATA_HANDLERS.add(GUI_UPGRADE_BUTTON);
		DATA_HANDLERS.add(GUI_TOGGLE_BUTTON);
	}

}