package com.jack12324.eop.recipe.recipes;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IBaseRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IFluidInRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IMultipleInputRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneOutput;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class AdvancedRecipe implements EOPRecipe, IFluidInRecipe, IBaseRecipe, IMultipleInputRecipe, IOneOutput {

	private final ItemStack[] inputStack;
	private final ItemStack baseStack;
	private final ItemStack outputStack;
	private final FluidStack inFluid;

	public AdvancedRecipe(ItemStack[] itemInput, ItemStack base, FluidStack inFluid, ItemStack itemOutput) {
		this.inputStack = itemInput;
		this.inFluid = inFluid;
		this.baseStack = base;
		this.outputStack = itemOutput;

	}

	@Override
	public ItemStack getBaseStack() {
		return baseStack;
	}

	@Override
	public FluidStack getInFluidStack() {
		return inFluid;
	}

	@Override
	public int getInFluidUseAmount() {
		return inFluid.amount;
	}

	@Override
	public ItemStack[] getInputStacks() {
		return inputStack;
	}

	@Override
	public ItemStack getOutputStack() {
		return outputStack;
	}

}
