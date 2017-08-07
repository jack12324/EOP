package com.jack12324.eop.machine.catalystInfuser;

import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;

public class TileEntityCatalystInfuser extends TEFluidProducer {

    public TileEntityCatalystInfuser() {
        super("catalyst_infuser", new InventorySlotHelper(1, 0, 0, 0, 0), 4000, 4000);
    }

    @Override
    public ArrayList<EOPRecipe> getRecipeList() {
        return RecipeHolder.CATALYSTINFUSERRECIPES;
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
