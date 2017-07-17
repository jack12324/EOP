package com.jack12324.eop.machine.activationChamber;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.gui.GuiBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiActivationChamber extends GuiBase {
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/activation_chamber.png");
	static int[] progressBarVals = { 49, // X to start draw
			30, // y to start draw
			3, // x of texture location
			169, // y of texture location
			77, // width
			15// height
	};
	static int[] fuelVals = { 74, // X to start draw
			47, // y to start draw
			178, // x of texture location
			2, // y of texture location
			3, // width
			20// height
	};

	private TileEntityActivationChamber tileEntity;

	private InventoryPlayer playerInv;

	public GuiActivationChamber(Container inventorySlotsIn, InventoryPlayer playerInv,
			TileEntityActivationChamber tileEntity) {
		super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, progressBarVals, fuelVals);
		this.playerInv = playerInv;
		this.tileEntity = tileEntity;

	}

}
