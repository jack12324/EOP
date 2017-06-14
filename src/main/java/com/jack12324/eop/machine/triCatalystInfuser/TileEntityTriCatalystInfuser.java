package com.jack12324.eop.machine.triCatalystInfuser;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.util.InventorySlotHelper;

public class TileEntityTriCatalystInfuser extends TEFluidProducer {

	private static TriCatalystInfuserRecipes recipes = TriCatalystInfuserRecipes.INSTANCE;

	public TileEntityTriCatalystInfuser() {
		super("dual_catalyst_infuser", new InventorySlotHelper(3, 0, 0, 0, 0), recipes, InitFluids.fluidDragonSoul, 100,
				4000, InitFluids.fluidLiquidEnd, 100, 4000);
	}
}
