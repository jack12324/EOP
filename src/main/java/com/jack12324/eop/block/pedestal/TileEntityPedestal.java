package com.jack12324.eop.block.pedestal;

import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.machine.BlockTE;
import com.jack12324.eop.machine.TEInventory;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityPedestal extends TEInventory {

	public TileEntityPedestal() {
		super(new InventorySlotHelper(1,0,0,0,0), "pedestal");
	}

	private Fluid dragonSoul = InitFluids.fluidDragonSoul;
	private int oldFluidAmount;
	private boolean lastActive = false;
	public final FluidTank tank = new FluidTank(1000) {
		@Override
		public boolean canDrain() {
			return true;
		}

		@Override
		public boolean canFillFluidType(FluidStack fluid) {
			return fluid.getFluid() == dragonSoul;
		}
	};

	@Override
	public void writeSyncableNBT(NBTTagCompound compound, NBTType type) {
		this.tank.writeToNBT(compound);
		NBTTagCompound tag = new NBTTagCompound();
		compound.setTag("tank", tag);
		super.writeSyncableNBT(compound, type);
	}

	@Override
	public void readSyncableNBT(NBTTagCompound compound, NBTType type) {
		this.tank.readFromNBT(compound);
		NBTTagCompound tag = compound.getCompoundTag("tank");
		super.readSyncableNBT(compound, type);
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		boolean active = false;
		if (!world.isRemote){
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

	protected boolean canUse() {

		if (this.slots.getStackInSlot(0).isEmpty() || !(this.slots.getStackInSlot(0).getItem() == (Item.getItemFromBlock(Blocks.DRAGON_EGG))) || this.tank.getFluidAmount() >= tank.getCapacity())

			return false;

		else
			return true;
	}

	private int fillTick = 1;

	protected boolean useLogic(boolean canUse) {
		boolean active = false;

		// If fuel is available, keep cooking the item, otherwise start
		// "uncooking" it at double speed
		if (canUse) {
			active = true;
			if (fillTick == 4) {
				this.tank.fillInternal(new FluidStack(InitFluids.fluidDragonSoul, 1), true);
				fillTick = 1;
			}
			else
				fillTick++;
		}

		return active;
	}

	protected void oldFluidCheck() {
		if (this.oldFluidAmount != this.tank.getFluidAmount() && this.sendUpdateWithInterval()) {
			this.oldFluidAmount = this.tank.getFluidAmount();
		}
	}

	@SideOnly(Side.CLIENT)
	public int getTankScaled(int i) {
		return (int) (this.tank.getFluidAmount() * i / (double) this.tank.getCapacity());
	}

}
