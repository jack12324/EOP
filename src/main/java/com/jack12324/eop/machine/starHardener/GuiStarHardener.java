package com.jack12324.eop.machine.starHardener;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.machine.GuiBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiStarHardener extends GuiBase {
	private TileEntityStarHardener tileEntity;
	private InventoryPlayer playerInv;
	private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
			"textures/gui/star_hardener.png");

	public GuiStarHardener(Container inventorySlotsIn, InventoryPlayer playerInv, TileEntityStarHardener tileEntity) {
		super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, progressBarVals);
		this.playerInv = playerInv;
		this.tileEntity = tileEntity;

	}

	static int[] progressBarVals = { 64, // X to start draw
			27, // y to start draw
			4, // x of texture location
			171, // y of texture location
			62, // width
			31// height
	};

}
