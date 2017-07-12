package com.jack12324.eop.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class RecipeHolder {

	public static final ArrayList<EOPRecipe> ACTIVATIONCHAMBERRECIPES = new ArrayList<EOPRecipe>();
	public static final ArrayList<EOPRecipe> DISABLINGPRESSRECIPES = new ArrayList<EOPRecipe>();

	public static final ArrayList<EOPRecipe> CATALYSTINFUSERRECIPES = new ArrayList<EOPRecipe>();
	public static final ArrayList<EOPRecipe> DUALCATALYSTINFUSERRECIPES = new ArrayList<EOPRecipe>();
	public static final ArrayList<EOPRecipe> TRICATALYSTINFUSERRECIPES = new ArrayList<EOPRecipe>();

	public static final ArrayList<EOPRecipe> ENDERICPURIFIERRECIPES = new ArrayList<EOPRecipe>();
	public static final ArrayList<EOPRecipe> PARTICLEEXCITERRECIPES = new ArrayList<EOPRecipe>();
	public static final ArrayList<EOPRecipe> STARHARDENERRECIPES = new ArrayList<EOPRecipe>();

	public static final ArrayList<EOPRecipe> EQUALIZINGSMELTERRECIPES = new ArrayList<EOPRecipe>();

	public static final ArrayList<EOPRecipe> PEDESTALRECIPES = new ArrayList<EOPRecipe>();

	public static void addActivationChamberRecipe(ItemStack input, ItemStack output) {
		addActivationChamberRecipe(input, output);
	}

	public static void addDisablingPressRecipe(ItemStack input, ItemStack output) {
		addDisablingPressRecipe(input, output);
	}

}
