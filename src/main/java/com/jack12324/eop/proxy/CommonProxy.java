package com.jack12324.eop.proxy;

import net.minecraft.item.Item;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void registerItemRenderer(Item itemBase, int meta, String name) {
		// TODO Auto-generated method stud
	}

	public String localize(String unlocalized, Object... args) {
		return I18n.translateToLocalFormatted(unlocalized, args);
	}

	public void preInit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub

	}

}
