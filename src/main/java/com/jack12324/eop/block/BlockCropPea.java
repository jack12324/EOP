package com.jack12324.eop.block;

import com.jack12324.eop.item.ModItems;

import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockCropPea extends BlockCrops {

	public BlockCropPea() {
		setUnlocalizedName("crop_pea");
		setRegistryName("crop_pea");
	}

	@Override
	protected Item getCrop() {
		return ModItems.pea;
	}

	@Override
	protected Item getSeed() {
		return ModItems.peaSeed;
	}

}
