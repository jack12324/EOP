package com.jack12324.eop.item.tool;

import com.jack12324.eop.ExtremeOreProcessing;
import net.minecraft.item.Item;

public class ModAxe extends net.minecraft.item.ItemAxe {

    private final String name;

    public ModAxe(ToolMaterial material, String name) {
        super(material, 8f, -3.1f);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
        super.setCreativeTab(ExtremeOreProcessing.creativeTab);
    }

    public void registerItemModel() {
        ExtremeOreProcessing.proxy.registerItemRenderer(this, 0, name);
    }

}