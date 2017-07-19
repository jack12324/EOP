package com.jack12324.eop.machine.slot;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.util.EOPItemStackHandler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class SlotSpecific extends SlotItemHandlerEOP {
	private final List<Item> items;

	public SlotSpecific(EOPItemStackHandler inventory, int index, int x, int y, Block item) {
		super(inventory, index, x, y);
		this.items = new ArrayList<>();
		items.add(Item.getItemFromBlock(item));

	}

	public SlotSpecific(EOPItemStackHandler inventory, int index, int x, int y, Item item) {
		super(inventory, index, x, y);
		this.items = new ArrayList<>();
		items.add(item);

	}

	public SlotSpecific(EOPItemStackHandler inventory, int index, int x, int y, List<Item> items) {
		super(inventory, index, x, y);
		this.items = new ArrayList<>(items);
	}

	@Override
	public boolean isItemValid(@Nonnull ItemStack itemstack) {
		return items.contains(itemstack.getItem());
	}
}