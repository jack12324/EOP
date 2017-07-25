package com.jack12324.eop.packet;

import com.jack12324.eop.ExtremeOreProcessing;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketClientToServer implements IMessage {

    public static class Handler implements IMessageHandler<PacketClientToServer, IMessage> {

        @Override
        public IMessage onMessage(final PacketClientToServer message, final MessageContext ctx) {
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                if (message.data != null && message.handler != null) {
                    message.handler.handleData(message.data, ctx);
                }
            });
            return null;
        }
    }

    private NBTTagCompound data;

    private IDataHandler handler;

    public PacketClientToServer() {

    }

    public PacketClientToServer(NBTTagCompound data, IDataHandler handler) {
        this.data = data;
        this.handler = handler;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer buffer = new PacketBuffer(buf);
        try {
            this.data = buffer.readCompoundTag();

            int handlerId = buffer.readInt();
            if (handlerId >= 0 && handlerId < PacketHandler.DATA_HANDLERS.size()) {
                this.handler = PacketHandler.DATA_HANDLERS.get(handlerId);
            }
        } catch (Exception e) {
            ExtremeOreProcessing.LOGGER.error("Something went wrong trying to receive a server packet!", e);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        PacketBuffer buffer = new PacketBuffer(buf);

        buffer.writeCompoundTag(this.data);
        buffer.writeInt(PacketHandler.DATA_HANDLERS.indexOf(this.handler));
    }
}