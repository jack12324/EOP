package com.jack12324.eop.machine.triCatalystInfuser;

import javax.annotation.Nullable;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityTriCatalystInfuser extends TEFluidProducer {

	private static TriCatalystInfuserRecipes recipes = TriCatalystInfuserRecipes.INSTANCE;

	private String TriCatalystInfuserCustomName = "container.TriCatalystInfuser.name";

	public TileEntityTriCatalystInfuser() {
		super("dualCatalystInfuser",new InventorySlotHelper(3,0, 0, 0,0),recipes,InitFluids.fluidDragonSoul,100,4000,InitFluids.fluidLiquidEnd,100,4000);
	}
	

	
	
	
	
	/** standard code to look up what the human-readable name is */
	@Nullable
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName())
				: new TextComponentTranslation(this.getName());
	}

	/**
	 * Get the name of this object. For players this returns their username
	 */
	public String getName() {
		return this.hasCustomName() ? this.TriCatalystInfuserCustomName : "container.TriCatalystInfuser.name";
	}

	/**
	 * Returns true if this thing is named
	 */
	public boolean hasCustomName() {
		return true;
	}

	public static void registerFixesTriCatalystInfuser(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY,
				new ItemStackDataLists(TileEntityTriCatalystInfuser.class, new String[] { "Items" }));
	}
}
