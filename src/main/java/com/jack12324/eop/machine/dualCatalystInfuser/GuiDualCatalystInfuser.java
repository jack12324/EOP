package com.jack12324.eop.machine.dualCatalystInfuser;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.gui.FluidBar;
import com.jack12324.eop.util.gui.GuiBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiDualCatalystInfuser extends GuiBase {
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/dual_catalyst_infuser.png");
	static int[] progressBarVals = { 63, // X to start draw
			24, // y to start draw
			3, // x of texture location
			170, // y of texture location
			86, // width
			35// height
	};
	private TileEntityDualCatalystInfuser tileEntity;
	private InventoryPlayer playerInv;

	private FluidBar fb2;

	final int FLUID_XPOS2 = 151;
	final int FLUID_YPOS2 = 18;

	public GuiDualCatalystInfuser(Container inventorySlotsIn, InventoryPlayer playerInv,
			TileEntityDualCatalystInfuser tileEntity) {
		super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, progressBarVals);
		this.playerInv = playerInv;
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
		this.fb2 = new FluidBar(this.tileEntity.outTank, guiLeft + FLUID_XPOS2, guiTop + FLUID_YPOS2);
	}

}
