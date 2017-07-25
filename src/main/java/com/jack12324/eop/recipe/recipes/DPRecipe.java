package com.jack12324.eop.recipe.recipes;

import com.jack12324.eop.recipe.recipeInterfaces.IBaseRecipe;
import net.minecraft.item.ItemStack;

public class DPRecipe extends BasicRecipe implements IBaseRecipe {

    private final ItemStack base;

    public DPRecipe(ItemStack input, ItemStack base, ItemStack output) {
        super(input, output);
        this.base = base;
    }

    @Override
    public ItemStack getBaseStack() {
        return base;
    }

    @Override
    public int getType() {
        return 2;
    }

}
