package com.jack12324.eop.fluids;

import com.google.common.base.Preconditions;
import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

class BlockFluidFlowing extends BlockFluidClassic {

    public BlockFluidFlowing(Fluid fluid, Material material, String unlocalizedName) {
        super(fluid, material);
        setRegistryName(unlocalizedName);
        setUnlocalizedName(unlocalizedName);

        this.displacements.put(this, false);
        this.setCreativeTab(ExtremeOreProcessing.creativeTab);

    }

    @Override
    public boolean canDisplace(IBlockAccess world, BlockPos pos) {
        return !world.getBlockState(pos).getMaterial().isLiquid() && super.canDisplace(world, pos);
    }

    @Override
    public boolean displaceIfPossible(World world, BlockPos pos) {
        return !world.getBlockState(pos).getMaterial().isLiquid() && super.displaceIfPossible(world, pos);
    }
    public Item createItemBlock() {
        final ItemBlock itemBlock = new ItemBlock(this);
        final ResourceLocation registryName = Preconditions.checkNotNull(this.getRegistryName());
        itemBlock.setRegistryName(registryName);
        return itemBlock;
    }

}
