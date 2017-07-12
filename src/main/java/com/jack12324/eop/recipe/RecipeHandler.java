package com.jack12324.eop.recipe;

import java.util.ArrayList;
import java.util.Arrays;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IFluidInRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IMultipleInputRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneInputRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneOutput;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class RecipeHandler {
	/**
	 * 
	 * @param recipeList
	 *            The collection of Recipes on which to operate
	 * @param inputs
	 *            The inputs to test for a viable output
	 * @return If it exists, the output for the given inputs, null if not
	 */
	public static ItemStack getItemOutput(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs) {
		EOPRecipe recipe;
		if (inputs.length == 1)
			recipe = getRecipeFromInput(recipeList, inputs[0]);
		else
			recipe = getRecipeFromMultipleInputs(recipeList, inputs);
		return recipe == null ? null : ((IOneOutput) recipe).getOutputStack();
	}

	/**
	 * Gets recipe if there are multiple inputs for a single output
	 * 
	 * @param recipeList
	 *            The collection of Recipes on which to operate
	 * @param inputs
	 *            The inputs to test for a viable recipe
	 * @return If it exists, the recipe using given inputs, null if not
	 */
	public static EOPRecipe getRecipeFromMultipleInputs(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs) {
		for (EOPRecipe recipe : recipeList) {
			if (compareItemStacks(((IMultipleInputRecipe) recipe).getInputStacks(), inputs))
				return recipe;
		}
		return null;
	}

	/**
	 * Gets recipe if there is a single input and output
	 * 
	 * @param recipeList
	 *            The collection of Recipes on which to operate
	 * @param input
	 *            The input to test for a viable recipe
	 * @return If it exists, the recipe using given input, null if not
	 */
	public static EOPRecipe getRecipeFromInput(ArrayList<EOPRecipe> recipeList, ItemStack input) {
		for (EOPRecipe recipe : recipeList) {
			if (((IOneInputRecipe) recipe).getInputStack().equals(input))
				return recipe;
		}
		return null;
	}

	/**
	 * Gets all input fluids used in a recipe list
	 * 
	 * @param recipes
	 *            List of recipes to check
	 * @return List of Fluids which are used as inputs in the recipes
	 */
	public static ArrayList<Fluid> getInFluids(ArrayList<EOPRecipe> recipes) {
		ArrayList<Fluid> fluids = new ArrayList<Fluid>();
		for (EOPRecipe recipe : recipes) {
			Fluid fluid = ((IFluidInRecipe) recipe).getInFluidStack().getFluid();
			if (!fluids.contains(fluid))
				fluids.add(fluid);
		}
		return fluids;
	}

	/**
	 * compares two Itemstack arrays to determine if they are the same
	 * regardless of order
	 * 
	 * @param stack
	 * @param stack2
	 * @return true if stacks contain same elements false if not
	 */
	private static boolean compareItemStacks(ItemStack[] stack, ItemStack[] stack2) {
		if (stack.length != stack2.length)
			return false;
		Arrays.sort(stack);
		Arrays.sort(stack2);
		return Arrays.equals(stack, stack2);
	}

	/**
	 * Gets the fluid amount used from recipe ItemInputs and fluid input
	 * 
	 * @param recipeList
	 * @param inputs
	 * @return
	 */
	public static int getInFluidAmountUsed(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs, Fluid fluid) {
		EOPRecipe recipe = getRecipeFromMultipleInputsAndFluid(recipeList, inputs, fluid);
		return recipe == null ? null : ((IFluidInRecipe) recipe).getInFluidUseAmount();

	}

}
