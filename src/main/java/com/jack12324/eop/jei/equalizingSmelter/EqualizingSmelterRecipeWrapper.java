package com.jack12324.eop.jei.equalizingSmelter;

import com.jack12324.eop.recipe.recipes.BasicRecipe;
import com.jack12324.eop.recipe.recipes.EQSRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

class EqualizingSmelterRecipeWrapper extends BlankRecipeWrapper {

    private final ItemStack[] inputs;
    private final ItemStack output;

    public EqualizingSmelterRecipeWrapper(EQSRecipe recipe) {
        this.inputs = recipe.getInputStacks();
        this.output = recipe.getOutputStack();

    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);

    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        List<ItemStack> list = new ArrayList<>(Arrays.asList(inputs));

        ingredients.setInputs(ItemStack.class, list);
        ingredients.setOutput(ItemStack.class, output);

    }

}
