package com.jack12324.eop.fluids;

import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public final class InitFluids {

	public static Fluid fluidScreamingLava;
	public static Fluid fluidStarWater;
	public static Fluid fluidLiquidEnd;
	public static Fluid fluidDragonSoul;

	public static Block blockScreamingLava;
	public static Block blockStarWater;
	public static Block blockLiquidEnd;
	public static Block blockDragonSoul;

	public static void init() {
		fluidScreamingLava = registerFluid("screaminglava", "block_screaming_lava").setLuminosity(10)
				.setViscosity(1000);
		fluidStarWater = registerFluid("starwater", "block_star_water").setViscosity(200).setLuminosity(15);
		fluidLiquidEnd = registerFluid("liquidend", "block_liquid_end").setViscosity(4000);
		fluidDragonSoul = registerFluid("dragonsoul", "block_dragon_soul").setGaseous(true).setDensity(-1)
				.setViscosity(500);

		blockScreamingLava = registerFluidBlock(fluidScreamingLava, Material.LAVA, "block_screaming_lava");
		blockStarWater = registerFluidBlock(fluidStarWater, Material.WATER, "block_star_water");
		blockLiquidEnd = registerFluidBlock(fluidLiquidEnd, Material.WATER, "block_liquid_end");
		blockDragonSoul = registerFluidBlock(fluidDragonSoul, Material.WATER, "block_dragon_soul");
	}

	private static Fluid registerFluid(String fluidName, String fluidTextureName) {
		Fluid fluid = new ModFluid(fluidName.toLowerCase(Locale.ROOT), fluidTextureName);
		FluidRegistry.registerFluid(fluid);
		FluidRegistry.addBucketForFluid(fluid);

		return FluidRegistry.getFluid(fluid.getName());
	}

	private static Block registerFluidBlock(Fluid fluid, Material material, String name) {
		return new BlockFluidFlowing(fluid, material, name);
	}
}