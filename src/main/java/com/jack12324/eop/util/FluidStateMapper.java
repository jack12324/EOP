package com.jack12324.eop.util;

import com.jack12324.eop.ExtremeOreProcessing;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/**
 * (Excerpted from Tinkers' Construct)
 */
@SideOnly(Side.CLIENT)
public class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {

    private final Fluid fluid;
    private final ModelResourceLocation location;

    public FluidStateMapper(Fluid fluid) {
        this.fluid = fluid;

        this.location = new ModelResourceLocation(new ResourceLocation(ExtremeOreProcessing.modID, "fluids"),
                fluid.getName());
    }

    @Nonnull
    @Override
    public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
        return this.location;
    }

    @Nonnull
    @Override
    protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
        return this.location;
    }
}