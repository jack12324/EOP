package com.jack12324.eop.machine.equalizingSmelter;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class VanillaFurnaceRecipes {
	// the list of smelting results
	private final Map<ItemStack, ItemStack> smeltingList = Maps.<ItemStack, ItemStack>newHashMap();
	// A list which contains how many experience points each recipe output will
	// give. */
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

	public VanillaFurnaceRecipes() {
		Map<ItemStack, ItemStack> metaList = FurnaceRecipes.instance().getSmeltingList();
		for (Entry<ItemStack, ItemStack> entry : metaList.entrySet()) {
			this.addSmeltingRecipe(entry.getKey(), entry.getValue(), 1, 0f);
		}

	}

	/**
	 * Adds a smelting recipe using an Item as the input item.
	 */
	public void addSmelting(Item input, ItemStack stack, int number, float experience) {
		this.addSmeltingRecipe(new ItemStack(input, 1, 32767), stack, number, experience);
	}

	/**
	 * Adds a smelting recipe using an ItemStack as the input for the recipe.
	 */
	public void addSmeltingRecipe(ItemStack input, ItemStack stack, int number, float experience) {
		if (getResult(input) != ItemStack.EMPTY) {
			net.minecraftforge.fml.common.FMLLog
					.info("Ignored smelting recipe with conflicting input: " + input + " = " + stack);
			return;
		}
		stack.setCount(number);
		this.smeltingList.put(input, stack);
		this.experienceList.put(stack, Float.valueOf(experience));
	}

	/**
	 * Compares two itemstacks to ensure that they are the same. This checks
	 * both the item and the metadata of the item.
	 */
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem()
				&& (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	/**
	 * Returns the smelting result of an item.
	 */
	public ItemStack getResult(ItemStack stack) {
		for (Entry<ItemStack, ItemStack> entry : this.smeltingList.entrySet()) {
			if (this.compareItemStacks(stack, entry.getKey())) {
				return entry.getValue();
			}
		}

		return ItemStack.EMPTY;
	}

	public float getSmeltingExperience(ItemStack stack) {
		float ret = stack.getItem().getSmeltingExperience(stack);
		if (ret != -1)
			return ret;

		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) {
			if (this.compareItemStacks(stack, entry.getKey())) {
				return entry.getValue().floatValue();
			}
		}

		return 0.0F;
	}

	public Map<ItemStack, ItemStack> getSmeltingList() {
		return this.smeltingList;
	}

}
