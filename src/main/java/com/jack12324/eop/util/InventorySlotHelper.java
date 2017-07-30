package com.jack12324.eop.util;

public class InventorySlotHelper {
    private int[] in;
    private int[] out;
    private int[] fuel;
    private int[] base;
    private final int[] other;
    private int[] upgrade;

    public InventorySlotHelper(int in, int out, int fuel, int base, int other) {
        int totalSlots = 0;

        this.setIn(new int[in]);
        for (int i = 0; i < in; i++) {
            this.getIn()[i] = totalSlots;
            totalSlots++;
        }
        this.setOut(new int[out]);
        for (int i = 0; i < out; i++) {
            this.getOut()[i] = totalSlots;
            totalSlots++;
        }
        this.setFuel(new int[fuel]);
        for (int i = 0; i < fuel; i++) {
            this.getFuel()[i] = totalSlots;
            totalSlots++;
        }
        this.setBase(new int[base]);
        for (int i = 0; i < base; i++) {
            this.getBase()[i] = totalSlots;
            totalSlots++;
        }
        this.other = new int[other];
        for (int i = 0; i < other; i++) {
            this.other[i] = totalSlots;
            totalSlots++;
        }
        this.upgrade = new int[0];

    }

    public InventorySlotHelper(InventorySlotHelper slots, int upgrade) {
        this(slots.getInSlotSize(), slots.getOutSlotSize(), slots.getFuelSlotSize(), slots.getBaseSlotSize(),
                slots.getOtherSlotSize());
        this.upgrade = new int[upgrade];
        for (int i = 0; i < upgrade; i++) {
            this.upgrade[i] = this.getTotalSize() - this.getUpgradeSlotSize() + i;
        }
    }

    public int[] getBase() {
        return base;
    }

    public int getBaseSlotIndex(int i) {
        return getBase()[i];
    }

    public int getBaseSlotSize() {
        return getBase().length;
    }

    public int[] getFuel() {
        return fuel;
    }

    public int getFuelSlotIndex(int i) {
        return getFuel()[i];
    }

    public int getFuelSlotSize() {
        return getFuel().length;
    }

    public int[] getIn() {
        return in;
    }

    public int getInSlotIndex(int i) {
        return getIn()[i];
    }

    public int getInSlotSize() {
        return getIn().length;
    }

    public int getOtherSlotIndex(int i) {
        return other[i];
    }

    private int getOtherSlotSize() {
        return other.length;
    }

    public int[] getOut() {
        return out;
    }

    public int getOutSlotIndex(int i) {
        return getOut()[i];
    }

    public int getOutSlotSize() {
        return getOut().length;
    }

    public int getTotalSize() {
        return getBaseSlotSize() + getInSlotSize() + getOutSlotSize() + getFuelSlotSize() + getOtherSlotSize()
                + getUpgradeSlotSize();
    }

    public int getUpgradeSlotIndex(int i) {
        return upgrade[i];
    }

    private int getUpgradeSlotSize() {
        return upgrade.length;
    }

    private void setBase(int[] base) {
        this.base = base;
    }

    private void setFuel(int[] fuel) {
        this.fuel = fuel;
    }

    private void setIn(int[] in) {
        this.in = in;
    }

    private void setOut(int[] out) {
        this.out = out;
    }

    public int[] getUpgrade() {
        return upgrade;
    }
}
