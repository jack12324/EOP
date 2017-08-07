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

    public void incrementSideVal(EnumFacing side) {
        int val;
        int index = this.getSideIndex(side);
        if (index != -1) {
            val = this.getSideVal(side);
            if (val == 4)
                this.sideIO[index] = 0;
            else if (val == 1)
                this.sideIO[index] = 3;
            else this.sideIO[index] = val + 1;
        }
    }
}
