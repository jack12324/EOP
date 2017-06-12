package com.jack12324.eop.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockModOre extends BlockBase  {
	
	
	
	//non ore dict with specific level
	public BlockModOre(String name, int harvestLevel){
		super(Material.ROCK, name);
		
		setHarvestLevel("pickaxe",harvestLevel);
		setHardness(3f);
		setResistance(15f);
	}
	//full control constructor
	public BlockModOre(Material material, String name, String harvestTool, int harvestLevel,float hardness, float resistance, SoundType sound){
		super(material, name);
		
		setSoundType(sound);
		setHarvestLevel(harvestTool,harvestLevel);
		setHardness(hardness);
		setResistance(resistance);
	}
	
	
	@Override
	public BlockModOre setCreativeTab(CreativeTabs tab){
		super.setCreativeTab(tab);
		return this;
	}
	
	
	
	
	
}
