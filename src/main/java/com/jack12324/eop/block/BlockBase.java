package com.jack12324.eop.block;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.item.ItemModelProvider;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class BlockBase extends Block implements ItemModelProvider{
	protected String name;
	
	public BlockBase(Material material, String name) {
		super(material);
		
		this.name = name;
		
		setCreativeTab(ExtremeOreProcessing.creativeTab);
		setUnlocalizedName(name);
		setRegistryName(name);
		setHardness(5f);
		setResistance(30f); 
	}
	
	@Override
	public void registerItemModel(Item itemBlock){
		ExtremeOreProcessing.proxy.registerItemRenderer(itemBlock, 0, name);
	}
	
	@Override
	public BlockBase setCreativeTab(CreativeTabs tab){
		super.setCreativeTab(tab);
		return this;
	}
	

}