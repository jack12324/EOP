package com.jack12324.eop.machine.dualCatalystInfuser;

import javax.annotation.Nullable;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidRegistry;

public class TileEntityDualCatalystInfuser extends TEFluidProducer {

	private static DualCatalystInfuserRecipes recipes = DualCatalystInfuserRecipes.INSTANCE;

	private String dualCatalystInfuserCustomName = "container.dualCatalystInfuser.name";

	public TileEntityDualCatalystInfuser() {
		super("dualCatalystInfuser",new InventorySlotHelper(2,0, 0, 0,0),recipes,FluidRegistry.WATER,100,4000,InitFluids.fluidStarWater,100,4000);
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
		return this.hasCustomName() ? this.dualCatalystInfuserCustomName : "container.dualCatalystInfuser.name";
	}

	/**
	 * Returns true if this thing is named
	 */
	public boolean hasCustomName() {
		return true;
	}

	public static void registerFixesDualCatalystInfuser(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY,
				new ItemStackDataLists(TileEntityDualCatalystInfuser.class, new String[] { "Items" }));
	}
}
