package com.jack12324.eop.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class BlockTileEntity<TE extends TileEntity> extends BlockBase {

    protected BlockTileEntity(Material material, String name) {
        super(material, name);
    }

    @Nullable
    @Override
    public abstract TE createTileEntity(@Nonnull World world, @Nonnull IBlockState state);

    protected TE getTileEntity(IBlockAccess world, BlockPos pos) {
        return (TE) world.getTileEntity(pos);
    }

    public abstract Class<TE> getTileEntityClass();

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

}