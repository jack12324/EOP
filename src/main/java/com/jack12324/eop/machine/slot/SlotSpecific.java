package com.jack12324.eop.machine.slot;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotSpecific extends SlotItemHandler {
	List<Item> items;
	private int limit = 64;

	public SlotSpecific(IItemHandler inventory, int index, int x, int y, List<Item> items) {
		super(inventory, index, x, y);
		this.items = new ArrayList<Item>(items);
	}

	public SlotSpecific(IItemHandler inventory, int index, int x, int y, Item item) {
		super(inventory, index, x, y);
		this.items = new ArrayList<Item>();
		items.add(item);

	}

	public SlotSpecific(IItemHandler inventory, int index, int x, int y, Block item) {
		this(inventory, index, x, y, Item.getItemFromBlock(item));
	}
	public SlotSpecific(IItemHandler inventory, int index, int x, int y, Item item,int stackLimit) {
		super(inventory, index, x, y);
		this.items = new ArrayList<Item>();
		items.add(item);
		this.limit=stackLimit;

	}

	@Override
	public int getSlotStackLimit() {
		return limit;

	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return items.contains(itemstack.getItem());
	}
}