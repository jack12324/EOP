package com.jack12324.eop.jei.Pedestal;


import com.jack12324.eop.recipe.recipes.PedestalRecipe;
import com.jack12324.eop.util.HelpfulMethods;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

class PedestalRecipeWrapper extends BlankRecipeWrapper {

    private final ItemStack input;
    private final FluidStack outFluid;
    private final int speed;

    PedestalRecipeWrapper(PedestalRecipe recipe) {
        this.input = recipe.getInputStack();
        this.outFluid = recipe.getOutFluidStack();
        this.speed = recipe.getPedestalSpeed();

    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        List<String> hoveringText = new ArrayList<>();
        if (HelpfulMethods.isInRect(79, 21, 18, 49, mouseX, mouseY)) {
            hoveringText.add("Filling every:");
            hoveringText.add(speed + " tick(s)");
        }
        if (!hoveringText.isEmpty())
            net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(hoveringText, mouseX, mouseY, recipeWidth, recipeHeight, -1, minecraft.fontRendererObj);
        super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);

    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, input);
        ingredients.setOutput(FluidStack.class, outFluid);

    }

}
