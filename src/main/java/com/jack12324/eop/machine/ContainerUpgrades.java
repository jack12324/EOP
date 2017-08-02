package com.jack12324.eop.machine;

import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.slot.SlotSpecific;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

import javax.annotation.Nonnull;

public class ContainerUpgrades extends MachineContainer {
    public ContainerUpgrades(InventoryPlayer playerInv, final TEInventory te) {
        this.addUpgradeSlots(te);
        this.addInventorySlots(playerInv);
    }

    protected void addUpgradeSlots(TEInventory te) {
        addSlotToContainer(
                new SlotSpecific(te.slots, te.slotHelper.getUpgradeSlotIndex(0), 26, 34, ModItems.speedUpgrade) {
                    @Override
                    public void onSlotChanged() {
                        te.markDirty();
                    }
                });
        addSlotToContainer(
                new SlotSpecific(te.slots, te.slotHelper.getUpgradeSlotIndex(1), 44, 34, ModItems.energyUpgrade) {
                    @Override
                    public void onSlotChanged() {
                        te.markDirty();
                    }
                });
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }
}
