package com.jack12324.eop.jei.disablingPress;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.jei.EOPRecipeCategory;
import com.jack12324.eop.recipe.BasicRecipe;
import com.jack12324.eop.recipe.DPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class disablingPressRecipeCategory extends EOPRecipeCategory<DPRecipe, disablingPressRecipeWrapper> {

	private final static ResourceLocation background = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/disabling_press.png");

	IDrawableAnimated progress;

	public disablingPressRecipeCategory(IGuiHelper helper) {
		super("disabling_press", "tile.disabling_press.name", helper.createDrawable(background, 3, 14, 153, 65),
				DPRecipe.class, new ItemStack(ModBlocks.disablingPress, 1));

		progress = helper.createAnimatedDrawable(helper.createDrawable(background, 3, 169, 86, 34), 200,
				StartDirection.LEFT, false);
	}

	@Override
	protected void drawProgress(Minecraft minecraft) {
		progress.draw(minecraft, 42, 10);
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(DPRecipe recipe) {
		return new disablingPressRecipeWrapper(recipe);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, disablingPressRecipeWrapper recipeWrapper,
			IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 23, 7);
		guiItemStacks.init(2, true, 77, 43);
		guiItemStacks.init(3, true, 13, 34);
		
		guiItemStacks.init(1, false, 131, 19);

		guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0).get(0));
		guiItemStacks.set(3, ingredients.getInputs(ItemStack.class).get(1).get(0));
		guiItemStacks.set(2, ingredients.getInputs(ItemStack.class).get(2));
		if (ingredients.getOutputs(ItemStack.class).size() > 0)
			guiItemStacks.set(1, ingredients.getOutputs(ItemStack.class).get(0));

	}

}
