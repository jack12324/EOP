package com.jack12324.eop.block;

import com.jack12324.eop.item.ItemOreDictionary;

import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class BlockOreDict extends BlockBase implements ItemOreDictionary {
	private final String oreName;

	public BlockOreDict(String name, String oreName) {
		super(Material.ROCK, name);
		this.oreName = oreName;
		setHardness(5f);
		setResistance(30f);

	}

	// Ore Dictionary ore
	public BlockOreDict(String name, String oreName, int harvestLevel) {
		super(Material.ROCK, name);
		this.oreName = oreName;
		setHarvestLevel("pickaxe", harvestLevel);
		setHardness(3f);
		setResistance(15f);

	}

	@Override
	public void initOreDict() {
		OreDictionary.registerOre(oreName, this);
	}

}
