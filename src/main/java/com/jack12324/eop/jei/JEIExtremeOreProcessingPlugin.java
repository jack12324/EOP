package com.jack12324.eop.jei;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.jack12324.eop.jei.activationChamber.activationChamberRecipeCategory;
import com.jack12324.eop.jei.activationChamber.activationChamberRecipeWrapper;
import com.jack12324.eop.machine.activationChamber.ActivationChamberRecipes;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.ActivationChamberRecipe;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

@JEIPlugin
public class JEIExtremeOreProcessingPlugin implements IModPlugin {

	public static IJeiHelpers jeiHelpers;
	public static IModRegistry modRegistry;

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerIngredients(IModIngredientRegistration registry) {
		// TODO Auto-generated method stub

	}

	Map<Class, EOPRecipeCategory> categories = new LinkedHashMap<>();

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		jeiHelpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

		categories.put(ActivationChamberRecipe.class, new activationChamberRecipeCategory(guiHelper));

		registry.addRecipeCategories(categories.values().toArray(new EOPRecipeCategory[categories.size()]));

	}

	@Override
	public void register(IModRegistry registryIn) {
		modRegistry = registryIn;
		for (EOPRecipeCategory<Object, IRecipeWrapper> cat : categories.values()) {
			cat.addCatalysts(registryIn);
			modRegistry.handleRecipes(cat.getRecipeClass(), cat, cat.getRecipeCategoryUid());
		}
		modRegistry.addRecipes(RecipeHolder.ACTIVATIONCHAMBERRECIPES, "eop.activation_chamber");

	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		// TODO Auto-generated method stub

	}

}
