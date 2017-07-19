package com.jack12324.eop.jei.Infusers;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class CatalystInfuserRecipeCategory extends InfuserRecipeCategory {

	private final static ResourceLocation background = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/catalyst_infuser.png");

	private final IDrawableAnimated progress;

	private static final int xOffset = 3;
	private static final int yOffset = 15;

	public CatalystInfuserRecipeCategory(IGuiHelper helper) {
		super(helper, background,"catalyst_infuser","tile.catalyst_infuser.name",ModBlocks.catalystInfuser,xOffset,yOffset);

		progress = helper.createAnimatedDrawable(helper.createDrawable(background, 6, 172, 68, 12), 200,
				StartDirection.LEFT, false);
	}

	@Override
	protected void drawProgress(Minecraft minecraft) {
		progress.draw(minecraft, 63 - xOffset, 35 - yOffset);
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull InfuserRecipeWrapper recipeWrapper,
						  @Nonnull IIngredients ingredients) {
		IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(0, true, 44 - xOffset - 1, 34 - yOffset - 1);
		guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));

		guiFluidStacks.init(0,true,25-xOffset, 18-yOffset,18,49,500,false, tankOverlay);
		guiFluidStacks.init(1,false,133-xOffset,18-yOffset	,18,49,500,false,tankOverlay);
		guiFluidStacks.set(0, ingredients.getInputs(FluidStack.class).get(0));

		if (ingredients.getOutputs(FluidStack.class).size() > 0){
			guiFluidStacks.set(1, ingredients.getOutputs(FluidStack.class).get(0));
		}

	}



}
