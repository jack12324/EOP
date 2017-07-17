package com.jack12324.eop.jei.activationChamber;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.machine.activationChamber.TileEntityActivationChamber;
import com.jack12324.eop.recipe.BasicRecipe;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class activationChamberRecipeWrapper extends BlankRecipeWrapper {

	private final ItemStack input;
	private final ItemStack output;

	public activationChamberRecipeWrapper(BasicRecipe recipe) {
		this.input = recipe.getInputStack();
		this.output = recipe.getOutputStack();

	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		super.drawInfo(minecraft, recipeWidth, recipeHeight, mouseX, mouseY);

	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		List<ItemStack> list = new ArrayList<ItemStack>();
		list.add(input);
		List<ItemStack> fuel = new ArrayList<ItemStack>();
		for (Item item : TileEntityActivationChamber.fuel) {
			fuel.add(new ItemStack(item));
		}

		List<List<ItemStack>> finalList = new ArrayList<List<ItemStack>>();
		finalList.add(list);
		finalList.add(fuel);

		ingredients.setInputLists(ItemStack.class, finalList);
		ingredients.setOutput(ItemStack.class, output);

	}

}
