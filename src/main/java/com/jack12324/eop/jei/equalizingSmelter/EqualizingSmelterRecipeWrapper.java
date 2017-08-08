package com.jack12324.eop.jei.equalizingSmelter;

import com.jack12324.eop.recipe.recipes.EQSRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

class EqualizingSmelterRecipeWrapper implements IRecipeWrapper {

    private final ItemStack[] inputs;
    private final ItemStack output;
    private final ItemStack extraOutput;

    public EqualizingSmelterRecipeWrapper(EQSRecipe recipe) {
        this.inputs = recipe.getInputStacks();
        this.output = recipe.getOutputStack();
        this.extraOutput = recipe.getExtraOutput();

    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        List<ItemStack> list = new ArrayList<>(Arrays.asList(inputs));
        List<ItemStack> outList = new ArrayList<>();
        outList.add(output);
        outList.add(extraOutput);

        ingredients.setInputs(ItemStack.class, list);
        ingredients.setOutputs(ItemStack.class, outList);

    }

}
