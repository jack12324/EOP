package com.jack12324.eop.machine.disablingPress;

import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileEntityDisablingPress extends TEPowered {

    public static final List<Item> fuel = new ArrayList<>(
            Arrays.asList(Item.getItemFromBlock(Blocks.BROWN_MUSHROOM), Item.getItemFromBlock(Blocks.RED_MUSHROOM)));
    private static final List<Integer> fuelTime = new ArrayList<>(Arrays.asList(800, 800));

    public TileEntityDisablingPress() {
        super("disabling_press", new InventorySlotHelper(1, 1, 1, 1, 0));
    }

    @Override
    public Item getFuel(int i) {
        return fuel.get(i);
    }

    @Override
    public int getFuelSize() {
        return fuel.size();
    }

    @Override
    public int getFuelTime(int i) {
        return fuelTime.get(i);
    }

    @Override
    public ArrayList<EOPRecipe> getRecipeList() {
        return RecipeHolder.DISABLINGPRESSRECIPES;
    }

    public void incrementSideVal(EnumFacing side) {
        int val;
        int index = this.getSideIndex(side);
        if (index != -1) {
            val = this.getSideVal(side);
            if (val >= 2)
                this.sideIO[index] = 0;
            else this.sideIO[index] = val + 1;
        }
    }

}
