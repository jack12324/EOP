package com.jack12324.eop.util.gui;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.packet.PacketClientToServer;
import com.jack12324.eop.packet.PacketHandler;
import com.jack12324.eop.util.Coord4D;
import com.jack12324.eop.util.EOPGuiButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class GuiUpgrade extends GuiContainer {

    private final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/upgrade_screen_gui.png");
    private final TileEntity tileEntity;

    public GuiUpgrade(Container serverGuiElement, TileEntity tileEntity) {
        super(serverGuiElement);
        this.tileEntity = tileEntity;
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) throws IOException {
        actionPerformed(par1GuiButton, 0);
    }

    private void actionPerformed(GuiButton button, int mbutton) throws IOException {
        if (button.id == 70) {
            NBTTagCompound compound = new NBTTagCompound();
            Coord4D pos = new Coord4D(tileEntity.getPos(), tileEntity.getWorld());
            compound = pos.write(compound);
            compound.setInteger("guiID", ModGuiHandler.getTileGui(tileEntity));
            PacketHandler.NETWORK.sendToServer(new PacketClientToServer(compound, PacketHandler.GUI_UPGRADE_BUTTON));
        } else if (button.id >= 71 && button.id <= 76) {

            NBTTagCompound compound = new NBTTagCompound();
            Coord4D pos = new Coord4D(tileEntity.getPos(), tileEntity.getWorld());
            compound = pos.write(compound);
            compound.setInteger("buttonID", button.id);
            PacketHandler.NETWORK.sendToServer(new PacketClientToServer(compound, PacketHandler.GUI_TOGGLE_BUTTON));
        } else
            super.actionPerformed(button);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(BG_TEXTURE);
        // gui base
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, getXSize(), getYSize());
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(70, guiLeft - 30, guiTop, 30, 20, "Exit"));

        this.buttonList.add(new EOPGuiButton(71, guiLeft + 98, guiTop + 34, 16, 16, "", EnumFacing.NORTH, this.tileEntity));
        this.buttonList.add(new EOPGuiButton(72, guiLeft + 70, guiTop + 34, 16, 16, "", EnumFacing.EAST, this.tileEntity));
        this.buttonList.add(new EOPGuiButton(73, guiLeft + 116, guiTop + 34, 16, 16, "", EnumFacing.WEST, this.tileEntity));
        this.buttonList.add(new EOPGuiButton(74, guiLeft + 98, guiTop + 16, 16, 16, "", EnumFacing.UP, this.tileEntity));
        this.buttonList.add(new EOPGuiButton(75, guiLeft + 98, guiTop + 52, 16, 16, "", EnumFacing.DOWN, this.tileEntity));
        this.buttonList.add(new EOPGuiButton(76, guiLeft + 116, guiTop + 16, 16, 16, "", EnumFacing.SOUTH, this.tileEntity));


    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

}
