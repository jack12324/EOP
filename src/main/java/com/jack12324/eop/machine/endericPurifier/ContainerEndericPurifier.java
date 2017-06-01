package com.jack12324.eop.machine.endericPurifier;

import com.jack12324.eop.machine.slot.SlotOutput;
import com.jack12324.eop.machine.slot.SlotSpecific;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerEndericPurifier extends Container{
	private int [] cachedFields;
private TileEntityEndericPurifier tileEntity;
	
	public ContainerEndericPurifier(InventoryPlayer playerInv, final TileEntityEndericPurifier tileEntity) {
		this.tileEntity = tileEntity;
		
		addSlotToContainer(new SlotItemHandler(tileEntity.slots, 0, 44, 19) {
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
	
	/**
     * Looks for changes made in the container, sends them to every listener.
     */
	@Override
    public void detectAndSendChanges()
    {
		
        super.detectAndSendChanges();
        
        boolean allFieldsHaveChanged = false;
        boolean fieldHasChanged [] = new boolean[tileEntity.getFieldCount()];
        if (cachedFields == null){
        	cachedFields = new int[tileEntity.getFieldCount()];
        	allFieldsHaveChanged = true;
        }
        for (int i = 0; i < cachedFields.length; ++i){
        	if(allFieldsHaveChanged || cachedFields[i] != tileEntity.getField(i)){
        		cachedFields[i] = tileEntity.getField(i);
        		fieldHasChanged[i] = true;
        	}
        }
        
        for(IContainerListener listener : this.listeners){
        	for(int fieldID = 0; fieldID< tileEntity.getFieldCount(); ++fieldID){
        		if(fieldHasChanged[fieldID]){
        			listener.sendProgressBarUpdate(this, fieldID, cachedFields[fieldID]);
        		}
        	}
        }

       
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        tileEntity.setField(id, data);
    }
}
