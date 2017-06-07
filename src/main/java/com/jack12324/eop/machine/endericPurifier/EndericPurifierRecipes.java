package com.jack12324.eop.machine.endericPurifier;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.EOPRecipes;
import com.jack12324.eop.machine.activationChamber.ActivationChamberRecipes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EndericPurifierRecipes extends EOPRecipes{
	//the list of activation results
	public static EndericPurifierRecipes INSTANCE = new EndericPurifierRecipes();
		private final Map<ItemStack[], ItemStack> activationList = Maps.<ItemStack[], ItemStack>newHashMap();
	 
	    
	    public EndericPurifierRecipes()
	    {
	    	
	        
	    }

	    /** Adds a activation recipe, where the input item is an instance of
		 * Block. */
		public void addActivationRecipeForBlock(Block[] input, ItemStack stack, int[] number) {
			Item[] itemInputs = new Item[input.length];
			for (int i = 0; i < itemInputs.length; i++) {
				itemInputs[i] = Item.getItemFromBlock(input[i]);
			}
			this.addActivation(itemInputs, stack, number);
		}

		/** Adds a activation recipe using an Item as the input item. */
		public void addActivation(Item[] input, ItemStack stack, int[] number) {
			ItemStack[] input2 = new ItemStack[input.length];
			for (int i = 0; i < input2.length; i++) {
				input2[i] = new ItemStack(input[i], number[i]);
			}
			this.addActivationRecipe(input2, stack);
		}

		/** Adds a activation recipe using an ItemStack as the input for the
		 * recipe. */
		public void addActivationRecipe(ItemStack[] input, ItemStack stack) {
			if (getResult(input) != ItemStack.EMPTY) {
				net.minecraftforge.fml.common.FMLLog.info("Ignored activation recipe with conflicting input: " + input + " = " + stack);
				return;
			}
			this.activationList.put(input, stack);
		}

		/** Returns the activation result of an item. */
		public ItemStack getResult(ItemStack[] stack) {
			for (Entry<ItemStack[], ItemStack> entry : this.activationList.entrySet()) {
				if (this.compareItemStacks(stack, entry.getKey())) {
					return entry.getValue();
				}
			}

			return ItemStack.EMPTY;
		}

		/** Compares two itemstacks to ensure that they are the same. This checks 
		 * both the item and the metadata of the item. */
		private boolean compareItemStacks(ItemStack[] stack, ItemStack[] stack2) {
			boolean size = stack.length == stack2.length;
			boolean equal = true;
			if (size) {
				for (int i = 0; i < stack.length; i++) {
					if (equal) {
						equal=false;
						for (int j = 0; j < stack.length; j++) {
							if (stack2[j].getItem() == stack[i].getItem()&& (stack2[j].getMetadata() == 32767 || stack2[j].getMetadata() == stack[i].getMetadata())){
								equal = true;
								}

						}
					}
				}
			}
			return (size&&equal);
		}

		public Map<ItemStack[], ItemStack> getActivationList() {
			return this.activationList;
		}
	 

}