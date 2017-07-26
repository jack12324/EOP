package com.jack12324.eop.util;

public enum GuiValues {

    //TODO pedestal eqs ??
    DEFAULT(175, 165, null, new int[]{8, 20}, null, new int[]{25, 18}, new int[]{0, 0}, null),
    ACTIVATIONCHAMBER(-1, -1, new int[]{49, 30, 3, 169, 77, 15}, null, new int[]{74, 47, 178, 2, 3, 20}, null, null, null),
    CATALYSTINFUSER(-1, -1, new int[]{63, 35, 6, 172, 68, 12}, null, null, null, new int[]{18, 133}, null),
    DISABLINGPRESS(-1, -1, new int[]{45, 24, 3, 169, 86, 34}, null, new int[]{82, 36, 179, 3, 12, 12}, null, null, null),
    DUALCATALYSTINFUSER(-1, -1, new int[]{63, 24, 3, 170, 86, 35}, null, null, null, new int[]{18, 151}, null),
    ENDERICPURIFIER(-1, -1, new int[]{42, 36, 6, 172, 89, 13}, null, null, null, null, null),
    EQUALIZINGSMELTER(175, 183, new int[]{44, 34, 180, 88, 25, 16, 107, 34, 180, 33, 25, 16, 44, 58, 180, 52, 25, 16, 107, 58, 180, 70, 25, 16}, null, null, null, null, new int[]{82, 24, 20, 180, 12, 11}),
    PARTICLEEXCITER(-1, -1, new int[]{43, 18, 2, 169, 89, 49}, null, null, null, null, null),
    STARHARDENER(-1, -1, new int[]{64, 27, 4, 171, 62, 31}, null, null, null, null, null),
    TRICATALYSTINFUSER(-1, -1, new int[]{63, 24, 3, 170, 86, 35}, null, null, null, new int[]{18, 151}, null);

    // some [x,y] coordinates of graphical elements
    private final int[] COOK_BAR_XPOS = {44, 107, 44, 107};
    private final int[] COOK_BAR_YPOS = {34, 34, 58, 58};
    private final int[] COOK_BAR_ICON_V = {88, 33, 52, 70};
    private final int COOK_BAR_WIDTH = 25;
    private final int COOK_BAR_HEIGHT = 16;

    private final int DUST_XPOS = 82;
    private final int DUST_YPOS = 24;
    private final int DUST_WIDTH = 12;
    private final int DUST_HEIGHT = 11;

    private final int POWER_XPOS = 8;
    private final int POWER_YPOS = 20;
    private final int POWER_WIDTH = 8;
    private final int POWER_HEIGHT = 45;

    public final int width;
    public final int height;
    public final int[] progress;
    public final int[] power;
    public final int[] fuel;
    public final int[] inTank;
    public final int[] outTank;
    public final int[] other;

    GuiValues(int width, int height, int[] progress, int[] power, int[] fuel, int[] inTank, int[] outTank, int[] other) {
        this.width = width;
        this.height = height;
        this.progress = progress;
        this.power = power;
        this.fuel = fuel;
        this.inTank = inTank;
        this.outTank = outTank;
        this.other = other;
    }


    public int getWidth() {
        return width == -1 ? DEFAULT.getWidth() : width;
    }

    public int getHeight() {
        return height == -1 ? DEFAULT.getHeight() : height;
    }

    public int[] getProgress() {
        return progress;
    }

    public int[] getPower() {
        return power == null ? DEFAULT.getPower() : power;
    }

    public int[] getFuel() {
        return fuel;
    }

    public int[] getInTank() {
        return inTank == null ? DEFAULT.getInTank() : inTank;
    }

    public int[] getOutTank() {
        return outTank == null ? DEFAULT.getOutTank() : outTank;
    }

    public int[] getOther() {
        return other;
    }
}

