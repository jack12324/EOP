package com.jack12324.eop.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class EOPItemStackHandler extends ItemStackHandler{

    private boolean tempIgnoreConditions;

   
    public static boolean isValid(ItemStack stack){
    	return stack != null && !stack.isEmpty();
    }
    
    public EOPItemStackHandler(int slots){
        super(slots);
    }

    public void decrStackSize(int slot, int amount){
        this.setStackInSlot(slot, addStackSize(this.getStackInSlot(slot), -amount));
    }

    public NonNullList<ItemStack> getItems(){
        return this.stacks;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate){
        if(!isValid(stack)){
            return ItemStack.EMPTY;
        }
        this.validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        int limit = this.getStackLimit(slot, stack);
        if(isValid(existing)){
            if(!ItemHandlerHelper.canItemStacksStack(stack, existing)){
                return stack;
            }
            limit -= existing.getCount();
        }
        if(limit <= 0){
            return stack;
        }

        if(!this.tempIgnoreConditions && !this.canInsert(stack, slot)){
            return stack;
        }

        boolean reachedLimit = stack.getCount() > limit;
        if(!simulate){
            if(!isValid(existing)){
                this.stacks.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
            }
            else{
                existing.grow(reachedLimit ? limit : stack.getCount());
            }

            this.onContentsChanged(slot);
        }

        return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()-limit) : ItemStack.EMPTY;

    }

    public ItemStack insertItemInternal(int slot, ItemStack stack, boolean simulate){
        this.tempIgnoreConditions = true;
        ItemStack result = this.insertItem(slot, stack, simulate);
        this.tempIgnoreConditions = false;
        return result;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate){
        if(amount <= 0){
            return ItemStack.EMPTY;
        }
        this.validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);
        if(!isValid(existing)){
            return ItemStack.EMPTY;
        }

        int toExtract = Math.min(amount, existing.getMaxStackSize());
        if(toExtract <= 0){
            return ItemStack.EMPTY;
        }

        if(!this.tempIgnoreConditions && !this.canExtract(this.getStackInSlot(slot), slot)){
            return ItemStack.EMPTY;
        }

        if(existing.getCount() <= toExtract){
            if(!simulate){
                this.stacks.set(slot, ItemStack.EMPTY);
                this.onContentsChanged(slot);
            }
            return existing;
        }
        else{
            if(!simulate){
                this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount()-toExtract));
                this.onContentsChanged(slot);
            }
            return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
        }
    }

    public ItemStack extractItemInternal(int slot, int amount, boolean simulate){
        this.tempIgnoreConditions = true;
        ItemStack result = this.extractItem(slot, amount, simulate);
        this.tempIgnoreConditions = false;
        return result;
    }

    public boolean canInsert(ItemStack stack, int slot){
        return true;
    }

    public boolean canExtract(ItemStack stack, int slot){
        return true;
    }
    
    public static ItemStack setStackSize(ItemStack stack, int size){
        return setStackSize(stack, size, false);
    }

    public static ItemStack setStackSize(ItemStack stack, int size, boolean containerOnEmpty){
        if(size <= 0){
            if(isValid(stack) && containerOnEmpty){
                return stack.getItem().getContainerItem(stack);
            }
            else{
                return ItemStack.EMPTY;
            }
        }
        stack.setCount(size);
        return stack;
    }

    public static ItemStack addStackSize(ItemStack stack, int size){
        return addStackSize(stack, size, false);
    }

    public static ItemStack addStackSize(ItemStack stack, int size, boolean containerOnEmpty){
        return setStackSize(stack, getStackSize(stack)+size, containerOnEmpty);
    }

    public static boolean isIInvEmpty(NonNullList<ItemStack> slots){
        for(ItemStack stack : slots){
            if(isValid(stack)){
                return false;
            }
        }

        return true;
    }
    public static int getStackSize(ItemStack stack){
        if(!isValid(stack)){
            return 0;
        }
        else{
            return stack.getCount();
        }
    }
}
