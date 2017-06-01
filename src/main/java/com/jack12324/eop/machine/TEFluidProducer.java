package com.jack12324.eop.machine;

import com.jack12324.eop.machine.TETickingMachine.NBTType;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class TEFluidProducer extends TEFluidUser{

	private Fluid outFluid;
	private int fluidProduceAmount;
	private int oldFluidAmount;
	public final FluidTank outTank = new FluidTank(2000) {
		@Override
		public boolean canDrain() {
			return false;
		}

		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return fluid.getFluid() == outFluid;
		}
	};

	public TEFluidProducer(String name, InventorySlotHelper slots, EOPRecipes recipes, Fluid inFluid, int fluidUseAmount,int inTankSize,Fluid outFluid,int fluidProduceAmount, int outTankSize) {
		super(name, slots, recipes,inFluid,fluidUseAmount,inTankSize);
		this.outFluid = inFluid;
		this.fluidProduceAmount = fluidUseAmount;
		this.outTank.setCapacity(outTankSize);

	}

	@Override
	public void writeSyncableNBT(NBTTagCompound compound, NBTType type) {
		this.outTank.writeToNBT(compound);
		NBTTagCompound tag = new NBTTagCompound();
		compound.setTag("outTank", tag);
		super.writeSyncableNBT(compound, type);
	}

	@Override
	public void readSyncableNBT(NBTTagCompound compound, NBTType type) {
		this.outTank.readFromNBT(compound);
		NBTTagCompound tag = compound.getCompoundTag("outTank");
		super.readSyncableNBT(compound, type);
	}

	

	@Override
	public void updateEntity() {
		super.updateEntity();
		if(!world.isRemote){
			this.oldOutFluidCheck();
		}
	}

	protected void oldOutFluidCheck() {
		if (this.oldFluidAmount != this.outTank.getFluidAmount() && this.sendUpdateWithInterval()) {
			this.oldFluidAmount = this.outTank.getFluidAmount();
		}
	}

	@Override
	protected boolean canUse(){
		if(super.canUse()&&((this.outTank.getFluidAmount()+this.fluidProduceAmount)<this.outTank.getCapacity()))
			return true;
		else
			return false;
	}
	

	@Override
	public void useItem() {
		super.useItem();
		outTank.fillInternal(new FluidStack(outFluid,fluidProduceAmount), true);
	}

}
