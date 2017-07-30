package com.jack12324.eop.util.gui;

import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.machine.activationChamber.TileEntityActivationChamber;
import com.jack12324.eop.machine.disablingPress.TileEntityDisablingPress;
import com.jack12324.eop.packet.PacketClientToServer;
import com.jack12324.eop.packet.PacketHandler;
import com.jack12324.eop.util.Coord4D;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.HelpfulMethods;
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

    private final InventoryPlayer playerInv;
    private final TEPowered tileEntity;
    private final ResourceLocation BG_TEXTURE;
    private final GuiValues guiValues;
    private PowerBar powerBar;
    private FluidBar fluidBar;
    private FluidBar fluidOutBar;
    private boolean fluid = false;
    private boolean fluidOut = false;
    private boolean fuel = false;

    public GuiBase(Container inventorySlotsIn, InventoryPlayer playerInv, TEPowered tileEntity,
                   ResourceLocation resourceLocation, GuiValues guiValues) {
        super(inventorySlotsIn);
        this.playerInv = playerInv;
        this.tileEntity = tileEntity;
        this.BG_TEXTURE = resourceLocation;
        this.guiValues = guiValues;
        if (tileEntity instanceof TEFluidUser)
            fluid = true;
        if (tileEntity instanceof TEFluidProducer)
            fluidOut = true;
        if (tileEntity instanceof TileEntityActivationChamber || tileEntity instanceof TileEntityDisablingPress)
            fuel = true;

    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) throws IOException {
        this.actionPerformed(par1GuiButton, 0);
    }

    protected void actionPerformed(GuiButton button, int mbutton) throws IOException {
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


    private void drawFuelBar() {
        double burnRemaining = tileEntity.fractionOfFuelRemaining();
        int yOffset = (int) ((1.0 - burnRemaining) * this.guiValues.getFuel()[5]);
        drawTexturedModalRect(guiLeft + this.guiValues.getFuel()[0], guiTop + this.guiValues.getFuel()[1] + yOffset, this.guiValues.getFuel()[2],
                this.guiValues.getFuel()[3] + yOffset, this.guiValues.getFuel()[4], this.guiValues.getFuel()[5] - yOffset);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(BG_TEXTURE);

        // gui base
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.guiValues.getWidth(), this.guiValues.getHeight());
        if (fuel)
            this.drawFuelBar();
        this.drawProgressBar();
        if (fluid)
            this.fluidBar.draw();
        if (fluidOut)
            this.fluidOutBar.draw();
        this.drawOther();
        this.drawPowerBar();
    }

    protected void drawOther() {
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        FontRenderer fontRenderer = this.fontRendererObj;
        String name = tileEntity.getDisplayedName();
        drawTitleText(fontRenderer, name, playerInv.getDisplayName().getUnformattedText());

        List<String> hoveringText = this.progressText(mouseX, mouseY);

        if (hoveringText.isEmpty() && fuel && HelpfulMethods.isInRect(guiLeft + this.guiValues.getFuel()[0], guiTop + this.guiValues.getFuel()[1], this.guiValues.getFuel()[4], this.guiValues.getFuel()[5],
                mouseX, mouseY)) {
            hoveringText.add("Fuel Time:");
            hoveringText.add(tileEntity.secondsOfFuelRemaining() + "s");
        }
        if (hoveringText.isEmpty()) {
            hoveringText = powerBar.drawText(mouseX, mouseY);
            if (hoveringText.isEmpty()) {
                hoveringText = this.fluidText(mouseX, mouseY);
                if (hoveringText.isEmpty()) {
                    hoveringText = this.otherText(mouseX, mouseY);
                }
            }
        }
        this.drawText(hoveringText, mouseX, mouseY);

    }

    protected void drawTitleText(FontRenderer fontRenderer, String name, String invName) {
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
        fontRenderer.drawString(invName, 8, ySize - 94, 0x404040);
    }

    private void drawPowerBar() {
        powerBar.draw();
    }

    private void drawProgressBar() {
        for (int i = 0; i < this.guiValues.getProgress().length; i += 6) {
            // get cook progress as a double between 0 and 1
            double cookProgress = tileEntity.fractionOfProgressTimeComplete(i / 6);
            // draw the cook progress bar
            drawTexturedModalRect(guiLeft + this.guiValues.getProgress()[i], guiTop + this.guiValues.getProgress()[i + 1], this.guiValues.getProgress()[i + 2],
                    this.guiValues.getProgress()[i + 3], (int) (cookProgress * this.guiValues.getProgress()[i + 4]), this.guiValues.getProgress()[i + 5]);
        }
    }

    private void drawText(List<String> hoveringText, int mouseX, int mouseY) {
        // If hoveringText is not empty draw the hovering text
        if (hoveringText != null && !hoveringText.isEmpty()) {
            drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
        }
    }

    private List<String> fluidText(int mouseX, int mouseY) {
        List<String> hoveringText = new ArrayList<>();
        if (fluid) {
            hoveringText = new ArrayList<>(fluidBar.drawText(mouseX, mouseY));
            if (hoveringText.isEmpty() && fluidOut)
                hoveringText = new ArrayList<>(fluidOutBar.drawText(mouseX, mouseY));
        }
        return hoveringText;
    }

    private List<String> progressText(int mouseX, int mouseY) {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < this.guiValues.getProgress().length; i += 6) {
            if (HelpfulMethods.isInRect(guiLeft + this.guiValues.getProgress()[i], guiTop + this.guiValues.getProgress()[i + 1], this.guiValues.getProgress()[i + 4],
                    this.guiValues.getProgress()[i + 5], mouseX, mouseY)) {
                temp.add("Progress:");
                int cookPercentage = (int) (tileEntity.fractionOfProgressTimeComplete(i / 6) * 100);
                temp.add(cookPercentage + "%");
            }
        }
        return temp;
    }

    protected List<String> otherText(int mouseX, int mouseY) {
        return null;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(69, guiLeft + this.guiValues.getWidth() + 1, guiTop, 30, 20, "Upgrades"));
        powerBar = new PowerBar(tileEntity, this.guiValues.getPower()[0] + guiLeft, this.guiValues.getPower()[1] + guiTop);
        if (fluid)
            fluidBar = new FluidBar(((TEFluidUser) tileEntity).inTank, guiLeft + this.guiValues.getInTank()[0], guiTop + this.guiValues.getInTank()[1]);
        if (fluidOut)
            fluidOutBar = new FluidBar(((TEFluidProducer) tileEntity).outTank, guiLeft + this.guiValues.getOutTank()[0], guiTop + this.guiValues.getOutTank()[1]);

    }

}
