package com.jack12324.eop.machine.equalizingSmelter;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.packet.PacketClientToServer;
import com.jack12324.eop.packet.PacketHandler;
import com.jack12324.eop.util.Coord4D;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.HelpfulMethods;
import com.jack12324.eop.util.gui.GuiBase;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiEqualizingSmelter extends GuiBase {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/equalizing_smelter.png");
    private static final GuiValues guiValues = GuiValues.EQUALIZINGSMELTER;

    private final TileEntityEqualizingSmelter tileEntity;

    public GuiEqualizingSmelter(Container inventorySlotsIn, InventoryPlayer playerInv,
                                TileEntityEqualizingSmelter tileEntity) {
        super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, guiValues);
        this.tileEntity = tileEntity;
    }

    @Override
    protected void actionPerformed(GuiButton button, int mbutton) throws IOException {
        if (button.id == 53 || button.id == 57) {
            NBTTagCompound compound = new NBTTagCompound();
            Coord4D pos = new Coord4D(tileEntity.getPos(), tileEntity.getWorld());
            compound = pos.write(compound);
            compound.setInteger("buttonID", button.id);
            PacketHandler.NETWORK.sendToServer(new PacketClientToServer(compound, PacketHandler.GUI_TOGGLE_BUTTON));
        } else {
            super.actionPerformed(button, mbutton);
        }
    }

    @Override
    protected void drawTitleText(FontRenderer fontRenderer, String name, String invName) {
        String[] parts = name.split(" ", 2);
        name = parts[0];
        String name2 = parts[1];
        fontRenderer.drawString(name, xSize / 8 - fontRenderer.getStringWidth(name) / 8, 6, 0x404040);
        fontRenderer.drawString(name2, xSize / 8 - fontRenderer.getStringWidth(name2) / 8, 16, 0x404040);
        fontRenderer.drawString(invName, 8, ySize - 76, 0x404040);
    }

    @Override
    protected void drawOther() {
        // extraFirestone slot
        double dustProgress = tileEntity.fractionOfDustProgress();
        int yOffset = (int) ((1.0 - dustProgress) * guiValues.getOther()[5]);
        drawTexturedModalRect(guiLeft + guiValues.getOther()[0], guiTop + guiValues.getOther()[1] + yOffset, guiValues.getOther()[2], guiValues.getOther()[3] + yOffset,
                guiValues.getOther()[4], guiValues.getOther()[5] - yOffset);
    }

    @Override
    public void drawProgressBar() {
        for (int i = 0; i < GuiValues.EQUALIZINGSMELTER.getProgress().length; i += 6) {
            // get cook progress as a double between 0 and 1
            double cookProgress = tileEntity.fractionOfProgressTimeComplete(i);
            // draw the cook progress bar
            if (i == 0 || i == 12)
                drawTexturedModalRect(guiLeft + guiValues.getProgress()[i], guiTop + guiValues.getProgress()[i + 1], guiValues.getProgress()[i + 2],
                        guiValues.getProgress()[i + 3], (int) (cookProgress * guiValues.getProgress()[i + 4]), guiValues.getProgress()[i + 5]);
            else {
                int xOffset = (int) ((1.0 - cookProgress) * guiValues.getProgress()[i + 4]);
                drawTexturedModalRect(guiLeft + guiValues.getProgress()[i] + xOffset, guiTop + guiValues.getProgress()[i + 1], guiValues.getProgress()[i + 2] + xOffset,
                        guiValues.getProgress()[i + 3], guiValues.getProgress()[i + 4] - xOffset, guiValues.getProgress()[i + 5]);
            }
        }
    }

    @Override
    protected List<String> otherText(int mouseX, int mouseY) {
        List<String> hoveringText = new ArrayList<>();

        // hovering text for buttons
        if (HelpfulMethods.isInRect(guiLeft - 40, guiTop + 30, 40, 20, mouseX, mouseY)) {
            hoveringText.add("Toggle Spread Mode");
            hoveringText.add("Spread Mode: " + tileEntity.getSpreadMode());
        }
        if (HelpfulMethods.isInRect(guiLeft - 30, guiTop + 5, 30, 20, mouseX, mouseY)) {
            hoveringText.add("Toggle Smelter Mode");
            if (tileEntity.getMode())
                hoveringText.add("Smelter Mode: Furnace");
            else
                hoveringText.add("Smelter Mode: Equalizing");
        }

        // hovering text for firestone slot
        if (HelpfulMethods.isInRect(guiLeft + guiValues.getOther()[0], guiTop + guiValues.getOther()[1], guiValues.getOther()[4], guiValues.getOther()[5], mouseX, mouseY)) {
            hoveringText.add("Progress:");
            int dustPercentage = (int) (tileEntity.fractionOfDustProgress() * 100);
            hoveringText.add(dustPercentage + "%");
        }
        return hoveringText;
    }


    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(53, guiLeft - 30, guiTop, 30, 20, "Mode"));
        this.buttonList.add(new GuiButton(57, guiLeft - 40, guiTop + 20, 40, 20, "Spread"));
    }

}
