package com.jack12324.eop.util;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.machine.ISideIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class EOPGuiButton extends GuiButton {
    private EnumFacing side;
    private ISideIO tileEntity;
    protected static final ResourceLocation BUTTON_TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID, "textures/gui/buttons.png");

    public EOPGuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText, EnumFacing side, TileEntity tileEntity) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.side = side;
        this.tileEntity = ((ISideIO) tileEntity);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(BUTTON_TEXTURE);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            int i = tileEntity.getSideVal(side);

            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

            this.drawTexturedModalRect(this.x, this.y, 0 + (i * 16), 0, 16, 16);
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }

    public String getVal() {
        int val = tileEntity.getSideVal(side);
        String text = "";
        switch (val) {
            case 0:
                text = I18n.format("eop.text.d");
                break;
            case 1:
                text = I18n.format("eop.text.ii");
                break;
            case 2:
                text = I18n.format("eop.text.io");
                break;
            case 3:
                text = I18n.format("eop.text.fi");
                break;
            case 4:
                text = I18n.format("eop.text.fo");
                break;

        }
        return text;
    }


}
