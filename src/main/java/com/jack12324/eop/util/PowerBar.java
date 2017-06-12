package com.jack12324.eop.util;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.machine.activationChamber.TileEntityActivationChamber;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class PowerBar extends Gui {

	private TEPowered tileEntity;
	int guiLeft;
	int guiTop;
	private static final ResourceLocation TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/fluid_bar.png");

	public PowerBar(TEPowered tileEntity, int guiLeft, int guiTop) {
		this.tileEntity = tileEntity;
		this.guiLeft = guiLeft;
		this.guiTop = guiTop;
	}

	final int POWER_XPOS = 8;
	final int POWER_YPOS = 20;
	final int POWER_ICON_U = 0; // texture position of POWER icon
	final int POWER_ICON_V = 109;
	final int POWER_WIDTH = 8;
	final int POWER_HEIGHT = 45;

	public void draw() {
		Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(TEXTURE);
		// draw the fuel remaining bar for the power bar
		double powerRemaining = tileEntity.fractionOfPowerRemaining();
		int yOffset = (int) ((1.0 - powerRemaining) * POWER_HEIGHT);

		drawTexturedModalRect(guiLeft + POWER_XPOS, guiTop + POWER_YPOS + yOffset, POWER_ICON_U, POWER_ICON_V + yOffset,
				POWER_WIDTH, POWER_HEIGHT - yOffset);
	}

	public ArrayList<String> drawText(int mouseX, int mouseY) {
		List<String> hoveringText = new ArrayList<String>();
		if (isInRect(guiLeft + POWER_XPOS, guiTop + POWER_YPOS, POWER_WIDTH, POWER_HEIGHT, mouseX, mouseY)) {
			hoveringText.add("Energy Stored:");
			int powerPercentage = (int) (tileEntity.fractionOfPowerRemaining() * 100);
			hoveringText.add(powerPercentage + "%");
			hoveringText.add(tileEntity.storage.getEnergyStored() + "/" + tileEntity.storage.getMaxEnergyStored());
			hoveringText.add(tileEntity.getEnergyPerTick() + "rf/t");
			return (ArrayList<String>) hoveringText;
		}
		return null;
	}

	public boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
		return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
	}
}
