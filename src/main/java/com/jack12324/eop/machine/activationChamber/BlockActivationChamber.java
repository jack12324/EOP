package com.jack12324.eop.machine.activationChamber;

import javax.annotation.Nullable;

import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.BlockTE;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockActivationChamber extends BlockTE<TileEntityActivationChamber> {

	public BlockActivationChamber() {
		super(Material.ROCK, "activation_chamber");
	}

	@Override
	protected int getGui() {
		return ModGuiHandler.ACTIVATIONCHAMBER;
	}

	// drop items in block as well as block
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityActivationChamber tile = getTileEntity(world, pos);
		if (tile instanceof IInventory) {
			InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
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