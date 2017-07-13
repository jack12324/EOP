package com.jack12324.eop.machine.catalystInfuser;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraftforge.fluids.FluidRegistry;

public class TileEntityCatalystInfuser extends TEFluidProducer {


	public TileEntityCatalystInfuser() {
		super("catalyst_infuser", new InventorySlotHelper(1, 0, 0, 0, 0), 4000, 4000);
	}
}
