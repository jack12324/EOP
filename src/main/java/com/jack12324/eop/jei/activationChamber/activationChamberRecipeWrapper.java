package com.jack12324.eop.jei.activationChamber;

import java.util.ArrayList;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class activationChamberRecipeWrapper extends BlankRecipeWrapper {
	
	public final ActivationChamberRecipe recipe;
	
	public activationChamberRecipeWrapper(ActivationChamberRecipe recipe){
		this.recipe=recipe;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInput(ItemStack.class, this.recipe.inputStack);

        
        ingredients.setOutput(ItemStack.class, this.recipe.outputStack);
		
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		 super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
		
	}

}
