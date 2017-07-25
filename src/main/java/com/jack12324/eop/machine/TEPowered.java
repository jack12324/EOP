package com.jack12324.eop.machine;

import com.jack12324.eop.ExtremeOreProcessing;
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
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Map;

public abstract class TEPowered extends TEInventory {
    private final double BASE_TICKS_NEEDED = 200;
    private double ticksNeeded = BASE_TICKS_NEEDED;
    private final double BASE_ENERGY_PER_TICK = 50;
    private double energyPerTick = BASE_ENERGY_PER_TICK;
    private int burnTimeInitialValue;
    private int burnTimeRemaining;
    private int[] inProgressTime;
    private final int[] oldValues;
    private int fuelMultiplier = 1;
    private boolean hasBase;
    private final boolean usesFuel;
    private int oldEnergy;

    public final EOPEnergyStorage storage;

    private boolean lastActive = false;

    protected TEPowered(String name, InventorySlotHelper slots) {
        this(name, slots, 100000, 100000, 0);
    }

    private TEPowered(String name, InventorySlotHelper slots, int capacity, int recieve, int extract) {

        super(new InventorySlotHelper(slots, 2), name);
        inProgressTime = new int[slots.getInSlotSize()];
        this.hasBase = slots.getBaseSlotSize() > 0;
        this.usesFuel = slots.getFuelSlotSize() > 0;
        storage = new EOPEnergyStorage(capacity, recieve, extract);
        oldValues = new int[2 + inProgressTime.length];
    }

    /**
     * handles fuel usage, also returns true if fuel is currently being used
     */
    private boolean burnFuel() {
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

    /**
     * determines if the item in the input slot can be activated and if there is
     * a place to put it afterwards. ie an open output slot
     */

    protected boolean canUse(int IOSlot) {
        if (this.getInputSlotItemStacks(IOSlot).length == 0 || this.getInputSlotItemStacks(IOSlot) == null) {
            return false;
        }
        ItemStack result = RecipeHandler.getItemStackOutput(this.getRecipeList(), getInputSlotItemStacks(IOSlot), getBase(), null);

        return result != null && !result.isEmpty() && getOutSlot(result) != -1;
    }

    /**
     * returns the amount of fuel remaining on the currently burning item
     */
    public double fractionOfFuelRemaining() {
        if (burnTimeInitialValue <= 0) {
            return 0;
        }
        double fraction = burnTimeRemaining / (double) burnTimeInitialValue;
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    public double fractionOfPowerRemaining() {
        if (this.storage.getEnergyStored() <= 0)
            return 0;
        double fraction = this.storage.getEnergyStored() / (double) this.storage.getMaxEnergyStored();
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    /**
     * Returns the amount of cook time completed on the currently cooking item.
     */
    public double fractionOfProgressTimeComplete(int index) {
        double fraction = this.inProgressTime[index] / getTicksNeeded();
        return MathHelper.clamp(fraction, 0.0, 1.0);
    }

    ItemStack getBase() {
        return this.hasBase ? this.slots.getStackInSlot(this.slotHelper.getBaseSlotIndex(0)) : null;
    }

    /**
     * @return array of Itemstacks from all base slots
     */
    public ItemStack[] getBaseSlotItemStacks() {
        ItemStack[] stack = new ItemStack[this.slotHelper.getBaseSlotSize()];
        for (int i = 0; i < stack.length; i++) {
            stack[i] = this.slots.getStackInSlot(this.slotHelper.getBaseSlotIndex(i));
        }
        return stack;
    }

    public double getEnergyPerTick() {
        return energyPerTick;
    }

    @Override
    public IEnergyStorage getEnergyStorage(EnumFacing facing) {
        return this.storage;
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the
     * activationChamber burning, or 0 if the item isn't fuel
     */
    protected Item getFuel(int i) {
        return null;
    }

    private int getFuelBurnTime(ItemStack stack) {
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

    protected int getFuelSize() {
        return 0;
    }

    protected int getFuelTime(int i) {
        return 0;
    }

    public int getInProgressTime(int index) {
        return inProgressTime[index];
    }

    /**
     * @return array of ItemStacks corresponding to input slots
     */
    ItemStack[] getInputSlotItemStacks(int slotPair) {
        if (this instanceof IOPairs && !this.EQSOverride()) {
            return ((IOPairs) this).getCurrentInputStacks(slotPair);
        } else {
            ItemStack[] stack = new ItemStack[this.slotHelper.getInSlotSize()];
            for (int i = 0; i < stack.length; i++) {
                stack[i] = this.slots.getStackInSlot(this.slotHelper.getInSlotIndex(i));
            }
            return stack;
        }
    }

    public ItemStack getInventory(int i) {
        return this.slots.getStackInSlot(i);
    }

    /**
     * @param itemstack ItemStack which is the result stack
     * @return index of available output slot or -1 if none are available
     */
    int getOutSlot(ItemStack itemstack) {
        ItemStack outSlot;
        boolean slotUsable;
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

    protected ArrayList<EOPRecipe> getRecipeList() {
        ExtremeOreProcessing.LOGGER.warn("EOP Warning: Sending empty recipe list");
        return new ArrayList<>();
    }

    private double getTicksNeeded() {
        return ticksNeeded;
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

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring
     * stack size) into the given slot. For guis use Slot.isItemValid
     */
    @Override
    boolean isItemValidForSlot(int index, ItemStack stack) {
        System.out.println("isItemValidForSlot TEPowered");
        // cant insert into output slot
        for (int indexes : this.slotHelper.getOut()) {
            if (index == indexes)
                return false;
        }
        return true;
    }

    /**
     * Don't rename this method to canInteractWith due to conflicts with
     * Container
     */
    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
    }

    private void oldActiveCheck(boolean active) {
        if (active != this.lastActive) {
            IBlockState currState = this.world.getBlockState(this.pos);
            if (currState.getValue(BlockTE.PROPERTYACTIVE) != (active)) {
                this.world.setBlockState(this.pos, currState.withProperty(BlockTE.PROPERTYACTIVE, active));
            }

            this.lastActive = active;
            markDirty();
        }
    }

    private void oldEnergyCheck() {
        if (this.oldEnergy != this.storage.getEnergyStored() && this.sendUpdateWithInterval()) {
            this.oldEnergy = this.storage.getEnergyStored();
            markDirty();

        }
    }

    protected void oldProgressTimeCheck() {
        boolean somethingChanged = false;
        for (int i = 0; i < oldValues.length; i++) {
            if (i == 0 && this.burnTimeInitialValue != this.oldValues[i] && this.sendUpdateWithInterval()) {
                this.oldValues[i] = this.burnTimeInitialValue;
                somethingChanged = true;
            } else if (i == 1 && this.burnTimeRemaining != this.oldValues[i] && this.sendUpdateWithInterval()) {
                this.oldValues[i] = this.burnTimeRemaining;
                somethingChanged = true;
            } else if (i > 1 && this.inProgressTime[i - 2] != this.oldValues[i] && this.sendUpdateWithInterval()) {
                this.oldValues[i] = this.inProgressTime[i - 2];
                somethingChanged = true;
            }
        }
        if (somethingChanged)
            markDirty();
    }

    @Override
    public void readSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        slots.deserializeNBT(compound.getCompoundTag("inventory"));
        this.burnTimeInitialValue = usesFuel
                ? getFuelBurnTime(this.slots.getStackInSlot(this.slotHelper.getFuelSlotIndex(0))) : 0;
        this.burnTimeRemaining = compound.getInteger("BurnTime");
        this.hasBase = compound.getBoolean("hasBase");//todo probably dont need
        this.inProgressTime = compound.getIntArray("ProgressTime").clone();
        this.energyPerTick = compound.getDouble("energyPerTick");
        this.ticksNeeded = compound.getDouble("ticksNeeded");

        this.storage.readFromNBT(compound);
        super.readSyncableNBT(compound, shouldSync);
    }

    protected void resetTime(int progressBar) {
        inProgressTime[progressBar] = 0;
    }

    private void resetUpgradeStats() {
        ticksNeeded = UpgradeHelper.getTicks(this, BASE_TICKS_NEEDED);
        energyPerTick = UpgradeHelper.getEnergyPerTick(this, BASE_ENERGY_PER_TICK);
    }

    /**
     * return the remaining burn time of the fuel in the given slot
     */
    public int secondsOfFuelRemaining() {
        if (burnTimeRemaining <= 0 || fuelMultiplier <= 0)
            return 0;
        return (int) (burnTimeRemaining / (20 * fuelMultiplier * BASE_TICKS_NEEDED / getTicksNeeded()));
    }

    protected void setInProgressTime(int index, int value) {
        inProgressTime[index] = value;
    }

    public void setInventory(int i, ItemStack itemStack) {
        this.slots.setStackInSlot(i, itemStack);
    }


    @Override
    public void updateEntity() {
        super.updateEntity();
        // If there is nothing to smelt or there is no room in the output, reset
        // cookTime and return
        boolean active = false;
        if (!this.world.isRemote) {
            if (!(this instanceof IOPairs) || this.EQSOverride()) {
                if (canUse(-1)) {
                    active = this.useLogic(0);
                } else {
                    this.resetTime(0);
                }
                this.resetUpgradeStats();
                oldEnergyCheck();
                oldActiveCheck(active);
                oldProgressTimeCheck();
            } else {
                for (int i = 0; i < ((IOPairs) this).getIONumber(); i++) {
                    if (canUse(i)) {
                        {
                            if (!active)
                                active = this.useLogic(i);
                            else
                                this.useLogic(i);
                        }
                    } else {
                        this.resetTime(i);
                    }
                    this.resetUpgradeStats();
                    this.oldActiveCheck(active);
                    this.oldEnergyCheck();
                    this.oldProgressTimeCheck();
                }
            }
        }

    }

    protected boolean EQSOverride() {
        return false;
    }

    void useFluid(ItemStack[] input, ItemStack base) {

    }

    /**
     * Turn one item from the inventory input stack into the appropriate output
     * item in the result stack
     */
    public void useItem(int IOSet) {

        ItemStack[] input = getInputSlotItemStacks(IOSet);
        ItemStack base = this.getBase();
        ItemStack result = this.getResult(input);
        int outIndex = this.getOutSlot(result);
        Map<Item, Integer> inputs;
        ItemStack output;
        if (outIndex != -1 && result != null && !result.isEmpty()) {
            inputs = RecipeHandler.getRecipeItemInputs(this.getRecipeList(), input, base, this.getInFluid());
            if (inputs != null) {
                output = this.slots.getStackInSlot(outIndex);
                if (output.isEmpty()) {
                    this.slots.setStackInSlot(outIndex, result.copy());
                } else if (output.getItem() == result.getItem()) {
                    output.grow(result.getCount());
                }
                this.useFluid(input, getBase());
                for (ItemStack stack : input) {
                    if (inputs.containsKey(stack.getItem())) {
                        stack.shrink(inputs.get(stack.getItem()));
                    }
                }
                if (base != null && !base.isEmpty())
                    base.shrink(inputs.get(base.getItem()));
            }

        } else if (this instanceof TEFluidProducer) {
            inputs = RecipeHandler.getRecipeItemInputs(this.getRecipeList(), input, base, this.getInFluid());
            if (inputs != null) {
                this.useFluid(input, getBase());
                for (ItemStack stack : input) {
                    if (inputs.containsKey(stack.getItem())) {
                        stack.shrink(inputs.get(stack.getItem()));
                    }
                    if (base != null && !base.isEmpty())
                        base.shrink(inputs.get(base.getItem()));
                }
            }
        }
    }

    ItemStack getResult(ItemStack[] input) {
        return RecipeHandler.getItemStackOutput(this.getRecipeList(), input, getBase(), this.getInFluid());
    }

    FluidStack getInFluid() {
        return null;
    }

    protected boolean useLogic(int IOSet) {
        boolean burning;
        boolean powered;
        boolean active;
        burning = true;
        powered = usePower();
        active = false;
        if (usesFuel && powered) {
            burning = burnFuel();
        }

        // If fuel is available, keep cooking the item, otherwise start
        // "uncooking" it at double speed
        if (burning && powered) {
            inProgressTime[IOSet] += 1;
            active = true;
        } else {
            inProgressTime[IOSet] -= 2;
        }

        if (inProgressTime[IOSet] < 0)
            inProgressTime[IOSet] = 0;

        // If cookTime has reached maxCookTime smelt the item and reset
        // cookTime
        if (inProgressTime[IOSet] >= getTicksNeeded()) {
            useItem(IOSet);
            inProgressTime[IOSet] = 0;
            markDirty();

        }
        return active;
    }

    private boolean usePower() {
        boolean powered = false;

        if (this.storage.getEnergyStored() >= getEnergyPerTick()) {
            this.storage.extractEnergyInternal((int) getEnergyPerTick(), false);
            powered = true;

        }
        return powered;
    }

    @Override
    public void writeSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        compound.setTag("inventory", slots.serializeNBT());
        compound.setInteger("BurnTime", this.burnTimeRemaining);
        compound.setIntArray("ProgressTime", this.inProgressTime);
        compound.setInteger("burnTimeInitialValue", burnTimeInitialValue);
        compound.setBoolean("hasBase", this.hasBase);
        compound.setDouble("energyPerTick", energyPerTick);
        compound.setDouble("ticksNeeded", getTicksNeeded());

        this.storage.writeToNBT(compound);
        super.writeSyncableNBT(compound, shouldSync);

    }
}