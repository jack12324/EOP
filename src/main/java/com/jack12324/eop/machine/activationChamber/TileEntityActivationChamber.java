package com.jack12324.eop.machine.activationChamber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.item.Item;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityActivationChamber extends TEPowered  {
	
	private String activationChamberCustomName = "container.activationChamber.name";
	private static ActivationChamberRecipes recipes = ActivationChamberRecipes.INSTANCE;

	List<Item> fuel = new ArrayList<Item>(Arrays.asList(ModItems.dustFirestone, Item.getItemFromBlock(ModBlocks.blockFirestone)));
	List<Integer> fuelTime = new ArrayList<Integer>(Arrays.asList(400,4000));

	public TileEntityActivationChamber() {
		super("activation_chamber",new InventorySlotHelper(1,1, 1, 0,0),recipes);
	}

	@Override
	public Item getFuel(int i){
		return fuel.get(i);
	}
	@Override
	public int getFuelSize(){
		return fuel.size();
	}
	public int getFuelTime(int i){
		return fuelTime.get(i)	;
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
		return this.hasCustomName() ? this.activationChamberCustomName : "container.activationChamber.name";
	}

	/**
	 * Returns true if this thing is named
	 */
	public boolean hasCustomName() {
		return true;
	}

	public static void registerFixesActivationChamber(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY,
				new ItemStackDataLists(TileEntityActivationChamber.class, new String[] { "Items" }));
	}

	

	


	

}