package com.jack12324.eop.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class EOPItemStackHandler extends ItemStackHandler{
	
	public EOPItemStackHandler(int slots){
        super(slots);
    }
	
	 public void grow(int index, int amount) {
 		getStackInSlot(index).grow(amount);
 	}
     
     public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
			setStackInSlot(slotIndex, itemstack);
			if (itemstack.isEmpty() && itemstack.getCount() > getSlotLimit(slotIndex)) { //  isEmpty(); getStackSize()
				itemstack.setCount(getSlotLimit(slotIndex));  //setStackSize
			}
		}
     
     public ItemStack decrStackSize(int slotIndex, int count) {
 		ItemStack itemStackInSlot = getStackInSlot(slotIndex);
 		if (itemStackInSlot.isEmpty()) return ItemStack.EMPTY;  // isEmpt();   EMPTY_ITEM

 		ItemStack itemStackRemoved;
 		if (itemStackInSlot.getCount() <= count) {  // getStackSize()
 			itemStackRemoved = itemStackInSlot;
 			setInventorySlotContents(slotIndex, ItemStack.EMPTY);   // EMPTY_ITEM
 		} else {
 			itemStackRemoved = itemStackInSlot.splitStack(count);
 			if (itemStackInSlot.getCount() == 0) { // getStackSize
 				setInventorySlotContents(slotIndex, ItemStack.EMPTY);   // EMPTY_ITEM
 			}
 		}
 		return itemStackRemoved;
 	}

}
