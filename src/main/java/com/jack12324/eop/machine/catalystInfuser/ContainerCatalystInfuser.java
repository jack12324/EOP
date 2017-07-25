package com.jack12324.eop.machine.catalystInfuser;

import com.jack12324.eop.machine.MachineContainer;
import com.jack12324.eop.machine.slot.SlotItemHandlerEOP;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ContainerCatalystInfuser extends MachineContainer {
    private final TileEntityCatalystInfuser tileEntity;

	public ContainerCatalystInfuser(InventoryPlayer playerInv, final TileEntityCatalystInfuser tileEntity) {
		this.tileEntity = tileEntity;

		addSlotToContainer(new SlotItemHandlerEOP(tileEntity.slots, 0, 44, 34) {
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
