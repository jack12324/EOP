package com.jack12324.eop.machine.activationChamber;

import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.BlockTE;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockActivationChamber extends BlockTE<TileEntityActivationChamber> {


    public BlockActivationChamber() {
        super(Material.ROCK, "activation_chamber");
    }

    // drop items in block as well as block
    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        TileEntityActivationChamber tile = getTileEntity(world, pos);
        if (tile instanceof IInventory) {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
        }
        super.breakBlock(world, pos, state);
    }

    @Nullable
    @Override
    public TileEntityActivationChamber createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new TileEntityActivationChamber();
    }

    @Override
    protected int getGui() {
        return ModGuiHandler.ACTIVATIONCHAMBER;
    }

    @Override
    public Class<TileEntityActivationChamber> getTileEntityClass() {
        return TileEntityActivationChamber.class;
    }



}