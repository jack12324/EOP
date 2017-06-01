package com.jack12324.eop.util.compat;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public final class TeslaUtil{

    @CapabilityInject(ITeslaConsumer.class)
    public static Capability<ITeslaConsumer> teslaConsumer;

    @CapabilityInject(ITeslaProducer.class)
    public static Capability<ITeslaProducer> teslaProducer;

    @CapabilityInject(ITeslaHolder.class)
    public static Capability<ITeslaHolder> teslaHolder;

}