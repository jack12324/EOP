package com.jack12324.eop.jei.activationChamber;

import java.util.Arrays;
import java.util.List;

import com.jack12324.eop.machine.activationChamber.recipeTest;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class activationChamberRecipeWrapper extends BlankRecipeWrapper {
	
	private final ItemStack input;
	private final ItemStack output;
	
	public activationChamberRecipeWrapper(recipeTest recipe){
		this.input = recipe.inputStack;
		this.output = recipe.outputStack;
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
