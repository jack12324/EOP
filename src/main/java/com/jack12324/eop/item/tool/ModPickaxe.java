package com.jack12324.eop.item.tool;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.item.ItemModelProvider;
import net.minecraft.item.Item;

public class ModPickaxe extends net.minecraft.item.ItemPickaxe implements ItemModelProvider {

    private final String name;

    public ModPickaxe(ToolMaterial material, String name) {
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
