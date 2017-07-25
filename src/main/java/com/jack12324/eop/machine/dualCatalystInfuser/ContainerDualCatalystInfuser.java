package com.jack12324.eop.machine.dualCatalystInfuser;

import com.jack12324.eop.machine.MachineContainer;
import com.jack12324.eop.machine.slot.SlotItemHandlerEOP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

import javax.annotation.Nonnull;

public class ContainerDualCatalystInfuser extends MachineContainer {
    private final TileEntityDualCatalystInfuser tileEntity;

    public ContainerDualCatalystInfuser(InventoryPlayer playerInv, final TileEntityDualCatalystInfuser tileEntity) {
        this.tileEntity = tileEntity;

        addSlotToContainer(new SlotItemHandlerEOP(tileEntity.slots, 0, 44, 16) {
            @Override
            public void onSlotChanged() {
                tileEntity.markDirty();
            }
        });
        addSlotToContainer(new SlotItemHandlerEOP(tileEntity.slots, 1, 44, 52) {
            @Override
            public void onSlotChanged() {
                tileEntity.markDirty();
            }
        });

        this.addInventorySlots(playerInv);
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return tileEntity.isUsableByPlayer(playerIn);
    }


}
