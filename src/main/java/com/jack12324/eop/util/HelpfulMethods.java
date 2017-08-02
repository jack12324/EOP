package com.jack12324.eop.util;

import com.jack12324.eop.machine.ISideIO;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class HelpfulMethods {
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
        return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }

    public static void doFluidPull(TileEntity teFrom, TileEntity teTo, EnumFacing sideTo) {
        boolean sideCanPull = ((ISideIO) teTo).getSideVal(sideTo) == 3;

        if (sideCanPull) {
            if (teFrom.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo.getOpposite()) && teTo.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo)) {

                IFluidHandler handlerFrom = teFrom.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo.getOpposite());
                IFluidHandler handlerTo = teTo.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo);
                FluidStack drain = handlerFrom.drain(100, false);
                if (drain != null) {
                    int filled = handlerTo.fill(drain.copy(), true);
                    handlerFrom.drain(filled, true);
                }

            }


        }

    }

    public static void doFluidPush(TileEntity teFrom, TileEntity teTo, EnumFacing sideTo) {
        boolean sideCanPull = ((ISideIO) teFrom).getSideVal(sideTo) == 4;

        if (sideCanPull) {
            if (teFrom.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo) && teTo.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo.getOpposite())) {

                IFluidHandler handlerFrom = teFrom.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo);
                IFluidHandler handlerTo = teTo.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo.getOpposite());
                FluidStack drain = handlerFrom.drain(100, false);
                if (drain != null) {
                    int filled = handlerTo.fill(drain.copy(), true);
                    handlerFrom.drain(filled, true);
                }

            }


        }

    }

    public static void doItemPush(TileEntity teFrom, TileEntity teTo, EnumFacing sideTo) {
        boolean sideCanPull = ((ISideIO) teFrom).getSideVal(sideTo) == 2;

        if (sideCanPull) {
            if (teFrom.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, sideTo) && teTo.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, sideTo.getOpposite())) {

                IItemHandler handlerFrom = teFrom.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, sideTo);
                IItemHandler handlerTo = teTo.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, sideTo.getOpposite());
                ItemStack extractResult;
                ItemStack insertResult;
                for (int i = 0; i < handlerFrom.getSlots(); i++) {
                    extractResult = handlerFrom.extractItem(i, 64, true);
                    if (extractResult != null && !extractResult.isEmpty()) {
                        for (int j = 0; j < handlerTo.getSlots(); j++) {
                            insertResult = handlerTo.insertItem(j, extractResult, true);
                            if (insertResult != null) {
                                handlerTo.insertItem(j, extractResult, false);
                                handlerFrom.extractItem(i, insertResult.getCount(), false);
                                break;
                            }
                        }
                    }

                }

            }


        }

    }

    public static void doItemPull(TileEntity teFrom, TileEntity teTo, EnumFacing sideTo) {
        boolean sideCanPull = ((ISideIO) teTo).getSideVal(sideTo) == 1;

        if (sideCanPull) {
            if (teFrom.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, sideTo.getOpposite()) && teTo.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, sideTo)) {

                IItemHandler handlerFrom = teFrom.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, sideTo);
                IItemHandler handlerTo = teTo.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, sideTo.getOpposite());
                ItemStack extractResult;
                ItemStack insertResult;
                for (int i = 0; i < handlerFrom.getSlots(); i++) {
                    extractResult = handlerFrom.extractItem(i, 64, true);
                    if (extractResult != null && !extractResult.isEmpty()) {
                        for (int j = 0; j < handlerTo.getSlots(); j++) {
                            insertResult = handlerTo.insertItem(j, extractResult, true);
                            if (insertResult != null) {
                                handlerTo.insertItem(j, extractResult, false);
                                handlerFrom.extractItem(i, insertResult.getCount(), false);
                                break;
                            }
                        }
                    }

                }

            }


        }

    }
}
