package com.jack12324.eop.machine.activationChamber;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.IButtonUse;
import com.jack12324.eop.machine.TEPowered;
import com.jack12324.eop.recipe.RecipeHolder;
import com.jack12324.eop.recipe.recipeInterfaces.EOPRecipe;
import com.jack12324.eop.util.InventorySlotHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TileEntityActivationChamber extends TEPowered implements IButtonUse {

    public static final List<Item> fuel = new ArrayList<>(
            Arrays.asList(ModItems.dustFirestone, Item.getItemFromBlock(ModBlocks.blockFirestone)));
    private final List<Integer> fuelTime = new ArrayList<>(Arrays.asList(400, 4000));
    private int[] sideIO = {0, 0, 0, 0, 0, 0};

    public TileEntityActivationChamber() {
        super("activation_chamber", new InventorySlotHelper(1, 1, 1, 0, 0));
    }

    @Override
    public Item getFuel(int i) {
        return fuel.get(i);
    }

    @Override
    public int getFuelSize() {
        return fuel.size();
    }

    @Override
    public int getFuelTime(int i) {
        return fuelTime.get(i);
    }

    @Override
    public ArrayList<EOPRecipe> getRecipeList() {
        return RecipeHolder.ACTIVATIONCHAMBERRECIPES;
    }

    @Override
    public void onButtonPress(int buttonId) {
        IBlockState currState = this.world.getBlockState(this.pos);
        if (buttonId == 71)
            this.world.setBlockState(this.pos, currState.withProperty(BlockActivationChamber.FRONTIO, this.rotateSideIOVal(0, currState)));
        else if (buttonId == 72)
            this.world.setBlockState(this.pos, currState.withProperty(BlockActivationChamber.LEFTIO, this.rotateSideIOVal(1, currState)));
        else if (buttonId == 73)
            this.world.setBlockState(this.pos, currState.withProperty(BlockActivationChamber.RIGHTIO, this.rotateSideIOVal(2, currState)));
        else if (buttonId == 74)
            this.world.setBlockState(this.pos, currState.withProperty(BlockActivationChamber.TOPIO, this.rotateSideIOVal(3, currState)));
        else if (buttonId == 75)
            this.world.setBlockState(this.pos, currState.withProperty(BlockActivationChamber.BOTTOMIO, this.rotateSideIOVal(4, currState)));
        else if (buttonId == 76)
            this.world.setBlockState(this.pos, currState.withProperty(BlockActivationChamber.BACKIO, this.rotateSideIOVal(5, currState)));
        else
            ExtremeOreProcessing.LOGGER.warn(buttonId + " is not a valid button id for " + this.getDisplayedName());
        this.setSidesFromState(this.world.getBlockState(this.pos));
        this.sendTileUpdate();
    }

    private int rotateSideIOVal(int side, IBlockState state) {
        int val;
        switch (side) {
            case 0:
                val = state.getValue(BlockActivationChamber.FRONTIO);
            case 1:
                val = state.getValue(BlockActivationChamber.LEFTIO);
            case 2:
                val = state.getValue(BlockActivationChamber.RIGHTIO);
            case 3:
                val = state.getValue(BlockActivationChamber.TOPIO);
            case 4:
                val = state.getValue(BlockActivationChamber.BOTTOMIO);
            case 5:
                val = state.getValue(BlockActivationChamber.BACKIO);
            default:
                val = 5;
        }
        return val == 5 ? 0 : val + 1;
    }

    private void setSidesFromState(IBlockState state) {
        sideIO[0] = state.getValue(BlockActivationChamber.FRONTIO);
        sideIO[1] = state.getValue(BlockActivationChamber.LEFTIO);
        sideIO[2] = state.getValue(BlockActivationChamber.RIGHTIO);
        sideIO[3] = state.getValue(BlockActivationChamber.TOPIO);
        sideIO[4] = state.getValue(BlockActivationChamber.BOTTOMIO);
        sideIO[5] = state.getValue(BlockActivationChamber.BACKIO);
    }

    @Override
    public void readSyncableNBT(NBTTagCompound compound, boolean shouldSync) {

        this.sideIO = compound.getIntArray("SideIO").clone();
        super.readSyncableNBT(compound, shouldSync);
    }

    @Override
    public void writeSyncableNBT(NBTTagCompound compound, boolean shouldSync) {

        compound.setIntArray("SideIO", this.sideIO);
        super.writeSyncableNBT(compound, shouldSync);

    }
}