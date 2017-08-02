package com.jack12324.eop.util.gui;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.packet.PacketClientToServer;
import com.jack12324.eop.packet.PacketHandler;
import com.jack12324.eop.util.Coord4D;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
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
            compound.setInteger("guiID", ModGuiHandler.getTileGui(tileEntity));
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

        this.buttonList.add(new GuiButton(71, guiLeft + 96, guiTop + 32, 20, 20, ""));
        this.buttonList.add(new GuiButton(72, guiLeft + 76, guiTop + 32, 20, 20, ""));
        this.buttonList.add(new GuiButton(73, guiLeft + 116, guiTop + 32, 20, 20, ""));
        this.buttonList.add(new GuiButton(74, guiLeft + 96, guiTop + 12, 20, 20, ""));
        this.buttonList.add(new GuiButton(75, guiLeft + 96, guiTop + 52, 20, 20, ""));
        this.buttonList.add(new GuiButton(76, guiLeft + 116, guiTop + 12, 20, 20, ""));


    }

}
