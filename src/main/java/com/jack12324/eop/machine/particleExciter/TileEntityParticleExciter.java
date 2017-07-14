package com.jack12324.eop.machine.particleExciter;

import java.util.ArrayList;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;

public class TileEntityParticleExciter extends TEFluidUser {


	public TileEntityParticleExciter() {
		super("particle_exciter", new InventorySlotHelper(1, 1, 0, 1, 0), 4000);
	}
	
	@Override
	public ArrayList<EOPRecipe> getRecipeList() {
		return RecipeHolder.PARTICLEEXCITERRECIPES;
	}
}
