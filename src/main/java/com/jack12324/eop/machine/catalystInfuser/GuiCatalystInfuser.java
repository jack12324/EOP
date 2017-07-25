package com.jack12324.eop.machine.catalystInfuser;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.gui.FluidBar;
import com.jack12324.eop.util.gui.GuiBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiCatalystInfuser extends GuiBase {
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/catalyst_infuser.png");
	private static final int[] progressBarVals = { 63, // X to start draw
			35, // y to start draw
			6, // x of texture location
			172, // y of texture location
			68, // width
			12// height
	};
	private final TileEntityCatalystInfuser tileEntity;

	private FluidBar fb2;

    public GuiCatalystInfuser(Container inventorySlotsIn, InventoryPlayer playerInv,
			TileEntityCatalystInfuser tileEntity) {
		super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, progressBarVals);
		this.tileEntity = tileEntity;
	}

	@Override
	protected void drawFluidBar() {
		super.drawFluidBar();
		fb2.draw();
	}

	@Override
	protected ArrayList<String> fluidText(int mouseX, int mouseY) {
		List<String> temp;
		temp = fb2.drawText(mouseX, mouseY);
		if (temp != null && !temp.isEmpty())
			return (ArrayList<String>) temp;
		else
			return super.fluidText(mouseX, mouseY);

	}

	@Override
	public void initGui() {
		super.initGui();
        int FLUID_YPOS2 = 18;
        int FLUID_XPOS2 = 133;
        this.fb2 = new FluidBar(this.tileEntity.outTank, guiLeft + FLUID_XPOS2, guiTop + FLUID_YPOS2);
	}

}
