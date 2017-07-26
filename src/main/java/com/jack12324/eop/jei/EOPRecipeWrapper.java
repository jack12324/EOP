package com.jack12324.eop.jei;

import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.HelpfulMethods;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public abstract class EOPRecipeWrapper extends BlankRecipeWrapper {

    GuiValues guiValues;

    protected GuiValues getGuiValues() {
        return guiValues;
    }

    @Override
    public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        if (getGuiValues() != null) {
            List<String> hoveringText = new ArrayList<>();
            boolean isOnProgress = false;
            for (int i = 0; i < getGuiValues().getProgress().length; i += 6) {
                if (HelpfulMethods.isInRect(guiValues.getProgress()[i], guiValues.getProgress()[i + 1], guiValues.getProgress()[i + 4], guiValues.getProgress()[i + 5], mouseX, mouseY)) {
                    isOnProgress = true;
                    break;
                }
            }
            if (isOnProgress)
                hoveringText.add("Time: " + TEPowered.BASE_TICKS_NEEDED / 20 + "s");

            else if (HelpfulMethods.isInRect(guiValues.getPower()[0], guiValues.getPower()[1], guiValues.getPower()[4], guiValues.getPower()[5], mouseX, mouseY))
                hoveringText.add("Power: " + TEPowered.BASE_TICKS_NEEDED * TEPowered.BASE_ENERGY_PER_TICK + "rf");

            if (hoveringText != null && !hoveringText.isEmpty()) {
                net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(hoveringText, mouseX, mouseY, recipeWidth, recipeHeight, -1, minecraft.fontRendererObj);
            }
        }
    }
}
