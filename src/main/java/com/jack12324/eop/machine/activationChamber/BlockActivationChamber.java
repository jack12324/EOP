package com.jack12324.eop.machine.activationChamber;

import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.machine.BlockTE;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockActivationChamber extends BlockTE<TileEntityActivationChamber> {


    public static final PropertyInteger FRONTIO = PropertyInteger.create("front", 0, 5);
    public static final PropertyInteger LEFTIO = PropertyInteger.create("left", 0, 5);
    public static final PropertyInteger RIGHTIO = PropertyInteger.create("right", 0, 5);
    public static final PropertyInteger TOPIO = PropertyInteger.create("top", 0, 5);
    public static final PropertyInteger BOTTOMIO = PropertyInteger.create("bottom", 0, 5);
    public static final PropertyInteger BACKIO = PropertyInteger.create("back", 0, 5);

    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PROPERTYFACING, PROPERTYACTIVE, FRONTIO, LEFTIO, RIGHTIO, TOPIO, BOTTOMIO, BACKIO);
    }


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

    @Override
    public int getMetaFromState(IBlockState state) {
        StringBuilder builder = new StringBuilder();
        builder.append(state.getValue(BlockHorizontal.FACING).getHorizontalIndex());
        builder.append(state.getValue(PROPERTYACTIVE) ? 0 : 1);
        builder.append(state.getValue(FRONTIO));
        builder.append(state.getValue(LEFTIO));
        builder.append(state.getValue(RIGHTIO));
        builder.append(state.getValue(TOPIO));
        builder.append(state.getValue(BOTTOMIO));
        builder.append(state.getValue(BACKIO));

        return Integer.parseInt(builder.toString());

    }

    @Nonnull
    @Override
    public IBlockState getStateFromMeta(int meta) {
        String string = Integer.toString(meta);

        EnumFacing facing = EnumFacing.getHorizontal(Character.getNumericValue(string.charAt(0)));
        boolean active = Character.getNumericValue(string.charAt(1)) == 0;
        int front = Character.getNumericValue(string.charAt(2));
        int left = Character.getNumericValue(string.charAt(3));
        int right = Character.getNumericValue(string.charAt(4));
        int top = Character.getNumericValue(string.charAt(5));
        int bottom = Character.getNumericValue(string.charAt(6));
        int back = Character.getNumericValue(string.charAt(7));

        return this.getDefaultState().withProperty(BlockHorizontal.FACING, facing).withProperty(PROPERTYACTIVE, active).withProperty(FRONTIO, front).withProperty(LEFTIO, left).withProperty(RIGHTIO, right).withProperty(TOPIO, top).withProperty(BOTTOMIO, bottom).withProperty(BACKIO, back);
    }

}