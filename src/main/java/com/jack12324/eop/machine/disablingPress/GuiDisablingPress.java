package com.jack12324.eop.machine.disablingPress;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiDisablingPress extends GuiContainer{
	private TileEntityDisablingPress tileEntity;
	private InventoryPlayer playerInv;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/disabling_press.png");

	public GuiDisablingPress(Container inventorySlotsIn, InventoryPlayer playerInv, TileEntityDisablingPress tileEntity) {
		super(inventorySlotsIn);
		this.playerInv = playerInv;
		this.tileEntity = tileEntity;
	}
	
	

		// some [x,y] coordinates of graphical elements
		final int COOK_BAR_XPOS = 45;
		final int COOK_BAR_YPOS = 24;
		final int COOK_BAR_ICON_U = 3;   // texture position of white arrow icon
		final int COOK_BAR_ICON_V = 169;
		final int COOK_BAR_WIDTH = 86;
		final int COOK_BAR_HEIGHT = 34;

		final int FLAME_XPOS = 82;
		final int FLAME_YPOS = 36;
		final int FLAME_ICON_U = 179;   // texture position of flame icon
		final int FLAME_ICON_V = 3;
		final int FLAME_WIDTH = 12;
		final int FLAME_HEIGHT = 12;
		
		final int POWER_XPOS = 9;
		final int POWER_YPOS = 21;
		final int POWER_ICON_U = 247; // texture position of POWER icon
		final int POWER_ICON_V = 147;
		final int POWER_WIDTH = 8;
		final int POWER_HEIGHT = 45;

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		
		//gui base
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 175, 165);
		
		// get cook progress as a double between 0 and 1
		double cookProgress = tileEntity.fractionOfActivationTimeComplete(0);
		// draw the cook progress bar
		drawTexturedModalRect(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_ICON_U, COOK_BAR_ICON_V, (int)(cookProgress * COOK_BAR_WIDTH), COOK_BAR_HEIGHT);
		
		// draw the fuel remaining bar for each fuel slot flame
		double burnRemaining = tileEntity.fractionOfFuelRemaining();
		int yOffset = (int)((1.0 - burnRemaining) * FLAME_HEIGHT);
		drawTexturedModalRect(guiLeft + FLAME_XPOS, guiTop + FLAME_YPOS + yOffset, FLAME_ICON_U, FLAME_ICON_V + yOffset, FLAME_WIDTH, FLAME_HEIGHT - yOffset);
				
		// draw the fuel remaining bar for the power bar
				double powerRemaining = tileEntity.fractionOfPowerRemaining();
				yOffset = (int) ((1.0 - powerRemaining) * POWER_HEIGHT);
				drawTexturedModalRect(guiLeft + POWER_XPOS, guiTop + POWER_YPOS + yOffset, POWER_ICON_U, POWER_ICON_V + yOffset,
						POWER_WIDTH, POWER_HEIGHT - yOffset);

	}
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY){
		FontRenderer fontRenderer = this.fontRendererObj;
		String name = I18n.format(ModBlocks.disablingPress.getUnlocalizedName() + ".name");
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
		
		List<String> hoveringText = new ArrayList<String>();
		
		// If the mouse is over the progress bar add the progress bar hovering text
		if (isInRect(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_WIDTH, COOK_BAR_HEIGHT, mouseX, mouseY)){
			hoveringText.add("Progress:");
			int cookPercentage =(int)(tileEntity.fractionOfActivationTimeComplete(0) * 100);
			hoveringText.add(cookPercentage + "%");
			}

		// If the mouse is over one of the burn time indicator add the burn time indicator hovering text
		
		if (isInRect(guiLeft + FLAME_XPOS, guiTop + FLAME_YPOS, FLAME_WIDTH, FLAME_HEIGHT, mouseX, mouseY)) {
			hoveringText.add("Fuel Time:");
			hoveringText.add(tileEntity.secondsOfFuelRemaining() + "s");
		}
		
		if (isInRect(guiLeft + POWER_XPOS, guiTop + POWER_YPOS, POWER_WIDTH, POWER_HEIGHT, mouseX,
				mouseY)) {
			hoveringText.add("Energy Stored:");
			int powerPercentage = (int) (tileEntity.fractionOfPowerRemaining() * 100);
			hoveringText.add(powerPercentage + "%");
			hoveringText.add(tileEntity.storage.getEnergyStored()+"/"+tileEntity.storage.getMaxEnergyStored());
			hoveringText.add(tileEntity.energyUse+"rf/t");
		}
		
		// If hoveringText is not empty draw the hovering text
		if (!hoveringText.isEmpty()){
			drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
		}

	}
	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY){
		return ((mouseX >= x && mouseX <= x+xSize) && (mouseY >= y && mouseY <= y+ySize));
	}


	
}
