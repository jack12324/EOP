package com.jack12324.eop.jei;

import com.jack12324.eop.jei.activationChamber.activationChamberRecipeWrapper;
import com.jack12324.eop.machine.activationChamber.recipeTest;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class wrapperFactory implements IRecipeWrapperFactory {

	@Override
	public IRecipeWrapper getRecipeWrapper(Object recipe) {
		return new activationChamberRecipeWrapper((recipeTest) recipe);
	}

}
