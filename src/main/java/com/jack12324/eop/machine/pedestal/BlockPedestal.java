package com.jack12324.eop.machine.pedestal;

import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.BlockTE;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockPedestal extends BlockTE<TileEntityPedestal> {

    public BlockPedestal() {
        super(Material.ROCK, "pedestal");
    }

    @Override
    public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        TileEntityPedestal tile = getTileEntity(world, pos);
        IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
        ItemStack stack = itemHandler != null ? itemHandler.getStackInSlot(0) : null;
        if (stack != null && !stack.isEmpty()) {
            EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            world.spawnEntity(item);
        }
        super.breakBlock(world, pos, state);
    }

    @Nullable
    @Override
    public TileEntityPedestal createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new TileEntityPedestal();
    }

    @Override
    public Class<TileEntityPedestal> getTileEntityClass() {
        return TileEntityPedestal.class;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    protected int getGui() {
        return ModGuiHandler.PEDESTAL;
    }


    public boolean isFullCube(IBlockState state) {
        return false;
    }

}
