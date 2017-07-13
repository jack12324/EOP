package com.jack12324.eop.util;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class EOPItemStackHandler extends ItemStackHandler {

	private boolean tempIgnore;

	public EOPItemStackHandler(int slots) {
		super(slots);
	}

	public boolean canExtract(ItemStack stack, int slot) {
		return true;
	}

	public boolean canInsert(ItemStack stack, int slot) {
		return true;
	}

	public ItemStack decrStackSize(int slotIndex, int count) {
		ItemStack itemStackInSlot = getStackInSlot(slotIndex);
		if (itemStackInSlot.isEmpty())
			return ItemStack.EMPTY;

		ItemStack itemStackRemoved;
		if (itemStackInSlot.getCount() <= count) {
			itemStackRemoved = itemStackInSlot;
			setInventorySlotContents(slotIndex, ItemStack.EMPTY);
		} else {
			itemStackRemoved = itemStackInSlot.splitStack(count);
			if (itemStackInSlot.getCount() == 0) {
				setInventorySlotContents(slotIndex, ItemStack.EMPTY);
			}
		}
		return itemStackRemoved;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (amount <= 0) {
			return ItemStack.EMPTY;
		}
		this.validateSlotIndex(slot);

		ItemStack existing = this.stacks.get(slot);
		if (existing.isEmpty()) {
			return ItemStack.EMPTY;
		}

		int toExtract = Math.min(amount, existing.getMaxStackSize());
		if (toExtract <= 0) {
			return ItemStack.EMPTY;
		}

		if (!this.tempIgnore && !this.canExtract(this.getStackInSlot(slot), slot)) {
			return ItemStack.EMPTY;
		}

		if (existing.getCount() <= toExtract) {
			if (!simulate) {
				this.stacks.set(slot, ItemStack.EMPTY);
				this.onContentsChanged(slot);
			}
			return existing;
		} else {
			if (!simulate) {
				this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
				this.onContentsChanged(slot);
			}
			return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
		}
	}

	public ItemStack extractItemIgnoreCondition(int slot, int amount, boolean simulate) {
		this.tempIgnore = true;
		ItemStack result = this.extractItem(slot, amount, simulate);
		this.tempIgnore = false;
		return result;
	}

	public void grow(int index, int amount) {
		getStackInSlot(index).grow(amount);
	}

	@Override
	public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
		if (stack.isEmpty()) {
			return ItemStack.EMPTY;
		}
		this.validateSlotIndex(slot);

		ItemStack existing = this.stacks.get(slot);

		int limit = this.getStackLimit(slot, stack);
		if (!existing.isEmpty()) {
			if (!ItemHandlerHelper.canItemStacksStack(stack, existing)) {
				return stack;
			}
			limit -= existing.getCount();
		}
		if (limit <= 0) {
			return stack;
		}

		if (!this.tempIgnore && !this.canInsert(stack, slot)) {
			return stack;
		}

		boolean reachedLimit = stack.getCount() > limit;
		if (!simulate) {
			if (existing.isEmpty()) {
				this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
			} else {
				existing.grow(reachedLimit ? limit : stack.getCount());
			}

			this.onContentsChanged(slot);
		}

		return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;

	}

	public ItemStack insertItemIgnoreCondition(int slot, ItemStack stack, boolean simulate) {
		this.tempIgnore = true;
		ItemStack result = this.insertItem(slot, stack, simulate);
		this.tempIgnore = false;
		return result;
	}

	public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
		setStackInSlot(slotIndex, itemstack);
		if (itemstack.isEmpty() && itemstack.getCount() > getSlotLimit(slotIndex)) {
			itemstack.setCount(getSlotLimit(slotIndex));
		}
	}

}
