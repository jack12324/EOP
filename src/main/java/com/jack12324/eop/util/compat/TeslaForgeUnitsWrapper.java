package com.jack12324.eop.util.compat;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraftforge.energy.IEnergyStorage;

//From Actually Additions mod by Ellpeck
public class TeslaForgeUnitsWrapper implements ITeslaProducer, ITeslaHolder, ITeslaConsumer {

    private final IEnergyStorage storage;

    public TeslaForgeUnitsWrapper(IEnergyStorage storage) {
        this.storage = storage;
    }

    @Override
    public long getCapacity() {
        return this.storage.getMaxEnergyStored();
    }

    @Override
    public long getStoredPower() {
        return this.storage.getEnergyStored();
    }

    @Override
    public long givePower(long power, boolean simulated) {
        return this.storage.receiveEnergy((int) power, simulated);
    }

    @Override
    public long takePower(long power, boolean simulated) {
        return this.storage.extractEnergy((int) power, simulated);
    }
}