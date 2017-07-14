package com.jack12324.eop.machine.equalizingSmelter;

import java.util.ArrayList;
import java.util.List;

import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.IButtonUse;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;

public class TileEntityEqualizingSmelter extends TEPowered implements IButtonUse {
	private static VanillaFurnaceRecipes vanillaRecipes = new VanillaFurnaceRecipes();
	private boolean furnaceMode = true;
	private boolean spreadMode = true;
	private boolean oldFurnaceMode = true;
	private boolean oldSpreadMode = true;
	private int dustProgress = 0;
	private int oldDustProgress = 0;

	public TileEntityEqualizingSmelter() {
		super("equalizing_smelter", new InventorySlotHelper(4, 4, 0, 0, 1));
	}

	@Override
	public boolean canUse() {
		return (super.canUse() && this.getInventory(this.slotHelper.getOtherSlotIndex(0)).getCount() < 64);
	}

	/**
	 * determines if the item in the input slot can be activated and if there is
	 * a place to put it afterwards. ie an open output slot
	 */

	protected boolean canUseF(int indexIn, int indexOut) {

		if (this.slots.getStackInSlot(indexIn).isEmpty()) {

			return false;
		} else {

			ItemStack itemstack = vanillaRecipes.getResult(this.slots.getStackInSlot(indexIn));

			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.slots.getStackInSlot(indexOut);
				if (itemstack1.isEmpty()) {
					return true;
				}
				if (!itemstack1.isItemEqual(itemstack)) {
					return false;
				}
				int result = itemstack1.getCount() + itemstack.getCount();
				return result <= this.slots.getSlotLimit(indexOut) && result <= itemstack1.getMaxStackSize();
			}
		}
	}

	/** returns the progress of earning an extra dust */
	public double fractionOfDustProgress() {
		if (dustProgress <= 0)
			return 0;
		double fraction = dustProgress / (double) 4;
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}

	public void furnaceUpdate() {
		super.superUpdate();
		boolean active = false;
		if (!this.world.isRemote) {
			for (int i = 0; i < 4; i++) {
				if (this.canUseF(this.slotHelper.getInSlotIndex(i), this.slotHelper.getOutSlotIndex(i))) {
					{
						if (!active)
							active = this.useLogicF(i);
						else
							this.useLogicF(i);
					}
				}

				else {
					this.resetTimeF(i);
				}
				this.resetUpgradeStats();
				this.oldActiveCheck(active);
				this.oldEnergyCheck();
				this.oldProgressTimeCheck();
			}

		}
	}

	public boolean getMode() {
		return furnaceMode;
	}

	public boolean getSpreadMode() {
		return spreadMode;
	}

	protected void oldModeCheck() {
		if (this.oldFurnaceMode != this.furnaceMode && this.sendUpdateWithInterval())
			this.oldFurnaceMode = this.furnaceMode;
		if (this.oldSpreadMode != this.spreadMode && this.sendUpdateWithInterval())
			this.oldSpreadMode = this.spreadMode;
	}

	@Override
	protected void oldProgressTimeCheck() {
		super.oldProgressTimeCheck();
		if (this.oldDustProgress != this.dustProgress && this.sendUpdateWithInterval())
			this.oldDustProgress = this.dustProgress;
	}

	@Override
	public void onButtonPress(int buttonId) {
		if (buttonId == 53)
			furnaceMode = !furnaceMode;
		else if (buttonId == 57)
			spreadMode = !spreadMode;
		else
			System.out.println(buttonId + " is not a valid button id for " + this.getDisplayedName());
	}

	@Override
	public void readSyncableNBT(NBTTagCompound compound, NBTType type) {
		if (type != NBTType.SAVE_BLOCK) {
			this.furnaceMode = compound.getBoolean("mode");
			this.dustProgress = compound.getInteger("dustProgress");
			this.spreadMode = compound.getBoolean("spreadMode");
		}
		super.readSyncableNBT(compound, type);
	}

	protected void resetTimeF(int i) {// TODO removed empty check
		setInProgressTime(i, 0);
	}

	public void setMode(boolean b) {
		furnaceMode = b;

	}

	public void setSpreadMode(boolean b) {
		spreadMode = b;

	}

	private void spread() {
		for (int i = 0; i < 4; i++) {
			List<Integer> indexList = new ArrayList<Integer>();
			ItemStack workingStack = this.slots.getStackInSlot(i);
			int temp = 0;
			int itemCount = 0;
			int remainder = 0;
			for (int index : this.slotHelper.getIn()) {
				if ((this.slots.getStackInSlot(index).isEmpty()
						|| this.slots.getStackInSlot(index).isItemEqual(workingStack))
						&& (Math.abs((double) (this.slots.getStackInSlot(index).getCount()
								- workingStack.getCount())) > 1)) {
					indexList.add(index);
					if (!this.slots.getStackInSlot(index).isEmpty())
						itemCount += this.slots.getStackInSlot(index).getCount();
				}
			}
			if (indexList.size() > 0) {
				remainder = itemCount % indexList.size();
				itemCount = itemCount / indexList.size();
				temp = itemCount;
				for (int index : indexList) {
					ItemStack output = workingStack.copy();

					if (remainder > 0) {
						temp = itemCount + 1;
						remainder--;
					}
					output.setCount(temp);
					this.setInventory(index, output);
				}
			}
		}
	}

	@Override
	public void updateEntity() {

		if (!this.world.isRemote) {
			if (spreadMode)
				spread();
			int speed = 1;
			if (furnaceMode) {
				furnaceUpdate();
			}

			else {

				super.updateEntity();
			}
			this.oldModeCheck();
		}
	}

	@Override
	public void useItem() {
		super.useItem();
		dustProgress++;
		if (dustProgress >= 4) {
			ItemStack result = new ItemStack(ModItems.dustFirestone);
			ItemStack output = this.getInventory(this.slotHelper.getOtherSlotIndex(0));

			if (output.isEmpty()) {
				this.setInventory(this.slotHelper.getOtherSlotIndex(0), result.copy());
			} else if (output.getItem() == result.getItem()) {
				result.setCount(output.getCount() + 1);
				this.setInventory(this.slotHelper.getOtherSlotIndex(0), result);
			}
			dustProgress = 0;
		}
	}

	/**
	 * Turn one item from the inventory input stack into the appropriate output
	 * item in the result stack
	 */
	public void useItemF(int indexIn, int indexOut) {
		ItemStack input = this.slots.getStackInSlot(indexIn);
		ItemStack result = vanillaRecipes.getResult(input);
		ItemStack output = this.slots.getStackInSlot(indexOut);

		if (output.isEmpty()) {
			this.slots.setStackInSlot(indexOut, result.copy());
		} else if (output.getItem() == result.getItem()) {
			output.grow(result.getCount());
		}

		input.shrink(1);

	}

	/**
	 * Returns true if the machine can activate an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */

	protected boolean useLogicF(int i) {
		boolean active = false;
		boolean powered = this.usePower();

		// If fuel is available, keep cooking the item, otherwise start
		// "uncooking" it at double speed
		if (powered) {
			setInProgressTime(i, this.getInProgressTime(i) + 1);
			active = true;
		} else {
			setInProgressTime(i, this.getInProgressTime(i) - 2);
		}

		if (this.getInProgressTime(i) < 0)
			setInProgressTime(i, 0);

		// If cookTime has reached maxCookTime smelt the item and reset
		// cookTime
		if (this.getInProgressTime(i) >= this.getTicksNeeded()) {
			useItemF(this.slotHelper.getInSlotIndex(i), this.slotHelper.getOutSlotIndex(i));
			setInProgressTime(i, 0);

		}
		return active;
	}

	@Override
	public void writeSyncableNBT(NBTTagCompound compound, NBTType type) {
		if (type != NBTType.SAVE_BLOCK) {
			compound.setBoolean("mode", furnaceMode);
			compound.setBoolean("spreadMode", spreadMode);
			compound.setInteger("dustProgress", dustProgress);
		}
		super.writeSyncableNBT(compound, type);

	}
	
	@Override
	public ArrayList<EOPRecipe> getRecipeList() {
		return RecipeHolder.EQUALIZINGSMELTERRECIPES;
	}
}
