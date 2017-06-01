package com.jack12324.eop.machine.disablingPress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityDisablingPress extends TEPowered{

	private String disablingPressCustomName = "container.disablingPress.name";
	private static DisablingPressRecipes recipes = DisablingPressRecipes.INSTANCE;

	static List<Item> fuel = new ArrayList<Item>(Arrays.asList(Item.getItemFromBlock(Blocks.BROWN_MUSHROOM),Item.getItemFromBlock(Blocks.RED_MUSHROOM)));
	static List<Integer> fuelTime = new ArrayList<Integer>(Arrays.asList(800,800));

	public TileEntityDisablingPress() {
		super("disablingPress",new InventorySlotHelper(1,1, 1, 1,0),recipes);
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
		return this.hasCustomName() ? this.disablingPressCustomName : "container.disablingPress.name";
	}

	/**
	 * Returns true if this thing is named
	 */
	public boolean hasCustomName() {
		return true;
	}

	public static void registerFixesDisablingPress(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY,
				new ItemStackDataLists(TileEntityDisablingPress.class, new String[] { "Items" }));
	}

	

	


	

}
