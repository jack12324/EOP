package com.jack12324.eop.recipe.recipes;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneInputRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneOutput;
import net.minecraft.item.ItemStack;

public class BasicRecipe implements EOPRecipe, IOneInputRecipe, IOneOutput {

    private final ItemStack inputStack;
    private final ItemStack outputStack;

    public BasicRecipe(ItemStack inputStack, ItemStack outputStack) {
        this.inputStack = inputStack;
        this.outputStack = outputStack;
    }

    @Override
    public ItemStack getInputStack() {
        return inputStack;
    }

    @Override
    public ItemStack getOutputStack() {
        return outputStack;
    }

    @Override
    public int getType() {
        return 1;
    }

}