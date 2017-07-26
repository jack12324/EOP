package com.jack12324.eop.machine.triCatalystInfuser;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.gui.GuiBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiTriCatalystInfuser extends GuiBase {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/tri_catalyst_infuser.png");

    public GuiTriCatalystInfuser(Container inventorySlotsIn, InventoryPlayer playerInv,
                                 TileEntityTriCatalystInfuser tileEntity) {
        super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, GuiValues.TRICATALYSTINFUSER);
    }

}
