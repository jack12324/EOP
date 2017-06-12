package com.jack12324.eop.machine.activationChamber;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.util.PowerBar;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiActivationChamber extends GuiContainer {
	private TileEntityActivationChamber tileEntity;
	private InventoryPlayer playerInv;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/activation_chamber.png");
	PowerBar powerBar;

	public GuiActivationChamber(Container inventorySlotsIn, InventoryPlayer playerInv,
			TileEntityActivationChamber tileEntity) {
		super(inventorySlotsIn);
		this.playerInv = playerInv;
		this.tileEntity = tileEntity;
		powerBar=new PowerBar(tileEntity,guiLeft,guiTop);
	}

	// some [x,y] coordinates of graphical elements
	final int PROGRESS_BAR_XPOS = 49;
	final int PROGRESS_BAR_YPOS = 30;
	final int PROGRESS_BAR_ICON_U = 3; // texture position of white arrow icon
	final int PROGRESS_BAR_ICON_V = 169;
	final int PROGRESS_BAR_WIDTH = 77;
	final int PROGRESS_BAR_HEIGHT = 15;

	final int FLAME_XPOS = 74;
	final int FLAME_YPOS = 47;
	final int FLAME_ICON_U = 178; // texture position of flame icon
	final int FLAME_ICON_V = 2;
	final int FLAME_WIDTH = 3;
	final int FLAME_HEIGHT = 20;

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);

		// gui base
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 175, 165);

		// get cook progress as a double between 0 and 1
		double cookProgress = tileEntity.fractionOfActivationTimeComplete(0);
		// draw the cook progress bar
		drawTexturedModalRect(guiLeft + PROGRESS_BAR_XPOS, guiTop + PROGRESS_BAR_YPOS, PROGRESS_BAR_ICON_U, PROGRESS_BAR_ICON_V,
				(int) (cookProgress * PROGRESS_BAR_WIDTH), PROGRESS_BAR_HEIGHT);

		// draw the fuel remaining bar for each fuel slot flame
		double burnRemaining = tileEntity.fractionOfFuelRemaining();
		int yOffset = (int) ((1.0 - burnRemaining) * FLAME_HEIGHT);
		drawTexturedModalRect(guiLeft + FLAME_XPOS, guiTop + FLAME_YPOS + yOffset, FLAME_ICON_U, FLAME_ICON_V + yOffset,
				FLAME_WIDTH, FLAME_HEIGHT - yOffset);

		powerBar.draw();

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		FontRenderer fontRenderer = this.fontRendererObj;
		String name = I18n.format(ModBlocks.activationChamber.getUnlocalizedName() + ".name");
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);

		List<String> hoveringText = new ArrayList<String>();

		// If the mouse is over the progress bar add the progress bar hovering
		// text
		if (isInRect(guiLeft + PROGRESS_BAR_XPOS, guiTop + PROGRESS_BAR_YPOS, PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT, mouseX,
				mouseY)) {
			hoveringText.add("Progress:");
			int cookPercentage = (int) (tileEntity.fractionOfActivationTimeComplete(0) * 100);
			hoveringText.add(cookPercentage + "%");
		}

		// If the mouse is over one of the burn time indicator add the burn time
		// indicator hovering text

		if (isInRect(guiLeft + FLAME_XPOS, guiTop + FLAME_YPOS, FLAME_WIDTH, FLAME_HEIGHT, mouseX, mouseY)) {
			hoveringText.add("Fuel Time:");
			hoveringText.add(tileEntity.secondsOfFuelRemaining() + "s");
		}

		if(hoveringText==null||hoveringText.isEmpty())
			hoveringText = powerBar.drawText(mouseX, mouseY);

		// If hoveringText is not empty draw the hovering text
		if (hoveringText!=null && !hoveringText.isEmpty()) {
			drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
		}

	}

	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
		return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
	}

}
