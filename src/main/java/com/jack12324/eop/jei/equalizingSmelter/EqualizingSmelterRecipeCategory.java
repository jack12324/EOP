package com.jack12324.eop.jei.equalizingSmelter;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.jei.EOPRecipeCategory;
import com.jack12324.eop.recipe.recipes.EQSRecipe;
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

import javax.annotation.Nonnull;

public class EqualizingSmelterRecipeCategory extends EOPRecipeCategory<EQSRecipe, EqualizingSmelterRecipeWrapper> {

    private final static ResourceLocation background = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/equalizing_smelter.png");

    private final IDrawableAnimated[] progress = new IDrawableAnimated[4];
    private final StartDirection[] dir = {StartDirection.LEFT, StartDirection.RIGHT, StartDirection.LEFT, StartDirection.RIGHT};
    private final IDrawableAnimated power;
    private final IDrawableAnimated dust;
    private static final int xOffset = 4;
    private static final int yOffset = 3;

    private final int[] COOK_BAR_XPOS = {44, 107, 44, 107};
    private final int[] COOK_BAR_YPOS = {34, 34, 58, 58};
    private final int[] COOK_BAR_ICON_V = {88, 33, 52, 70};

    public EqualizingSmelterRecipeCategory(IGuiHelper helper) {
        super("equalizing_smelter", "tile.equalizing_smelter.name",
                helper.createDrawable(background, xOffset, yOffset, 151, 80), EQSRecipe.class,
                new ItemStack(ModBlocks.equalizingSmelter, 1));

        for (int i = 0; i < progress.length; i++) {
            progress[i] = helper.createAnimatedDrawable(helper.createDrawable(background, 180, COOK_BAR_ICON_V[i], 25, 16), 200,
                    dir[i], false);
        }

        power = helper.createAnimatedDrawable(
                helper.createDrawable(new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/fluid_bar.png"), 0,
                        109, 8, 45),
                200, StartDirection.TOP, true);
        dust = helper.createAnimatedDrawable(helper.createDrawable(background, 180, 20, 12, 11), 800, StartDirection.BOTTOM,
                false);
    }

    @Override
    protected void drawProgress(Minecraft minecraft) {
        for (int i = 0; i < progress.length; i++)
            progress[i].draw(minecraft, COOK_BAR_XPOS[i] - xOffset, COOK_BAR_YPOS[i] - yOffset);
    }

    @Override
    protected void drawPowerBar(Minecraft minecraft) {
        power.draw(minecraft, 8 - xOffset, 20 - yOffset);
    }

    @Override
    protected void drawOther(Minecraft minecraft) {
        dust.draw(minecraft, 82 - xOffset, 24 - yOffset);
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull EQSRecipe recipe) {
        return new EqualizingSmelterRecipeWrapper(recipe);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull EqualizingSmelterRecipeWrapper recipeWrapper,
                          @Nonnull IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        guiItemStacks.init(0, true, 26 - xOffset - 1, 28 - yOffset - 1);
        guiItemStacks.init(1, true, 134 - xOffset - 1, 28 - yOffset - 1);
        guiItemStacks.init(2, true, 26 - xOffset - 1, 64 - yOffset - 1);
        guiItemStacks.init(3, true, 134 - xOffset - 1, 64 - yOffset - 1);


        guiItemStacks.init(4, false, 71 - xOffset - 1, 37 - yOffset - 1);
        guiItemStacks.init(5, false, 80 - xOffset - 1, 6 - yOffset - 1);

        guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
        guiItemStacks.set(1, ingredients.getInputs(ItemStack.class).get(1));
        guiItemStacks.set(2, ingredients.getInputs(ItemStack.class).get(2));
        guiItemStacks.set(3, ingredients.getInputs(ItemStack.class).get(3));


        if (ingredients.getOutputs(ItemStack.class).size() > 0) {
            guiItemStacks.set(4, ingredients.getOutputs(ItemStack.class).get(0));
            guiItemStacks.set(5, new ItemStack(ModItems.dustFirestone));
        }

    }

}
