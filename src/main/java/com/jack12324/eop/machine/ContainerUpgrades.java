package com.jack12324.eop.machine;

import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.slot.SlotSpecific;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerUpgrades extends MachineContainer {
	public ContainerUpgrades(InventoryPlayer playerInv, final TEInventory te) {
		this.addUpgradeSlots(te);
		this.addInventorySlots(playerInv);
	}

	protected void addUpgradeSlots(TEInventory te) {
		System.out.println(te == null);
		addSlotToContainer(
				new SlotSpecific(te.slots, te.slotHelper.getUpgradeSlotIndex(0), 20, 20, ModItems.speedUpgrade) {
					@Override
					public void onSlotChanged() {
						te.markDirty();
					}
				});
		addSlotToContainer(
				new SlotSpecific(te.slots, te.slotHelper.getUpgradeSlotIndex(1), 40, 20, ModItems.energyUpgrade) {
					@Override
					public void onSlotChanged() {
						te.markDirty();
					}
				});
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
}
