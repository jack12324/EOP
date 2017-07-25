package com.jack12324.eop.machine.endericPurifier;

import com.jack12324.eop.machine.TEFluidUser;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;

import java.util.ArrayList;

public class TileEntityEndericPurifier extends TEFluidUser {

    public TileEntityEndericPurifier() {
        super("enderic_purifier", new InventorySlotHelper(1, 1, 0, 1, 0), 4000);
    }

    @Override
    public ArrayList<EOPRecipe> getRecipeList() {
        return RecipeHolder.ENDERICPURIFIERRECIPES;
    }
}
