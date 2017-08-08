package com.jack12324.eop.recipe.recipes;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IMultipleInputRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneOutput;
import net.minecraft.item.ItemStack;

public class EQSRecipe implements EOPRecipe, IMultipleInputRecipe, IOneOutput {

    private final ItemStack[] inputs;
    private final ItemStack output;
    private final ItemStack extraOutput;

    public EQSRecipe(ItemStack[] inputs, ItemStack output, ItemStack extraOutput) {
        this.inputs = inputs;
        this.output = output;
        this.extraOutput = extraOutput;
    }

    @Override
    public ItemStack getOutputStack() {
        return output;
    }

    @Override
    public ItemStack[] getInputStacks() {
        return inputs;
    }

    public ItemStack getExtraOutput() {
        return extraOutput;
    }

    @Override
    public int getType() {
        return 3;
    }

}
