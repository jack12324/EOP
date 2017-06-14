package com.jack12324.eop.machine.dualCatalystInfuser;

import com.jack12324.eop.machine.slot.SlotItemHandlerEOP;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDualCatalystInfuser extends Container {
	private int[] cachedFields;
	private TileEntityDualCatalystInfuser tileEntity;

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

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();

			if (index < containerSlots) {
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tileEntity.isUsableByPlayer(playerIn);
	}

}
