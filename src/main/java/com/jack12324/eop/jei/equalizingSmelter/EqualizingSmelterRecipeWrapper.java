package com.jack12324.eop.jei.equalizingSmelter;

import com.jack12324.eop.jei.EOPRecipeWrapper;
import com.jack12324.eop.machine.equalizingSmelter.TileEntityEqualizingSmelter;
import com.jack12324.eop.recipe.recipes.EQSRecipe;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.HelpfulMethods;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

class EqualizingSmelterRecipeWrapper extends EOPRecipeWrapper {

    private final ItemStack[] inputs;
    private final ItemStack output;

    public EqualizingSmelterRecipeWrapper(EQSRecipe recipe) {
        this.inputs = recipe.getInputStacks();
        this.output = recipe.getOutputStack();

    }

    @Override
    protected GuiValues getGuiValues() {
        return GuiValues.EQUALIZINGSMELTER;
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        List<String> hoveringText = new ArrayList<>();
        super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);
        if (HelpfulMethods.isInRect(getGuiValues().getOther()[0], getGuiValues().getOther()[1], getGuiValues().getOther()[4], getGuiValues().getOther()[5], mouseX, mouseY)) {
            hoveringText.add("Produces extra firestone dust every:");
            hoveringText.add(TileEntityEqualizingSmelter.DUSTTICK + " operations");
        }
        if (hoveringText != null && !hoveringText.isEmpty())
            net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(hoveringText, mouseX, mouseY, recipeWidth, recipeHeight, -1, minecraft.fontRendererObj);


    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        List<ItemStack> list = new ArrayList<>(Arrays.asList(inputs));

        ingredients.setInputs(ItemStack.class, list);
        ingredients.setOutput(ItemStack.class, output);

    }

}
