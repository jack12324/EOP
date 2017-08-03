package com.jack12324.eop.machine.starHardener;

import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;

public class TileEntityStarHardener extends TEFluidUser {

    public TileEntityStarHardener() {
        super("star_hardener", new InventorySlotHelper(1, 1, 0, 1, 0), 4000);
    }

    @Override
    public ArrayList<EOPRecipe> getRecipeList() {
        return RecipeHolder.STARHARDENERRECIPES;
    }

    @Override
    protected int getSideIndex(EnumFacing side) {
        int index = super.getSideIndex(side);
        return index > 3 ? 0 : index;
    }
}
