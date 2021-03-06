package com.jack12324.eop.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.util.Random;

class BlockDustOre extends BlockModOre {

    private final Item drop;
    private final int least_quantity;
    private final int most_quantity;

    public BlockDustOre(Material material, String name, String harvestTool, int harvestLevel, Item drop, int least,
                        int most, float hardness, float resistance, SoundType sound) {
        super(material, name, harvestTool, harvestLevel, hardness, resistance, sound);
        this.drop = drop;
        this.least_quantity = least;
        this.most_quantity = most;

    }

    @Override
    public int damageDropped(IBlockState blockstate) {
        return 0;
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState blockstate, Random random, int fortune) {
        return this.drop;
    }

    @Override
    public int quantityDropped(IBlockState blockstate, int fortune, @Nonnull Random random) {
        if (this.least_quantity >= this.most_quantity)
            return this.least_quantity;
        return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
    }

}
