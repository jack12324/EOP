package com.jack12324.eop.machine.particleExciter;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.util.InventorySlotHelper;

public class TileEntityParticleExciter extends TEFluidUser {

	private static ParticleExciterRecipes recipes = ParticleExciterRecipes.INSTANCE;

	public TileEntityParticleExciter() {
		super("particle_exciter", new InventorySlotHelper(1, 1, 0, 1, 0), recipes, InitFluids.fluidScreamingLava, 100,
				4000);
	}
}
