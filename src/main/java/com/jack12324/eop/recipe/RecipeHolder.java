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

	public static void addCatalystInfuserRecipe(ItemStack itemInput, FluidStack inFluid, FluidStack outFluid) {
		ItemStack[] items = { itemInput };
		CATALYSTINFUSERRECIPES.add(new InfuserRecipe(items, inFluid, outFluid));
	}

	public static void addDisablingPressRecipe(ItemStack input, ItemStack output) {
		DISABLINGPRESSRECIPES.add(new BasicRecipe(input, output));
	}

	public static void addDualCatalystInfuserRecipe(ItemStack item1, ItemStack item2, FluidStack inFluid,
			FluidStack outFluid) {
		ItemStack[] items = { item1, item2 };
		DUALCATALYSTINFUSERRECIPES.add(new InfuserRecipe(items, inFluid, outFluid));
	}

	public static void addEndericPurifierRecipe(ItemStack itemInput, ItemStack base, FluidStack inFluid,
			ItemStack itemOutput) {
		ItemStack[] items = { itemInput };
		ENDERICPURIFIERRECIPES.add(new AdvancedRecipe(items, base, inFluid, itemOutput));
	}

	public static void addTriCatalystInfuserRecipe(ItemStack item1, ItemStack item2, ItemStack item3,
			FluidStack inFluid, FluidStack outFluid) {
		ItemStack[] items = { item1, item2, item3 };
		TRICATALYSTINFUSERRECIPES.add(new InfuserRecipe(items, inFluid, outFluid));
	}

}
