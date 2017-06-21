package com.jack12324.eop.util.gui;

import com.jack12324.eop.ExtremeOreProcessing;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiUpgrade extends GuiContainer {

	private ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/sauce.png");;

	public GuiUpgrade(Container serverGuiElement, InventoryPlayer inventory, TileEntity tileEntity) {
		super(serverGuiElement);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		// gui base
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 20, 20);
	}

}
