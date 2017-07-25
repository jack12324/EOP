package com.jack12324.eop.util;

public enum Upgrade {
    SPEED("speed", 10), ENERGY("energy", 10);

    private final int maxStack;
    private final String name;

    Upgrade(String name, int maxStack) {
        this.maxStack = maxStack;
        this.name = name;
    }

    public int getMax() {
        return maxStack;
    }
}
