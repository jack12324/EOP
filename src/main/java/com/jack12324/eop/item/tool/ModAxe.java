package com.jack12324.eop.item.tool;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.item.ItemModelProvider;

import net.minecraft.item.Item;

public class ModAxe extends net.minecraft.item.ItemAxe implements ItemModelProvider {

	private String name;

	public ModAxe(ToolMaterial material, String name) {
		super(material, 8f, -3.1f);
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