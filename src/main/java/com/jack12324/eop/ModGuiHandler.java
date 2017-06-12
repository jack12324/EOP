package com.jack12324.eop;

import com.jack12324.eop.machine.activationChamber.ContainerActivationChamber;
import com.jack12324.eop.machine.activationChamber.GuiActivationChamber;
import com.jack12324.eop.machine.activationChamber.TileEntityActivationChamber;
import com.jack12324.eop.machine.catalystInfuser.ContainerCatalystInfuser;
import com.jack12324.eop.machine.catalystInfuser.GuiCatalystInfuser;
import com.jack12324.eop.machine.catalystInfuser.TileEntityCatalystInfuser;
import com.jack12324.eop.machine.disablingPress.ContainerDisablingPress;
import com.jack12324.eop.machine.disablingPress.GuiDisablingPress;
import com.jack12324.eop.machine.disablingPress.TileEntityDisablingPress;
import com.jack12324.eop.machine.dualCatalystInfuser.ContainerDualCatalystInfuser;
import com.jack12324.eop.machine.dualCatalystInfuser.GuiDualCatalystInfuser;
import com.jack12324.eop.machine.dualCatalystInfuser.TileEntityDualCatalystInfuser;
import com.jack12324.eop.machine.endericPurifier.ContainerEndericPurifier;
import com.jack12324.eop.machine.endericPurifier.GuiEndericPurifier;
import com.jack12324.eop.machine.endericPurifier.TileEntityEndericPurifier;
import com.jack12324.eop.machine.equalizingSmelter.ContainerEqualizingSmelter;
import com.jack12324.eop.machine.equalizingSmelter.GuiEqualizingSmelter;
import com.jack12324.eop.machine.equalizingSmelter.TileEntityEqualizingSmelter;
import com.jack12324.eop.machine.particleExciter.ContainerParticleExciter;
import com.jack12324.eop.machine.particleExciter.GuiParticleExciter;
import com.jack12324.eop.machine.particleExciter.TileEntityParticleExciter;
import com.jack12324.eop.machine.pedestal.ContainerPedestal;
import com.jack12324.eop.machine.pedestal.GuiPedestal;
import com.jack12324.eop.machine.pedestal.TileEntityPedestal;
import com.jack12324.eop.machine.starHardener.ContainerStarHardener;
import com.jack12324.eop.machine.starHardener.GuiStarHardener;
import com.jack12324.eop.machine.starHardener.TileEntityStarHardener;
import com.jack12324.eop.machine.triCatalystInfuser.ContainerTriCatalystInfuser;
import com.jack12324.eop.machine.triCatalystInfuser.GuiTriCatalystInfuser;
import com.jack12324.eop.machine.triCatalystInfuser.TileEntityTriCatalystInfuser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;



public class ModGuiHandler implements IGuiHandler{
	
	public static final int ACTIVATIONCHAMBER = 0;
	public static final int EQUALIZINGSMELTER = 1;
	public static final int DISABLINGPRESS =2;
	public static final int PARTICLEEXCITER = 3;
	public static final int PEDESTAL = 4;
	public static final int CATALYSTINFUSER =5;
	public static final int DUALCATALYSTINFUSER =6;
	public static final int ENDERICPURIFIER =7;
	public static final int STARHARDENER=8;
	public static final int TRICATALYSTINFUSER=9;

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case ACTIVATIONCHAMBER:
			return new ContainerActivationChamber(player.inventory, (TileEntityActivationChamber)world.getTileEntity(new BlockPos(x,y,z)));
		case EQUALIZINGSMELTER:
			return new ContainerEqualizingSmelter(player.inventory, (TileEntityEqualizingSmelter)world.getTileEntity(new BlockPos(x,y,z)));
		case DISABLINGPRESS:
			return new ContainerDisablingPress(player.inventory, (TileEntityDisablingPress)world.getTileEntity(new BlockPos(x,y,z)));
		case PARTICLEEXCITER:
			return new ContainerParticleExciter(player.inventory, (TileEntityParticleExciter)world.getTileEntity(new BlockPos(x,y,z)));
		case PEDESTAL:
			return new ContainerPedestal(player.inventory, (TileEntityPedestal)world.getTileEntity(new BlockPos(x, y, z)));
		case CATALYSTINFUSER:
			return new ContainerCatalystInfuser(player.inventory, (TileEntityCatalystInfuser)world.getTileEntity(new BlockPos(x, y, z)));
		case DUALCATALYSTINFUSER:
			return new ContainerDualCatalystInfuser(player.inventory, (TileEntityDualCatalystInfuser)world.getTileEntity(new BlockPos(x, y, z)));
		case ENDERICPURIFIER:
			return new ContainerEndericPurifier(player.inventory, (TileEntityEndericPurifier)world.getTileEntity(new BlockPos(x, y, z)));
		case STARHARDENER:
			return new ContainerStarHardener(player.inventory, (TileEntityStarHardener)world.getTileEntity(new BlockPos(x, y, z)));
		case TRICATALYSTINFUSER:
			return new ContainerTriCatalystInfuser(player.inventory, (TileEntityTriCatalystInfuser)world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		 
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos xyz = new BlockPos(x,y,z);
		switch (ID) {
		case ACTIVATIONCHAMBER:
			return new GuiActivationChamber(getServerGuiElement(ID, player, world, x, y, z), player.inventory, (TileEntityActivationChamber) world.getTileEntity(xyz));
		case EQUALIZINGSMELTER:
			return new GuiEqualizingSmelter(getServerGuiElement(ID, player, world, x, y, z), player.inventory, (TileEntityEqualizingSmelter) world.getTileEntity(xyz));
		case DISABLINGPRESS:
			return new GuiDisablingPress(getServerGuiElement(ID, player, world, x, y, z), player.inventory, (TileEntityDisablingPress) world.getTileEntity(xyz));
		case PARTICLEEXCITER:
			return new GuiParticleExciter(getServerGuiElement(ID, player, world, x, y, z), player.inventory, (TileEntityParticleExciter) world.getTileEntity(xyz));
		case PEDESTAL:
			return new GuiPedestal(getServerGuiElement(ID,player,world,x,y,z),player.inventory,(TileEntityPedestal)world.getTileEntity(xyz));
		case CATALYSTINFUSER:
			return new GuiCatalystInfuser(getServerGuiElement(ID,player,world,x,y,z),player.inventory,(TileEntityCatalystInfuser)world.getTileEntity(xyz));
		case DUALCATALYSTINFUSER:
			return new GuiDualCatalystInfuser(getServerGuiElement(ID,player,world,x,y,z),player.inventory,(TileEntityDualCatalystInfuser)world.getTileEntity(xyz));
		case ENDERICPURIFIER:
			return new GuiEndericPurifier(getServerGuiElement(ID,player,world,x,y,z),player.inventory,(TileEntityEndericPurifier)world.getTileEntity(xyz));
		case STARHARDENER:
			return new GuiStarHardener(getServerGuiElement(ID,player,world,x,y,z),player.inventory,(TileEntityStarHardener)world.getTileEntity(xyz));
		case TRICATALYSTINFUSER:
			return new GuiTriCatalystInfuser(getServerGuiElement(ID,player,world,x,y,z),player.inventory,(TileEntityTriCatalystInfuser)world.getTileEntity(xyz));
		default:
			return null;
	}
	}

}
