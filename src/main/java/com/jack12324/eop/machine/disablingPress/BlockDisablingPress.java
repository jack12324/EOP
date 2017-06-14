package com.jack12324.eop.machine.disablingPress;

import javax.annotation.Nullable;

import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.BlockTE;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDisablingPress extends BlockTE<TileEntityDisablingPress> {

	public BlockDisablingPress() {
		super(Material.ROCK, "disabling_press");

	}

	@Override
	protected int getGui() {
		return ModGuiHandler.DISABLINGPRESS;
	}

	// drop items in block as well as block
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityDisablingPress tile = getTileEntity(world, pos);
		if (tile instanceof IInventory) {
			InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public Class<TileEntityDisablingPress> getTileEntityClass() {
		return TileEntityDisablingPress.class;
	}

	@Nullable
	@Override
	public TileEntityDisablingPress createTileEntity(World world, IBlockState state) {
		return new TileEntityDisablingPress();
	}

}