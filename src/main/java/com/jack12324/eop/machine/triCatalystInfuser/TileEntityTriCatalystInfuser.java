package com.jack12324.eop.machine.triCatalystInfuser;

import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.TEFluidProducer;
import com.jack12324.eop.recipe.RecipeHandler;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

import java.util.ArrayList;

public class TileEntityTriCatalystInfuser extends TEFluidProducer {

    public TileEntityTriCatalystInfuser() {
        super("tri_catalyst_infuser", new InventorySlotHelper(3, 0, 0, 0, 0), 4000, 4000);
    }

    @Override
    public ArrayList<EOPRecipe> getRecipeList() {
        return RecipeHolder.TRICATALYSTINFUSERRECIPES;
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

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (this.slotHelper.getUpgradeSlotIndex(0) == index && stack.getItem() != ModItems.speedUpgrade)
            return false;
        if (this.slotHelper.getUpgradeSlotIndex(1) == index && stack.getItem() != ModItems.energyUpgrade)
            return false;

        for (int indexes : this.slotHelper.getIn()) {
            if (index == indexes && (!RecipeHandler.getInItems(this.getRecipeList()).contains(stack.getItem()) || hasFullStackOfItem(stack.getItem())))
                return false;
        }
        return true;
    }

    private boolean hasFullStackOfItem(Item item) {
        ItemStack[] stacks = new ItemStack[this.slotHelper.getInSlotSize()];
        for (int i = 0; i < stacks.length; i++)
            stacks[i] = this.slots.getStackInSlot(this.slotHelper.getInSlotIndex(i));

        for (ItemStack stack : stacks) {
            if (stack.getItem() == item && stack.getCount() >= stack.getItem().getItemStackLimit(stack))
                return true;
        }
        return false;
    }
}
