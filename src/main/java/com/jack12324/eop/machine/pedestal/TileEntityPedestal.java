package com.jack12324.eop.machine.pedestal;

import com.jack12324.eop.machine.BlockTE;
import com.jack12324.eop.machine.TEInventory;
import com.jack12324.eop.recipe.RecipeHandler;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import java.util.ArrayList;

public class TileEntityPedestal extends TEInventory {

    private final ArrayList<Fluid> outFluid;
    private int oldFluidAmount;
    private boolean lastActive = false;
    public final FluidTank tank = new FluidTank(1000) {
        @Override
        public boolean canDrain() {
            return true;
        }

        @Override
        public boolean canFillFluidType(FluidStack fluid) {
            return outFluid.contains(fluid.getFluid());
        }
    };
    private int fillTick = 1;

    public TileEntityPedestal() {
        super(new InventorySlotHelper(1, 0, 0, 0, 0), "pedestal");
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
        }
    }

    @Override
    public void readSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        this.tank.readFromNBT(compound);
        NBTTagCompound tag = compound.getCompoundTag("tank");
        this.tank.readFromNBT(tag);
        super.readSyncableNBT(compound, shouldSync);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean active = false;
        if (!world.isRemote) {
            active = this.useLogic(canUse());
            oldFluidCheck();
        }

        if (active != this.lastActive) {
            IBlockState currState = this.world.getBlockState(this.pos);
            if (currState.getValue(BlockTE.PROPERTYACTIVE) != active) {
                this.world.setBlockState(this.pos, currState.withProperty(BlockTE.PROPERTYACTIVE, active));
            }

            this.lastActive = active;

        }
    }

    private boolean useLogic(boolean canUse) {
        boolean active = false;

        if (canUse) {
            active = true;
            if (fillTick == RecipeHandler.getPedestalSpeed(this.slots.getStackInSlot(0))) {
                FluidStack result = RecipeHandler.getFluidStackOutput(this.getRecipeList(), new ItemStack[]{this.slots.getStackInSlot(0)}, null, null);
                tank.fillInternal(result, true);
                fillTick = 1;
            } else
                fillTick++;
        }

        return active;
    }

    @Override
    public void writeSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        this.tank.writeToNBT(compound);
        NBTTagCompound tag = new NBTTagCompound();
        compound.setTag("tank", tag);
        super.writeSyncableNBT(compound, shouldSync);
    }

}
