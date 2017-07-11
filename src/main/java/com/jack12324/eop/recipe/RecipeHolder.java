package com.jack12324.eop.recipe;

import java.util.ArrayList;

import com.jack12324.eop.machine.activationChamber.recipeTest;

import net.minecraft.item.ItemStack;

public class RecipeHolder {

	public static final ArrayList<recipeTest> ACTIVATIONRECIPES = new ArrayList<recipeTest>();
	
	 public static void addTestActivationRecipe(ItemStack input, ItemStack output){
		 ACTIVATIONRECIPES.add(new recipeTest(input, output));
	    }
}
