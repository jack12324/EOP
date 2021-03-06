package com.jack12324.eop.jei.advancedMachines;

import com.jack12324.eop.recipe.recipes.AdvancedRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

class AdvancedMachineRecipeWrapper implements IRecipeWrapper {

    private final List<ItemStack> inputs = new ArrayList<>();
    private final FluidStack inFluid;
    private final ItemStack output;

    public AdvancedMachineRecipeWrapper(AdvancedRecipe recipe) {
        this.inputs.add(recipe.getInputStack());
        this.inFluid = recipe.getInFluidStack();
        this.output = recipe.getOutputStack();
        this.inputs.add(recipe.getBaseStack());

    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInputs(ItemStack.class, inputs);
        ingredients.setInput(FluidStack.class, inFluid);
        ingredients.setOutput(ItemStack.class, output);

    }

}
