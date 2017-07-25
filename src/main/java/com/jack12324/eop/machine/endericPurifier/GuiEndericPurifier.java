package com.jack12324.eop.machine.endericPurifier;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.gui.GuiBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiEndericPurifier extends GuiBase {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/enderic_purifier.png");
    private static final int[] progressBarVals = {42, // X to start draw
            36, // y to start draw
            6, // x of texture location
            172, // y of texture location
            89, // width
            13// height
    };


    public GuiEndericPurifier(Container inventorySlotsIn, InventoryPlayer playerInv,
                              TileEntityEndericPurifier tileEntity) {
        super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, progressBarVals);

    }

}
