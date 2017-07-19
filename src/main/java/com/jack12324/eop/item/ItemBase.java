package com.jack12324.eop.item;

import com.jack12324.eop.ExtremeOreProcessing;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;

public class ItemBase extends Item implements ItemModelProvider {
	private final String name;

	public ItemBase(String name) {
		this.name = name;
		setCreativeTab(ExtremeOreProcessing.creativeTab);
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	public ItemBase(String name, int maxSize) {
		this(name);
		this.setMaxStackSize(maxSize);
	}

	@Override
	public void registerItemModel(Item item) {
		ExtremeOreProcessing.proxy.registerItemRenderer(item, 0, name);
	}

	@Nonnull
	@Override
	public ItemBase setCreativeTab(@Nonnull CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
