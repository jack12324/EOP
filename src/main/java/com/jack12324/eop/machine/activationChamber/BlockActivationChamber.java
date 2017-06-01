package com.jack12324.eop.machine.activationChamber;

import javax.annotation.Nullable;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.BlockTE;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockActivationChamber extends BlockTE<TileEntityActivationChamber> {
	
	
    
	public BlockActivationChamber()
	{
		super(Material.ROCK, "activation_chamber");
	}
	

	
	@Override 
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) 
	{
		if (!world.isRemote) 
		{
			player.openGui(ExtremeOreProcessing.instance, ModGuiHandler.ACTIVATIONCHAMBER, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	//drop items in block as well as block
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityActivationChamber tile = getTileEntity(world, pos);
		if (tile instanceof IInventory){
			InventoryHelper.dropInventoryItems(world, pos, (IInventory)tile);
		}
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public Class<TileEntityActivationChamber> getTileEntityClass() {
		return TileEntityActivationChamber.class;
	}
	
	@Nullable
	@Override
	public TileEntityActivationChamber createTileEntity(World world, IBlockState state) {
		return new TileEntityActivationChamber();
	}
	
	
}