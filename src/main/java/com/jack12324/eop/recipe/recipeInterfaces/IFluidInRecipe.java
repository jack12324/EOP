package com.jack12324.eop.recipe.recipeInterfaces;

import java.util.ArrayList;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public interface IFluidInRecipe {
	public FluidStack getInFluidStack();

	public int getInFluidUseAmount();
}
