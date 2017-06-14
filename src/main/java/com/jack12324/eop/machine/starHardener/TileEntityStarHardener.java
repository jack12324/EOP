package com.jack12324.eop.machine.starHardener;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.util.InventorySlotHelper;

public class TileEntityStarHardener extends TEFluidUser {

	private static StarHardenerRecipes recipes = StarHardenerRecipes.INSTANCE;

	public TileEntityStarHardener() {
		super("star_hardener", new InventorySlotHelper(1, 1, 0, 1, 0), recipes, InitFluids.fluidStarWater, 100, 4000);
	}

}
