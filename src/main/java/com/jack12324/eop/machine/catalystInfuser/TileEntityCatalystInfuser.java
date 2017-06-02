package com.jack12324.eop.machine.catalystInfuser;

import javax.annotation.Nullable;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.machine.particleExciter.TileEntityParticleExciter;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class TileEntityCatalystInfuser extends TEFluidProducer {

	private static CatalystInfuserRecipes recipes = CatalystInfuserRecipes.INSTANCE;

	public TileEntityCatalystInfuser() {
	super("catalystInfuser",new InventorySlotHelper(1,0, 0, 0,0),recipes,FluidRegistry.LAVA,100,4000,InitFluids.fluidScreamingLava,100,4000);
	}

	public static void registerFixesCatalystInfuser(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY,
				new ItemStackDataLists(TileEntityCatalystInfuser.class, new String[] { "Items" }));
	}
}
