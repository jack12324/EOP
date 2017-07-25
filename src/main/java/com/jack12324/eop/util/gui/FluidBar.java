package com.jack12324.eop.util.gui;

import com.jack12324.eop.ExtremeOreProcessing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import java.util.ArrayList;
import java.util.List;

public class FluidBar extends Gui {
    private static final ResourceLocation TEXTURE = new ResourceLocation(ExtremeOreProcessing.modID,
            "textures/gui/fluid_bar.png");
    private final int x;
    private final int y;
    private final FluidTank tank;
    private final int HEIGHT = 49;
    private final int WIDTH = 18;

    public FluidBar(FluidTank tank, int x, int y) {
        this.x = x;
        this.y = y;
        this.tank = tank;

    }

    public void draw() {
        Minecraft mc = Minecraft.getMinecraft();
        mc.renderEngine.bindTexture(TEXTURE);
        drawTexturedModalRect(this.x, this.y, 0, 49, WIDTH, HEIGHT);
        // Draw fluid
        FluidStack stack = this.tank.getFluid();
        Fluid fluid = stack == null ? null : stack.getFluid();
        if (fluid != null && fluid.getStill() != null) {
            TextureAtlasSprite fluidTexture = mc.getTextureMapBlocks().getTextureExtry(fluid.getStill().toString());
            mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            int fluidHeight = (int) ((HEIGHT - 2) * (this.tank.getFluidAmount() / (double) this.tank.getCapacity()));
            System.out.println(fluidHeight);

            if (fluidTexture != null)
                drawTexturedModalRect(this.x, this.y + ((HEIGHT - 1) - fluidHeight), fluidTexture, WIDTH, fluidHeight);

            // Draw lines over fluid

            mc.renderEngine.bindTexture(TEXTURE);
            drawTexturedModalRect(this.x, this.y, 0, 0, WIDTH, HEIGHT);
        }
    }

    public ArrayList<String> drawText(int mouseX, int mouseY) {
        List<String> hoveringText = new ArrayList<>();
        if (isInRect(this.x, this.y, WIDTH, HEIGHT, mouseX, mouseY) && this.tank.getFluid() != null) {

            hoveringText.add(this.tank.getFluid().getLocalizedName());
            hoveringText.add(this.tank.getFluidAmount() + "/" + (this.tank.getCapacity() + " mB"));
            return (ArrayList<String>) hoveringText;
        }
        return null;
    }

    private boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }
}
