package com.jack12324.eop.recipe;

import com.jack12324.eop.recipe.recipeInterfaces.IBaseRecipe;

import net.minecraft.item.ItemStack;

public class DPRecipe extends BasicRecipe implements IBaseRecipe {

	private ItemStack base;

	public DPRecipe(ItemStack input, ItemStack base, ItemStack output) {
		super(input, output);
		this.base = base;
	}

	@Override
	public ItemStack getBaseStack() {
		return base;
	}

}
