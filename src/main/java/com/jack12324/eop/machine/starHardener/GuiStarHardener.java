package com.jack12324.eop.machine.starHardener;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.gui.GuiBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiStarHardener extends GuiBase {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/star_hardener.png");


    public GuiStarHardener(Container inventorySlotsIn, InventoryPlayer playerInv, TileEntityStarHardener tileEntity) {
        super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, GuiValues.STARHARDENER);

    }

}
