package com.jack12324.eop.jei.Infusers;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.jei.EOPRecipeCategory;
import com.jack12324.eop.recipe.recipes.InfuserRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class InfuserRecipeCategory extends EOPRecipeCategory<InfuserRecipe, InfuserRecipeWrapper> {


	private final static ResourceLocation fluidBackground = new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/fluid_bar.png");


	private final IDrawableAnimated power;
	final IDrawableStatic tankOverlay;
	final IDrawableStatic tankBack;
	private int xOffset;
	private int yOffset;

	InfuserRecipeCategory(IGuiHelper helper, ResourceLocation background, String uniqueName, String localKey, Block block, int xOffset, int yOffset) {
		super(uniqueName, localKey,
				helper.createDrawable(background, xOffset, yOffset, 153, 65), InfuserRecipe.class,
				new ItemStack(block, 1));
		this.xOffset=xOffset;
		this.yOffset=yOffset;

		power = helper.createAnimatedDrawable(
				helper.createDrawable(fluidBackground, 0,109, 8, 45),200, StartDirection.TOP, true);

		tankOverlay =helper.createDrawable(fluidBackground,0,0,18,49);
		tankBack =helper.createDrawable(fluidBackground,0,49,18,49);
	}

	@Override
	protected void drawProgress(Minecraft minecraft) {}


	@Override
	protected void drawPowerBar(Minecraft minecraft) {
		power.draw(minecraft, 5, 6);
	}

	@Override
	protected void drawOther(Minecraft minecraft) {
		tankBack.draw(minecraft,25-xOffset, 18-yOffset);
	}

	@Nonnull
    @Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull InfuserRecipe recipe) {
		return new InfuserRecipeWrapper(recipe);
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull InfuserRecipeWrapper recipeWrapper,
						  @Nonnull IIngredients ingredients) {}

}
