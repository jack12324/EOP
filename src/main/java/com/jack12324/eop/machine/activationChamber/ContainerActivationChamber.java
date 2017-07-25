package com.jack12324.eop.machine.activationChamber;

import com.jack12324.eop.machine.MachineContainer;
import com.jack12324.eop.machine.slot.SlotItemHandlerEOP;
import com.jack12324.eop.machine.slot.SlotOutput;
import com.jack12324.eop.machine.slot.SlotSpecific;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

import javax.annotation.Nonnull;

public class ContainerActivationChamber extends MachineContainer {

    private final TileEntityActivationChamber activationChamber;

	public ContainerActivationChamber(InventoryPlayer playerInv, final TileEntityActivationChamber activationChamber) {
		this.activationChamber = activationChamber;

		addSlotToContainer(new SlotItemHandlerEOP(activationChamber.slots, 0, 26, 30) {
			@Override
			public void onSlotChanged() {
				activationChamber.markDirty();
			}
		});
		addSlotToContainer(new SlotSpecific(activationChamber.slots, 2, 80, 48, TileEntityActivationChamber.fuel) {
			@Override
			public void onSlotChanged() {
				activationChamber.markDirty();
			}
		});
		addSlotToContainer(new SlotOutput(activationChamber.slots, 1, 134, 30) {
			@Override
			public void onSlotChanged() {
				activationChamber.markDirty();
			}
		});

		this.addInventorySlots(playerInv);
	}

	@Override
	public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
		return activationChamber.isUsableByPlayer(playerIn);
	}

}
