package com.jack12324.eop.util.gui;

import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.packet.PacketClientToServer;
import com.jack12324.eop.packet.PacketHandler;
import com.jack12324.eop.util.Coord4D;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiBase extends GuiContainer {
    private static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    private final InventoryPlayer playerInv;
    private final TEPowered tileEntity;
    private final ResourceLocation BG_TEXTURE;
    private int baseWidth = 175;
    private int baseHeight = 165;
    private PowerBar powerBar;
    private FluidBar fluidBar;
    private final int[] progressBar;
    private int fuelBar[];
    private int tankX = 25;
    private int tankY = 18;
    private boolean fluid = false;

    private boolean fuel = false;

    private GuiBase(Container inventorySlotsIn, InventoryPlayer playerInv, TEPowered tileEntity,
                    ResourceLocation resourceLocation, int baseWidth, int baseHeight, int[] progressVals) {
        this(inventorySlotsIn, playerInv, tileEntity, resourceLocation, progressVals);
        this.baseHeight = baseHeight;
        this.baseWidth = baseWidth;
    }

    protected GuiBase(Container inventorySlotsIn, InventoryPlayer playerInv, TEPowered tileEntity,
                      ResourceLocation resourceLocation, int[] progressVals) {
        super(inventorySlotsIn);
        BG_TEXTURE = resourceLocation;
        this.playerInv = playerInv;
        this.tileEntity = tileEntity;
        this.progressBar = new int[progressVals.length];
        System.arraycopy(progressVals, 0, this.progressBar, 0, progressBar.length);
        if (tileEntity instanceof TEFluidUser)
            fluid = true;

    }

    protected GuiBase(Container inventorySlotsIn, InventoryPlayer playerInv, TEPowered tileEntity,
                      ResourceLocation resourceLocation, int[] progressVals, int[] fuelVals) {
        this(inventorySlotsIn, playerInv, tileEntity, resourceLocation, progressVals);
        this.fuelBar = new int[fuelVals.length];
        System.arraycopy(fuelVals, 0, this.fuelBar, 0, fuelBar.length);
        fuel = true;

    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) throws IOException {
        actionPerformed(par1GuiButton, 0);
    }

    private void actionPerformed(GuiButton button, int mbutton) throws IOException {
        EntityPlayer player = Minecraft.getMinecraft().player;
        if (button.id == 69) {
            NBTTagCompound compound = new NBTTagCompound();
            Coord4D pos = new Coord4D(tileEntity.getPos(), tileEntity.getWorld());
            compound = pos.write(compound);
            compound.setInteger("guiID", ModGuiHandler.UPGRADES);
            PacketHandler.NETWORK.sendToServer(new PacketClientToServer(compound, PacketHandler.GUI_UPGRADE_BUTTON));
        } else {
            super.actionPerformed(button);
        }
    }

    protected void drawFluidBar() {
        fluidBar.draw();
    }

    private void drawFuelBar() {
        double burnRemaining = tileEntity.fractionOfFuelRemaining();
        int yOffset = (int) ((1.0 - burnRemaining) * this.fuelBar[5]);
        drawTexturedModalRect(guiLeft + this.fuelBar[0], guiTop + this.fuelBar[1] + yOffset, this.fuelBar[2],
                this.fuelBar[3] + yOffset, this.fuelBar[4], this.fuelBar[5] - yOffset);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(BG_TEXTURE);

        // gui base
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, baseWidth, baseHeight);
        if (fuel)
            this.drawFuelBar();
        this.drawProgressBar();
        // this.drawPowerBar();
        if (fluid)
            this.drawFluidBar();
        this.drawPowerBar();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        FontRenderer fontRenderer = this.fontRendererObj;
        String name = tileEntity.getDisplayedName();
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
        fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);

        List<String> hoveringText = new ArrayList<>();

        // If the mouse is over the progress bar add the progress bar hovering
        // text
        if (isInRect(guiLeft + this.progressBar[0], guiTop + this.progressBar[1], this.progressBar[4],
                this.progressBar[5], mouseX, mouseY)) {
            hoveringText.add("Progress:");
            int cookPercentage = (int) (tileEntity.fractionOfProgressTimeComplete(0) * 100);
            hoveringText.add(cookPercentage + "%");
        } else if (fuel && isInRect(guiLeft + this.fuelBar[0], guiTop + this.fuelBar[1], this.fuelBar[4], this.fuelBar[5],
                mouseX, mouseY)) {
            hoveringText.add("Fuel Time:");
            hoveringText.add(tileEntity.secondsOfFuelRemaining() + "s");
        } else if (hoveringText.isEmpty())
            hoveringText = powerBar.drawText(mouseX, mouseY);
        else if (hoveringText.isEmpty())
            hoveringText = this.fluidText(mouseX, mouseY);

        if (hoveringText != null && !hoveringText.isEmpty())
            this.drawText(hoveringText, mouseX, mouseY);

    }

    private void drawPowerBar() {
        powerBar.draw();
    }

    private void drawProgressBar() {
        // get cook progress as a double between 0 and 1
        double cookProgress = tileEntity.fractionOfProgressTimeComplete(0);
        // draw the cook progress bar
        drawTexturedModalRect(guiLeft + this.progressBar[0], guiTop + this.progressBar[1], this.progressBar[2],
                this.progressBar[3], (int) (cookProgress * this.progressBar[4]), this.progressBar[5]);
    }

    private void drawText(List<String> hoveringText, int mouseX, int mouseY) {
        // If hoveringText is not empty draw the hovering text
        if (hoveringText != null && !hoveringText.isEmpty()) {
            drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
        }
    }

    protected ArrayList<String> fluidText(int mouseX, int mouseY) {
        return fluidBar.drawText(mouseX, mouseY);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(69, guiLeft - 30, guiTop, 30, 20, "Upgrades"));
        powerBar = new PowerBar(tileEntity, guiLeft, guiTop);
        if (fluid)
            fluidBar = new FluidBar(((TEFluidUser) tileEntity).inTank, guiLeft + tankX, guiTop + tankY);
    }

}
