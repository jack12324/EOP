package com.jack12324.eop.machine.equalizingSmelter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.util.InventorySlotHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityEqualizingSmelter extends TEPowered {
	private String equalizingSmelterCustomName = "container.equalizingSmelter.name";
	private static EqualizingSmelterRecipes equalizingRecipes = EqualizingSmelterRecipes.INSTANCE;
	private static VanillaFurnaceRecipes vanillaRecipes = new VanillaFurnaceRecipes();
	private boolean furnaceMode = true;
	private boolean spreadMode = true;
	private boolean oldFurnaceMode = true;
	private boolean oldSpreadMode = true;
	private int dustProgress = 0;
	private int oldDustProgress = 0;

	public TileEntityEqualizingSmelter() {
		super("equalizingSmelter", new InventorySlotHelper(4, 4, 0, 0, 1), equalizingRecipes);
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

	/** returns the progress of earning an extra dust */
	public double fractionOfDustProgress() {
		if (dustProgress <= 0)
			return 0;
		double fraction = dustProgress / (double) 4;
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}

	@Override
	public void updateEntity() {
		
		if (!this.world.isRemote) {
			if (spreadMode)
				spread();
			int speed = 1;
			if (furnaceMode) {
				for (int i = 0; i < 4; i++) {
					if (canUseF(this.slotHelper.getInSlotIndex(i), this.slotHelper.getOutSlotIndex(i)))
						speed++;
				}

				if (speed != 1)
					speed--;
				this.setSpeedMultiplier(speed);
				this.setFuelMultiplier((int) (speed * (.5 + speed / 2.0)));
				furnaceUpdate();
			}

			else {
				setFuelMultiplier(1);
				setSpeedMultiplier(1);

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
			} else if (output.getItem() == result.getItem()){
				result.setCount(output.getCount()+1);
				this.setInventory(this.slotHelper.getOtherSlotIndex(0), result);}
			dustProgress = 0;
		}
	}

	@Override
	public boolean canUse() {
		return (super.canUse() && this.getInventory(this.slotHelper.getOtherSlotIndex(0)).getCount() < 64);
	}

	@Override
	protected void oldProgressTimeCheck() {
		super.oldProgressTimeCheck();
		if (this.oldDustProgress != this.dustProgress && this.sendUpdateWithInterval())
			this.oldDustProgress = this.dustProgress;
	}

	protected void oldModeCheck() {
		if (this.oldFurnaceMode != this.furnaceMode && this.sendUpdateWithInterval())
			this.oldFurnaceMode = this.furnaceMode;
		if (this.oldSpreadMode != this.spreadMode && this.sendUpdateWithInterval())
			this.oldSpreadMode = this.spreadMode;
	}
/*
	@Override
	public int getField(int id) {
		if (id == 2) {
			if (furnaceMode)
				return 1;
			else
				return 0;
		} else if (id == 1)
			return dustProgress;
		else if (id == 0) {
			if (spreadMode)
				return 1;
			else
				return 0;
		}

		else
			return super.getField(id - 3);
	}

	@Override
	public void setField(int id, int value) {
		if (id == 2) {
			if (value == 1)
				this.furnaceMode = true;
			else
				this.furnaceMode = false;
		} else if (id == 1)
			this.dustProgress = value;
		else if (id == 0) {
			if (value == 1)
				this.spreadMode = true;
			else
				this.spreadMode = false;
		} else
			super.setField(id - 3, value);

	}

	@Override
	public int getFieldCount() {
		return super.getFieldCount() + 3;
	}
*/
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
	public void readSyncableNBT(NBTTagCompound compound, NBTType type) {
		if (type != NBTType.SAVE_BLOCK) {
			this.furnaceMode = compound.getBoolean("mode");
			this.dustProgress = compound.getInteger("dustProgress");
			this.spreadMode = compound.getBoolean("spreadMode");
		}
		super.readSyncableNBT(compound, type);
	}

	/** standard code to look up what the human-readable name is */
	@Nullable
	@Override
	public ITextComponent getDisplayName() {
		return this.hasCustomName() ? new TextComponentString(this.getName())
				: new TextComponentTranslation(this.getName());
	}

	/** Get the name of this object. For players this returns their username */
	public String getName() {
		return this.hasCustomName() ? this.equalizingSmelterCustomName : "container.equalizingSmelter.name";
	}

	/** Returns true if this thing is named */
	public boolean hasCustomName() {
		return true;
	}

	public static void registerFixesEqualizingSmelter(DataFixer fixer) {
		fixer.registerWalker(FixTypes.BLOCK_ENTITY,
				new ItemStackDataLists(TileEntityEqualizingSmelter.class, new String[] { "Items" }));
	}

	public boolean getMode() {
		return furnaceMode;
	}

	public void setMode(boolean b) {
		furnaceMode = b;

	}

	public boolean getSpreadMode() {
		return spreadMode;
	}

	public void setSpreadMode(boolean b) {
		spreadMode = b;

	}

	public void furnaceUpdate() {
		super.superUpdate();
		boolean active = false;
		if (!this.world.isRemote) {
			for (int i = 0; i < this.inProgressTime.length; i++) {
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
				this.oldActiveCheck(active);
				this.oldEnergyCheck();
				this.oldProgressTimeCheck();
			}

		}
	}

	protected void resetTimeF(int i) {// TODO removed empty check
		inProgressTime[i] = 0;
	}

	protected boolean useLogicF(int i) {
		boolean active = false;
		boolean burning = true;
		boolean powered = this.usePower();
		if (this.usesFuel && powered) {
			burning = this.burnFuel();
		}

		// If fuel is available, keep cooking the item, otherwise start
		// "uncooking" it at double speed
		if (burning && powered) {
			inProgressTime[i] += speedMultiplier;
			active = true;
		} else {
			inProgressTime[i] -= 2;
		}

		if (inProgressTime[i] < 0)
			inProgressTime[i] = 0;

		// If cookTime has reached maxCookTime smelt the item and reset
		// cookTime
		if (inProgressTime[i] >= COOK_TIME_FOR_COMPLETION) {
			useItemF(this.slotHelper.getInSlotIndex(i), this.slotHelper.getOutSlotIndex(i));
			inProgressTime[i] = 0;

		}
		return active;
	}

	/**
	 * Returns true if the machine can activate an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */

	/**
	 * determines if the item in the input slot can be activated and if there is
	 * a place to put it afterwards. ie an open output slot
	 */

	protected boolean canUseF(int indexIn, int indexOut) {

		if (((ItemStack) this.slots.getStackInSlot(indexIn)).isEmpty()) {

			return false;
		} else {

			ItemStack itemstack = vanillaRecipes.getResult((ItemStack) this.slots.getStackInSlot(indexIn));

			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = (ItemStack) this.slots.getStackInSlot(indexOut);
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

	/**
	 * Turn one item from the inventory input stack into the appropriate output
	 * item in the result stack
	 */
	public void useItemF(int indexIn, int indexOut) {
		ItemStack input = (ItemStack) this.slots.getStackInSlot(indexIn);
		ItemStack result = vanillaRecipes.getResult(input);
		ItemStack output = (ItemStack) this.slots.getStackInSlot(indexOut);

		if (output.isEmpty()) {
			this.slots.setStackInSlot(indexOut, result.copy());
		} else if (output.getItem() == result.getItem()) {
			output.grow(result.getCount());
		}

		input.shrink(1);

	}
}
