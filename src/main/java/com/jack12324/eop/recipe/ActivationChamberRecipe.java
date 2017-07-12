package com.jack12324.eop.recipe;

import net.minecraft.item.ItemStack;

public class ActivationChamberRecipe implements EOPRecipe, IOneInputRecipe, IOneOutput {

	public ItemStack inputStack;
	public ItemStack outputStack;

	public ActivationChamberRecipe(ItemStack inputStack, ItemStack outputStack) {
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