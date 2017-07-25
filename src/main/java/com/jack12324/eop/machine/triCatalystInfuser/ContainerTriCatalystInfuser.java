package com.jack12324.eop.machine.triCatalystInfuser;

import com.jack12324.eop.machine.MachineContainer;
import com.jack12324.eop.machine.slot.SlotItemHandlerEOP;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ContainerTriCatalystInfuser extends MachineContainer {
    private final TileEntityTriCatalystInfuser tileEntity;

	public ContainerTriCatalystInfuser(InventoryPlayer playerInv, final TileEntityTriCatalystInfuser tileEntity) {
		this.tileEntity = tileEntity;

		addSlotToContainer(new SlotItemHandlerEOP(tileEntity.slots, 0, 44, 16) {
			@Override
			public void onSlotChanged() {
				tileEntity.markDirty();
			}
		});
		addSlotToContainer(new SlotItemHandlerEOP(tileEntity.slots, 2, 44, 52) {
			@Override
			public void onSlotChanged() {
				tileEntity.markDirty();
			}
		});
		addSlotToContainer(new SlotItemHandlerEOP(tileEntity.slots, 1, 44, 34) {
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
