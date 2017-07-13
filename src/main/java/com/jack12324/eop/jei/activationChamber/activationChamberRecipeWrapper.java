package com.jack12324.eop.jei.activationChamber;

import com.jack12324.eop.recipe.BasicRecipe;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class activationChamberRecipeWrapper extends BlankRecipeWrapper {

	private final ItemStack input;
	private final ItemStack output;

	public activationChamberRecipeWrapper(BasicRecipe recipe) {
		this.input = recipe.getInputStack();
		this.output = recipe.getOutputStack();

	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);

	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);

	}

}
