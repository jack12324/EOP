package com.jack12324.eop.item;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class ItemPeaSeed extends ItemSeeds implements ItemModelProvider {

	public ItemPeaSeed() {
		super(ModBlocks.cropPea, Blocks.FARMLAND);
		setUnlocalizedName("pea_seed");
		setRegistryName("pea_seed");
	}

	@Override
	public void registerItemModel(Item item) {
		ExtremeOreProcessing.proxy.registerItemRenderer(item, 0, "pea_seed");
	}
}