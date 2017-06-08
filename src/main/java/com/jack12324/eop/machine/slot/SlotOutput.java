package com.jack12324.eop.machine.slot;


import com.jack12324.eop.util.EOPItemStackHandler;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotOutput extends SlotItemHandlerEOP
{
	public SlotOutput(EOPItemStackHandler inventory, int index, int x, int y)
	{
		super(inventory, index, x, y);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return false;
	}
}