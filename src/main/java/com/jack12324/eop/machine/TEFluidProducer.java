package com.jack12324.eop.machine;

import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerFluidMap;

public abstract class TEFluidProducer extends TEFluidUser {
	private final FluidHandlerFluidMap handlerMap;
	private Fluid outFluid;
	private int fluidProduceAmount;
	private int oldOutFluidAmount;
	public final FluidTank outTank = new FluidTank(2000) {
		@Override
		public boolean canDrain() {
			return true;
		}

		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return fluid.getFluid() == outFluid;
		}
	};

	public TEFluidProducer(String name, InventorySlotHelper slots, EOPRecipes recipes, Fluid inFluid,
			int fluidUseAmount, int inTankSize, Fluid outFluid, int fluidProduceAmount, int outTankSize) {
		super(name, slots, recipes, inFluid, fluidUseAmount, inTankSize);
		this.outFluid = outFluid;
		this.fluidProduceAmount = fluidUseAmount;
		this.outTank.setCapacity(outTankSize);
		this.handlerMap = new FluidHandlerFluidMap();
		this.handlerMap.addHandler(inFluid, this.inTank);
		this.handlerMap.addHandler(outFluid, this.outTank);

	}

	@Override
	public void writeSyncableNBT(NBTTagCompound compound, NBTType type) {
		NBTTagCompound tag = new NBTTagCompound();
		this.outTank.writeToNBT(tag);
		compound.setTag("outTank", tag);
		super.writeSyncableNBT(compound, type);
		System.out.println("TEFP write");
	}

	@Override
	public void readSyncableNBT(NBTTagCompound compound, NBTType type) {
		NBTTagCompound tag = compound.getCompoundTag("outTank");
		this.outTank.readFromNBT(tag);
		super.readSyncableNBT(compound, type);
		System.out.println("TEFP read");
	}

	private int counter = 0;

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!world.isRemote) {
			this.oldOutFluidCheck();
			if (counter % 20 == 0)
				System.out
						.println("Server    " + this.inTank.getFluidAmount() + "    " + this.outTank.getFluidAmount());
		}
		if (world.isRemote && counter % 20 == 0)
			System.out.println("Client    " + this.inTank.getFluidAmount() + "    " + this.outTank.getFluidAmount());
		counter++;
	}

	protected void oldOutFluidCheck() {
		if (this.oldOutFluidAmount != this.outTank.getFluidAmount() && this.sendUpdateWithInterval()) {
			System.out.println("outfluid");
			this.oldOutFluidAmount = this.outTank.getFluidAmount();
		}
	}

	@Override
	protected boolean canUse() {
		if (super.canUse() && ((this.outTank.getFluidAmount() + this.fluidProduceAmount) < this.outTank.getCapacity()))
			return true;
		else
			return false;
	}

	@Override
	public IFluidHandler getFluidHandler(EnumFacing facing) {
		return this.handlerMap;
	}

	@Override
	public void useItem() {
		super.useItem();
		outTank.fillInternal(new FluidStack(outFluid, fluidProduceAmount), true);
	}

}
