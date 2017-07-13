package com.jack12324.eop.machine.slot;

import com.jack12324.eop.util.EOPItemStackHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class SlotItemHandlerEOP extends SlotItemHandler {

	private final EOPItemStackHandler handler;

	public SlotItemHandlerEOP(EOPItemStackHandler handler, int index, int xPosition, int yPosition) {
		super(handler, index, xPosition, yPosition);
		this.handler = handler;
	}

	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		return !this.handler.extractItemIgnoreCondition(this.getSlotIndex(), 1, true).isEmpty();
	}

	@Override
	public ItemStack decrStackSize(int amount) {
		return this.handler.extractItemIgnoreCondition(this.getSlotIndex(), amount, false);
	}

	@Override
	public int getItemStackLimit(ItemStack stack) {
		ItemStack maxAdd = stack.copy();
		maxAdd.setCount(stack.getMaxStackSize());
		ItemStack currentStack = this.handler.getStackInSlot(this.getSlotIndex());
		this.handler.setStackInSlot(this.getSlotIndex(), ItemStack.EMPTY);
		ItemStack remainder = this.handler.insertItemIgnoreCondition(this.getSlotIndex(), maxAdd, true);
		this.handler.setStackInSlot(this.getSlotIndex(), currentStack);
		return stack.getMaxStackSize() - remainder.getCount();
	}

	/**
	 * simulates insert and returns true if the stack is able to be inserted
	 * using insertItemPlayer()
	 */
	@Override
	public boolean isItemValid(ItemStack stack) {
		if (stack != null && !stack.isEmpty()) {
			ItemStack currentStack = this.handler.getStackInSlot(this.getSlotIndex());
			this.handler.setStackInSlot(this.getSlotIndex(), ItemStack.EMPTY);
			ItemStack remainder = this.handler.insertItemIgnoreCondition(this.getSlotIndex(), stack, true);
			this.handler.setStackInSlot(this.getSlotIndex(), currentStack);
			return remainder == null || remainder.getCount() < stack.getCount();
		}
		return false;
	}
}