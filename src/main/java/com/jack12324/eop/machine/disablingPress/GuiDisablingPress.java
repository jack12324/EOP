package com.jack12324.eop.machine.disablingPress;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.gui.GuiBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiDisablingPress extends GuiBase {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/disabling_press.png");


    public GuiDisablingPress(Container inventorySlotsIn, InventoryPlayer playerInv,
                             TileEntityDisablingPress tileEntity) {
        super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, GuiValues.DISABLINGPRESS);

    }

}
