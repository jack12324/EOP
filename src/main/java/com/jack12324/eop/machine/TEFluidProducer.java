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

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (!world.isRemote) {
			this.oldOutFluidCheck();

		}

	}

	protected void oldOutFluidCheck() {
		if (this.oldOutFluidAmount != this.outTank.getFluidAmount() && this.sendUpdateWithInterval()) {
			System.out.println("outfluid");
			this.oldOutFluidAmount = this.outTank.getFluidAmount();
		}
	}

	@Override
	public boolean fluidCanUse() {
		FluidStack result = RecipeHandler.getFluidOutput(this.getRecipeList(), getInputSlotItemStacks(),
				this.getBaseSlotItemStacks(), this.inTank.getFluid());

		if (result == null || (this.outTank.getFluidAmount() > 0 && !result.isFluidEqual(this.outTank.getFluid()))
				|| this.outTank.getFluidAmount() + result.amount > this.outTank.getCapacity()) {
			return false;
		} else
			return true;
	}

	@Override
	public void useFluid(ItemStack[] input) {
		super.useFluid(input);
		FluidStack result = RecipeHandler.getFluidOutput(this.getRecipeList(), input, this.inTank.getFluid());
		outTank.fillInternal(result, true);
	}

	public void useFluid(ItemStack[] input, ItemStack[] base) {
		super.useFluid(input, base);
		FluidStack result = RecipeHandler.getFluidOutput(this.getRecipeList(), input, base, this.inTank.getFluid());
		outTank.fillInternal(result, true);
	}

	@Override
	public IFluidHandler getFluidHandler(EnumFacing facing) {
		return this.handlerMap;
	}

}
