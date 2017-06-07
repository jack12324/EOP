package com.jack12324.eop.machine.dualCatalystInfuser;

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

public class BlockDualCatalystInfuser extends BlockTE<TileEntityDualCatalystInfuser> {

	public BlockDualCatalystInfuser() {
		super(Material.ROCK, "dual_catalyst_infuser");

	}


	// drop items in block as well as block
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityDualCatalystInfuser tile = getTileEntity(world, pos);
		if (tile instanceof IInventory) {
			InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
		}
		super.breakBlock(world, pos, state);
	}

	@Override
	public Class<TileEntityDualCatalystInfuser> getTileEntityClass() {
		return TileEntityDualCatalystInfuser.class;
	}

	@Nullable
	@Override
	public TileEntityDualCatalystInfuser createTileEntity(World world, IBlockState state) {
		return new TileEntityDualCatalystInfuser();
	}

}
