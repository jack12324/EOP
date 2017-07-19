package com.jack12324.eop.machine;

import com.jack12324.eop.util.EOPItemStackHandler;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemHandlerHelper;

public abstract class TEInventory extends TETickingMachine {

	private static boolean isValid(ItemStack stack) {
		return stack != null && !stack.isEmpty();
	}

	private static void loadSlots(IItemHandlerModifiable slots, NBTTagCompound compound) {
		if (slots != null && slots.getSlots() > 0) {
			NBTTagList tagList = compound.getTagList("Items", 10);
			for (int i = 0; i < slots.getSlots(); i++) {
				NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
				slots.setStackInSlot(i,
						tagCompound.hasKey("id") ? new ItemStack(tagCompound) : ItemStack.EMPTY);
			}
		}
	}

	private static void saveSlots(IItemHandler slots, NBTTagCompound compound) {
		if (slots != null && slots.getSlots() > 0) {
			NBTTagList tagList = new NBTTagList();
			for (int i = 0; i < slots.getSlots(); i++) {
				ItemStack slot = slots.getStackInSlot(i);
				NBTTagCompound tagCompound = new NBTTagCompound();
				if (isValid(slot)) {
					slot.writeToNBT(tagCompound);
				}
				tagList.appendTag(tagCompound);
			}
			compound.setTag("Items", tagList);
		}
	}

	public final EOPItemStackHandler slots;

	public final InventorySlotHelper slotHelper;

	protected TEInventory(InventorySlotHelper slots, String name) {
		super(name);
		this.slotHelper = slots;
		this.slots = new EOPItemStackHandler(slotHelper.getTotalSize()) {

			@Override
			public boolean canExtract(ItemStack stack, int slot) {
				System.out.println("canExtract TEI");
				return TEInventory.this.canExtractItem(slot, stack);
			}

			@Override
			public boolean canInsert(ItemStack stack, int slot) {
				System.out.println("can Insert TEI");
				return TEInventory.this.isItemValidForSlot(slot, stack);
			}

			@Override
			public int getSlotLimit(int slot) {
				return TEInventory.this.getMaxStackSizePerSlot(slot);
			}

			@Override
			protected void onContentsChanged(int slot) {
				super.onContentsChanged(slot);
				TEInventory.this.markDirty();
			}
		};
	}

	private boolean canExtractItem(int slot, ItemStack stack) {
		System.out.println("canExtractItem TEInventory");
		for (int index : this.slotHelper.getOut()) {
			if (index == slot)
				return true;
		}
		return false;
	}

	@Override
	public int getComparatorStrength() {
		return ItemHandlerHelper.calcRedstoneFromInventory(this.slots);
	}

	@Override
	public IItemHandler getItemHandler(EnumFacing facing) {
		return this.slots;
	}

	private int getMaxStackSizePerSlot(int slot) {
		return 64;
	}

	boolean isItemValidForSlot(int slot, ItemStack stack) {
		System.out.println("isItemValidForSlot TEInventory");
		return true;
	}

	@Override
	public void markDirty() {
		super.markDirty();

		if (this.shouldSyncSlots()) {
			this.sendUpdate();
		}
	}

	@Override
	public void readSyncableNBT(NBTTagCompound compound, NBTType type) {
		super.readSyncableNBT(compound, type);
		if (type == NBTType.SAVE_TILE || (type == NBTType.SYNC && this.shouldSyncSlots())) {
			loadSlots(this.slots, compound);
		}
	}

	private boolean shouldSyncSlots() {
		return false;
	}

	@Override
	public void writeSyncableNBT(NBTTagCompound compound, NBTType type) {
		super.writeSyncableNBT(compound, type);
		if (type == NBTType.SAVE_TILE || (type == NBTType.SYNC && this.shouldSyncSlots())) {
			saveSlots(this.slots, compound);
		}
	}
}