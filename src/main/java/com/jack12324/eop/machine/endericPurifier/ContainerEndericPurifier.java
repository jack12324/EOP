package com.jack12324.eop.machine.endericPurifier;

import com.jack12324.eop.machine.MachineContainer;
import com.jack12324.eop.machine.slot.SlotItemHandlerEOP;
import com.jack12324.eop.machine.slot.SlotOutput;
import com.jack12324.eop.machine.slot.SlotSpecific;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ContainerEndericPurifier extends MachineContainer {
    private final TileEntityEndericPurifier tileEntity;

	public ContainerEndericPurifier(InventoryPlayer playerInv, final TileEntityEndericPurifier tileEntity) {
		this.tileEntity = tileEntity;

		addSlotToContainer(new SlotItemHandlerEOP(tileEntity.slots, 0, 44, 19) {
			@Override
			public void onSlotChanged() {
				tileEntity.markDirty();
			}
		});
		addSlotToContainer(new SlotSpecific(tileEntity.slots, 2, 44, 50, Blocks.END_STONE) {
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
