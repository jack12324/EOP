package com.jack12324.eop.machine.dualCatalystInfuser;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.gui.GuiBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiDualCatalystInfuser extends GuiBase {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/dual_catalyst_infuser.png");

    public GuiDualCatalystInfuser(Container inventorySlotsIn, InventoryPlayer playerInv,
                                  TileEntityDualCatalystInfuser tileEntity) {
        super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, GuiValues.DUALCATALYSTINFUSER);
    }

}
