package com.jack12324.eop.util;

import com.google.common.collect.Maps;
import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Map;

public class OreLineHelper {
    private static final OreLineHelper INSTANCE = new OreLineHelper();

    public static Item get(String key) {
        return INSTANCE.list.get(key);
    }

    private final Map<String, Item> list = Maps.newHashMap();

    private OreLineHelper() {

        list.put("oreIron", Item.getItemFromBlock(Blocks.IRON_ORE));
        list.put("ingotIron", Items.IRON_INGOT);
        list.put("activatedIronDust", ModItems.activatedIronDust);
        list.put("dormantIronPowder", ModItems.dormantIronPowder);
        list.put("excitedIronScraps", ModItems.excitedIronScraps);
        list.put("astralIronSlivers", ModItems.astralIronSlivers);
        list.put("pureIronEssence", ModItems.pureIronEssence);

        list.put("oreGold", Item.getItemFromBlock(Blocks.GOLD_ORE));
        list.put("ingotGold", Items.GOLD_INGOT);
        list.put("activatedGoldDust", ModItems.activatedGoldDust);
        list.put("dormantGoldPowder", ModItems.dormantGoldPowder);
        list.put("excitedGoldScraps", ModItems.excitedGoldScraps);
        list.put("astralGoldSlivers", ModItems.astralGoldSlivers);
        list.put("pureGoldEssence", ModItems.pureGoldEssence);

        list.put("oreTungsten", Item.getItemFromBlock(ModBlocks.oreTungsten));
        list.put("ingotTungsten", ModItems.ingotTungsten);
        list.put("activatedTungstenDust", ModItems.activatedTungstenDust);
        list.put("dormantTungstenPowder", ModItems.dormantTungstenPowder);
        list.put("excitedTungstenScraps", ModItems.excitedTungstenScraps);
        list.put("astralTungstenSlivers", ModItems.astralTungstenSlivers);
        list.put("pureTungstenEssence", ModItems.pureTungstenEssence);

        list.put("oreCobalt", Item.getItemFromBlock(ModBlocks.oreCobalt));
        list.put("ingotCobalt", ModItems.ingotCobalt);
        list.put("activatedCobaltDust", ModItems.activatedCobaltDust);
        list.put("dormantCobaltPowder", ModItems.dormantCobaltPowder);
        list.put("excitedCobaltScraps", ModItems.excitedCobaltScraps);
        list.put("astralCobaltSlivers", ModItems.astralCobaltSlivers);
        list.put("pureCobaltEssence", ModItems.pureCobaltEssence);

        list.put("oreNickel", Item.getItemFromBlock(ModBlocks.oreNickel));
        list.put("ingotNickel", ModItems.ingotNickel);
        list.put("activatedNickelDust", ModItems.activatedNickelDust);
        list.put("dormantNickelPowder", ModItems.dormantNickelPowder);
        list.put("excitedNickelScraps", ModItems.excitedNickelScraps);
        list.put("astralNickelSlivers", ModItems.astralNickelSlivers);
        list.put("pureNickelEssence", ModItems.pureNickelEssence);

    }
}
