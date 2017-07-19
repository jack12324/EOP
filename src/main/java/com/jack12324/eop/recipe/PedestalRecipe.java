package com.jack12324.eop.recipe;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IFluidOutRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneInputRecipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class PedestalRecipe implements EOPRecipe, IFluidOutRecipe, IOneInputRecipe {

	private final ItemStack input;
	private final FluidStack outFluid;
	private final int speed;

	public PedestalRecipe(ItemStack input, FluidStack outFluid, int speed) {
		this.input = input;
		this.outFluid = outFluid;
		this.speed = speed;
	}

	@Override
	public ItemStack getInputStack() {
		return input;
	}

	@Override
	public FluidStack getOutFluidStack() {
		return outFluid;
	}

	public int getPedestalSpeed() {
		return speed;
	}

}
