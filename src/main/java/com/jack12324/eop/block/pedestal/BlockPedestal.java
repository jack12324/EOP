package com.jack12324.eop.block.pedestal;

import javax.annotation.Nullable;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.ModGuiHandler;
import com.jack12324.eop.block.BlockTileEntity;
import com.jack12324.eop.machine.BlockTE;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockPedestal extends BlockTE<TileEntityPedestal>{
	
	public BlockPedestal(){
		super(Material.ROCK,"pedestal");
	}
	
	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state){
		return false;
	}
	
	@Override
	public Class<TileEntityPedestal> getTileEntityClass(){
		return TileEntityPedestal.class;
	}
	
	@Nullable
	@Override
	public TileEntityPedestal createTileEntity(World world, IBlockState state) {
		return new TileEntityPedestal();
	}
	@Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing par6, float par7, float par8, float par9){
        if(!world.isRemote){
            TileEntityPedestal te = (TileEntityPedestal)world.getTileEntity(pos);
            if(te != null){
                if(true){//!this.tryUseItemOnTank(player, hand, te.tank)){
                	System.out.println("tap");
                	player.openGui(ExtremeOreProcessing.instance, ModGuiHandler.PEDESTAL, world, pos.getX(), pos.getY(), pos.getZ());
                }
            }
            return true;
        }
        return true;
    }

	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state){
		TileEntityPedestal tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = ((IItemHandler) itemHandler).getStackInSlot(0);
		if (!stack.isEmpty()){
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(item);
		}
		super.breakBlock(world, pos, state);
	}
	
	

}
