package com.jack12324.eop;

import com.jack12324.eop.item.ModItems;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Events {
    @SubscribeEvent
    public void onDragonDrop(LivingDropsEvent event) {
        if (event.getEntity() instanceof EntityDragon) {
            BlockPos pos = new BlockPos(event.getEntity());
            ItemStack stack = new ItemStack(ModItems.dragonScale);
            EntityItem drop = new EntityItem(event.getEntity().getEntityWorld(), pos.getX(), pos.getY(), pos.getZ(), stack);
            event.getDrops().add(drop);
        }
    }
}
