package com.jack12324.eop.machine.equalizingSmelter;

import com.jack12324.eop.machine.MachineContainer;
import com.jack12324.eop.machine.slot.SlotItemHandlerEOP;
import com.jack12324.eop.machine.slot.SlotOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import javax.annotation.Nonnull;

public class ContainerEqualizingSmelter extends MachineContainer {

    private final TileEntityEqualizingSmelter equalizingSmelter;

    public ContainerEqualizingSmelter(InventoryPlayer playerInv, final TileEntityEqualizingSmelter equalizingSmelter) {
        this.equalizingSmelter = equalizingSmelter;

        addSlotToContainer(new SlotItemHandlerEOP(equalizingSmelter.slots, 0, 26, 28) {
            @Override
            public void onSlotChanged() {
                equalizingSmelter.markDirty();
            }
        });
        addSlotToContainer(new SlotItemHandlerEOP(equalizingSmelter.slots, 1, 134, 28) {
            @Override
            public void onSlotChanged() {
                equalizingSmelter.markDirty();
            }
        });
        addSlotToContainer(new SlotItemHandlerEOP(equalizingSmelter.slots, 2, 26, 64) {
            @Override
            public void onSlotChanged() {
                equalizingSmelter.markDirty();
            }
        });
        addSlotToContainer(new SlotItemHandlerEOP(equalizingSmelter.slots, 3, 134, 64) {
            @Override
            public void onSlotChanged() {
                equalizingSmelter.markDirty();
            }
        });
        addSlotToContainer(new SlotOutput(equalizingSmelter.slots, 4, 71, 37) {
            @Override
            public void onSlotChanged() {
                equalizingSmelter.markDirty();
            }
        });
        addSlotToContainer(new SlotOutput(equalizingSmelter.slots, 5, 89, 37) {
            @Override
            public void onSlotChanged() {
                equalizingSmelter.markDirty();
            }
        });
        addSlotToContainer(new SlotOutput(equalizingSmelter.slots, 6, 71, 55) {
            @Override
            public void onSlotChanged() {
                equalizingSmelter.markDirty();
            }
        });
        addSlotToContainer(new SlotOutput(equalizingSmelter.slots, 7, 89, 55) {
            @Override
            public void onSlotChanged() {
                equalizingSmelter.markDirty();
            }
        });
        addSlotToContainer(new SlotOutput(equalizingSmelter.slots, 8, 80, 6) {
            @Override
            public void onSlotChanged() {
                equalizingSmelter.markDirty();
            }
        });

        this.addInventorySlots(playerInv);
    }

    @Override
    protected void addInventorySlots(InventoryPlayer playerInv) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 102 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 160));
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return equalizingSmelter.isUsableByPlayer(playerIn);
    }


}
