package com.jack12324.eop.recipe;

import java.util.ArrayList;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IMultipleInputRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneInputRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneOutput;

import net.minecraft.item.ItemStack;

public class recipeHandler {
	public static ItemStack getItemOutput(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs) {
		EOPRecipe recipe;
		if (inputs.length == 1)
			recipe = getRecipeFromInput(recipeList, inputs[0]);
		else
			recipe = getRecipeFromMultipleInputs(recipeList, inputs);
		return recipe == null ? null : ((IOneOutput) recipe).getOutputStack();
	}

	public static EOPRecipe getRecipeFromMultipleInputs(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs) {
		for (EOPRecipe recipe : recipeList) {
			if (compareItemStacks(((IMultipleInputRecipe) recipe).getInputStacks(), inputs))
				return recipe;
		}
		return null;
	}

	public static EOPRecipe getRecipeFromInput(ArrayList<EOPRecipe> recipeList, ItemStack input) {
		for (EOPRecipe recipe : recipeList) {
			if (((IOneInputRecipe) recipe).getInputStack().equals(input))
				return recipe;
		}
		return null;
	}

	/**
	 * Compares two itemstack arrays
	 */
	private static boolean compareItemStacks(ItemStack[] stack, ItemStack[] stack2) {
		boolean size = stack.length == stack2.length;
		boolean equal = true;
		if (stack.equals(stack2))
			return true;
		else if (size) {
			for (int i = 0; i < stack.length; i++) {
				if (equal) {
					equal = false;
					for (int j = 0; j < stack.length; j++) {
						if (stack2[j].getItem() == stack[i].getItem() && (stack2[j].getMetadata() == 32767
								|| stack2[j].getMetadata() == stack[i].getMetadata()))
							equal = true;

					}
				}
			}
		}
		return (size && equal);
	}

}
