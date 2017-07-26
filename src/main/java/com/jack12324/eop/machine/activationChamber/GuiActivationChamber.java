package com.jack12324.eop.machine.activationChamber;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.gui.GuiBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiActivationChamber extends GuiBase {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/activation_chamber.png");


    public GuiActivationChamber(Container inventorySlotsIn, InventoryPlayer playerInv,
                                TileEntityActivationChamber tileEntity) {
        super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, GuiValues.ACTIVATIONCHAMBER);

    }

}
