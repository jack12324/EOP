package com.jack12324.eop.machine.triCatalystInfuser;

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

public class BlockTriCatalystInfuser extends BlockTE<TileEntityTriCatalystInfuser> {

    public BlockTriCatalystInfuser() {
        super(Material.ROCK, "tri_catalyst_infuser");

    }

    // drop items in block as well as block
    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        TileEntityTriCatalystInfuser tile = getTileEntity(world, pos);
        if (tile instanceof IInventory) {
            InventoryHelper.dropInventoryItems(world, pos, (IInventory) tile);
        }
        super.breakBlock(world, pos, state);
    }

    @Nullable
    @Override
    public TileEntityTriCatalystInfuser createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new TileEntityTriCatalystInfuser();
    }

    @Override
    protected int getGui() {
        return ModGuiHandler.TRICATALYSTINFUSER;
    }

    @Override
    public Class<TileEntityTriCatalystInfuser> getTileEntityClass() {
        return TileEntityTriCatalystInfuser.class;
    }

}
