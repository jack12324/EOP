package com.jack12324.eop.config;

import com.jack12324.eop.ExtremeOreProcessing;
import net.minecraftforge.common.config.Config;

@Config(modid = ExtremeOreProcessing.modID)
@Config.LangKey("eop.config.title")
public class EOPConfig {

    public static final Machines machines = new Machines();

    @Config.Comment("How often ticking tile entities will send a packet from server to client, operation occurs every X tick(s)")
    @Config.RangeInt(min = 1, max = 100)
    public static int updateTick = 5;


    public static class Machines {
        @Config.Comment("The base number of ticks a machine takes to produce an output")
        @Config.RangeInt(min = 1)
        public int baseTicksNeeded = 200;

        @Config.Comment("The base amount of energy in rf any powered machine will use per tick")
        @Config.RangeDouble(min = 1)
        public double baseEnergyPerTick = 50;

        @Config.Comment("How many equalizing operations need to happen before the equalizing smelter produces its extra output, 0 to disable this feature completely")
        @Config.RangeInt(min = 0)
        public int equalizingSmelterExtraOutputSpeed = 4;
    }

}
