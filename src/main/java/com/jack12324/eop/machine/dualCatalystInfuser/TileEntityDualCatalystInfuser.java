package com.jack12324.eop.machine.dualCatalystInfuser;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraftforge.fluids.FluidRegistry;

public class TileEntityDualCatalystInfuser extends TEFluidProducer {

	private static DualCatalystInfuserRecipes recipes = DualCatalystInfuserRecipes.INSTANCE;

	public TileEntityDualCatalystInfuser() {
		super("dual_catalyst_infuser", new InventorySlotHelper(2, 0, 0, 0, 0), recipes, FluidRegistry.WATER, 100, 4000,
				InitFluids.fluidStarWater, 100, 4000);
	}
}
