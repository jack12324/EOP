package com.jack12324.eop.machine.starHardener;

import javax.annotation.Nullable;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

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
