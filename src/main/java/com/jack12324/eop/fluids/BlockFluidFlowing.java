package com.jack12324.eop.fluids;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModItemBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

class BlockFluidFlowing extends BlockFluidClassic {

	private static void registerBlock(Block block, ModItemBlock itemBlock, String name) {
		block.setUnlocalizedName(ExtremeOreProcessing.modID + "." + name);

		block.setRegistryName(ExtremeOreProcessing.modID, name);
		GameRegistry.register(block);

		itemBlock.setRegistryName(block.getRegistryName());
		GameRegistry.register(itemBlock);

		block.setCreativeTab(ExtremeOreProcessing.creativeTab);
	}

	private final String name;

	public BlockFluidFlowing(Fluid fluid, Material material, String unlocalizedName) {
		super(fluid, material);
		this.name = unlocalizedName;
		this.displacements.put(this, false);

		this.register();
	}

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		return !world.getBlockState(pos).getMaterial().isLiquid() && super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		return !world.getBlockState(pos).getMaterial().isLiquid() && super.displaceIfPossible(world, pos);
	}

	private String getBaseName() {
		return this.name;
	}

	private ModItemBlock getItemBlock() {
		return new ModItemBlock(this);
	}

	private void register() {
		registerBlock(this, this.getItemBlock(), this.getBaseName());
	}

	private boolean shouldAddCreative() {
		return false;
	}

}
