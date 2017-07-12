package com.jack12324.eop.recipe;

import java.util.ArrayList;

import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

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
		ACTIVATIONCHAMBERRECIPES.add(new BasicRecipe(input, output));
	}

	public static void addDisablingPressRecipe(ItemStack input, ItemStack output) {
		DISABLINGPRESSRECIPES.add(new BasicRecipe(input, output));
	}

	public static void addCatalystInfuserRecipe(ItemStack itemInput, FluidStack inFluid, FluidStack outFluid) {
		CATALYSTINFUSERRECIPES.add(new InfuserRecipe(itemInput, inFluid, outFluid));
	}

}
