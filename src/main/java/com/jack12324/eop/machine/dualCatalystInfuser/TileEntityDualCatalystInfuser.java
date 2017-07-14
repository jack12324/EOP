package com.jack12324.eop.machine.dualCatalystInfuser;

import java.util.ArrayList;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraftforge.fluids.FluidRegistry;

public class TileEntityDualCatalystInfuser extends TEFluidProducer {


	public TileEntityDualCatalystInfuser() {
		super("dual_catalyst_infuser", new InventorySlotHelper(2, 0, 0, 0, 0), 4000, 4000);
	}
	
	@Override
	public ArrayList<EOPRecipe> getRecipeList() {
		return RecipeHolder.DUALCATALYSTINFUSERRECIPES;
	}
}
