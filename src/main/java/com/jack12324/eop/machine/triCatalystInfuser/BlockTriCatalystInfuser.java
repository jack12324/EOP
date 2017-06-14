package com.jack12324.eop.machine.triCatalystInfuser;

import javax.annotation.Nullable;

import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.BlockTE;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTriCatalystInfuser extends BlockTE<TileEntityTriCatalystInfuser> {

	public BlockTriCatalystInfuser() {
		super(Material.ROCK, "tri_catalyst_infuser");

	}

	@Override
	protected int getGui() {
		return ModGuiHandler.TRICATALYSTINFUSER;
	}

	// drop items in block as well as block
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityTriCatalystInfuser tile = getTileEntity(world, pos);
		if (tile instanceof IInventory) {
			InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public Class<TileEntityTriCatalystInfuser> getTileEntityClass() {
		return TileEntityTriCatalystInfuser.class;
	}

	@Nullable
	@Override
	public TileEntityTriCatalystInfuser createTileEntity(World world, IBlockState state) {
		return new TileEntityTriCatalystInfuser();
	}

}
