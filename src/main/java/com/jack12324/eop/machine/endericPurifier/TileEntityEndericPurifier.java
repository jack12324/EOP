package com.jack12324.eop.machine.endericPurifier;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.util.InventorySlotHelper;

public class TileEntityEndericPurifier extends TEFluidUser {

	private static EndericPurifierRecipes recipes = EndericPurifierRecipes.INSTANCE;

	public TileEntityEndericPurifier() {
		super("enderic_purifier", new InventorySlotHelper(1, 1, 0, 1, 0), recipes, InitFluids.fluidLiquidEnd, 100,
				4000);
	}
}
