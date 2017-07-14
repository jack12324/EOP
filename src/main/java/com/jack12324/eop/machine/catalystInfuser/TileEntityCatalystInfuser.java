package com.jack12324.eop.machine.catalystInfuser;

import java.util.ArrayList;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraftforge.fluids.FluidRegistry;

public class TileEntityCatalystInfuser extends TEFluidProducer {


	public TileEntityCatalystInfuser() {
		super("catalyst_infuser", new InventorySlotHelper(1, 0, 0, 0, 0), 4000, 4000);
	}
	
	@Override
	public ArrayList<EOPRecipe> getRecipeList() {
		return RecipeHolder.CATALYSTINFUSERRECIPES;
	}
}
