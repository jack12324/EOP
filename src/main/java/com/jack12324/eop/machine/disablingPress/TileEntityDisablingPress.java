package com.jack12324.eop.machine.disablingPress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class TileEntityDisablingPress extends TEPowered {

	public static List<Item> fuel = new ArrayList<Item>(
			Arrays.asList(Item.getItemFromBlock(Blocks.BROWN_MUSHROOM), Item.getItemFromBlock(Blocks.RED_MUSHROOM)));
	static List<Integer> fuelTime = new ArrayList<Integer>(Arrays.asList(800, 800));

	public TileEntityDisablingPress() {
		super("disabling_press", new InventorySlotHelper(1, 1, 1, 1, 0));
	}

	@Override
	public Item getFuel(int i) {
		return fuel.get(i);
	}

	@Override
	public int getFuelSize() {
		return fuel.size();
	}

	@Override
	public int getFuelTime(int i) {
		return fuelTime.get(i);
	}

	@Override
	public ArrayList<EOPRecipe> getRecipeList() {
		return RecipeHolder.DISABLINGPRESSRECIPES;
	}

}
