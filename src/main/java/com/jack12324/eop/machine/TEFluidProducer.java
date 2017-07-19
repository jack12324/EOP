package com.jack12324.eop.machine;

import java.util.ArrayList;

import com.jack12324.eop.recipe.RecipeHandler;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerFluidMap;

public abstract class TEFluidProducer extends TEFluidUser {
	private final FluidHandlerFluidMap handlerMap;
	private final ArrayList<Fluid> outFluid;
	private int oldOutFluidAmount;
	public final FluidTank outTank = new FluidTank(2000) {
		@Override
		public boolean canDrain() {
			return true;
		}

		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return outFluid.contains(fluid.getFluid());
		}
	};

	protected TEFluidProducer(String name, InventorySlotHelper slots, int inTankSize, int outTankSize) {
		super(name, slots, inTankSize);
		this.outFluid = new ArrayList<>(RecipeHandler.getOutFluids(this.getRecipeList()));
		this.outTank.setCapacity(outTankSize);
		this.handlerMap = new FluidHandlerFluidMap();

		for (Fluid fluid : this.inFluid)
			this.handlerMap.addHandler(fluid, this.inTank);
		for (Fluid fluid : this.outFluid)
			this.handlerMap.addHandler(fluid, this.outTank);

	}

	@Override
	public boolean canUse() {
		if (this.inTank.getFluid() == null)
			return false;
		FluidStack result = RecipeHandler.getFluidOutput(this.getRecipeList(), getInputSlotItemStacks(),
				this.inTank.getFluid());

		return result != null && (this.outTank.getFluidAmount() <= 0 || result.isFluidEqual(this.outTank.getFluid()))
				&& this.outTank.getFluidAmount() + result.amount <= this.outTank.getCapacity();
	}

	@Override
	public IFluidHandler getFluidHandler(EnumFacing facing) {
		return this.handlerMap;
	}

	private void oldOutFluidCheck() {
		if (this.oldOutFluidAmount != this.outTank.getFluidAmount() && this.sendUpdateWithInterval()) {
			System.out.println("outfluid");
			this.oldOutFluidAmount = this.outTank.getFluidAmount();
		}
	}

	@Override
	public void readSyncableNBT(NBTTagCompound compound, NBTType type) {
		NBTTagCompound tag = compound.getCompoundTag("outTank");
		this.outTank.readFromNBT(tag);
		super.readSyncableNBT(compound, type);
		System.out.println("TEFP read");
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!world.isRemote) {
			this.oldOutFluidCheck();

		}

	}

	@Override
	public void useFluid(ItemStack[] input) {
		super.useFluid(input);
		FluidStack result = RecipeHandler.getFluidOutput(this.getRecipeList(), input, this.inTank.getFluid());
		outTank.fillInternal(result, true);
	}

	@Override
	public void useFluid(ItemStack[] input, ItemStack base) {
		super.useFluid(input, base);
		FluidStack result = RecipeHandler.getFluidOutput(this.getRecipeList(), input, base, this.inTank.getFluid());
		outTank.fillInternal(result, true);
	}

	@Override
	public void writeSyncableNBT(NBTTagCompound compound, NBTType type) {
		NBTTagCompound tag = new NBTTagCompound();
		this.outTank.writeToNBT(tag);
		compound.setTag("outTank", tag);
		super.writeSyncableNBT(compound, type);
		System.out.println("TEFP write");
	}

}
