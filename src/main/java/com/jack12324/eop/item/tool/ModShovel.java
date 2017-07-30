package com.jack12324.eop.item.tool;

import com.jack12324.eop.ExtremeOreProcessing;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public class ModShovel extends ItemSpade{

    private final String name;

    public ModShovel(ToolMaterial material, String name) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        this.name = name;
        super.setCreativeTab(ExtremeOreProcessing.creativeTab);
    }

    public void registerItemModel() {
        ExtremeOreProcessing.proxy.registerItemRenderer(this, 0, name);
    }

}