package com.jack12324.eop.jei.catalystInfuser;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.jei.EOPRecipeCategory;
import com.jack12324.eop.recipe.InfuserRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class CatalystInfuserRecipeCategory extends EOPRecipeCategory<InfuserRecipe, CatalystInfuserRecipeWrapper> {

	private final static ResourceLocation background = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/catalyst_infuser.png");
	private final static ResourceLocation fluidBackground = new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/fluid_bar.png");

	private final IDrawableAnimated progress;
	private final IDrawableAnimated power;
	private final IDrawableStatic tankOverlay;
	private static final int xOffset = 3;
	private static final int yOffset = 15;

	public CatalystInfuserRecipeCategory(IGuiHelper helper) {
		super("catalyst_infuser", "tile.catalyst_infuser.name",
				helper.createDrawable(background, xOffset, yOffset, 153, 65), InfuserRecipe.class,
				new ItemStack(ModBlocks.catalystInfuser, 1));

		progress = helper.createAnimatedDrawable(helper.createDrawable(background, 6, 172, 68, 12), 200,
				StartDirection.LEFT, false);
		power = helper.createAnimatedDrawable(
				helper.createDrawable(fluidBackground, 0,109, 8, 45),200, StartDirection.TOP, true);

		tankOverlay =helper.createDrawable(fluidBackground,0,49,18,49);
	}

	@Override
	protected void drawProgress(Minecraft minecraft) {
		progress.draw(minecraft, 49 - xOffset, 30 - yOffset);
	}

	@Override
	protected void drawPowerBar(Minecraft minecraft) {
		power.draw(minecraft, 5, 6);
	}

	@Override
	protected void drawOther(Minecraft minecraft) {
		tankOverlay.draw(minecraft,25-xOffset, 18-yOffset);
		tankOverlay.draw(minecraft,133-xOffset, 18-yOffset);
	}

	@Nonnull
    @Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull InfuserRecipe recipe) {
		return new CatalystInfuserRecipeWrapper(recipe);
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull CatalystInfuserRecipeWrapper recipeWrapper,
						  @Nonnull IIngredients ingredients) {
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(0, true, 26 - xOffset - 1, 30 - yOffset - 1);
		guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));

		guiFluidStacks.init(0,true,4-xOffset-1,4-yOffset-1	);
		guiFluidStacks.init(1,false,4-xOffset-1,4-yOffset-1	);
		guiFluidStacks.set(0, ingredients.getInputs(FluidStack.class).get(0));

		if (ingredients.getOutputs(Fluid.class).size() > 0)
			guiFluidStacks.set(0, ingredients.getOutputs(FluidStack.class).get(1));

	}

}
