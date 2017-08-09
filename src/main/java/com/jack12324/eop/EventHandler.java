package com.jack12324.eop;

import com.jack12324.eop.item.ModItems;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void onDragonDrop(LivingDropsEvent event) {
        if (event.getEntityLiving() instanceof EntityDragon) {
            BlockPos pos = new BlockPos(event.getEntityLiving());
            ItemStack stack = new ItemStack(ModItems.dragonScale, 1 + event.getLootingLevel());
            EntityItem drop = new EntityItem(event.getEntity().getEntityWorld(), pos.getX(), pos.getY(), pos.getZ(), stack);
            event.getDrops().add(drop);


        }
    }

    @SubscribeEvent
    public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(ExtremeOreProcessing.modID)) {
            ConfigManager.sync(ExtremeOreProcessing.modID, Config.Type.INSTANCE);
        }
    }
}
