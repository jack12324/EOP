package com.jack12324.eop.machine;

import net.minecraft.item.ItemStack;

public interface IOPairs {
    ItemStack[] getCurrentInputStacks(int slot);

    int getIONumber();
}
