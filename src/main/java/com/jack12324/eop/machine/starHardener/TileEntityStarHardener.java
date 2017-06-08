package com.jack12324.eop.machine.starHardener;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

public class TileEntityStarHardener extends TEFluidUser {

	private static StarHardenerRecipes recipes = StarHardenerRecipes.INSTANCE;

	private String starHardenerCustomName = "container.starHardener.name";

	public TileEntityStarHardener() {
		super("starHardener",new InventorySlotHelper(1,1, 0, 1,0),recipes,InitFluids.fluidStarWater,100,4000);
	}
	


	public static void registerFixesStarHardener(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY,
				new ItemStackDataLists(TileEntityStarHardener.class, new String[] { "Items" }));
	}
}
