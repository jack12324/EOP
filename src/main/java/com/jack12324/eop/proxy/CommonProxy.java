package com.jack12324.eop.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy {

    public EntityPlayer getPlayer(MessageContext context) {
        return context.getServerHandler().player;
    }


    public void preInit(FMLPreInitializationEvent event) {
        // TODO Auto-generated method stub

    }

    public void registerItemRenderer(Item itemBase, int meta, String name) {
        // TODO Auto-generated method stud
    }

}
