package com.jack12324.eop.machine;
import com.jack12324.eop.ExtremeOreProcessing;
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
import net.minecraftforge.fluids.FluidUtil;

public abstract class BlockTE<TE extends TileEntity> extends BlockTileEntity<TE>{

  public BlockTE(Material material, String name)
  {
    super(material,name);
  }

  public static final PropertyDirection PROPERTYFACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
  public static final PropertyBool PROPERTYACTIVE = PropertyBool.create("on");

 
  protected boolean tryUseItemOnTank(EntityPlayer player, EnumHand hand, World world, BlockPos pos, EnumFacing side){
     System.out.println("tryUseItemOnTank");
          return FluidUtil.interactWithFluidHandler(player, hand, world, pos, null);
      
  }
  @Override
  public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing par6, float par7, float par8, float par9){
      if(!world.isRemote){
          TileEntity te = (TileEntity)world.getTileEntity(pos);
          if(te != null){
              if(!this.tryUseItemOnTank(player, hand, world, pos,null)&&this.getGui()!=-1){
              	player.openGui(ExtremeOreProcessing.instance, this.getGui(), world, pos.getX(), pos.getY(), pos.getZ());
              }
          }
          return true;
      }
      return true;
  }
  protected int getGui(){
	  return -1;
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