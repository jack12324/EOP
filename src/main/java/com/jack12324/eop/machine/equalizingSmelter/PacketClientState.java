package com.jack12324.eop.machine.equalizingSmelter;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketClientState implements IMessage, IMessageHandler<PacketClientState, IMessage> {

	private long pos;

	private boolean mode;
	private boolean spreadMode;

	public PacketClientState() {

	}

	public PacketClientState(TileEntityEqualizingSmelter tile) {
		pos = tile.getPos().toLong();
		mode = tile.getMode();
		spreadMode = tile.getSpreadMode();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos);
		buf.writeBoolean(mode);
		buf.writeBoolean(spreadMode);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = buf.readLong();
		mode = buf.readBoolean();
		spreadMode = buf.readBoolean();
	}

	public BlockPos getPos() {
		return BlockPos.fromLong(pos);
	}

	@Override
	public IMessage onMessage(PacketClientState message, MessageContext ctx) {
		TileEntity te = ctx.getServerHandler().playerEntity.world.getTileEntity(message.getPos());
		if (te instanceof TileEntityEqualizingSmelter) {
			TileEntityEqualizingSmelter me = (TileEntityEqualizingSmelter) te;
			me.setMode(message.mode);
			me.setSpreadMode(message.spreadMode);

			IBlockState bs = ctx.getServerHandler().playerEntity.world.getBlockState(message.getPos());
			ctx.getServerHandler().playerEntity.world.notifyBlockUpdate(message.getPos(), bs, bs, 3);
		}
		return null;
	}
}