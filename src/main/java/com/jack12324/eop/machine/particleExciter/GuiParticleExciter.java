package com.jack12324.eop.machine.particleExciter;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.gui.GuiBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiParticleExciter extends GuiBase {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/particle_exciter.png");

    public GuiParticleExciter(Container inventorySlotsIn, InventoryPlayer playerInv,
                              TileEntityParticleExciter tileEntity) {
        super(inventorySlotsIn, playerInv, tileEntity, BG_TEXTURE, GuiValues.PARTICLEEXCITER);

    }

}
