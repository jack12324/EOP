package com.jack12324.eop.jei.activationChamber;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.jei.EOPRecipeCategory;
import com.jack12324.eop.recipe.BasicRecipe;

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

public class activationChamberRecipeCategory extends EOPRecipeCategory<BasicRecipe, activationChamberRecipeWrapper> {

	private final static ResourceLocation background = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/activation_chamber.png");

	IDrawableAnimated progress;
	IDrawableAnimated power;
	IDrawableAnimated fuel;
	private static final int xOffset=3;
	private static final int yOffset=14;

	public activationChamberRecipeCategory(IGuiHelper helper) {
		super("activation_chamber", "tile.activation_chamber.name", helper.createDrawable(background, xOffset, yOffset, 153, 65),
				BasicRecipe.class, new ItemStack(ModBlocks.activationChamber, 1));

		progress = helper.createAnimatedDrawable(helper.createDrawable(background, 3, 169, 78, 15), 200,
				StartDirection.LEFT, false);
		power = helper.createAnimatedDrawable(helper.createDrawable(new ResourceLocation(ExtremeOreProcessing.modID,"textures/gui/fluid_bar.png"), 0, 109, 8, 45), 200,
				StartDirection.TOP, true);
		fuel = helper.createAnimatedDrawable(helper.createDrawable(background, 178, 2, 3, 20), 200,
				StartDirection.TOP, true);
	}

	@Override
	protected void drawProgress(Minecraft minecraft) {
		progress.draw(minecraft, 49-this.xOffset, 30-this.yOffset);
	}
	@Override
	protected void drawPowerBar(Minecraft minecraft){
		power.draw(minecraft,5,6);
	}
	@Override
	protected void drawOther(Minecraft minecraft){
		fuel.draw(minecraft,74-xOffset,47-yOffset);
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(BasicRecipe recipe) {
		return new activationChamberRecipeWrapper(recipe);
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, activationChamberRecipeWrapper recipeWrapper,
			IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
		guiItemStacks.init(0, true, 26-this.xOffset-1, 30-this.yOffset-1);
		guiItemStacks.init(1, false, 134-this.xOffset-1, 30-this.yOffset-1);
		guiItemStacks.init(2, true, 80-this.xOffset-1, 48-this.yOffset-1);

		guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0).get(0));
		guiItemStacks.set(2, ingredients.getInputs(ItemStack.class).get(1));
		if (ingredients.getOutputs(ItemStack.class).size() > 0)
			guiItemStacks.set(1, ingredients.getOutputs(ItemStack.class).get(0));

	}

}
