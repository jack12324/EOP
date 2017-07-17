package com.jack12324.eop.machine.triCatalystInfuser;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.gui.FluidBar;
import com.jack12324.eop.util.gui.GuiBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiTriCatalystInfuser extends GuiBase {
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/tri_catalyst_infuser.png");
	static int[] progressBarVals = { 43, // X to start draw
			18, // y to start draw
			2, // x of texture location
			169, // y of texture location
			88, // width
			49// height
	};
	private TileEntityTriCatalystInfuser tileEntity;
	private InventoryPlayer playerInv;

	private FluidBar fb2;

	final int FLUID_XPOS2 = 151;

	final int FLUID_YPOS2 = 18;

	public GuiTriCatalystInfuser(Container inventorySlotsIn, InventoryPlayer playerInv,
			TileEntityTriCatalystInfuser tileEntity) {
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
		List<String> temp = new ArrayList<String>();
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
