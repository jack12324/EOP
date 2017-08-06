package com.jack12324.eop.machine.equalizingSmelter;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.IButtonUse;
import com.jack12324.eop.machine.IOPairs;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;

public class TileEntityEqualizingSmelter extends TEPowered implements IButtonUse, IOPairs {
    private boolean furnaceMode = true;
    private boolean spreadMode = true;
    private boolean oldFurnaceMode = true;
    private boolean oldSpreadMode = true;
    private int dustProgress = 0;
    private int oldDustProgress = 0;
    private static final int DUSTTICK = 4;

    public void incrementSideVal(EnumFacing side) {
        int val;
        int index = this.getSideIndex(side);
        if (index != -1) {
            val = this.getSideVal(side);
            if (val > 2)
                this.sideIO[index] = 0;
            else this.sideIO[index] = val++;
        }
    }

    public TileEntityEqualizingSmelter() {
        super("equalizing_smelter", new InventorySlotHelper(4, 4, 0, 0, 1));
    }

    @Override
    public boolean canUse(int slot) {
        return (super.canUse(slot) && this.getInventory(this.slotHelper.getOtherSlotIndex(0)).getCount() < 64);
    }

    /**
     * returns the progress of earning an extra dust
     */
    double fractionOfDustProgress() {
        if (dustProgress <= 0)
            return 0;
        double fraction = dustProgress / (double) 4;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    public boolean getMode() {
        return furnaceMode;
    }

    boolean getSpreadMode() {
        return spreadMode;
    }

    private void oldModeCheck() {
        if (this.oldFurnaceMode != this.furnaceMode && this.sendUpdateWithInterval())
            this.oldFurnaceMode = this.furnaceMode;
        if (this.oldSpreadMode != this.spreadMode && this.sendUpdateWithInterval())
            this.oldSpreadMode = this.spreadMode;
    }

    @Override
    protected void oldProgressTimeCheck() {
        super.oldProgressTimeCheck();
        if (this.oldDustProgress != this.dustProgress && this.sendUpdateWithInterval())
            this.oldDustProgress = this.dustProgress;
    }

    @Override
    public void onButtonPress(int buttonId) {
        if (buttonId == 53)
            furnaceMode = !furnaceMode;
        else if (buttonId == 57)
            spreadMode = !spreadMode;
        else
            ExtremeOreProcessing.LOGGER.warn(buttonId + " is not a valid button id for " + this.getDisplayedName());
        this.oldModeCheck();
    }

    @Override
    public void readSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        this.furnaceMode = compound.getBoolean("mode");
        this.dustProgress = compound.getInteger("dustProgress");
        this.spreadMode = compound.getBoolean("spreadMode");

        super.readSyncableNBT(compound, shouldSync);
    }

    private void spread() {
        int size = this.slotHelper.getInSlotSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                balanceTwoSlots(this.slotHelper.getInSlotIndex(i), this.slotHelper.getInSlotIndex(j));
            }
        }
    }

    private void balanceTwoSlots(int index1, int index2) {

        ItemStack stack1 = this.slots.getStackInSlot(index1);
        ItemStack stack2 = this.slots.getStackInSlot(index2);
        if (stack1.isItemEqual(stack2) || stack2.isEmpty()) {
            int dif = Math.abs(stack1.getCount() - stack2.getCount());
            if (dif > 1) {
                dif /= 2;
                if (stack1.getCount() > stack2.getCount()) {
                    this.slots.getStackInSlot(index1).shrink(dif);
                    if (stack2.isEmpty())
                        this.slots.setStackInSlot(index2, new ItemStack(stack1.getItem(), dif));
                    else
                        this.slots.getStackInSlot(index2).grow(dif);
                } else {
                    this.slots.getStackInSlot(index2).shrink(dif);
                    if (stack1.isEmpty())
                        this.slots.setStackInSlot(index1, new ItemStack(stack1.getItem(), dif));
                    else
                        this.slots.getStackInSlot(index1).grow(dif);

                }
                markDirty();
            }
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!this.world.isRemote) {
            if (spreadMode)
                spread();
            this.oldModeCheck();
        }
    }

    @Override
    public void useItem(int IOSet) {
        super.useItem(IOSet);
        if (!furnaceMode) {
            dustProgress++;
            if (dustProgress >= DUSTTICK) {
                ItemStack result = new ItemStack(ModItems.dustFirestone);
                ItemStack output = this.getInventory(this.slotHelper.getOtherSlotIndex(0));

                if (output.isEmpty()) {
                    this.setInventory(this.slotHelper.getOtherSlotIndex(0), result.copy());
                } else if (output.getItem() == result.getItem()) {
                    result.setCount(output.getCount() + 1);
                    this.setInventory(this.slotHelper.getOtherSlotIndex(0), result);
                }
                dustProgress = 0;
            }
        }
    }

    @Override
    protected boolean useLogic(int IOSet) {
        boolean result = super.useLogic(IOSet);
        if (!furnaceMode) {
            int prog = this.getInProgressTime(0);
            for (int i = 0; i < 4; i++)
                this.setInProgressTime(i, prog);
        }
        return result;
    }

    @Override
    public void writeSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        compound.setBoolean("mode", furnaceMode);
        compound.setBoolean("spreadMode", spreadMode);
        compound.setInteger("dustProgress", dustProgress);
        super.writeSyncableNBT(compound, shouldSync);

    }

    @Override
    protected void resetTime(int progressBar) {
        if (furnaceMode)
            super.resetTime(progressBar);
        else
            for (int i = 0; i < 4; i++)
                this.setInProgressTime(i, 0);
    }

    @Override
    public ArrayList<EOPRecipe> getRecipeList() {
        return furnaceMode ? RecipeHolder.VANILLAFURNACERECIPES : RecipeHolder.EQUALIZINGSMELTERRECIPES;
    }

    @Override
    public ItemStack[] getCurrentInputStacks(int slot) {
        return new ItemStack[]{this.slots.getStackInSlot(this.slotHelper.getInSlotIndex(slot))};
    }

    @Override
    public int getIONumber() {
        return 4;
    }

    @Override
    public boolean EQSOverride() {
        return !furnaceMode;
    }
}

