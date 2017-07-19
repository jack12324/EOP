package com.jack12324.eop.jei.Pedestal;


import com.jack12324.eop.recipe.recipes.PedestalRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

class PedestalRecipeWrapper extends BlankRecipeWrapper {

	private final ItemStack input;
	private final FluidStack outFluid;

	PedestalRecipeWrapper(PedestalRecipe recipe) {
		this.input = recipe.getInputStack();
		this.outFluid = recipe.getOutFluidStack();

	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);

	}

	@Override
	public void getIngredients(@Nonnull IIngredients ingredients) {
		ingredients.setInput(ItemStack.class, input);
		ingredients.setOutput(FluidStack.class, outFluid);

	}

}
