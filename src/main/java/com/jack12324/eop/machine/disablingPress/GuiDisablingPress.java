package com.jack12324.eop.machine.disablingPress;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.machine.GuiBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiDisablingPress extends GuiBase {
	private TileEntityDisablingPress tileEntity;
	private InventoryPlayer playerInv;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/disabling_press.png");

	public GuiDisablingPress(Container inventorySlotsIn, InventoryPlayer playerInv,
			TileEntityDisablingPress tileEntity) {
		super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, progressBarVals, fuelVals);
		this.playerInv = playerInv;
		this.tileEntity = tileEntity;

	}

	static int[] progressBarVals = { 45, // X to start draw
			24, // y to start draw
			3, // x of texture location
			169, // y of texture location
			86, // width
			34// height
	};
	static int[] fuelVals = { 82, // X to start draw
			36, // y to start draw
			179, // x of texture location
			3, // y of texture location
			12, // width
			12// height
	};

}
