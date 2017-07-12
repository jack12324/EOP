package com.jack12324.eop.machine;

import java.util.ArrayList;

import com.jack12324.eop.recipe.RecipeHandler;
import com.jack12324.eop.recipe.recipeInterfaces.IFluidInRecipe;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TEFluidUser extends TEPowered {
	private ArrayList<Fluid> inFluid;
	private int oldInFluidAmount;
	public final FluidTank inTank = new FluidTank(2000) {
		@Override
		public boolean canDrain() {
			return false;
		}

		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return inFluid.contains(fluid);
		}
	};

	public TEFluidUser(String name, InventorySlotHelper slots, EOPRecipes recipes, Fluid inFluid, int fluidUseAmount) {
		this(name, slots, recipes, inFluid, fluidUseAmount, 2000);

	}

	public TEFluidUser(String name, InventorySlotHelper slots, EOPRecipes recipes, Fluid inFluid, int fluidUseAmount,
			int tankSize) {
		super(name, slots, recipes);
		this.inFluid = new ArrayList<Fluid>(RecipeHandler.getInFluids(this.getRecipeList()));
		this.inTank.setCapacity(tankSize);

	}

	@Override
	public void writeSyncableNBT(NBTTagCompound compound, NBTType type) {
		NBTTagCompound tag = new NBTTagCompound();
		this.inTank.writeToNBT(tag);
		compound.setTag("inTank", tag);
		super.writeSyncableNBT(compound, type);
		System.out.println("TEFU write");
	}

	@Override
	public void readSyncableNBT(NBTTagCompound compound, NBTType type) {
		NBTTagCompound tag = compound.getCompoundTag("inTank");
		this.inTank.readFromNBT(tag);
		super.readSyncableNBT(compound, type);
		System.out.println("TEFU read");
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!this.world.isRemote) {
			oldInFluidCheck();
		}
	}

	protected void oldInFluidCheck() {
		if (this.oldInFluidAmount != this.inTank.getFluidAmount() && this.sendUpdateWithInterval()) {
			System.out.println("influid");
			this.oldInFluidAmount = this.inTank.getFluidAmount();
		}
	}

	@Override
	protected boolean canUse() {
		if (super.canUse() && this.inTank.getFluidAmount() > this.fluidUseAmount) {
			return true;

		} else {
			return false;
		}
	}

	@Override
	public boolean fluidCanUse() {
		ItemStack result = RecipeHandler.getItemOutput(this.getRecipeList(), getInputSlotItemStacks(),
				this.inTank.getFluid());

		if (result == null || result.isEmpty()) {
			return false;
		} else if (this instanceof TEFluidProducer) {
			return true;
		} else {
			return getOutSlot(result) == -1 ? false : true;
		}
	}

	@Override
	public IFluidHandler getFluidHandler(EnumFacing facing) {
		return this.inTank;
	}

	@Override
	public void useFluid(ItemStack[] input) {
		inTank.drainInternal(
				RecipeHandler.getInFluidAmountUsed(this.getRecipeList(), input, inTank.getFluid().getFluid()), true);
	}

	@SideOnly(Side.CLIENT)
	public int getTankScaled(int i) {
		return (int) (this.inTank.getFluidAmount() * i / (double) this.inTank.getCapacity());
	}

}
