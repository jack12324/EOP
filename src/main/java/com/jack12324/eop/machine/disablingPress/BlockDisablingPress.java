package com.jack12324.eop.machine.disablingPress;

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

public class BlockDisablingPress extends BlockTE<TileEntityDisablingPress> {

    public BlockDisablingPress() {
        super(Material.ROCK, "disabling_press");

    }

    // drop items in block as well as block
    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        TileEntityDisablingPress tile = getTileEntity(world, pos);
        if (tile instanceof IInventory) {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
        }
        super.breakBlock(world, pos, state);
    }

    @Nullable
    @Override
    public TileEntityDisablingPress createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new TileEntityDisablingPress();
    }

    @Override
    protected int getGui() {
        return ModGuiHandler.DISABLINGPRESS;
    }

    @Override
    public Class<TileEntityDisablingPress> getTileEntityClass() {
        return TileEntityDisablingPress.class;
    }

}