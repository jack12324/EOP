package com.jack12324.eop.machine.endericPurifier;

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

public class TileEntityEndericPurifier extends TEFluidUser {

	private static EndericPurifierRecipes recipes = EndericPurifierRecipes.INSTANCE;

	private String endericPurifierCustomName = "container.endericPurifier.name";

	public TileEntityEndericPurifier() {
		super("endericPurifier",new InventorySlotHelper(1,1, 0, 1,0),recipes,InitFluids.fluidLiquidEnd,100,4000);
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
		return this.hasCustomName() ? this.endericPurifierCustomName : "container.endericPurifier.name";
	}

	/**
	 * Returns true if this thing is named
	 */
	public boolean hasCustomName() {
		return true;
	}

	public static void registerFixesEndericPurifier(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY,
				new ItemStackDataLists(TileEntityEndericPurifier.class, new String[] { "Items" }));
	}
}
