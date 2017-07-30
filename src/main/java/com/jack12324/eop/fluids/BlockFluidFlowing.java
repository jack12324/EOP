package com.jack12324.eop.fluids;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;

class BlockFluidFlowing extends BlockFluidClassic {

    private final String name;

    public BlockFluidFlowing(Fluid fluid, Material material, String unlocalizedName) {
        super(fluid, material);
        this.name = unlocalizedName;
        this.displacements.put(this, false);
        this.setCreativeTab(ExtremeOreProcessing.creativeTab);


        setUnlocalizedName(name);
        setRegistryName(name);
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
        return new ItemBlock(this);
    }
    public void registerItemModel() {
        ExtremeOreProcessing.proxy.registerItemRenderer( Item.getItemFromBlock(this), 0, name);
    }

}
