package com.jack12324.eop.item;

import com.jack12324.eop.ExtremeOreProcessing;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements ItemModelProvider{
	protected String name;
	
	public ItemBase(String name){
		this.name = name;
		setCreativeTab(ExtremeOreProcessing.creativeTab);
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	@Override
	public void registerItemModel(Item item) {
		ExtremeOreProcessing.proxy.registerItemRenderer(item, 0, name);
	}
	
	@Override
	public ItemBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
