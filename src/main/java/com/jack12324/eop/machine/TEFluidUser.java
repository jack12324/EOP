package com.jack12324.eop.machine;

import com.jack12324.eop.recipe.RecipeHandler;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;

public abstract class TEFluidUser extends TEPowered {
    final ArrayList<Fluid> inFluid;
    private int oldInFluidAmount;
    public final FluidTank inTank = new FluidTank(2000) {
        @Override
        public boolean canDrain() {
            return false;
        }

        @Override
        public boolean canFillFluidType(FluidStack fluid) {
            return inFluid.contains(fluid.getFluid());
        }
    };

    protected TEFluidUser(String name, InventorySlotHelper slots, int tankSize) {
        super(name, slots);
        this.inFluid = new ArrayList<>(RecipeHandler.getInFluids(this.getRecipeList()));
        this.inTank.setCapacity(tankSize);

    }

    @Override
    protected FluidStack getInFluid() {
        return this.inTank.getFluid();
    }


    @Override
    public boolean canUse(int IOSet) {
        if (this.inTank.getFluid() == null)
            return false;
        ItemStack result = RecipeHandler.getItemStackOutput(this.getRecipeList(), getInputSlotItemStacks(IOSet), this.getBase(),
                this.inTank.getFluid());

        return result != null && !result.isEmpty() && getOutSlot(result) != -1;
    }

    @Override
    public IFluidHandler getFluidHandler(EnumFacing facing) {
        return this.inTank;
    }

    private void oldInFluidCheck() {
        if (this.oldInFluidAmount != this.inTank.getFluidAmount() && this.sendUpdateWithInterval()) {
            this.oldInFluidAmount = this.inTank.getFluidAmount();
            markDirty();
        }
    }

    @Override
    public void readSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        NBTTagCompound tag = compound.getCompoundTag("inTank");
        this.inTank.readFromNBT(tag);
        super.readSyncableNBT(compound, shouldSync);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!this.world.isRemote) {
            oldInFluidCheck();
        }
    }

    @Override
    void useFluid(ItemStack[] input, ItemStack base) {
        if (inTank.getFluid() != null) {
            int amt = RecipeHandler.getInFluidAmountUsed(this.getRecipeList(), input, base, inTank.getFluid().getFluid());
            if (amt != -1)
                inTank.drainInternal(amt, true);
        }

    }

    @Override
    public void writeSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        NBTTagCompound tag = new NBTTagCompound();
        this.inTank.writeToNBT(tag);
        compound.setTag("inTank", tag);
        super.writeSyncableNBT(compound, shouldSync);
    }

    ItemStack getResult(ItemStack[] input) {
        return RecipeHandler.getItemStackOutput(this.getRecipeList(), input, getBase(), inTank.getFluid());
    }

}
