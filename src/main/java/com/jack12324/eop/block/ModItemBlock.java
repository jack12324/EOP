package com.jack12324.eop.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ModItemBlock extends ItemBlock {

	public ModItemBlock(Block block) {
		super(block);
		this.setHasSubtypes(false);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return this.getUnlocalizedName();
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

}
