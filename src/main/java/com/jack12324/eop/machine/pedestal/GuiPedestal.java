package com.jack12324.eop.machine.pedestal;

import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.util.gui.FluidBar;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiPedestal extends GuiContainer {
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/pedestal.png");
	private final InventoryPlayer playerInv;
	private FluidBar fb;
	private final TileEntityPedestal tileEntity;

    public GuiPedestal(Container container, InventoryPlayer playerInv, TileEntityPedestal tileEntity) {
		super(container);
		this.tileEntity = tileEntity;
		this.playerInv = playerInv;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BG_TEXTURE);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);

		fb.draw();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = I18n.format(ModBlocks.pedestal.getUnlocalizedName() + ".name");
		fontRendererObj.drawString(name, xSize / 2 - fontRendererObj.getStringWidth(name) / 2, 6, 0x404040);
		fontRendererObj.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);

		List<String> hoveringText;
		hoveringText = fb.drawText(mouseX, mouseY);

		// If hoveringText is not empty draw the hovering text
		if (hoveringText != null && !hoveringText.isEmpty()) {
			drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRendererObj);
		}
	}

	@Override
	public void initGui() {
		super.initGui();
        int FLUID_YPOS = 21;
        int FLUID_XPOS = 79;
        this.fb = new FluidBar(this.tileEntity.tank, guiLeft + FLUID_XPOS, guiTop + FLUID_YPOS);
	}
}
