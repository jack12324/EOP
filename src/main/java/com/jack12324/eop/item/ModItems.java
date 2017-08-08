package com.jack12324.eop.item;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.item.tool.*;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {

    public static ItemBase ingotTungsten= new ItemBase("ingot_tungsten");
    public static ItemOreDict ingotNickel = new ItemOreDict("ingot_nickel", "ingotNickel");
    public static ItemOreDict ingotCobalt =  new ItemOreDict("ingot_cobalt", "ingotCobalt");
    public static ItemBase ingotNickelTungstenAlloy = new ItemBase("ingot_nickel_tungsten_alloy");
    public static ItemBase ingotIronTungstenAlloy = new ItemBase("ingot_iron_tungsten_alloy");
    public static ItemBase ingotCobaltTungstenAlloy = new ItemBase("ingot_cobalt_tungsten_alloy");
    public static ItemBase ingotTungstenCarbideAlloy = new ItemBase("ingot_tungsten_carbide_alloy");
    public static ItemBase ingotFirestoneTungstenAlloy = new ItemBase("ingot_firestone_tungsten_alloy");
    public static ItemBase ingotSoulInfusedTungsten = new ItemBase("ingot_soul_infused_tungsten");

    public static ItemBase dustFirestone = new ItemBase("dust_firestone");
    public static ItemBase dustRedsoul = new ItemBase("dust_redsoul");
    public static ItemBase slag = new ItemBase("slag");

    public static ItemBase componentCrudeComponent = new ItemBase("component_crude_component");
    public static ItemBase componentBasicComponent = new ItemBase("component_basic_component");
    public static ItemBase componentAdvancedComponent = new ItemBase("component_advanced_component");
    public static ItemBase componentEliteComponent = new ItemBase("component_elite_component");
    public static ItemBase componentEnergyModule = new ItemBase("component_energy_module");

    public static ModSword tungstenSword = new ModSword(ExtremeOreProcessing.tungstenToolMaterial, "sword_tungsten");
    public static ModPickaxe tungstenPickaxe = new ModPickaxe(ExtremeOreProcessing.tungstenToolMaterial, "pickaxe_tungsten");
    public static ModShovel tungstenShovel = new ModShovel(ExtremeOreProcessing.tungstenToolMaterial, "shovel_tungsten");
    public static ModAxe tungstenAxe = new ModAxe(ExtremeOreProcessing.tungstenToolMaterial, "axe_tungsten");
    public static ModHoe tungstenHoe = new ModHoe(ExtremeOreProcessing.tungstenToolMaterial, "hoe_tungsten");

    public static ModArmor tungstenHelmet = new ModArmor(ExtremeOreProcessing.tungstenArmorMaterial, EntityEquipmentSlot.HEAD, "helmet_tungsten");
    public static ModArmor tungstenChestplate = new ModArmor(ExtremeOreProcessing.tungstenArmorMaterial, EntityEquipmentSlot.CHEST, "chestplate_tungsten");
    public static ModArmor tungstenLeggings = new ModArmor(ExtremeOreProcessing.tungstenArmorMaterial, EntityEquipmentSlot.LEGS, "leggings_tungsten");
    public static ModArmor tungstenBoots = new ModArmor(ExtremeOreProcessing.tungstenArmorMaterial, EntityEquipmentSlot.FEET, "boots_tungsten");

    public static ItemBase activatedGoldDust = new ItemBase("activated_gold_dust");
    public static ItemBase activatedIronDust = new ItemBase("activated_iron_dust");
    public static ItemBase activatedNickelDust = new ItemBase("activated_nickel_dust");
    public static ItemBase activatedCobaltDust = new ItemBase("activated_cobalt_dust");
    public static ItemBase activatedTungstenDust = new ItemBase("activated_tungsten_dust");

    public static ItemBase dormantGoldPowder = new ItemBase("dormant_gold_powder");
    public static ItemBase dormantIronPowder = new ItemBase("dormant_iron_powder");
    public static ItemBase dormantNickelPowder = new ItemBase("dormant_nickel_powder");
    public static ItemBase dormantCobaltPowder = new ItemBase("dormant_cobalt_powder");
    public static ItemBase dormantTungstenPowder = new ItemBase("dormant_tungsten_powder");

    public static ItemBase excitedGoldScraps = new ItemBase("excited_gold_scraps");
    public static ItemBase excitedIronScraps = new ItemBase("excited_iron_scraps");
    public static ItemBase excitedNickelScraps = new ItemBase("excited_nickel_scraps");
    public static ItemBase excitedCobaltScraps = new ItemBase("excited_cobalt_scraps");
    public static ItemBase excitedTungstenScraps = new ItemBase("excited_tungsten_scraps");

    public static ItemBase astralGoldSlivers = new ItemBase("astral_gold_slivers");
    public static ItemBase astralIronSlivers = new ItemBase("astral_iron_slivers");
    public static ItemBase astralNickelSlivers = new ItemBase("astral_nickel_slivers");
    public static ItemBase astralCobaltSlivers = new ItemBase("astral_cobalt_slivers");
    public static ItemBase astralTungstenSlivers = new ItemBase("astral_tungsten_slivers");

    public static ItemBase pureGoldEssence = new ItemBase("pure_gold_essence");
    public static ItemBase pureIronEssence = new ItemBase("pure_iron_essence");
    public static ItemBase pureNickelEssence = new ItemBase("pure_nickel_essence");
    public static ItemBase pureCobaltEssence = new ItemBase("pure_cobalt_essence");
    public static ItemBase pureTungstenEssence = new ItemBase("pure_tungsten_essence");

    public static ItemBase speedUpgrade = new ItemBase("speed_upgrade", 10);
    public static ItemBase energyUpgrade = new ItemBase("energy_upgrade", 10);

    public static ItemBase dragonScale = new ItemBase("dragon_scale");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                ingotTungsten,
                ingotNickel,
                ingotCobalt,
                ingotNickelTungstenAlloy,
                ingotIronTungstenAlloy,
                ingotCobaltTungstenAlloy,
                ingotTungstenCarbideAlloy,
                ingotFirestoneTungstenAlloy,
                ingotSoulInfusedTungsten,
                dustFirestone,
                dustRedsoul,
                componentCrudeComponent,
                componentBasicComponent,
                componentAdvancedComponent,
                componentEliteComponent,
                componentEnergyModule,
                tungstenSword,
                tungstenPickaxe,
                tungstenShovel,
                tungstenAxe,
                tungstenHoe,
                tungstenHelmet,
                tungstenChestplate,
                tungstenLeggings,
                tungstenBoots,
                activatedGoldDust,
                activatedIronDust,
                activatedNickelDust,
                activatedCobaltDust,
                activatedTungstenDust,
                dormantGoldPowder,
                dormantIronPowder,
                dormantNickelPowder,
                dormantCobaltPowder,
                dormantTungstenPowder,
                excitedGoldScraps,
                excitedIronScraps,
                excitedNickelScraps,
                excitedCobaltScraps,
                excitedTungstenScraps,
                astralGoldSlivers,
                astralIronSlivers,
                astralNickelSlivers,
                astralCobaltSlivers,
                astralTungstenSlivers,
                pureGoldEssence,
                pureIronEssence,
                pureNickelEssence,
                pureCobaltEssence,
                pureTungstenEssence,
                speedUpgrade,
                energyUpgrade,
                dragonScale,
                slag
        );
    }

    public static void registerModels() {
                ingotTungsten.registerItemModel();
                ingotNickel.registerItemModel();
                ingotCobalt.registerItemModel();
                ingotNickelTungstenAlloy.registerItemModel();
                ingotIronTungstenAlloy.registerItemModel();
                ingotCobaltTungstenAlloy.registerItemModel();
                ingotTungstenCarbideAlloy.registerItemModel();
                ingotFirestoneTungstenAlloy.registerItemModel();
                ingotSoulInfusedTungsten.registerItemModel();
                dustFirestone.registerItemModel();
                dustRedsoul.registerItemModel();
                componentCrudeComponent.registerItemModel();
                componentBasicComponent.registerItemModel();
                componentAdvancedComponent.registerItemModel();
                componentEliteComponent.registerItemModel();
                componentEnergyModule.registerItemModel();
                tungstenSword.registerItemModel();
                tungstenPickaxe.registerItemModel();
                tungstenShovel.registerItemModel();
                tungstenAxe.registerItemModel();
                tungstenHoe.registerItemModel();
                tungstenHelmet.registerItemModel();
                tungstenChestplate.registerItemModel();
                tungstenLeggings.registerItemModel();
                tungstenBoots.registerItemModel();
                activatedGoldDust.registerItemModel();
                activatedIronDust.registerItemModel();
                activatedNickelDust.registerItemModel();
                activatedCobaltDust.registerItemModel();
                activatedTungstenDust.registerItemModel();
                dormantGoldPowder.registerItemModel();
                dormantIronPowder.registerItemModel();
                dormantNickelPowder.registerItemModel();
                dormantCobaltPowder.registerItemModel();
                dormantTungstenPowder.registerItemModel();
                excitedGoldScraps.registerItemModel();
                excitedIronScraps.registerItemModel();
                excitedNickelScraps.registerItemModel();
                excitedCobaltScraps.registerItemModel();
                excitedTungstenScraps.registerItemModel();
                astralGoldSlivers.registerItemModel();
                astralIronSlivers.registerItemModel();
                astralNickelSlivers.registerItemModel();
                astralCobaltSlivers.registerItemModel();
                astralTungstenSlivers.registerItemModel();
                pureGoldEssence.registerItemModel();
                pureIronEssence.registerItemModel();
                pureNickelEssence.registerItemModel();
                pureCobaltEssence.registerItemModel();
                pureTungstenEssence.registerItemModel();
                speedUpgrade.registerItemModel();
                energyUpgrade.registerItemModel();
                dragonScale.registerItemModel();
        slag.registerItemModel();
    }
}
