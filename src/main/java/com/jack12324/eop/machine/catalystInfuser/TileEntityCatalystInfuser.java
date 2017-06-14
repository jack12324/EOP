package com.jack12324.eop.machine.catalystInfuser;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraftforge.fluids.FluidRegistry;

public class TileEntityCatalystInfuser extends TEFluidProducer {

	private static CatalystInfuserRecipes recipes = CatalystInfuserRecipes.INSTANCE;

	public TileEntityCatalystInfuser() {
		super("catalystInfuser", new InventorySlotHelper(1, 0, 0, 0, 0), recipes, FluidRegistry.LAVA, 100, 4000,
				InitFluids.fluidScreamingLava, 100, 4000);
	}
}
