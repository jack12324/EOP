package com.jack12324.eop.machine;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.packet.PacketHandler;
import com.jack12324.eop.packet.PacketServerToClient;
import com.jack12324.eop.util.Coord4D;
import com.jack12324.eop.util.compat.TeslaForgeUnitsWrapper;
import com.jack12324.eop.util.compat.TeslaUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public abstract class TETickingMachine extends TileEntity implements ITickable {


    private final String name;

    private int ticksElapsed;

    private final TileEntity[] tilesAround = new TileEntity[6];

    private boolean hasSavedDataOnChangeOrWorldStart;

    private Object teslaWrapper;

    TETickingMachine(String name) {
        this.name = name;
    }

    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            IItemHandler handler = this.getItemHandler(facing);
            if (handler != null) {
                return (T) handler;
            }
        } else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            IFluidHandler tank = this.getFluidHandler(facing);
            if (tank != null) {
                return (T) tank;
            }
        } else if (capability == CapabilityEnergy.ENERGY) {
            IEnergyStorage storage = this.getEnergyStorage(facing);
            if (storage != null) {
                return (T) storage;
            }
        } else if (ExtremeOreProcessing.teslaLoaded) {//TODO might not need?
            if (capability == TeslaUtil.teslaConsumer || capability == TeslaUtil.teslaProducer
                    || capability == TeslaUtil.teslaHolder) {
                IEnergyStorage storage = this.getEnergyStorage(facing);
                if (storage != null) {
                    if (this.teslaWrapper == null) {
                        this.teslaWrapper = new TeslaForgeUnitsWrapper(storage);
                    }
                    return (T) this.teslaWrapper;
                }
            }
        }
        return super.getCapability(capability, facing);
    }


    public String getDisplayedName() {
        return I18n.format("tile." + name + ".name");
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(this.getDisplayedName());
    }

    IEnergyStorage getEnergyStorage(EnumFacing facing) {
        return null;
    }

    IFluidHandler getFluidHandler(EnumFacing facing) {
        return null;
    }

    IItemHandler getItemHandler(EnumFacing facing) {
        return null;
    }

    @Override
    public final SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeSyncableNBT(compound, true);
        return new SPacketUpdateTileEntity(this.pos, -1, compound);
    }

    @Nonnull
    @Override
    public final NBTTagCompound getUpdateTag() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeSyncableNBT(compound, true);
        return compound;
    }

    @Override
    public final void handleUpdateTag(@Nonnull NBTTagCompound compound) {
        this.readSyncableNBT(compound, true);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        return this.getCapability(capability, facing) != null;
    }

    @Override
    public final void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.readSyncableNBT(pkt.getNbtCompound(), true);
    }

    @Override
    public final void readFromNBT(NBTTagCompound compound) {
        this.readSyncableNBT(compound, false);
    }

    public void readSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        super.readFromNBT(compound);
        if (!shouldSync) {
            this.ticksElapsed = compound.getInteger("TicksElapsed");
        }
    }

    private void saveDataOnChangeOrWorldStart() {
        for (EnumFacing side : EnumFacing.values()) {
            BlockPos pos = this.pos.offset(side);
            if (this.world.isBlockLoaded(pos)) {
                this.tilesAround[side.ordinal()] = this.world.getTileEntity(pos);
            }
        }
    }

    final void sendTileUpdate() {
        if (this.world != null && !this.world.isRemote) {
            NBTTagCompound compound = new NBTTagCompound();
            this.writeSyncableNBT(compound, true);

            NBTTagCompound data = new NBTTagCompound();
            data.setTag("Data", compound);
            Coord4D coord4D = new Coord4D(this.getPos(), this.getWorld());
            data = coord4D.write(data);
            PacketHandler.NETWORK.sendToAllAround(new PacketServerToClient(data, PacketHandler.TILE_ENTITY_HANDLER),
                    new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), this.getPos().getX(),
                            this.getPos().getY(), this.getPos().getZ(), 64));
        }
    }

    protected boolean sendUpdateWithInterval() {
        if (this.ticksElapsed % 5 == 0) {
            this.sendTileUpdate();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, @Nonnull IBlockState oldState, @Nonnull IBlockState newState) {
        return !oldState.getBlock().isAssociatedBlock(newState.getBlock());
    }

    private boolean shouldSaveDataOnChangeOrWorldStart() {
        return true;
    }

    @Override
    public final void update() {
        this.updateEntity();
    }

    public void updateEntity() {
        this.ticksElapsed++;

        if (!this.world.isRemote) {

            if (!this.hasSavedDataOnChangeOrWorldStart) {
                if (this.shouldSaveDataOnChangeOrWorldStart()) {
                    this.saveDataOnChangeOrWorldStart();
                }

                this.hasSavedDataOnChangeOrWorldStart = true;
            }
        }
    }

    void writeSyncableNBT(NBTTagCompound compound, boolean shouldSync) {
        super.writeToNBT(compound);

        if (!shouldSync) {
            compound.setInteger("TicksElapsed", this.ticksElapsed);
        }
    }

    @Nonnull
    @Override
    public final NBTTagCompound writeToNBT(NBTTagCompound compound) {
        this.writeSyncableNBT(compound, false);
        return compound;
    }
}