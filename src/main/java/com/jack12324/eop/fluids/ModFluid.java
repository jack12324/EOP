package com.jack12324.eop.fluids;

import com.jack12324.eop.ExtremeOreProcessing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

class ModFluid extends Fluid {

    public ModFluid(String fluidName, String textureName) {
        super(fluidName, new ResourceLocation(ExtremeOreProcessing.modID, "blocks/" + textureName + "_still"),
                new ResourceLocation(ExtremeOreProcessing.modID, "blocks/" + textureName + "_flowing"));
    }

    @Override
    public String getUnlocalizedName() {
        return "fluid." + ExtremeOreProcessing.modID + "." + this.unlocalizedName;
    }
}