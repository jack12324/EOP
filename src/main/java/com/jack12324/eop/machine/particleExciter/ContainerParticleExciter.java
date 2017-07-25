package com.jack12324.eop.machine.particleExciter;

import com.jack12324.eop.machine.MachineContainer;
import com.jack12324.eop.machine.slot.SlotItemHandlerEOP;
import com.jack12324.eop.machine.slot.SlotOutput;
import com.jack12324.eop.machine.slot.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;

import javax.annotation.Nonnull;

public class ContainerParticleExciter extends MachineContainer {
    private final TileEntityParticleExciter tileEntity;

    public ContainerParticleExciter(InventoryPlayer playerInv, final TileEntityParticleExciter tileEntity) {
        this.tileEntity = tileEntity;

        addSlotToContainer(new SlotItemHandlerEOP(tileEntity.slots, 0, 80, 21) {
            @Override
            public void onSlotChanged() {
                tileEntity.markDirty();
            }
        });
        addSlotToContainer(new SlotSpecific(tileEntity.slots, 2, 80, 48, Blocks.NETHERRACK) {
            @Override
            public void onSlotChanged() {
                tileEntity.markDirty();
            }
        });
        addSlotToContainer(new SlotOutput(tileEntity.slots, 1, 134, 34) {
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
