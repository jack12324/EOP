package com.jack12324.eop.jei.activationChamber;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.jei.EOPRecipeCategory;
import com.jack12324.eop.machine.activationChamber.recipeTest;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class activationChamberRecipeCategory extends EOPRecipeCategory<recipeTest, activationChamberRecipeWrapper> {

	private final static ResourceLocation background = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/activation_chamber.png");

	IDrawableAnimated progress;

	public activationChamberRecipeCategory(IGuiHelper helper) {
		super("activation_chamber", "tile.activation_chamber.name", helper.createDrawable(background, 3, 3, 170, 80),
				recipeTest.class, new ItemStack(ModBlocks.activationChamber, 1));

		progress = helper.createAnimatedDrawable(helper.createDrawable(background, 3, 169, 78, 15), 200,
				StartDirection.LEFT, false);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, activationChamberRecipeWrapper recipeWrapper,
			IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 22, 26);
		guiItemStacks.init(1, false, 130, 26);

		guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
		if (ingredients.getOutputs(ItemStack.class).size() > 0)
			guiItemStacks.set(1, ingredients.getOutputs(ItemStack.class).get(0));

	}

	@Override
	public IRecipeWrapper getRecipeWrapper(recipeTest recipe) {
		return new activationChamberRecipeWrapper(recipe);
	}

	@Override
	protected void drawProgress(Minecraft minecraft) {
		progress.draw(minecraft, 46, 27);
	}

}
