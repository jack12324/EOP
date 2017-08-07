package com.jack12324.eop.machine;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public class TESideIO extends TEInventory implements IButtonUse, ISideIO {
    protected TESideIO(InventorySlotHelper slots, String name) {
        super(slots, name);
    }

    public int[] sideIO = {0, 0, 0, 0, 0, 0};

    @Override
    public void onButtonPress(int buttonId) {
        if (buttonId == 71)
            this.incrementSideVal(EnumFacing.NORTH);
        else if (buttonId == 72)
            this.incrementSideVal(EnumFacing.EAST);
        else if (buttonId == 73)
            this.incrementSideVal(EnumFacing.WEST);
        else if (buttonId == 74)
            this.incrementSideVal(EnumFacing.UP);
        else if (buttonId == 75)
            this.incrementSideVal(EnumFacing.DOWN);
        else if (buttonId == 76)
            this.incrementSideVal(EnumFacing.SOUTH);
        else
            ExtremeOreProcessing.LOGGER.warn(buttonId + " is not a valid button id for " + this.getDisplayedName());
        this.sendTileUpdate();
    }

    @Override
    public void readSyncableNBT(NBTTagCompound compound, boolean shouldSync) {

        this.sideIO = compound.getIntArray("SideIO").clone();
        super.readSyncableNBT(compound, shouldSync);
    }

    @Override
    public void writeSyncableNBT(NBTTagCompound compound, boolean shouldSync) {

        compound.setIntArray("SideIO", this.sideIO);
        super.writeSyncableNBT(compound, shouldSync);

    }

    @Override
    public int getSideVal(EnumFacing side) {
        switch (side) {
            case UP:
                return sideIO[3];
            case DOWN:
                return sideIO[4];
            case EAST:
                return sideIO[1];
            case WEST:
                return sideIO[2];
            case NORTH:
                return sideIO[0];
            case SOUTH:
                return sideIO[5];
            default:
                return -1;
        }

    }

    protected int getSideIndex(EnumFacing side) {
        int index;
        switch (side) {
            case UP:
                index = 3;
                break;
            case DOWN:
                index = 4;
                break;
            case EAST:
                index = 1;
                break;
            case WEST:
                index = 2;
                break;
            case NORTH:
                index = 0;
                break;
            case SOUTH:
                index = 5;
                break;
            default:
                index = -1;
                break;
        }
        return index;
    }

    public void incrementSideVal(EnumFacing side) {
        int val;
        int index = this.getSideIndex(side);
        if (index != -1) {
            val = this.getSideVal(side);
            if (val == 4)
                this.sideIO[index] = 0;
            else this.sideIO[index] = val + 1;
        }
    }
}
