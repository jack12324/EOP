package com.jack12324.eop.jei.advancedMachines;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.recipe.recipes.AdvancedRecipe;
import com.jack12324.eop.util.GuiValues;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
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

public class ParticleExciterRecipeCategory extends AdvancedMachineRecipeCategory {
    private final static ResourceLocation background = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/particle_exciter.png");

    private final IDrawableAnimated progress;

    private static final int xOffset = 3;
    private static final int yOffset = 15;

    public ParticleExciterRecipeCategory(IGuiHelper helper) {
        super(helper, background, "particle_exciter", "tile.particle_exciter.name", ModBlocks.particleExciter, xOffset, yOffset);

        progress = helper.createAnimatedDrawable(helper.createDrawable(background, 2, 169, 89, 49), 200,
                IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull AdvancedRecipe recipe) {
        return new AdvancedMachineRecipeWrapper(GuiValues.PARTICLEEXCITER, recipe);
    }

    @Override
    protected void drawProgress(Minecraft minecraft) {
        progress.draw(minecraft, 43 - xOffset, 18 - yOffset);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull AdvancedMachineRecipeWrapper recipeWrapper,
                          @Nonnull IIngredients ingredients) {
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(0, true, 80 - xOffset - 1, 21 - yOffset - 1);
        guiItemStacks.init(1, true, 80 - xOffset - 1, 48 - yOffset - 1);
        guiItemStacks.init(2, false, 134 - xOffset - 1, 34 - yOffset - 1);

        guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
        guiItemStacks.set(1, ingredients.getInputs(ItemStack.class).get(1));

        guiFluidStacks.init(0, true, 25 - xOffset, 18 - yOffset, 18, 49, 400, false, tankOverlay);
        guiFluidStacks.set(0, ingredients.getInputs(FluidStack.class).get(0));

        if (ingredients.getOutputs(ItemStack.class).size() > 0) {
            guiItemStacks.set(2, ingredients.getOutputs(ItemStack.class).get(0));

        }
    }
}
