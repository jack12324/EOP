package com.jack12324.eop.util.gui;

import java.io.IOException;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.packet.PacketClientToServer;
import com.jack12324.eop.packet.PacketHandler;
import com.jack12324.eop.util.Coord4D;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiUpgrade extends GuiContainer {

	private ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/sauce.png");
	TileEntity tileEntity;

	public GuiUpgrade(Container serverGuiElement, InventoryPlayer inventory, TileEntity tileEntity) {
		super(serverGuiElement);
		this.tileEntity=tileEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		// gui base
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 20, 20);
	}
	
	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.add(new GuiButton(70, guiLeft - 30, guiTop, 30, 20, "Exit"));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) throws IOException {
		actionPerformed(par1GuiButton, 0);
	}

	private void actionPerformed(GuiButton button, int mbutton) throws IOException {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if (button.id == 70) {
			NBTTagCompound compound = new NBTTagCompound();
			Coord4D pos = new Coord4D(tileEntity.getPos(), tileEntity.getWorld());
			compound = pos.write(compound);
			compound.setInteger("guiID", ModGuiHandler.getTileGui(tileEntity));
			PacketHandler.NETWORK.sendToServer(new PacketClientToServer(compound, PacketHandler.GUI_BUTTON2));
		} else {
			super.actionPerformed(button);
		}
	}

}
