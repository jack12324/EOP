package com.jack12324.eop.machine.starHardener;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.BlockTE;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockStarHardener extends BlockTE<TileEntityStarHardener> {

	public BlockStarHardener() {
		super(Material.ROCK, "star_hardener");

	}

	// drop items in block as well as block
	@Override
	public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
		TileEntityStarHardener tile = getTileEntity(world, pos);
		if (tile instanceof IInventory) {
			InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
		}
		super.breakBlock(world, pos, state);
	}

	@Nullable
	@Override
	public TileEntityStarHardener createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
		return new TileEntityStarHardener();
	}

	@Override
	protected int getGui() {
		return ModGuiHandler.STARHARDENER;
	}

	@Override
	public Class<TileEntityStarHardener> getTileEntityClass() {
		return TileEntityStarHardener.class;
	}

}
