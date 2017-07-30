package com.jack12324.eop.jei.disablingPress;

import com.jack12324.eop.machine.disablingPress.TileEntityDisablingPress;
import com.jack12324.eop.recipe.recipes.DPRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

class disablingPressRecipeWrapper implements IRecipeWrapper {

    private final ItemStack input;
    private final ItemStack output;
    private final ItemStack base;

    public disablingPressRecipeWrapper(DPRecipe recipe) {
        this.input = recipe.getInputStack();
        this.output = recipe.getOutputStack();
        this.base = recipe.getBaseStack();

    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        List<ItemStack> list = new ArrayList<>();
        list.add(input);
        List<ItemStack> list2 = new ArrayList<>();
        list2.add(base);
        List<ItemStack> fuel = new ArrayList<>();
        for (Item item : TileEntityDisablingPress.fuel) {
            fuel.add(new ItemStack(item));
        }

        List<List<ItemStack>> finalList = new ArrayList<>();
        finalList.add(list);
        finalList.add(list2);
        finalList.add(fuel);

        ingredients.setInputLists(ItemStack.class, finalList);
        ingredients.setOutput(ItemStack.class, output);

    }

}
