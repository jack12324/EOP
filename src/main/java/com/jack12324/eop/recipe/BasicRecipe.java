package com.jack12324.eop.recipe;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneInputRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneOutput;

import net.minecraft.item.ItemStack;

public class BasicRecipe implements EOPRecipe, IOneInputRecipe, IOneOutput {

	public ItemStack inputStack;
	public ItemStack outputStack;

	public BasicRecipe(ItemStack inputStack, ItemStack outputStack) {
		this.inputStack = inputStack;
		this.outputStack = outputStack;
	}

	@Override
	public ItemStack getInputStack() {
		return inputStack;
	}

	@Override
	public ItemStack getOutputStack() {
		return outputStack;
	}

}