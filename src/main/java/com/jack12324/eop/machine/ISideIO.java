package com.jack12324.eop.machine;

import net.minecraft.util.EnumFacing;

public interface ISideIO {
    int getSideVal(EnumFacing side);

    EnumFacing getAlteredSide(EnumFacing inSide);
}
