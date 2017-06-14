package com.jack12324.eop.item;

import com.jack12324.eop.ExtremeOreProcessing;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class ModArmor extends net.minecraft.item.ItemArmor implements ItemModelProvider {

	private String name;

	public ModArmor(ArmorMaterial material, EntityEquipmentSlot slot, String name) {
		super(material, 0, slot);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.name = name;
		setCreativeTab(ExtremeOreProcessing.creativeTab);
	}

	@Override
	public void registerItemModel(Item item) {
		ExtremeOreProcessing.proxy.registerItemRenderer(this, 0, name);

	}

}
