package com.jack12324.eop.client;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.item.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class EOPTab extends CreativeTabs {

	public EOPTab() {
		super(ExtremeOreProcessing.modID);
		setBackgroundImageName("item_search.png");
	}
	
	@Override
	public ItemStack getTabIconItem(){
		return new ItemStack(ModItems.ingotTungsten);
	}
	
	@Override
	public boolean hasSearchBar(){
		return true;
	}
}
