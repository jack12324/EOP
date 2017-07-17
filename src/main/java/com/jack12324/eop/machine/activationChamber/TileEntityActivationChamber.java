package com.jack12324.eop.machine.activationChamber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.item.Item;

public class TileEntityActivationChamber extends TEPowered {

	public static List<Item> fuel = new ArrayList<Item>(
			Arrays.asList(ModItems.dustFirestone, Item.getItemFromBlock(ModBlocks.blockFirestone)));
	List<Integer> fuelTime = new ArrayList<Integer>(Arrays.asList(400, 4000));

	public TileEntityActivationChamber() {
		super("activation_chamber", new InventorySlotHelper(1, 1, 1, 0, 0));
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
		return RecipeHolder.ACTIVATIONCHAMBERRECIPES;
	}
}