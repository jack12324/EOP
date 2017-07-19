package com.jack12324.eop.jei.Infusers;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IGuiFluidStackGroup;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class TriCatalystInfuserRecipeCategory extends InfuserRecipeCategory {
    private final static ResourceLocation background = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/tri_catalyst_infuser.png");

    private IDrawableAnimated progress;
    private static final int xOffset = 3;
    private static final int yOffset = 15;

    public TriCatalystInfuserRecipeCategory(IGuiHelper helper) {
        super(helper,background,"tri_catalyst_infuser","tile.tri_catalyst_infuser.name", ModBlocks.triCatalystInfuser,xOffset,yOffset  );

        progress = helper.createAnimatedDrawable(helper.createDrawable(background, 2, 169, 88, 49), 200,
                IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    protected void drawProgress(Minecraft minecraft) {
        progress.draw(minecraft, 43 - xOffset, 18 - yOffset);
    }

    @Override
    protected void drawOther(Minecraft minecraft) {
        super.drawOther(minecraft);
        tankBack.draw(minecraft,151-xOffset, 18-yOffset);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull InfuserRecipeWrapper recipeWrapper,
                          @Nonnull IIngredients ingredients) {
        IGuiFluidStackGroup guiFluidStacks = recipeLayout.getFluidStacks();
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        guiItemStacks.init(0, true, 44 - xOffset - 1, 16 - yOffset - 1);
        guiItemStacks.init(1, true, 44 - xOffset - 1, 34 - yOffset - 1);
        guiItemStacks.init(2, true, 44 - xOffset - 1, 52 - yOffset - 1);

        guiItemStacks.set(0, ingredients.getInputs(ItemStack.class).get(0));
        guiItemStacks.set(1, ingredients.getInputs(ItemStack.class).get(1));
        guiItemStacks.set(2, ingredients.getInputs(ItemStack.class).get(2));

        guiFluidStacks.init(0,true,25-xOffset, 18-yOffset,18,49,500,false, super.tankOverlay);
        guiFluidStacks.init(1,false,151-xOffset,18-yOffset	,18,49,500,false,super.tankOverlay);

        guiFluidStacks.set(0, ingredients.getInputs(FluidStack.class).get(0));
        if (ingredients.getOutputs(FluidStack.class).size() > 0){
            guiFluidStacks.set(1, ingredients.getOutputs(FluidStack.class).get(0));}

    }
}
