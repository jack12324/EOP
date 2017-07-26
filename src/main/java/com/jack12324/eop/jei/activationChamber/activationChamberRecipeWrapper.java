package com.jack12324.eop.jei.activationChamber;

import com.jack12324.eop.jei.EOPRecipeWrapper;
import com.jack12324.eop.machine.activationChamber.TileEntityActivationChamber;
import com.jack12324.eop.recipe.recipes.BasicRecipe;
import com.jack12324.eop.util.GuiValues;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

class activationChamberRecipeWrapper extends EOPRecipeWrapper {

    private final ItemStack input;
    private final ItemStack output;

    public activationChamberRecipeWrapper(BasicRecipe recipe) {
        this.input = recipe.getInputStack();
        this.output = recipe.getOutputStack();

    }

    @Override
    protected GuiValues getGuiValues() {
        return GuiValues.ACTIVATIONCHAMBER;
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        List<ItemStack> list = new ArrayList<>();
        list.add(input);
        List<ItemStack> fuel = new ArrayList<>();
        for (Item item : TileEntityActivationChamber.fuel) {
            fuel.add(new ItemStack(item));
        }

        List<List<ItemStack>> finalList = new ArrayList<>();
        finalList.add(list);
        finalList.add(fuel);

        ingredients.setInputLists(ItemStack.class, finalList);
        ingredients.setOutput(ItemStack.class, output);

    }

}
