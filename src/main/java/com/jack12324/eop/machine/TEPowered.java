package com.jack12324.eop.machine;

import java.util.ArrayList;

import com.jack12324.eop.recipe.RecipeHandler;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;
import com.jack12324.eop.util.Upgrade;
import com.jack12324.eop.util.UpgradeHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.energy.IEnergyStorage;

public abstract class TEPowered extends TEInventory {
	private final double BASE_TICKS_NEEDED = 200;
	private double ticksNeeded = BASE_TICKS_NEEDED;
	private final double BASE_ENERGY_PER_TICK = 50;
	private double energyPerTick = BASE_ENERGY_PER_TICK;
	private int burnTimeInitialValue;
	private int burnTimeRemaining;
	private int[] inProgressTime;
	private int[] oldValues;
	private EOPRecipes recipes;
	private int baseSpeed = 1;
	private int fuelMultiplier = 1;
	private boolean hasBase;
	private boolean usesFuel;
	private int oldEnergy;

	public EOPEnergyStorage storage;

	public double getTicksNeeded() {
		return ticksNeeded;
	}

	public int getInProgressTime(int index) {
		return inProgressTime[index];
	}

	public void setInProgressTime(int index, int value) {
		inProgressTime[index] = value;
	}

	public void setBaseSpeed(int speedMultiplier) {
		this.baseSpeed = speedMultiplier;
	}

	public void setFuelMultiplier(int fuelMultiplier) {
		this.fuelMultiplier = fuelMultiplier;
	}

	public double getEnergyPerTick() {
		return energyPerTick;
	}

	public int getFuelMultiplier() {
		return this.fuelMultiplier;
	}

	public void setRecipes(EOPRecipes recipes) {
		this.recipes = recipes;
	}

	public ItemStack getInventory(int i) {
		return this.slots.getStackInSlot(i);
	}

	public void setInventory(int i, ItemStack itemStack) {
		this.slots.setStackInSlot(i, itemStack);
	}

	public TEPowered(String name, InventorySlotHelper slots, EOPRecipes recipes) {
		this(name, slots, recipes, 100000, 100000, 0);
	}

	public TEPowered(String name, InventorySlotHelper slots, EOPRecipes recipes, int capacity, int recieve,
			int extract) {

		super(new InventorySlotHelper(slots, 2), name);
		inProgressTime = new int[slots.getInSlotSize()];
		this.recipes = recipes;
		this.hasBase = slots.getBaseSlotSize() > 0 ? true : false;
		this.usesFuel = slots.getFuelSlotSize() > 0 ? true : false;
		storage = new EOPEnergyStorage(capacity, recieve, extract);
		oldValues = new int[2 + inProgressTime.length];
		for (int val : oldValues)
			val = 0;
	}

	public double fractionOfPowerRemaining() {
		if (this.storage.getEnergyStored() <= 0)
			return 0;
		double fraction = this.storage.getEnergyStored() / (double) this.storage.getMaxEnergyStored();
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}

	/** returns the amount of fuel remaining on the currently burning item */
	public double fractionOfFuelRemaining() {
		if (burnTimeInitialValue <= 0) {
			return 0;
		}
		double fraction = burnTimeRemaining / (double) burnTimeInitialValue;
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}

	/** return the remaining burn time of the fuel in the given slot */
	public int secondsOfFuelRemaining() {
		if (burnTimeRemaining <= 0 || fuelMultiplier <= 0)
			return 0;
		return (int) (burnTimeRemaining / (20 * fuelMultiplier * BASE_TICKS_NEEDED / getTicksNeeded()));
	}

	/**
	 * Returns the amount of cook time completed on the currently cooking item.
	 */
	public double fractionOfProgressTimeComplete(int index) {
		double fraction = this.inProgressTime[index] / getTicksNeeded();
		return MathHelper.clamp(fraction, 0.0, 1.0);
	}

	@Override
	public void writeSyncableNBT(NBTTagCompound compound, NBTType type) {
		if (type != NBTType.SAVE_BLOCK) {
			compound.setTag("inventory", slots.serializeNBT());
			compound.setInteger("BurnTime", this.burnTimeRemaining);
			compound.setIntArray("ProgressTime", this.inProgressTime);
			compound.setInteger("burnTimeInitialValue", burnTimeInitialValue);
			compound.setBoolean("hasBase", this.hasBase);
			compound.setDouble("energyPerTick", energyPerTick);
			compound.setDouble("ticksNeeded", getTicksNeeded());
		}
		this.storage.writeToNBT(compound);
		super.writeSyncableNBT(compound, type);

	}

	@Override
	public void readSyncableNBT(NBTTagCompound compound, NBTType type) {
		if (type != NBTType.SAVE_BLOCK) {
			slots.deserializeNBT(compound.getCompoundTag("inventory"));
			this.burnTimeInitialValue = usesFuel
					? getFuelBurnTime(this.slots.getStackInSlot(this.slotHelper.getFuelSlotIndex(0))) : 0;
			this.burnTimeRemaining = compound.getInteger("BurnTime");
			this.hasBase = compound.getBoolean("hasBase");
			this.inProgressTime = compound.getIntArray("ProgressTime").clone();
			this.energyPerTick = compound.getDouble("energyPerTick");
			this.ticksNeeded = compound.getDouble("ticksNeeded");
		}
		this.storage.readFromNBT(compound);
		super.readSyncableNBT(compound, type);
	}

	public int getUpgrade(Upgrade type) {
		switch (type) {
		case SPEED:
			return this.slots.getStackInSlot(this.slotHelper.getUpgradeSlotIndex(0)).getCount();
		case ENERGY:
			return this.slots.getStackInSlot(this.slotHelper.getUpgradeSlotIndex(1)).getCount();
		default:
			return 0;
		}
	}

	public void superUpdate() {
		super.updateEntity();
	}

	private boolean lastActive = false;

	@Override
	public void updateEntity() {
		super.updateEntity();
		// If there is nothing to smelt or there is no room in the output, reset
		// cookTime and return
		boolean active = false;
		if (!this.world.isRemote) {
			if (canUse()) {
				active = this.useLogic();
			}

			else {
				resetTime();
			}
			this.resetUpgradeStats();
			oldEnergyCheck();
			oldActiveCheck(active);// TODO moved this inside isRemote structure
									// not sure if right?
			oldProgressTimeCheck();
		}

	}

	protected void oldActiveCheck(boolean active) {
		if (active != this.lastActive) {
			IBlockState currState = this.world.getBlockState(this.pos);
			if (currState.getValue(BlockTE.PROPERTYACTIVE) != (active)) {
				this.world.setBlockState(this.pos, currState.withProperty(BlockTE.PROPERTYACTIVE, active));
			}

			this.lastActive = active;
		}
	}

	protected void oldEnergyCheck() {
		if (this.oldEnergy != this.storage.getEnergyStored() && this.sendUpdateWithInterval()) {
			this.oldEnergy = this.storage.getEnergyStored();

		}
	}

	protected void oldProgressTimeCheck() {
		for (int i = 0; i < oldValues.length; i++) {
			if (i == 0 && this.burnTimeInitialValue != this.oldValues[i] && this.sendUpdateWithInterval())
				this.oldValues[i] = this.burnTimeInitialValue;
			else if (i == 1 && this.burnTimeRemaining != this.oldValues[i] && this.sendUpdateWithInterval())
				this.oldValues[i] = this.burnTimeRemaining;
			else if (i > 1 && this.inProgressTime[i - 2] != this.oldValues[i] && this.sendUpdateWithInterval())
				this.oldValues[i] = this.inProgressTime[i - 2];
		}
	}

	protected void resetTime() {
		for (int i = 0; i < inProgressTime.length; i++)
			inProgressTime[i] = 0;// TODO fix
	}

	protected boolean useLogic() {
		boolean burning;
		boolean powered;
		boolean active = false;
		burning = true;
		powered = usePower();
		active = false;
		if (usesFuel && powered) {
			burning = burnFuel();
		}

		// If fuel is available, keep cooking the item, otherwise start
		// "uncooking" it at double speed
		if (burning && powered) {
			inProgressTime[0] += 1;
			active = true;
		} else {
			inProgressTime[0] -= 2;
		}

		if (inProgressTime[0] < 0)
			inProgressTime[0] = 0;

		// If cookTime has reached maxCookTime smelt the item and reset
		// cookTime
		if (inProgressTime[0] >= getTicksNeeded()) {
			useItem();
			inProgressTime[0] = 0;

		}
		for (int i = 0; i < inProgressTime.length; i++) {
			inProgressTime[i] = inProgressTime[0];
		}
		return active;
	}

	protected boolean usePower() {
		boolean powered = false;

		if (this.storage.getEnergyStored() >= getEnergyPerTick()) {
			this.storage.extractEnergyInternal((int) getEnergyPerTick(), false);
			powered = true;

		}
		return powered;
	}

	/** handles fuel usage, also returns true if fuel is currently being used */
	protected boolean burnFuel() {
		boolean inventoryChanged = false;
		boolean burning = false;
		int fuelSlotNumber = this.slotHelper.getFuelSlotIndex(0);

		if (burnTimeRemaining > 0) {
			burning = true;
			burnTimeRemaining -= fuelMultiplier;
		}
		if (burnTimeRemaining <= 0) {
			if (!slots.getStackInSlot(fuelSlotNumber).isEmpty()
					&& getFuelBurnTime(slots.getStackInSlot(fuelSlotNumber)) > 0) {
				// If the stack in this slot is not null and is fuel, set
				// burnTimeRemaining & burnTimeInitialValue to the item's burn
				// time and decrease the stack size
				burnTimeRemaining = burnTimeInitialValue = getFuelBurnTime(slots.getStackInSlot(fuelSlotNumber));
				this.slots.decrStackSize(fuelSlotNumber, 1); // decreaseStackSize()
				inventoryChanged = true;
				// If the stack size now equals 0 set the slot contents to the
				// items container item. This is for fuel items such as lava
				// buckets so that the bucket is not consumed. If the item dose
				// not have a container item getContainerItem returns null which
				// sets the slot contents to null
				if (slots.getStackInSlot(fuelSlotNumber).getCount() == 0) { // getStackSize()
					slots.setStackInSlot(fuelSlotNumber, slots.getStackInSlot(fuelSlotNumber).getItem()
							.getContainerItem(slots.getStackInSlot(fuelSlotNumber)));
				}
			}
		}

		if (inventoryChanged)
			markDirty();
		return burning;
	}

	/**
	 * Returns true if the machine can activate an item, i.e. has a source item,
	 * destination stack isn't full, etc.
	 */

	/**
	 * determines if the item in the input slot can be activated and if there is
	 * a place to put it afterwards. ie an open output slot
	 */

	protected boolean canUse() {

		for (int index : getInputSlotIndices()) {
			if (this.slots.getStackInSlot(index).isEmpty()) {
				return false;
			}
		}
		if (!(this instanceof TEFluidUser)) {
			ItemStack result = RecipeHandler.getItemOutput(this.getRecipeList(), getInputSlotItemStacks());

			if (result == null || result.isEmpty()) {
				return false;
			} else if (this instanceof TEFluidProducer) {
				return true;
			} else {
				return getOutSlot(result) == -1 ? false : true;
			}
		} else
			return this.fluidCanUse();

	}

	public boolean fluidCanUse() {
		return false;
	}

	public ArrayList<EOPRecipe> getRecipeList() {
		return null;
	}

	/**
	 * @return array of ints corresponding to input slots and base slot indices
	 */
	public int[] getInputSlotIndices() {
		int[] stack = new int[this.slotHelper.getInSlotSize() + this.slotHelper.getBaseSlotSize()];
		int count = 0;
		for (int i = 0; i < this.slotHelper.getInSlotSize(); i++) {
			stack[i] = this.slotHelper.getInSlotIndex(i);
			count++;
		}
		if (count < stack.length - 1) {
			for (int i = 0; i < this.slotHelper.getBaseSlotSize(); i++) {
				stack[count] = this.slotHelper.getBaseSlotIndex(i);
				count++;
			}
		}
		return stack;
	}

	/**
	 * @return array of ItemStacks corresponding to input slots and base slots
	 */
	public ItemStack[] getInputSlotItemStacks() {
		ItemStack[] stack = new ItemStack[this.slotHelper.getInSlotSize() + this.slotHelper.getBaseSlotSize()];
		int count = 0;
		for (int i = 0; i < this.slotHelper.getInSlotSize(); i++) {
			stack[i] = this.slots.getStackInSlot(this.slotHelper.getInSlotIndex(i));
			count++;
		}
		if (count <= stack.length - 1) {
			for (int i = 0; i < this.slotHelper.getBaseSlotSize(); i++) {
				stack[count] = this.slots.getStackInSlot(this.slotHelper.getBaseSlotIndex(i));
				count++;
			}
		}
		return stack;
	}

	/**
	 * @param itemstack
	 *            ItemStack which is the result stack
	 * @return index of available output slot or -1 if none are available
	 */
	public int getOutSlot(ItemStack itemstack) {
		ItemStack outSlot;
		boolean slotUsable = false;
		for (int i = 0; i < this.slotHelper.getOutSlotSize(); i++) {
			outSlot = this.slots.getStackInSlot(this.slotHelper.getOutSlotIndex(i));
			if (outSlot.isEmpty()) {
				slotUsable = true;
			} else if (!outSlot.isItemEqual(itemstack)) {
				slotUsable = false;
			} else {
				int result = outSlot.getCount() + itemstack.getCount();
				slotUsable = result <= this.slots.getSlotLimit(this.slotHelper.getOutSlotIndex(i))
						&& result <= outSlot.getMaxStackSize();
			}
			if (slotUsable)
				return this.slotHelper.getOutSlotIndex(i);

		}
		return -1;
	}

	/**
	 * Turn one item from the inventory input stack into the appropriate output
	 * item in the result stack
	 */
	public void useItem() {

		ItemStack[] input = getInputSlotItemStacks();
		ItemStack result = RecipeHandler.getItemOutput(this.getRecipeList(), getInputSlotItemStacks());
		int outIndex = this.getOutSlot(result);
		ItemStack output = null;// TODO risky
		if (outIndex != -1) {
			output = this.slots.getStackInSlot(outIndex);
			if (output.isEmpty()) {
				this.slots.setStackInSlot(outIndex, result.copy());
			} else if (output.getItem() == result.getItem()) {
				output.grow(result.getCount());
			}

			for (ItemStack stack : input) {
				stack.shrink(1);
			}
		}
		this.useFluid(input);
	}

	public void useFluid(ItemStack[] input) {

	}

	/**
	 * Returns the number of ticks that the supplied fuel item will keep the
	 * activationChamber burning, or 0 if the item isn't fuel
	 */
	public Item getFuel(int i) {
		return null;
	}

	public int getFuelSize() {
		return 0;
	}

	public int getFuelTime(int i) {
		return 0;
	}

	public int getFuelBurnTime(ItemStack stack) {
		if (stack.isEmpty()) {
			return 0;
		} else {
			Item item = stack.getItem();
			for (int i = 0; i < this.getFuelSize(); i++) {
				if (this.getFuel(i).equals(item))
					return this.getFuelTime(i);
			}

			return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
		}
	}

	protected void resetUpgradeStats() {
		ticksNeeded = UpgradeHelper.getTicks(this, BASE_TICKS_NEEDED);
		energyPerTick = UpgradeHelper.getEnergyPerTick(this, BASE_ENERGY_PER_TICK);
	}

	/** determines if an item is acceptable fuel for the machine */
	public boolean isItemFuel(ItemStack stack) {
		return this.getFuelBurnTime(stack) > 0;
	}

	/**
	 * Don't rename this method to canInteractWith due to conflicts with
	 * Container
	 */
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(this.pos) != this ? false
				: player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot. For guis use Slot.isItemValid
	 */
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		System.out.println("isItemValidForSlot TEPowered");
		// cant insert into output slot
		for (int indexes : this.slotHelper.getOut()) {
			if (index == indexes)
				return false;
		}
		return true;
	}

	/**
	 * Returns true if automation can insert the given item in the given slot
	 * from the given side.
	 */
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		System.out.println("canInsertItem TEPowered");
		for (int i : this.slotHelper.getOut()) {
			if (i == index)
				return false;
		}
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public int getComparatorStrength() {
		float calc = ((float) this.storage.getEnergyStored() / (float) this.storage.getMaxEnergyStored()) * 15F;
		return (int) calc;
	}

	@Override
	public IEnergyStorage getEnergyStorage(EnumFacing facing) {
		return this.storage;
	}

	@Override
	public boolean isRedstoneToggle() {
		return true;
	}
}