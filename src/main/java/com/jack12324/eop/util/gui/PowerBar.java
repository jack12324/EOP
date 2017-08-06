package com.jack12324.eop.util.gui;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.machine.TEPowered;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

class PowerBar extends Gui {

    private static final ResourceLocation TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/fluid_bar.png");
    private final TEPowered tileEntity;
    private final int xPos;
    private final int yPos;

    private final int POWER_WIDTH = 8;
    private final int POWER_HEIGHT = 45;

    PowerBar(TEPowered tileEntity, int xPos, int yPos) {
        this.tileEntity = tileEntity;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void draw() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.getTextureManager().bindTexture(TEXTURE);
        // draw the fuel remaining bar for the power bar
        double powerRemaining = tileEntity.fractionOfPowerRemaining();
        int yOffset = (int) ((1.0 - powerRemaining) * POWER_HEIGHT);

        int POWER_ICON_V = 109;
        int POWER_ICON_U = 0;
        drawTexturedModalRect(xPos, yPos + yOffset, POWER_ICON_U, POWER_ICON_V + yOffset,
                POWER_WIDTH, POWER_HEIGHT - yOffset);
    }

    List<String> drawText(int mouseX, int mouseY) {
        List<String> hoveringText = new ArrayList<>();
        if (isInRect(xPos, yPos, POWER_WIDTH, POWER_HEIGHT, mouseX, mouseY)) {
            hoveringText.add("Energy Stored:");
            int powerPercentage = (int) (tileEntity.fractionOfPowerRemaining() * 100);
            hoveringText.add(powerPercentage + "%");
            hoveringText.add(tileEntity.storage.getEnergyStored() + "/" + tileEntity.storage.getMaxEnergyStored());
            hoveringText.add("Using: " + (int) tileEntity.getEnergyPerTick() + "rf/t");
            hoveringText.add("Net: " + tileEntity.getEnergyDiff() + "rf/t");
            return hoveringText;
        }
        return hoveringText;
    }

    private boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }
}
