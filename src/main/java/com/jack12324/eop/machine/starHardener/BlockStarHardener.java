package com.jack12324.eop.machine.starHardener;

import javax.annotation.Nullable;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.BlockTE;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.UniversalBucket;

public class BlockStarHardener extends BlockTE<TileEntityStarHardener> {

	public BlockStarHardener() {
		super(Material.ROCK, "star_hardener");

	}
/*
	 @Override
	    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing par6, float par7, float par8, float par9){
	        if(!world.isRemote){
	            TileEntityStarHardener te = (TileEntityStarHardener)world.getTileEntity(pos);
	            if(te != null){
	                if(!this.tryUseItemOnTank(player, hand, world, pos, null)){
	                	player.openGui(ExtremeOreProcessing.instance, ModGuiHandler.STARHARDENER, world, pos.getX(), pos.getY(), pos.getZ());
	                }
	            }
	            return true;
	        }
	        return true;
	    }*/
	@Override
	protected int getGui(){
		return ModGuiHandler.STARHARDENER;
	}


	// drop items in block as well as block
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityStarHardener tile = getTileEntity(world, pos);
		if (tile instanceof IInventory) {
			InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public Class<TileEntityStarHardener> getTileEntityClass() {
		return TileEntityStarHardener.class;
	}

	@Nullable
	@Override
	public TileEntityStarHardener createTileEntity(World world, IBlockState state) {
		return new TileEntityStarHardener();
	}

}
