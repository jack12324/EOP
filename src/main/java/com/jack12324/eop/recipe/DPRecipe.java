package com.jack12324.eop.recipe;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IBaseRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneInputRecipe;
import com.jack12324.eop.recipe.recipeInterfaces.IOneOutput;

import net.minecraft.item.ItemStack;

public class DPRecipe extends BasicRecipe implements IBaseRecipe{

	
	private ItemStack base;
	
	public DPRecipe(ItemStack input, ItemStack base, ItemStack output){
		super(input, output);
	}
	

	@Override
	public ItemStack getBaseStack() {
		return base;
	}

}
