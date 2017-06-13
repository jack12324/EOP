package com.jack12324.eop.machine.starHardener;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.machine.GuiBase;
import com.jack12324.eop.util.FluidBar;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiStarHardener extends GuiBase {
	private TileEntityStarHardener tileEntity;
	private InventoryPlayer playerInv;

	public GuiStarHardener(Container inventorySlotsIn, InventoryPlayer playerInv, TileEntityStarHardener tileEntity) {
		super(inventorySlotsIn, playerInv, tileEntity,"textures/gui/star_hardener.png",175,165, progressBarVals,25,18);
		this.playerInv = playerInv;
		this.tileEntity = tileEntity;
		
	}
	static int[] progressBarVals= 
		{		64,//X to start draw
				27,//y to start draw
				4, //x of texture location
				171,//y of texture location
				62, //width
				31//height	
				};

	
}
