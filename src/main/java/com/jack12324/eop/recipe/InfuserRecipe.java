package com.jack12324.eop.recipe;

import com.jack12324.eop.recipe.recipeInterfaces.IFluidInRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IFluidOutRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IMultipleInputRecipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class InfuserRecipe implements IMultipleInputRecipe, IFluidInRecipe, IFluidOutRecipe {
	public ItemStack[] inputStacks;
	public FluidStack inFluid;
	public FluidStack outFluid;

	public InfuserRecipe(ItemStack[] itemInput, FluidStack inFluid, FluidStack outFluid) {
		this.inputStacks = itemInput;
		this.inFluid = inFluid;
		this.outFluid = outFluid;

	}

	@Override
	public FluidStack getOutFluidStack() {
		return outFluid;
	}

	@Override
	public FluidStack getInFluidStack() {
		return inFluid;
	}

	@Override
	public ItemStack[] getInputStacks() {
		return inputStacks;
	}
}
