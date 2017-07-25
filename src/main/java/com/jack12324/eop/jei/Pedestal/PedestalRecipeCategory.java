package com.jack12324.eop.jei.Pedestal;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.jei.EOPRecipeCategory;
import com.jack12324.eop.recipe.recipes.PedestalRecipe;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class PedestalRecipeCategory extends EOPRecipeCategory<PedestalRecipe, PedestalRecipeWrapper> {


    private final static ResourceLocation fluidBackground = new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/fluid_bar.png");
    private final static ResourceLocation background = new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/pedestal.png");

    private final IDrawableStatic tankOverlay;
    private final IDrawableStatic tankBack;
    private final static int xOffset = 3;
    private final static int yOffset = 15;

    public PedestalRecipeCategory(IGuiHelper helper) {
        super("pedestal", "tile.pedestal.name",
                helper.createDrawable(background, xOffset, yOffset, 153, 65), PedestalRecipe.class,
                new ItemStack(ModBlocks.pedestal, 1));

        tankOverlay = helper.createDrawable(fluidBackground, 0, 0, 18, 49);
        tankBack = helper.createDrawable(fluidBackground, 0, 49, 18, 49);
    }


    @Override
    protected void drawOther(Minecraft minecraft) {
        tankBack.draw(minecraft, 79 - xOffset, 21 - yOffset);
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull PedestalRecipe recipe) {
        return new PedestalRecipeWrapper(recipe);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull PedestalRecipeWrapper recipeWrapper,
                          @Nonnull IIngredients ingredients) {
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(0, true, 43 - xOffset, 46 - yOffset);
        guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));

        guiFluidStacks.init(0, false, 79 - xOffset, 21 - yOffset, 18, 49, 8, false, tankOverlay);

        if (ingredients.getOutputs(FluidStack.class).size() > 0) {
            guiFluidStacks.set(0, ingredients.getOutputs(FluidStack.class).get(0));
        }

    }
}
