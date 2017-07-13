package com.jack12324.eop.machine;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

public class EOPEnergyStorage extends EnergyStorage {

	public EOPEnergyStorage(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
	}

	@Override
	public int extractEnergy(int maxExtract, boolean simulate) {
		if (!this.canExtract()) {
			return 0;
		}
		int energy = this.getEnergyStored();

		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
		if (!simulate) {
			this.setEnergyStored(energy - energyExtracted);
		}
		return energyExtracted;
	}

	public int extractEnergyInternal(int maxExtract, boolean simulate) {
		int before = this.maxExtract;
		this.maxExtract = Integer.MAX_VALUE;

		int toReturn = this.extractEnergy(maxExtract, simulate);

		this.maxExtract = before;
		return toReturn;
	}

	public void readFromNBT(NBTTagCompound compound) {
		this.setEnergyStored(compound.getInteger("Energy"));
	}

	@Override
	public int receiveEnergy(int maxReceive, boolean simulate) {
		if (!this.canReceive()) {
			return 0;
		}
		int energy = this.getEnergyStored();

		int energyReceived = Math.min(this.capacity - energy, Math.min(this.maxReceive, maxReceive));
		if (!simulate) {
			this.setEnergyStored(energy + energyReceived);
		}

		return energyReceived;
	}

	public int receiveEnergyInternal(int maxReceive, boolean simulate) {
		int before = this.maxReceive;
		this.maxReceive = Integer.MAX_VALUE;

		int toReturn = this.receiveEnergy(maxReceive, simulate);

		this.maxReceive = before;
		return toReturn;
	}

	public void setEnergyStored(int energy) {
		this.energy = energy;
	}

	public void writeToNBT(NBTTagCompound compound) {
		compound.setInteger("Energy", this.getEnergyStored());
	}
}
