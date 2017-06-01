package com.jack12324.eop.packet;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.machine.TETickingMachine;
import com.jack12324.eop.machine.equalizingSmelter.PacketClientState;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class PacketHandler
{
 public static final List<IDataHandler> DATA_HANDLERS = new ArrayList<IDataHandler>();
 public static SimpleNetworkWrapper NETWORK;

 public static final IDataHandler TILE_ENTITY_HANDLER = new IDataHandler(){
     @Override
     @SideOnly(Side.CLIENT)
     public void handleData(NBTTagCompound compound, MessageContext context){
         World world = Minecraft.getMinecraft().world;
         if(world != null){
             TileEntity tile = world.getTileEntity(new BlockPos(compound.getInteger("X"), compound.getInteger("Y"), compound.getInteger("Z")));
             if(tile instanceof TETickingMachine){
                 ((TETickingMachine)tile).readSyncableNBT(compound.getCompoundTag("Data"), TETickingMachine.NBTType.SYNC);
             }
         }
     }
 };

 public static final void init() {
 NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(ExtremeOreProcessing.modID);
 NETWORK.registerMessage(PacketServerToClient.Handler.class, PacketServerToClient.class, 0, Side.CLIENT);
 NETWORK.registerMessage(PacketClientToServer.Handler.class, PacketClientToServer.class, 1, Side.SERVER);
 NETWORK.registerMessage(PacketClientState.class, PacketClientState.class, 2, Side.SERVER);//TODO add button handler and erase this
 
 DATA_HANDLERS.add(TILE_ENTITY_HANDLER);
 }

 


}