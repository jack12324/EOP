package com.jack12324.eop.machine.triCatalystInfuser;

import java.util.ArrayList;

import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;

public class TileEntityTriCatalystInfuser extends TEFluidProducer {

	public TileEntityTriCatalystInfuser() {
		super("tri_catalyst_infuser", new InventorySlotHelper(3, 0, 0, 0, 0), 4000, 4000);
	}

	@Override
	public ArrayList<EOPRecipe> getRecipeList() {
		return RecipeHolder.TRICATALYSTINFUSERRECIPES;
	}
}
