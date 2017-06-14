package com.jack12324.eop.machine.equalizingSmelter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.packet.PacketHandler;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiEqualizingSmelter extends GuiContainer {
	private TileEntityEqualizingSmelter tileEntity;
	private InventoryPlayer playerInv;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/equalizing_smelter.png");
	private static final int SMELT_MODE_BUTTON_ID = 53;

	public GuiEqualizingSmelter(Container inventorySlotsIn, InventoryPlayer playerInv,
			TileEntityEqualizingSmelter tileEntity) {
		super(inventorySlotsIn);
		this.playerInv = playerInv;
		this.tileEntity = tileEntity;

	}

	// some [x,y] coordinates of graphical elements
	final int[] COOK_BAR_XPOS = { 44, 107, 44, 107 };
	final int[] COOK_BAR_YPOS = { 34, 34, 58, 58 };
	final int COOK_BAR_ICON_U = 180; // texture position of white arrow icon
	final int[] COOK_BAR_ICON_V = { 88, 33, 52, 70 };
	final int COOK_BAR_WIDTH = 25;
	final int COOK_BAR_HEIGHT = 16;

	final int DUST_XPOS = 82;
	final int DUST_YPOS = 24;
	final int DUST_ICON_U = 180; // texture position of flame icon
	final int DUST_ICON_V = 20;
	final int DUST_WIDTH = 12;
	final int DUST_HEIGHT = 11;

	final int POWER_XPOS = 9;
	final int POWER_YPOS = 21;
	final int POWER_ICON_U = 247; // texture position of POWER icon
	final int POWER_ICON_V = 147;
	final int POWER_WIDTH = 8;
	final int POWER_HEIGHT = 45;

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.add(new GuiButton(53, guiLeft - 30, guiTop, 30, 20, "Mode"));
		this.buttonList.add(new GuiButton(57, guiLeft - 40, guiTop + 20, 40, 20, "Spread"));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);

		// gui base
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 175, 183);

		// get cook progress as a double between 0 and 1
		double[] cookProgress = new double[4];
		for (int i = 0; i < 4; i++) {
			cookProgress[i] = tileEntity.fractionOfProgressTimeComplete(i);
			if (i % 2 != 0) {
				int xOffset = (int) ((1.0 - cookProgress[i]) * COOK_BAR_WIDTH);
				drawTexturedModalRect(guiLeft + COOK_BAR_XPOS[i] + xOffset, guiTop + COOK_BAR_YPOS[i],
						COOK_BAR_ICON_U + xOffset, COOK_BAR_ICON_V[i], COOK_BAR_WIDTH - xOffset, COOK_BAR_HEIGHT);
			} else
				drawTexturedModalRect(guiLeft + COOK_BAR_XPOS[i], guiTop + COOK_BAR_YPOS[i], COOK_BAR_ICON_U,
						COOK_BAR_ICON_V[i], (int) (cookProgress[i] * COOK_BAR_WIDTH), COOK_BAR_HEIGHT);
		}

		// extraFirestone slot
		double dustProgress = tileEntity.fractionOfDustProgress();
		int yOffset = (int) ((1.0 - dustProgress) * DUST_HEIGHT);
		drawTexturedModalRect(guiLeft + DUST_XPOS, guiTop + DUST_YPOS + yOffset, DUST_ICON_U, DUST_ICON_V + yOffset,
				DUST_WIDTH, DUST_HEIGHT - yOffset);

		// draw the fuel remaining bar for the power bar
		double powerRemaining = tileEntity.fractionOfPowerRemaining();
		yOffset = (int) ((1.0 - powerRemaining) * POWER_HEIGHT);
		drawTexturedModalRect(guiLeft + POWER_XPOS, guiTop + POWER_YPOS + yOffset, POWER_ICON_U, POWER_ICON_V + yOffset,
				POWER_WIDTH, POWER_HEIGHT - yOffset);

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		FontRenderer fontRenderer = this.fontRendererObj;
		fontRenderer.drawString("Energized", xSize / 8 - fontRenderer.getStringWidth("Energized") / 8, 6, 0x404040);
		fontRenderer.drawString(" Smelter ", xSize / 8 - fontRenderer.getStringWidth(" Smelter ") / 8, 15, 0x404040);
		fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 75, 0x404040);

		List<String> hoveringText = new ArrayList<String>();

		// If the mouse is over the progress bar add the progress bar hovering
		// text
		for (int i = 0; i < 4; i++) {
			if (isInRect(guiLeft + COOK_BAR_XPOS[i], guiTop + COOK_BAR_YPOS[i], COOK_BAR_WIDTH, COOK_BAR_HEIGHT, mouseX,
					mouseY)) {
				hoveringText.add("Progress:");
				int cookPercentage = (int) (tileEntity.fractionOfProgressTimeComplete(i) * 100);
				hoveringText.add(cookPercentage + "%");
			}
		}
		// hovering text for buttons
		if (isInRect(guiLeft - 40, guiTop + 30, 40, 20, mouseX, mouseY)) {
			hoveringText.add("Toggle Spread Mode");
			hoveringText.add("Spread Mode: " + tileEntity.getSpreadMode());
		}
		if (isInRect(guiLeft - 30, guiTop + 5, 30, 20, mouseX, mouseY)) {
			hoveringText.add("Toggle Smelter Mode");
			if (tileEntity.getMode())
				hoveringText.add("Smelter Mode: Furnace");
			else
				hoveringText.add("Smelter Mode: Equalizing");
		}

		// hovering text for firestone slot
		if (isInRect(guiLeft + DUST_XPOS, guiTop + DUST_YPOS, DUST_WIDTH, DUST_HEIGHT, mouseX, mouseY)) {
			hoveringText.add("Progress:");
			int dustPercentage = (int) (tileEntity.fractionOfDustProgress() * 100);
			hoveringText.add(dustPercentage + "%");
		}

		if (isInRect(guiLeft + POWER_XPOS, guiTop + POWER_YPOS, POWER_WIDTH, POWER_HEIGHT, mouseX, mouseY)) {
			hoveringText.add("Energy Stored:");
			int powerPercentage = (int) (tileEntity.fractionOfPowerRemaining() * 100);
			hoveringText.add(powerPercentage + "%");
			hoveringText.add(tileEntity.storage.getEnergyStored() + "/" + tileEntity.storage.getMaxEnergyStored());
			hoveringText.add((int) tileEntity.getEnergyPerTick() + "rf/t");
		}

		// If hoveringText is not empty draw the hovering text
		if (!hoveringText.isEmpty()) {
			drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
		}

	}

	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
		return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) throws IOException {
		actionPerformed(par1GuiButton, 0);
	}

	private void actionPerformed(GuiButton button, int mbutton) throws IOException {
		if (button.id == 53) {

			tileEntity.setMode(mbutton == 0 ? !tileEntity.getMode() : !tileEntity.getMode());
			PacketHandler.NETWORK.sendToServer(new PacketClientState(tileEntity));
		} else if (button.id == 57) {

			tileEntity.setSpreadMode(mbutton == 0 ? !tileEntity.getSpreadMode() : !tileEntity.getSpreadMode());
			PacketHandler.NETWORK.sendToServer(new PacketClientState(tileEntity));
		} else {
			super.actionPerformed(button);
		}
	}

}
