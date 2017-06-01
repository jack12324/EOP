package com.jack12324.eop.machine;
import com.jack12324.eop.block.BlockTileEntity;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidUtil;

/**
 * User: The Grey Ghost
 * Date: 24/12/2014
 *
 * BlockVariants uses a model which
 * - doesn't occupy the entire 1x1x1m space,
 * - is made up of two pieces,
 * - uses a CUTOUT texture (with seethrough holes)
 * - has variants (can face in four directions, and can be four different colours)
 * We can walk over it without colliding.
 * For background information on blocks see here http://greyminecraftcoder.blogspot.com.au/2014/12/blocks-18.html
 * For a couple of the methods below the Forge guys have marked it as deprecated.  But you still need to override those
 *   "deprecated" block methods.  What they mean is "when you want to find out if a block is (eg) isOpaqueCube(),
 *   don't call block.isOpaqueCube(), call iBlockState.isOpaqueCube() instead".
 * If that doesn't make sense to you yet, don't worry.  Just ignore the "deprecated method" warning.
 */
public abstract class BlockTE<TE extends TileEntity> extends BlockTileEntity<TE>{

  public BlockTE(Material material, String name)
  {
    super(material,name);
  }

  public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
  public static final PropertyBool PROPERTYACTIVE = PropertyBool.create("on");

 
  protected boolean tryUseItemOnTank(EntityPlayer player, EnumHand hand, FluidTank tank){
      ItemStack heldItem = player.getHeldItem(hand);

      if(heldItem !=null && !heldItem.isEmpty()){
          FluidActionResult result = FluidUtil.interactWithFluidHandler(heldItem, tank, player);
          if(result.isSuccess()){
              player.setHeldItem(hand, result.getResult());
              return true;
          }
      }

      return false;
  }

 
  @Override
  public IBlockState getStateFromMeta(int meta){
      boolean active = meta >= 4;
      EnumFacing facing = EnumFacing.getHorizontal(active ? meta-4 : meta);
      return this.getDefaultState().withProperty(BlockHorizontal.FACING, facing).withProperty(PROPERTYACTIVE, active);
  }

  @Override
  public int getMetaFromState(IBlockState state){
      int meta = state.getValue(BlockHorizontal.FACING).getHorizontalIndex();
      return state.getValue(PROPERTYACTIVE) ? meta+4 : meta;
  }

  
  @Override
  public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
  {
    return state;
  }

  
  @Override
  protected BlockStateContainer createBlockState()
  {
    return new BlockStateContainer(this, PROPERTYFACING, PROPERTYACTIVE);
  }
  
  @Override
  public IBlockState withRotation(IBlockState state, Rotation rot){
      return state.withProperty(BlockHorizontal.FACING, rot.rotate(state.getValue(BlockHorizontal.FACING)));
  }

  @Override
  public IBlockState withMirror(IBlockState state, Mirror mirror){
      return this.withRotation(state, mirror.toRotation(state.getValue(BlockHorizontal.FACING)));
  }

  
  @Override
  public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase player, ItemStack stack){
      world.setBlockState(pos, state.withProperty(PROPERTYFACING, player.getHorizontalFacing().getOpposite()), 2);

      super.onBlockPlacedBy(world, pos, state, player, stack);
  }



}