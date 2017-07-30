package com.jack12324.eop.block;

import com.google.common.base.Preconditions;
import com.jack12324.eop.ExtremeOreProcessing;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class BlockBase extends Block {
    private final String name;

    public BlockBase(Material material, String name) {
        super(material);

        this.name = name;

        setCreativeTab(ExtremeOreProcessing.creativeTab);
        setUnlocalizedName(name);
        setRegistryName(name);
        setHardness(5f);
        setResistance(30f);
    }

    public void registerItemModel() {
        ExtremeOreProcessing.proxy.registerItemRenderer( Item.getItemFromBlock(this), 0, name);
    }

    public Item createItemBlock() {
        final ItemBlock itemBlock = new ItemBlock(this);
        final ResourceLocation registryName = Preconditions.checkNotNull(this.getRegistryName());
        itemBlock.setRegistryName(registryName);
        return itemBlock;
    }

    @Nonnull
    @Override
    public BlockBase setCreativeTab(@Nonnull CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

}