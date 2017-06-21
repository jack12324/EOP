package com.jack12324.eop.util.gui;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.machine.TEPowered;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiBase extends GuiContainer {
	private InventoryPlayer playerInv;
	private TEPowered tileEntity;
	private ResourceLocation BG_TEXTURE;
	private int baseWidth = 175;
	private int baseHeight = 165;
	private PowerBar powerBar;
	private FluidBar fluidBar;
	private int progressBar[];
	private int fuelBar[];
	private int tankX = 25;
	private int tankY = 18;
	boolean fluid = false;
	boolean fuel = false;

	public GuiBase(Container inventorySlotsIn, InventoryPlayer playerInv, TEPowered tileEntity,
			ResourceLocation resourceLocation, int[] progressVals) {
		super(inventorySlotsIn);
		BG_TEXTURE = resourceLocation;
		this.playerInv = playerInv;
		this.tileEntity = tileEntity;
		this.progressBar = new int[progressVals.length];
		for (int i = 0; i < progressBar.length; i++)
			this.progressBar[i] = progressVals[i];
		if (tileEntity instanceof TEFluidUser)
			fluid = true;

	}

	public GuiBase(Container inventorySlotsIn, InventoryPlayer playerInv, TEPowered tileEntity,
			ResourceLocation resourceLocation, int baseWidth, int baseHeight, int[] progressVals) {
		this(inventorySlotsIn, playerInv, tileEntity, resourceLocation, progressVals);
		this.baseHeight = baseHeight;
		this.baseWidth = baseWidth;
	}

	public GuiBase(Container inventorySlotsIn, InventoryPlayer playerInv, TEPowered tileEntity,
			ResourceLocation resourceLocation, int baseHeight, int baseWidth, int[] progressVals, int tankX,
			int tankY) {
		this(inventorySlotsIn, playerInv, tileEntity, resourceLocation, baseHeight, baseWidth, progressVals);
		this.tankX = tankX;
		this.tankY = tankY;

	}

	public GuiBase(Container inventorySlotsIn, InventoryPlayer playerInv, TEPowered tileEntity,
			ResourceLocation resourceLocation, int baseHeight, int baseWidth, int[] progressVals, int[] fuelVals) {
		this(inventorySlotsIn, playerInv, tileEntity, resourceLocation, baseHeight, baseWidth, progressVals);
		this.fuelBar = new int[fuelVals.length];
		for (int i = 0; i < fuelBar.length; i++)
			this.fuelBar[i] = fuelVals[i];
		fuel = true;

	}

	public GuiBase(Container inventorySlotsIn, InventoryPlayer playerInv, TEPowered tileEntity,
			ResourceLocation resourceLocation, int[] progressVals, int[] fuelVals) {
		this(inventorySlotsIn, playerInv, tileEntity, resourceLocation, progressVals);
		this.fuelBar = new int[fuelVals.length];
		for (int i = 0; i < fuelBar.length; i++)
			this.fuelBar[i] = fuelVals[i];
		fuel = true;

	}

	@Override
	public void initGui() {
		super.initGui();
		powerBar = new PowerBar(tileEntity, guiLeft, guiTop);
		if (fluid)
			fluidBar = new FluidBar(((TEFluidUser) tileEntity).inTank, guiLeft + tankX, guiTop + tankY);
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

	protected void drawFuelBar() {
		double burnRemaining = tileEntity.fractionOfFuelRemaining();
		int yOffset = (int) ((1.0 - burnRemaining) * this.fuelBar[5]);
		drawTexturedModalRect(guiLeft + this.fuelBar[0], guiTop + this.fuelBar[1] + yOffset, this.fuelBar[2],
				this.fuelBar[3] + yOffset, this.fuelBar[4], this.fuelBar[5] - yOffset);
	}

	protected void drawFluidBar() {
		fluidBar.draw();
	}

	protected void drawPowerBar() {
		powerBar.draw();
	}

	protected void drawProgressBar() {
		// get cook progress as a double between 0 and 1
		double cookProgress = tileEntity.fractionOfProgressTimeComplete(0);
		// draw the cook progress bar
		drawTexturedModalRect(guiLeft + this.progressBar[0], guiTop + this.progressBar[1], this.progressBar[2],
				this.progressBar[3], (int) (cookProgress * this.progressBar[4]), this.progressBar[5]);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		FontRenderer fontRenderer = this.fontRendererObj;
		String name = tileEntity.getDisplayedName();
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);

		List<String> hoveringText = new ArrayList<String>();

		// If the mouse is over the progress bar add the progress bar hovering
		// text
		if (isInRect(guiLeft + this.progressBar[0], guiTop + this.progressBar[1], this.progressBar[4],
				this.progressBar[5], mouseX, mouseY)) {
			hoveringText.add("Progress:");
			int cookPercentage = (int) (tileEntity.fractionOfProgressTimeComplete(0) * 100);
			hoveringText.add(cookPercentage + "%");
		}
		if (fuel && isInRect(guiLeft + this.fuelBar[0], guiTop + this.fuelBar[1], this.fuelBar[4], this.fuelBar[5],
				mouseX, mouseY)) {
			hoveringText.add("Fuel Time:");
			hoveringText.add(tileEntity.secondsOfFuelRemaining() + "s");
		}

		if (hoveringText == null || hoveringText.isEmpty())
			hoveringText = powerBar.drawText(mouseX, mouseY);

		if (fluid && (hoveringText == null || hoveringText.isEmpty()))
			hoveringText = fluidText(mouseX, mouseY);

		this.drawText(hoveringText, mouseX, mouseY);

	}

	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
		return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
	}

	protected ArrayList<String> fluidText(int mouseX, int mouseY) {
		return fluidBar.drawText(mouseX, mouseY);
	}

	public void drawText(List<String> hoveringText, int mouseX, int mouseY) {
		// If hoveringText is not empty draw the hovering text
		if (hoveringText != null && !hoveringText.isEmpty()) {
			drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
		}
	}

}
