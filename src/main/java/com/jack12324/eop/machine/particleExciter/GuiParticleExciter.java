package com.jack12324.eop.machine.particleExciter;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.gui.GuiBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiParticleExciter extends GuiBase {
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/particle_exciter.png");
	private static final int[] progressBarVals = { 43, // X to start draw
			18, // y to start draw
			2, // x of texture location
			169, // y of texture location
			89, // width
			49// height
	};

	public GuiParticleExciter(Container inventorySlotsIn, InventoryPlayer playerInv,
			TileEntityParticleExciter tileEntity) {
		super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, progressBarVals);

	}

}
