package com.jack12324.eop.jei.Infusers;

import com.jack12324.eop.jei.EOPRecipeWrapper;
import com.jack12324.eop.recipe.recipes.InfuserRecipe;
import com.jack12324.eop.util.GuiValues;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InfuserRecipeWrapper extends EOPRecipeWrapper {

    private final List<ItemStack> inputs;
    private final FluidStack inFluid;
    private final FluidStack outFluid;
    private final GuiValues guiValues;

    public InfuserRecipeWrapper(GuiValues guiValues, InfuserRecipe recipe) {
        this.inputs = new ArrayList<>(Arrays.asList(recipe.getInputStacks()));
        this.inFluid = recipe.getInFluidStack();
        this.outFluid = recipe.getOutFluidStack();
        this.guiValues = guiValues;

    }

    @Override
    protected GuiValues getGuiValues() {
        return this.guiValues;
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);

    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, inputs);
        ingredients.setInput(FluidStack.class, inFluid);
        ingredients.setOutput(FluidStack.class, outFluid);

    }

}
