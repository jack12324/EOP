package com.jack12324.eop.util;

import com.jack12324.eop.machine.TEPowered;

public class UpgradeHelper {

	public static float fractionUpgrades(TEPowered te, Upgrade type) {
		return (float) te.getUpgrade(type) / (float) type.getMax();
	}

	public static double getEnergyPerTick(TEPowered te, double baseEnergyPerTick) {
		return baseEnergyPerTick
				* Math.pow(10, 2 * fractionUpgrades(te, Upgrade.SPEED) - fractionUpgrades(te, Upgrade.ENERGY));
	}

	public static double getTicks(TEPowered te, double baseTicksNeeded) {
		return (int) (baseTicksNeeded * Math.pow(10, -fractionUpgrades(te, Upgrade.SPEED)));
	}

}
