package com.jack12324.eop.machine.pedestal;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.GuiValues;
import com.jack12324.eop.util.gui.FluidBar;
import com.jack12324.eop.util.gui.GuiBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class GuiPedestal extends GuiBase {
    private static final ResourceLocation BG_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/pedestal.png");
    private FluidBar fb;
    private final TileEntityPedestal tileEntity;

    public GuiPedestal(Container container, InventoryPlayer playerInv, TileEntityPedestal tileEntity) {
        super(container, playerInv, tileEntity, BG_TEXTURE, GuiValues.DEFAULT);
        this.tileEntity = tileEntity;
    }

    @Override
    protected void drawOther() {
        fb.draw();
    }

    @Override
    protected List<String> otherText(int mouseX, int mouseY) {
        return fb.drawText(mouseX, mouseY);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.fb = new FluidBar(this.tileEntity.tank, guiLeft + 79, guiTop + 21);
    }
}
