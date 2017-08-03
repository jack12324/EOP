package com.jack12324.eop.machine.dualCatalystInfuser;

import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;

public class TileEntityDualCatalystInfuser extends TEFluidProducer {

    public TileEntityDualCatalystInfuser() {
        super("dual_catalyst_infuser", new InventorySlotHelper(2, 0, 0, 0, 0), 4000, 4000);
    }

    @Override
    public ArrayList<EOPRecipe> getRecipeList() {
        return RecipeHolder.DUALCATALYSTINFUSERRECIPES;
    }

    @Override
    protected int getSideIndex(EnumFacing side) {
        int index = super.getSideIndex(side);
        return index == 2 ? 3 : index;
    }
}
