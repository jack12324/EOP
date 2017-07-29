package com.jack12324.eop.machine.pedestal;

import com.jack12324.eop.machine.MachineContainer;
import com.jack12324.eop.machine.slot.SlotItemHandlerEOP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class ContainerPedestal extends MachineContainer {

    public ContainerPedestal(InventoryPlayer playerInv, final TileEntityPedestal pedestal) {
        IItemHandler inventory = pedestal.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,
                EnumFacing.NORTH);
        addSlotToContainer(new SlotItemHandlerEOP(pedestal.slots, 0, 44, 47) {
            @Override
            public void onSlotChanged() {
                pedestal.markDirty();
            }
        });

        this.addInventorySlots(playerInv);
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer player) {
        return true;
    }


}
