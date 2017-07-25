package com.jack12324.eop.recipe.recipes;

import com.jack12324.eop.recipe.recipeInterfaces.*;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class AdvancedRecipe implements EOPRecipe, IFluidInRecipe, IBaseRecipe, IOneInputRecipe, IOneOutput {

    private final ItemStack inputStack;
    private final ItemStack baseStack;
    private final ItemStack outputStack;
    private final FluidStack inFluid;

    public AdvancedRecipe(ItemStack itemInput, ItemStack base, FluidStack inFluid, ItemStack itemOutput) {
        this.inputStack = itemInput;
        this.inFluid = inFluid;
        this.baseStack = base;
        this.outputStack = itemOutput;

    }

    @Override
    public ItemStack getBaseStack() {
        return baseStack;
    }

    @Override
    public FluidStack getInFluidStack() {
        return inFluid;
    }

    @Override
    public int getInFluidUseAmount() {
        return inFluid.amount;
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
        return 0;
    }

}
