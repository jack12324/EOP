package com.jack12324.eop.item;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.item.tool.ModAxe;
import com.jack12324.eop.item.tool.ModHoe;
import com.jack12324.eop.item.tool.ModPickaxe;
import com.jack12324.eop.item.tool.ModShovel;
import com.jack12324.eop.item.tool.ModSword;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static ItemBase ingotTungsten;
	public static ItemOreDict ingotNickel;
	public static ItemOreDict ingotCobalt;
	public static ItemBase ingotNickelTungstenAlloy;
	public static ItemBase ingotIronTungstenAlloy;
	public static ItemBase ingotCobaltTungstenAlloy;
	public static ItemBase ingotTungstenCarbideAlloy;
	public static ItemBase ingotFirestoneTungstenAlloy;
	public static ItemBase ingotSoulInfusedTungsten;

	public static ItemBase dustFirestone;
	public static ItemBase dustRedsoul;

	public static ItemBase componentCrudeComponent;
	public static ItemBase componentBasicComponent;
	public static ItemBase componentAdvancedComponent;
	public static ItemBase componentEliteComponent;
	public static ItemBase componentEnergyModule;

	public static ModSword tungstenSword;
	public static ModPickaxe tungstenPickaxe;
	public static ModShovel tungstenShovel;
	public static ModAxe tungstenAxe;
	public static ModHoe tungstenHoe;
	public static ModSword swordPrickly;

	public static ModArmor tungstenHelmet;
	public static ModArmor tungstenChestplate;
	public static ModArmor tungstenLeggings;
	public static ModArmor tungstenBoots;
	public static ModArmor cobaltHelmet;

	public static ItemPeaSeed peaSeed;
	public static ItemBase pea;

	public static ItemBase activatedGoldDust;
	public static ItemBase activatedIronDust;
	public static ItemBase activatedNickelDust;
	public static ItemBase activatedCobaltDust;
	public static ItemBase activatedTungstenDust;

	public static ItemBase dormantGoldPowder;
	public static ItemBase dormantIronPowder;
	public static ItemBase dormantNickelPowder;
	public static ItemBase dormantCobaltPowder;
	public static ItemBase dormantTungstenPowder;

	public static ItemBase excitedGoldScraps;
	public static ItemBase excitedIronScraps;
	public static ItemBase excitedNickelScraps;
	public static ItemBase excitedCobaltScraps;
	public static ItemBase excitedTungstenScraps;

	public static ItemBase astralGoldSlivers;
	public static ItemBase astralIronSlivers;
	public static ItemBase astralNickelSlivers;
	public static ItemBase astralCobaltSlivers;
	public static ItemBase astralTungstenSlivers;

	public static ItemBase pureGoldEssence;
	public static ItemBase pureIronEssence;
	public static ItemBase pureNickelEssence;
	public static ItemBase pureCobaltEssence;
	public static ItemBase pureTungstenEssence;

	public static ItemBase dragonScale;

	public static ItemBase cactusSpine;

	public static void init() {

		//ingots
		ingotTungsten = register(new ItemBase("ingot_tungsten"));
		ingotNickel = register(new ItemOreDict("ingot_nickel", "ingotNickel"));
		ingotCobalt = register(new ItemOreDict("ingot_cobalt", "ingotCobalt"));

		ingotNickelTungstenAlloy = register(new ItemBase("ingot_nickel_tungsten_alloy"));
		ingotIronTungstenAlloy = register(new ItemBase("ingot_iron_tungsten_alloy"));
		ingotCobaltTungstenAlloy = register(new ItemBase("ingot_cobalt_tungsten_alloy"));
		ingotTungstenCarbideAlloy = register(new ItemBase("ingot_tungsten_carbide_alloy"));
		ingotFirestoneTungstenAlloy = register(new ItemBase("ingot_firestone_tungsten_alloy"));
		ingotSoulInfusedTungsten = register(new ItemBase("ingot_soul_infused_tungsten"));

		//dusts
		dustFirestone = register(new ItemBase("dust_firestone"));
		dustRedsoul = register(new ItemBase("dust_redsoul"));

		//crafting components
		componentCrudeComponent = register(new ItemBase("component_crude_component"));
		componentBasicComponent = register(new ItemBase("component_basic_component"));
		componentAdvancedComponent = register(new ItemBase("component_advanced_component"));
		componentEliteComponent = register(new ItemBase("component_elite_component"));
		componentEnergyModule = register(new ItemBase("component_energy_module"));

		//armor
		tungstenHelmet = register(new ModArmor(ExtremeOreProcessing.tungstenArmorMaterial, EntityEquipmentSlot.HEAD, "helmet_tungsten"));
		tungstenChestplate = register(new ModArmor(ExtremeOreProcessing.tungstenArmorMaterial, EntityEquipmentSlot.CHEST, "chestplate_tungsten"));
		tungstenLeggings = register(new ModArmor(ExtremeOreProcessing.tungstenArmorMaterial, EntityEquipmentSlot.LEGS, "leggings_tungsten"));
		tungstenBoots = register(new ModArmor(ExtremeOreProcessing.tungstenArmorMaterial, EntityEquipmentSlot.FEET, "boots_tungsten"));

		//cobaltHelmet = register(new ModArmor(ExtremeOreProcessing.cobaltArmorMaterial, EntityEquipmentSlot.HEAD, "helmet_cobalt"));

		//tools
		tungstenSword = register(new ModSword(ExtremeOreProcessing.tungstenToolMaterial, "sword_tungsten"));
		tungstenPickaxe = register(new ModPickaxe(ExtremeOreProcessing.tungstenToolMaterial, "pickaxe_tungsten"));
		tungstenAxe = register(new ModAxe(ExtremeOreProcessing.tungstenToolMaterial, "axe_tungsten"));
		tungstenShovel = register(new ModShovel(ExtremeOreProcessing.tungstenToolMaterial, "shovel_tungsten"));
		tungstenHoe = register(new ModHoe(ExtremeOreProcessing.tungstenToolMaterial, "hoe_tungsten"));
		swordPrickly = register(new ModSword(ExtremeOreProcessing.cactusToolMaterial, "prickly_sword"));

		//God Tier Crops
		pea = register(new ItemBase("pea").setCreativeTab(CreativeTabs.FOOD));

		//Activated Dusts
		activatedGoldDust = register(new ItemBase("activated_gold_dust"));
		activatedIronDust = register(new ItemBase("activated_iron_dust"));
		activatedNickelDust = register(new ItemBase("activated_nickel_dust"));
		activatedCobaltDust = register(new ItemBase("activated_cobalt_dust"));
		activatedTungstenDust = register(new ItemBase("activated_tungsten_dust"));

		//Dormant Powders
		dormantGoldPowder = register(new ItemBase("dormant_gold_powder"));
		dormantIronPowder = register(new ItemBase("dormant_iron_powder"));
		dormantNickelPowder = register(new ItemBase("dormant_nickel_powder"));
		dormantCobaltPowder = register(new ItemBase("dormant_cobalt_powder"));
		dormantTungstenPowder = register(new ItemBase("dormant_tungsten_powder"));

		//Excited Scraps
		excitedGoldScraps = register(new ItemBase("excited_gold_scraps"));
		excitedIronScraps = register(new ItemBase("excited_iron_scraps"));
		excitedNickelScraps = register(new ItemBase("excited_nickel_scraps"));
		excitedCobaltScraps = register(new ItemBase("excited_cobalt_scraps"));
		excitedTungstenScraps = register(new ItemBase("excited_tungsten_scraps"));

		//Astral Slivers
		astralGoldSlivers = register(new ItemBase("astral_gold_slivers"));
		astralIronSlivers = register(new ItemBase("astral_iron_slivers"));
		astralNickelSlivers = register(new ItemBase("astral_nickel_slivers"));
		astralCobaltSlivers = register(new ItemBase("astral_cobalt_slivers"));
		astralTungstenSlivers = register(new ItemBase("astral_tungsten_slivers"));

		//Astral Slivers
		pureGoldEssence = register(new ItemBase("pure_gold_essence"));
		pureIronEssence = register(new ItemBase("pure_iron_essence"));
		pureNickelEssence = register(new ItemBase("pure_nickel_essence"));
		pureCobaltEssence = register(new ItemBase("pure_cobalt_essence"));
		pureTungstenEssence = register(new ItemBase("pure_tungsten_essence"));

		//God tier scales
		dragonScale = register(new ItemBase("dragon_scale"));

		//God tier cactus spines
		cactusSpine = register(new ItemBase("cactus_spine"));

	}

	public static void initSeeds() {
		peaSeed = register(new ItemPeaSeed());
	}

	private static <T extends Item> T register(T item) {
		GameRegistry.register(item);

		if (item instanceof ItemModelProvider) {
			((ItemModelProvider) item).registerItemModel(item);
		}
		if (item instanceof ItemOreDictionary) {
			((ItemOreDictionary) item).initOreDict();
		}
		return item;
	}
}
