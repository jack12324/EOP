package com.jack12324.eop.machine.pedestal;

import com.jack12324.eop.machine.BlockTE;
import com.jack12324.eop.machine.TESideIO;
import com.jack12324.eop.recipe.RecipeHandler;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;

public class TileEntityPedestal extends TESideIO {

    private final ArrayList<Fluid> outFluid;
    private int oldFluidAmount;
    private boolean lastActive = false;
    final FluidTank tank = new FluidTank(1000) {
        @Override
        public boolean canDrain() {
            return true;
        }

        @Override
        public boolean canFillFluidType(FluidStack fluid) {
            return false;
        }
    };
    private int fillTick = 1;

    public TileEntityPedestal() {
        super(new InventorySlotHelper(new InventorySlotHelper(1, 0, 0, 0, 0), 2), "pedestal");
        this.outFluid = new ArrayList<>(RecipeHandler.getOutFluids(this.getRecipeList()));
    }

    private ArrayList<EOPRecipe> getRecipeList() {
        return RecipeHolder.PEDESTALRECIPES;
    }

    private boolean canUse() {
        FluidStack result = RecipeHandler.getFluidStackOutput(this.getRecipeList(), new ItemStack[]{this.slots.getStackInSlot(0)}, null, null);

        return result != null && (this.tank.getFluidAmount() <= 0 || result.isFluidEqual(this.tank.getFluid()))
                && this.tank.getFluidAmount() + result.amount <= this.tank.getCapacity();
    }

    private void oldFluidCheck() {
        if (this.oldFluidAmount != this.tank.getFluidAmount() && this.sendUpdateWithInterval()) {
            this.oldFluidAmount = this.tank.getFluidAmount();
            this.markDirty();
        }
    }
    @Override
    public void readSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        slots.deserializeNBT(compound.getCompoundTag("inventory"));

        NBTTagCompound tag = compound.getCompoundTag("tank");
        this.tank.readFromNBT(tag);
        super.readSyncableNBT(compound, shouldSync);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean active;
        if (!world.isRemote) {
            active = this.useLogic(canUse());
            oldFluidCheck();
            oldActiveCheck(active);
        }
    }

    private void oldActiveCheck(boolean active) {
        if (active != this.lastActive) {
            IBlockState currState = this.world.getBlockState(this.pos);
            if (currState.getValue(BlockTE.PROPERTYACTIVE) != (active)) {
                this.world.setBlockState(this.pos, currState.withProperty(BlockTE.PROPERTYACTIVE, active));
            }

            this.lastActive = active;
            markDirty();
        }
    }

    @Override
    public IFluidHandler getFluidHandler(EnumFacing facing) {
        return this.tank;
    }

    private boolean useLogic(boolean canUse) {
        boolean active = false;

        if (canUse) {
            active = true;
            if (fillTick == RecipeHandler.getPedestalSpeed(this.slots.getStackInSlot(0))) {
                FluidStack result = RecipeHandler.getFluidStackOutput(this.getRecipeList(), new ItemStack[]{this.slots.getStackInSlot(0)}, null, null);
                tank.fillInternal(result, true);
                fillTick = 1;
                markDirty();
            } else
                fillTick++;
        }

        return active;
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        for (int indexes : this.slotHelper.getUpgrade()) {
            if (index == indexes)
                return false;
        }

        for (int indexes : this.slotHelper.getIn()) {
            if (index == indexes && !RecipeHandler.getInItems(this.getRecipeList()).contains(stack.getItem()))
                return false;
        }
        return true;
    }


    @Override
    public void writeSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        NBTTagCompound tag = new NBTTagCompound();
        this.tank.writeToNBT(tag);
        compound.setTag("tank", tag);

        compound.setTag("inventory", slots.serializeNBT());

        super.writeSyncableNBT(compound, shouldSync);
    }

    public void incrementSideVal(EnumFacing side) {
        int val;
        int index = this.getSideIndex(side);
        if (index != -1) {
            val = this.getSideVal(side);
            if (val == 1)
                this.sideIO[index] = 4;
            else if (val == 4)
                this.sideIO[index] = 0;
            else this.sideIO[index] = val + 1;
        }
    }

}
