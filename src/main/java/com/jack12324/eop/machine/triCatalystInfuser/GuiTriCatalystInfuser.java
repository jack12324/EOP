package com.jack12324.eop.machine.triCatalystInfuser;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.util.FluidBar;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiTriCatalystInfuser extends GuiContainer {
	private TileEntityTriCatalystInfuser tileEntity;
	private InventoryPlayer playerInv;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/tri_catalyst_infuser.png");
	private FluidBar fb;
	private FluidBar fb2;

	public GuiTriCatalystInfuser(Container inventorySlotsIn, InventoryPlayer playerInv, TileEntityTriCatalystInfuser tileEntity) {
		super(inventorySlotsIn);
		this.playerInv = playerInv;
		this.tileEntity = tileEntity;
	}

	// some [x,y] coordinates of graphical elements
	final int COOK_BAR_XPOS = 43;
	final int COOK_BAR_YPOS = 18;
	final int COOK_BAR_ICON_U = 2; // texture position of white arrow icon
	final int COOK_BAR_ICON_V = 169;
	final int COOK_BAR_WIDTH = 88;
	final int COOK_BAR_HEIGHT = 49;

	final int POWER_XPOS = 9;
	final int POWER_YPOS = 21;
	final int POWER_ICON_U = 247; // texture position of POWER icon
	final int POWER_ICON_V = 147;
	final int POWER_WIDTH = 8;
	final int POWER_HEIGHT = 45;
	
	final int FLUID_XPOS=25;
	final int FLUID_YPOS=18;
	final int FLUID_XPOS2=151;
	final int FLUID_YPOS2=18;

	@Override
    public void initGui(){
        super.initGui();
       this.fb = new FluidBar(this.tileEntity.inTank,guiLeft+FLUID_XPOS, guiTop+FLUID_YPOS);
       this.fb2 =new FluidBar(this.tileEntity.outTank,guiLeft+FLUID_XPOS2, guiTop+FLUID_YPOS2);
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);

		//gui base
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 175, 165);

		// get cook progress as a double between 0 and 1
		double cookProgress = tileEntity.fractionOfActivationTimeComplete(0);
		// draw the cook progress bar
		drawTexturedModalRect(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_ICON_U, COOK_BAR_ICON_V, (int) (cookProgress * COOK_BAR_WIDTH), COOK_BAR_HEIGHT);

		// draw the fuel remaining bar for the power bar
		double powerRemaining = tileEntity.fractionOfPowerRemaining();
		int yOffset = (int) ((1.0 - powerRemaining) * POWER_HEIGHT);
		drawTexturedModalRect(guiLeft + POWER_XPOS, guiTop + POWER_YPOS + yOffset, POWER_ICON_U, POWER_ICON_V + yOffset, POWER_WIDTH, POWER_HEIGHT - yOffset);
		
		//FluidBar fb = new FluidBar(this.tileEntity.inTank,guiLeft+FLUID_XPOS, guiTop+FLUID_YPOS);
		fb.draw();
		fb2.draw();

	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		FontRenderer fontRenderer = this.fontRendererObj;
		String name = I18n.format(ModBlocks.triCatalystInfuser.getUnlocalizedName() + ".name");
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);

		List<String> hoveringText = new ArrayList<String>();

		// If the mouse is over the progress bar add the progress bar hovering text
		if (isInRect(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_WIDTH, COOK_BAR_HEIGHT, mouseX, mouseY)) {
			hoveringText.add("Progress:");
			int cookPercentage = (int) (tileEntity.fractionOfActivationTimeComplete(0) * 100);
			hoveringText.add(cookPercentage + "%");
		}

		

		if (isInRect(guiLeft + POWER_XPOS, guiTop + POWER_YPOS, POWER_WIDTH, POWER_HEIGHT, mouseX, mouseY)) {
			hoveringText.add("Energy Stored:");
			int powerPercentage = (int) (tileEntity.fractionOfPowerRemaining() * 100);
			hoveringText.add(powerPercentage + "%");
			hoveringText.add(tileEntity.storage.getEnergyStored() + "/" + tileEntity.storage.getMaxEnergyStored());
			hoveringText.add(tileEntity.energyUse + "rf/t");
		}
		
		if(hoveringText==null||hoveringText.isEmpty())
			hoveringText = (ArrayList<String>)fb.drawText(mouseX, mouseY);
		if(hoveringText==null||hoveringText.isEmpty())
			hoveringText = (ArrayList<String>)fb2.drawText(mouseX, mouseY);
		

		// If hoveringText is not empty draw the hovering text
		if (hoveringText!=null&&!hoveringText.isEmpty()) {
			drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
		}

	}

	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
		return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
	}

}
