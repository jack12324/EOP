package com.jack12324.eop.machine;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.packet.PacketHandler;
import com.jack12324.eop.packet.PacketServerToClient;
import com.jack12324.eop.util.compat.TeslaForgeUnitsWrapper;
import com.jack12324.eop.util.compat.TeslaUtil;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaProducer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public abstract class TETickingMachine extends TileEntity implements ITickable {

	public enum NBTType {
		SAVE_TILE, SYNC, SAVE_BLOCK
	}

	public static void doEnergyInteraction(TileEntity tileFrom, TileEntity tileTo, EnumFacing sideTo, int maxTransfer) {
		if (maxTransfer > 0) {
			EnumFacing opp = sideTo == null ? null : sideTo.getOpposite();
			if (tileFrom.hasCapability(CapabilityEnergy.ENERGY, sideTo)
					&& tileTo.hasCapability(CapabilityEnergy.ENERGY, opp)) {
				IEnergyStorage handlerFrom = tileFrom.getCapability(CapabilityEnergy.ENERGY, sideTo);
				IEnergyStorage handlerTo = tileTo.getCapability(CapabilityEnergy.ENERGY, opp);

				if (handlerFrom != null && handlerTo != null) {
					int drain = handlerFrom.extractEnergy(maxTransfer, true);
					if (drain > 0) {
						int filled = handlerTo.receiveEnergy(drain, false);
						handlerFrom.extractEnergy(filled, false);
						return;
					}
				}
			}

			if (ExtremeOreProcessing.teslaLoaded) {
				if (tileTo.hasCapability(TeslaUtil.teslaConsumer, opp)
						&& tileFrom.hasCapability(TeslaUtil.teslaProducer, sideTo)) {
					ITeslaConsumer handlerTo = tileTo.getCapability(TeslaUtil.teslaConsumer, opp);
					ITeslaProducer handlerFrom = tileFrom.getCapability(TeslaUtil.teslaProducer, sideTo);

					if (handlerTo != null && handlerFrom != null) {
						long drain = handlerFrom.takePower(maxTransfer, true);
						if (drain > 0) {
							long filled = handlerTo.givePower(drain, false);
							handlerFrom.takePower(filled, false);
						}
					}
				}
			}
		}
	}

	public static void doFluidInteraction(TileEntity tileFrom, TileEntity tileTo, EnumFacing sideTo, int maxTransfer) {
		if (maxTransfer > 0) {
			if (tileFrom.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo)
					&& tileTo.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, sideTo.getOpposite())) {
				IFluidHandler handlerFrom = tileFrom.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
						sideTo);
				IFluidHandler handlerTo = tileTo.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,
						sideTo.getOpposite());
				FluidStack drain = handlerFrom.drain(maxTransfer, false);
				if (drain != null) {
					int filled = handlerTo.fill(drain.copy(), true);
					handlerFrom.drain(filled, true);
				}
			}
		}
	}

	private final String name;
	public boolean isRedstonePowered;
	public boolean isPulseMode;
	public boolean stopFromDropping;

	protected int ticksElapsed;

	protected TileEntity[] tilesAround = new TileEntity[6];

	protected boolean hasSavedDataOnChangeOrWorldStart;

	private Object teslaWrapper;

	public TETickingMachine(String name) {
		this.name = name;
	}

	public void activateOnPulse() {

	}

	public boolean canPlayerUse(EntityPlayer player) {
		return player.getDistanceSq(this.getPos().getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64
				&& !this.isInvalid() && this.world.getTileEntity(this.pos) == this;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
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
		} else if (ExtremeOreProcessing.teslaLoaded) {
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

	public int getComparatorStrength() {
		return 0;
	}

	public String getDisplayedName() {
		return I18n.format("tile." + name + ".name");
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(this.getDisplayedName());
	}

	public IEnergyStorage getEnergyStorage(EnumFacing facing) {
		return null;
	}

	public IFluidHandler getFluidHandler(EnumFacing facing) {
		return null;
	}

	public IItemHandler getItemHandler(EnumFacing facing) {
		return null;
	}

	@Override
	public final SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound compound = new NBTTagCompound();
		this.writeSyncableNBT(compound, NBTType.SYNC);
		return new SPacketUpdateTileEntity(this.pos, -1, compound);
	}

	@Override
	public final NBTTagCompound getUpdateTag() {
		NBTTagCompound compound = new NBTTagCompound();
		this.writeSyncableNBT(compound, NBTType.SYNC);
		return compound;
	}

	@Override
	public final void handleUpdateTag(NBTTagCompound compound) {
		this.readSyncableNBT(compound, NBTType.SYNC);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return this.getCapability(capability, facing) != null;
	}

	public boolean isRedstoneToggle() {
		return false;
	}

	@Override
	public final void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		this.readSyncableNBT(pkt.getNbtCompound(), NBTType.SYNC);
	}

	@Override
	public final void readFromNBT(NBTTagCompound compound) {
		this.readSyncableNBT(compound, NBTType.SAVE_TILE);
	}

	public void readSyncableNBT(NBTTagCompound compound, NBTType type) {
		if (type != NBTType.SAVE_BLOCK) {
			super.readFromNBT(compound);
		}

		if (type == NBTType.SAVE_TILE) {
			this.isRedstonePowered = compound.getBoolean("Redstone");
			this.ticksElapsed = compound.getInteger("TicksElapsed");
			this.stopFromDropping = compound.getBoolean("StopDrop");
		}
		if (this.isRedstoneToggle()) {
			this.isPulseMode = compound.getBoolean("IsPulseMode");
		}
	}

	public boolean respondsToPulses() {
		return this.isRedstoneToggle() && this.isPulseMode;
	}

	public void saveDataOnChangeOrWorldStart() {
		for (EnumFacing side : EnumFacing.values()) {
			BlockPos pos = this.pos.offset(side);
			if (this.world.isBlockLoaded(pos)) {
				this.tilesAround[side.ordinal()] = this.world.getTileEntity(pos);
			}
		}
	}

	public final void sendUpdate() {
		if (this.world != null && !this.world.isRemote) {
			NBTTagCompound compound = new NBTTagCompound();
			this.writeSyncableNBT(compound, NBTType.SYNC);

			NBTTagCompound data = new NBTTagCompound();
			data.setTag("Data", compound);
			data.setInteger("X", this.pos.getX());
			data.setInteger("Y", this.pos.getY());
			data.setInteger("Z", this.pos.getZ());
			PacketHandler.NETWORK.sendToAllAround(new PacketServerToClient(data, PacketHandler.TILE_ENTITY_HANDLER),
					new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), this.getPos().getX(),
							this.getPos().getY(), this.getPos().getZ(), 64));
		}
	}

	protected boolean sendUpdateWithInterval() {
		if (this.ticksElapsed % 2 == 0) {
			this.sendUpdate();
			return true;
		} else {
			return false;
		}
	}

	public void setRedstonePowered(boolean powered) {
		this.isRedstonePowered = powered;
		this.markDirty();
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return !oldState.getBlock().isAssociatedBlock(newState.getBlock());
	}

	public boolean shouldSaveDataOnChangeOrWorldStart() {
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

	public void writeSyncableNBT(NBTTagCompound compound, NBTType type) {
		if (type != NBTType.SAVE_BLOCK) {
			super.writeToNBT(compound);
		}

		if (type == NBTType.SAVE_TILE) {
			compound.setBoolean("Redstone", this.isRedstonePowered);
			compound.setInteger("TicksElapsed", this.ticksElapsed);
			compound.setBoolean("StopDrop", this.stopFromDropping);
		}
		if (this.isRedstoneToggle() && (type != NBTType.SAVE_BLOCK || this.isPulseMode)) {
			compound.setBoolean("IsPulseMode", this.isPulseMode);
		}
	}

	@Override
	public final NBTTagCompound writeToNBT(NBTTagCompound compound) {
		this.writeSyncableNBT(compound, NBTType.SAVE_TILE);
		return compound;
	}
}