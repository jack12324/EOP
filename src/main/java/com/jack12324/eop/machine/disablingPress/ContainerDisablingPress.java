package com.jack12324.eop.machine.disablingPress;

import com.jack12324.eop.machine.MachineContainer;
import com.jack12324.eop.machine.slot.SlotItemHandlerEOP;
import com.jack12324.eop.machine.slot.SlotOutput;
import com.jack12324.eop.machine.slot.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;

import javax.annotation.Nonnull;

public class ContainerDisablingPress extends MachineContainer {
    private final TileEntityDisablingPress disablingPress;

    public ContainerDisablingPress(InventoryPlayer playerInv, final TileEntityDisablingPress disablingPress) {
        this.disablingPress = disablingPress;

        addSlotToContainer(new SlotSpecific(disablingPress.slots, 2, 80, 57, TileEntityDisablingPress.fuel) {
            @Override
            public void onSlotChanged() {
                disablingPress.markDirty();
            }
        });
        addSlotToContainer(new SlotSpecific(disablingPress.slots, 3, 26, 48, Blocks.SAND) {
            @Override
            public void onSlotChanged() {
                disablingPress.markDirty();
            }
        });
        addSlotToContainer(new SlotItemHandlerEOP(disablingPress.slots, 0, 26, 21) {
            @Override
            public void onSlotChanged() {
                disablingPress.markDirty();
            }
        });

        addSlotToContainer(new SlotOutput(disablingPress.slots, 1, 134, 33) {
            @Override
            public void onSlotChanged() {
                disablingPress.markDirty();
            }
        });

        this.addInventorySlots(playerInv);
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return disablingPress.isUsableByPlayer(playerIn);
    }


}
