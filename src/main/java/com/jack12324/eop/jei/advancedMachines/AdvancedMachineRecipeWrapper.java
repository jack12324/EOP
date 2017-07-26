package com.jack12324.eop.jei.advancedMachines;

import com.jack12324.eop.jei.EOPRecipeWrapper;
import com.jack12324.eop.recipe.recipes.AdvancedRecipe;
import com.jack12324.eop.util.GuiValues;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

class AdvancedMachineRecipeWrapper extends EOPRecipeWrapper {

    private final List<ItemStack> inputs = new ArrayList<>();
    private final FluidStack inFluid;
    private final ItemStack output;
    private final GuiValues guiValues;

    public AdvancedMachineRecipeWrapper(GuiValues guiValues, AdvancedRecipe recipe) {
        this.inputs.add(recipe.getInputStack());
        this.inFluid = recipe.getInFluidStack();
        this.output = recipe.getOutputStack();
        this.inputs.add(recipe.getBaseStack());
        this.guiValues = guiValues;

    }

    @Override
    protected GuiValues getGuiValues() {
        return this.guiValues;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, inputs);
        ingredients.setInput(FluidStack.class, inFluid);
        ingredients.setOutput(ItemStack.class, output);

    }

}
