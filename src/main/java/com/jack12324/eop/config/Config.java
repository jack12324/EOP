package com.jack12324.eop.config;

import com.jack12324.eop.ExtremeOreProcessing;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config {
    private static final String CATEGORY_GENERAL = "general";

    public static int baseTicksNeeded = 200;
    public static double baseEnergyPerTick = 50;
    public static int updateTick = 5;
    public static int equalizingSmelterExtraOutputSpeed = 4;

    public static void readConfig() {
        Configuration cfg = ExtremeOreProcessing.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            ExtremeOreProcessing.LOGGER.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        baseTicksNeeded = cfg.getInt("baseTicksNeeded", CATEGORY_GENERAL, baseTicksNeeded, 1, Integer.MAX_VALUE, "The base number of ticks a machine takes to produce an output");
        baseEnergyPerTick = cfg.getFloat("baseEnergyPerTick", CATEGORY_GENERAL, (float) baseEnergyPerTick, 1, Float.MAX_VALUE, "The base amount of energy in rf any powered machine will use per tick");
        updateTick = cfg.getInt("updateTick", CATEGORY_GENERAL, updateTick, 1, Integer.MAX_VALUE, "How often ticking tile entities will send a packet from server to client, operation occurs every X tick(s)");
        equalizingSmelterExtraOutputSpeed = cfg.getInt("equalizingSmelterExtraOutputSpeed", CATEGORY_GENERAL, equalizingSmelterExtraOutputSpeed, 0, Integer.MAX_VALUE, "How many equalizing operations need to happen before the equalizing smelter produces its extra output, 0 to disable this feature completely");
    }
}
