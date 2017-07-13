package com.jack12324.eop.recipe;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IMultipleInputRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneOutput;

import net.minecraft.item.ItemStack;

public class EQSRecipe implements EOPRecipe, IMultipleInputRecipe, IOneOutput{

	ItemStack[] inputs;
	ItemStack output;
	
	EQSRecipe(ItemStack[] inputs, ItemStack output){
		this.inputs=inputs;
		this.output=output;
	}
	
	@Override
	public ItemStack getOutputStack() {
		return output;
	}

	@Override
	public ItemStack[] getInputStacks() {
		return inputs;
	}

}
