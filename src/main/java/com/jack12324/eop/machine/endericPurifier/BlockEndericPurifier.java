package com.jack12324.eop.machine.endericPurifier;

import javax.annotation.Nullable;

import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.BlockTE;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockEndericPurifier extends BlockTE<TileEntityEndericPurifier> {

	public BlockEndericPurifier() {
		super(Material.ROCK, "enderic_purifier");

	}

	@Override
	protected int getGui() {
		return ModGuiHandler.ENDERICPURIFIER;
	}

	// drop items in block as well as block
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityEndericPurifier tile = getTileEntity(world, pos);
		if (tile instanceof IInventory) {
			InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public Class<TileEntityEndericPurifier> getTileEntityClass() {
		return TileEntityEndericPurifier.class;
	}

	@Nullable
	@Override
	public TileEntityEndericPurifier createTileEntity(World world, IBlockState state) {
		return new TileEntityEndericPurifier();
	}

}
