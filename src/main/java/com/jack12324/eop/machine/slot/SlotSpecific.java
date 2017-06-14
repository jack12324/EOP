package com.jack12324.eop.machine.slot;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.util.EOPItemStackHandler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSpecific extends SlotItemHandlerEOP {
	List<Item> items;

	public SlotSpecific(EOPItemStackHandler inventory, int index, int x, int y, List<Item> items) {
		super(inventory, index, x, y);
		this.items = new ArrayList<Item>(items);
	}

	public SlotSpecific(EOPItemStackHandler inventory, int index, int x, int y, Item item) {
		super(inventory, index, x, y);
		this.items = new ArrayList<Item>();
		items.add(item);

	}

	public SlotSpecific(EOPItemStackHandler inventory, int index, int x, int y, Block item) {
		super(inventory, index, x, y);
		this.items = new ArrayList<Item>();
		items.add(Item.getItemFromBlock(item));

	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return items.contains(itemstack.getItem());
	}
}