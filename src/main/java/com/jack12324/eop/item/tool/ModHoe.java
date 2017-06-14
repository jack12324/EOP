package com.jack12324.eop.item.tool;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.item.ItemModelProvider;

import net.minecraft.item.Item;

public class ModHoe extends net.minecraft.item.ItemHoe implements ItemModelProvider {

	private String name;

	public ModHoe(ToolMaterial material, String name) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.name = name;
		super.setCreativeTab(ExtremeOreProcessing.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		ExtremeOreProcessing.proxy.registerItemRenderer(this, 0, name);
	}

}