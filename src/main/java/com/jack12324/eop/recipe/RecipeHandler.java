package com.jack12324.eop.recipe;

import java.util.ArrayList;
import java.util.Arrays;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IBaseRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IFluidInRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IFluidOutRecipe;
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
	 * Get output from fluid and items, only works if fluidStack is more than
	 * useAmount
	 * 
	 * @param recipeList
	 *            Recipe list on which to operate
	 * @param inputs
	 *            Itemstack inputs
	 * @param fluid
	 *            FluidStack input
	 * @return ItemStack output if inputs and fluids have an output and fluid
	 *         stack is large enough, null if not
	 */
	public static ItemStack getItemOutput(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs, FluidStack fluid) {
		EOPRecipe recipe;
		recipe = getRecipeFromMultipleInputsAndFluid(recipeList, inputs, fluid);
		return recipe == null ? null : ((IOneOutput) recipe).getOutputStack();
	}

	/**
	 * getItemOutput variation with base ItemStack
	 * 
	 * @param recipeList
	 * @param inputSlotItemStacks
	 * @param baseSlotItemStacks
	 * @param fluid
	 * @return
	 */
	public static ItemStack getItemOutput(ArrayList<EOPRecipe> recipeList, ItemStack[] inputSlotItemStacks,
			ItemStack baseSlotItemStacks, FluidStack fluid) {
		EOPRecipe recipe;
		recipe = getRecipeFromMultipleInputsBaseAndFluid(recipeList, inputSlotItemStacks, baseSlotItemStacks, fluid);
		return recipe == null ? null : ((IOneOutput) recipe).getOutputStack();
	}

	/**
	 * GetItemOutput variation with input and base
	 * 
	 * @param recipeList
	 * @param input
	 * @param base
	 * @return
	 */
	public static ItemStack getItemOutput(ArrayList<EOPRecipe> recipeList, ItemStack[] input, ItemStack base) {
		EOPRecipe recipe;
		recipe = getRecipeFromMultipleInputsBase(recipeList, input, base);
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
		EOPRecipe recipe = getRecipeFromMultipleInputsAndFluidIgnoreAmount(recipeList, inputs, fluid);
		return recipe == null ? null : ((IFluidInRecipe) recipe).getInFluidUseAmount();

	}

	/**
	 * Get recipe from fluid and items, ignores amount
	 * 
	 * @param recipeList
	 *            Recipe list on which to operate
	 * @param inputs
	 *            Itemstack inputs
	 * @param fluid
	 *            Fluid input
	 * @return recipe if inputs and fluids have an output, null if not
	 */
	private static EOPRecipe getRecipeFromMultipleInputsAndFluidIgnoreAmount(ArrayList<EOPRecipe> recipeList,
			ItemStack[] inputs, Fluid fluid) {
		for (EOPRecipe recipe : recipeList) {
			if (compareItemStacks(((IMultipleInputRecipe) recipe).getInputStacks(), inputs)
					&& fluid.equals(((IFluidInRecipe) recipe).getInFluidStack().getFluid()))
				return recipe;
		}
		return null;
	}

	/**
	 * Get recipe from fluid and items, only works if fluidStack is more than
	 * useAmount
	 * 
	 * @param recipeList
	 *            Recipe list on which to operate
	 * @param inputs
	 *            Itemstack inputs
	 * @param fluid
	 *            FluidStack input
	 * @return recipe if inputs and fluids have an output and fluid stack is
	 *         large enough, null if not
	 */
	private static EOPRecipe getRecipeFromMultipleInputsAndFluid(ArrayList<EOPRecipe> recipeList, ItemStack[] inputs,
			FluidStack fluid) {
		for (EOPRecipe recipe : recipeList) {
			if (compareItemStacks(((IMultipleInputRecipe) recipe).getInputStacks(), inputs)
					&& fluid.isFluidEqual(((IFluidInRecipe) recipe).getInFluidStack())
					&& fluid.amount >= ((IFluidInRecipe) recipe).getInFluidUseAmount())
				return recipe;
		}
		return null;
	}

	/**
	 * Get recipe from fluid and items and base, only works if fluidStack is
	 * more than useAmount
	 * 
	 * @param recipeList
	 *            Recipe list on which to operate
	 * @param inputs
	 *            Itemstack inputs
	 * @Param base Itemstack base inputs
	 * @param fluid
	 *            FluidStack input
	 * @return recipe if inputs and fluids have an output and fluid stack is
	 *         large enough, null if not
	 */
	private static EOPRecipe getRecipeFromMultipleInputsBaseAndFluid(ArrayList<EOPRecipe> recipeList,
			ItemStack[] inputs, ItemStack base, FluidStack fluid) {
		for (EOPRecipe recipe : recipeList) {
			if (compareItemStacks(((IMultipleInputRecipe) recipe).getInputStacks(), inputs)
					&& base.isItemEqual(((IBaseRecipe) recipe).getBaseStack())
					&& fluid.isFluidEqual(((IFluidInRecipe) recipe).getInFluidStack())
					&& fluid.amount >= ((IFluidInRecipe) recipe).getInFluidUseAmount())
				return recipe;
		}
		return null;
	}

	/**
	 * getRecipe variation with base and input
	 * 
	 * @param recipeList
	 * @param input
	 * @param base
	 * @return
	 */
	private static EOPRecipe getRecipeFromMultipleInputsBase(ArrayList<EOPRecipe> recipeList, ItemStack[] input,
			ItemStack base) {
		for (EOPRecipe recipe : recipeList) {
			if (compareItemStacks(((IMultipleInputRecipe) recipe).getInputStacks(), input)
					&& base.isItemEqual(((IBaseRecipe) recipe).getBaseStack()))
				return recipe;
		}
		return null;
	}

	public static int getInFluidAmountUsed(ArrayList<EOPRecipe> recipeList, ItemStack[] input, ItemStack base,
			Fluid fluid) {
		EOPRecipe recipe = getRecipeFromMultipleInputsAndFluidIgnoreAmount(recipeList, input, base, fluid);
		return recipe == null ? null : ((IFluidInRecipe) recipe).getInFluidUseAmount();
	}

	/**
	 * getRecipe variation with base and input and fluid, ignores amount
	 * 
	 * @param recipeList
	 * @param input
	 * @param base
	 * @return
	 */
	private static EOPRecipe getRecipeFromMultipleInputsAndFluidIgnoreAmount(ArrayList<EOPRecipe> recipeList,
			ItemStack[] input, ItemStack base, Fluid fluid) {
		for (EOPRecipe recipe : recipeList) {
			if (compareItemStacks(((IMultipleInputRecipe) recipe).getInputStacks(), input)
					&& base.isItemEqual(((IBaseRecipe) recipe).getBaseStack())
					&& fluid.equals(((IFluidInRecipe) recipe).getInFluidStack().getFluid()))
				return recipe;
		}
		return null;
	}

	public static FluidStack getFluidOutput(ArrayList<EOPRecipe> recipeList, ItemStack[] input, ItemStack base,
			FluidStack fluid) {
		EOPRecipe recipe;
		recipe = getRecipeFromMultipleInputsAndFluidIgnoreAmount(recipeList, input, base, fluid.getFluid());
		return recipe == null ? null : ((IFluidOutRecipe) recipe).getOutFluidStack();
	}

	public static FluidStack getFluidOutput(ArrayList<EOPRecipe> recipeList, ItemStack[] input, FluidStack fluid) {
		EOPRecipe recipe;
		recipe = getRecipeFromMultipleInputsAndFluidIgnoreAmount(recipeList, input, fluid.getFluid());
		return recipe == null ? null : ((IFluidOutRecipe) recipe).getOutFluidStack();
	}

}
