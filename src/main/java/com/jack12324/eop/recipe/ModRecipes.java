package com.jack12324.eop.recipe;

import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.item.ItemBase;
import com.jack12324.eop.item.ModArmor;
import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.item.tool.ModAxe;
import com.jack12324.eop.item.tool.ModHoe;
import com.jack12324.eop.item.tool.ModPickaxe;
import com.jack12324.eop.item.tool.ModShovel;
import com.jack12324.eop.item.tool.ModSword;
import com.jack12324.eop.util.OreLineHelper;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class ModRecipes {

	private static void addOreLine(String name) {
		ItemStack ore = new ItemStack(OreLineHelper.get("ore" + name));
		Item ingot = OreLineHelper.get("ingot" + name);
		Item dust = OreLineHelper.get("activated" + name + "Dust");
		Item powder = OreLineHelper.get("dormant" + name + "Powder");
		Item scraps = OreLineHelper.get("excited" + name + "Scraps");
		Item slivers = OreLineHelper.get("astral" + name + "Slivers");
		Item essence = OreLineHelper.get("pure" + name + "Essence");

		RecipeHolder.addActivationChamberRecipe(ore, new ItemStack(dust, 2));
		RecipeHolder.addDisablingPressRecipe(ore, new ItemStack(Blocks.SAND), new ItemStack(powder, 3));
		RecipeHolder.addParticleExciterRecipe(ore, new ItemStack(Blocks.NETHERRACK),
				new FluidStack(InitFluids.fluidScreamingLava, 100), new ItemStack(scraps, 4));
		RecipeHolder.addStarHardenerRecipe(ore, new ItemStack(Blocks.OBSIDIAN),
				new FluidStack(InitFluids.fluidStarWater, 100), new ItemStack(slivers, 5));
		RecipeHolder.addEndericPurifierRecipe(ore, new ItemStack(Blocks.END_STONE),
				new FluidStack(InitFluids.fluidLiquidEnd, 100), new ItemStack(essence, 6));

		RecipeHolder.addStarHardenerRecipe(new ItemStack(essence), new ItemStack(Blocks.OBSIDIAN),
				new FluidStack(InitFluids.fluidStarWater, 100), new ItemStack(slivers));
		RecipeHolder.addParticleExciterRecipe(new ItemStack(slivers), new ItemStack(Blocks.NETHERRACK),
				new FluidStack(InitFluids.fluidScreamingLava, 100), new ItemStack(scraps));
		RecipeHolder.addDisablingPressRecipe(new ItemStack(scraps), new ItemStack(Blocks.SAND), new ItemStack(powder));
		RecipeHolder.addActivationChamberRecipe(new ItemStack(powder), new ItemStack(dust));
		RecipeHolder.addEqualizingSmelterRecipe(new ItemStack(dust), new ItemStack(dust), new ItemStack(dust),
				new ItemStack(dust), new ItemStack(ingot, 4));
	}

	private static void armorToolRecipes(ModArmor helmet, ModArmor chestplate, ModArmor leggings, ModArmor boots,
			ModSword sword, ModPickaxe pickaxe, ModAxe axe, ModShovel shovel, ModHoe hoe, ItemBase material) {
		GameRegistry.addShapedRecipe(new ItemStack(helmet), "aaa", "a a", 'a', material);
		GameRegistry.addShapedRecipe(new ItemStack(chestplate), "a a", "aaa", "aaa", 'a', material);
		GameRegistry.addShapedRecipe(new ItemStack(leggings), "aaa", "a a", "a a", 'a', material);
		GameRegistry.addShapedRecipe(new ItemStack(boots), "a a", "a a", 'a', material);

		GameRegistry.addShapedRecipe(new ItemStack(sword), " a ", " a ", " b ", 'a', material, 'b', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(pickaxe), "aaa", " b ", " b ", 'a', material, 'b', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(axe), "aa ", "ab ", " b ", 'a', material, 'b', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(shovel), " a ", " b ", " b ", 'a', material, 'b', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(hoe), "aa ", " b ", " b ", 'a', material, 'b', Items.STICK);

	}

	public static void init() {
		// block to ingots
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotTungsten, 9), ModBlocks.blockTungsten);
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.ingotCobalt, 9), ModBlocks.blockCobalt));
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.dustFirestone, 9), ModBlocks.blockFirestone);
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.dustRedsoul, 9), ModBlocks.blockRedsoul);
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.ingotNickel, 9), ModBlocks.blockNickel));

		// ingots to block
		GameRegistry.addRecipe(
				new ShapedOreRecipe((ModBlocks.blockCobalt), "aaa", "aaa", "aaa", 'a', ModItems.ingotCobalt));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockFirestone), "aaa", "aaa", "aaa", 'a',
				ModItems.dustFirestone);
		GameRegistry
				.addRecipe(new ShapedOreRecipe(ModBlocks.blockNickel, "aaa", "aaa", "aaa", 'a', ModItems.ingotNickel));
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockRedsoul), "aaa", "aaa", "aaa", 'a',
				ModItems.dustRedsoul);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockTungsten), "aaa", "aaa", "aaa", 'a',
				ModItems.ingotTungsten);

		// components
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.componentCrudeComponent), " a ", "bcb", "dbd", 'a',
				Items.COAL, 'b', ModItems.ingotTungstenCarbideAlloy, 'c', Blocks.STONE, 'd', Blocks.COBBLESTONE);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.componentBasicComponent), " a ", "bcb", "dbd", 'a',
				Items.REDSTONE, 'b', ModItems.ingotIronTungstenAlloy, 'c', ModBlocks.blockFirestone, 'd',
				ModItems.componentCrudeComponent);
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.componentAdvancedComponent, " a ", "bcb", "dbd", 'a',
				ModItems.ingotCobalt, 'b', ModItems.ingotNickelTungstenAlloy, 'c', Blocks.LAPIS_BLOCK, 'd',
				ModItems.componentBasicComponent));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.componentEliteComponent), " a ", "bcb", "dbd", 'a',
				Items.ENDER_EYE, 'b', ModItems.ingotCobaltTungstenAlloy, 'c', Items.EMERALD, 'd',
				ModItems.componentAdvancedComponent);
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.componentEnergyModule), "aba", "aca", "aaa", 'a',
				ModItems.ingotTungsten, 'b', Items.GOLD_INGOT, 'c', ModBlocks.blockFirestone);

		// machine frames
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockCrudeMachineFrame), "aba", "bcb", "ada", 'a',
				ModItems.ingotTungsten, 'b', ModItems.componentCrudeComponent, 'c', Blocks.OBSIDIAN, 'd',
				ModItems.componentEnergyModule);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockBasicMachineFrame), "aba", "bcb", "ada", 'a',
				ModItems.ingotFirestoneTungstenAlloy, 'b', ModItems.componentBasicComponent, 'c',
				ModBlocks.blockCrudeMachineFrame, 'd', ModItems.componentEnergyModule);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockAdvancedMachineFrame), "aba", "bcb", "ada", 'a',
				ModItems.ingotCobaltTungstenAlloy, 'b', ModItems.componentAdvancedComponent, 'c',
				ModBlocks.blockBasicMachineFrame, 'd', ModItems.componentEnergyModule);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.blockEliteMachineFrame), "aba", "bcb", "ada", 'a',
				ModItems.ingotSoulInfusedTungsten, 'b', ModItems.componentEliteComponent, 'c',
				ModBlocks.blockAdvancedMachineFrame, 'd', ModItems.componentEnergyModule);

		// Machine
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.equalizingSmelter), "aaa", "bcb", "ded", 'a',
				ModItems.ingotTungsten, 'b', ModItems.componentCrudeComponent, 'c', Blocks.GOLD_BLOCK, 'd',
				ModItems.ingotNickel, 'e', ModBlocks.blockCrudeMachineFrame);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.activationChamber), "aaa", "bcb", "ded", 'a',
				ModItems.ingotTungsten, 'b', ModItems.componentCrudeComponent, 'c', Blocks.GOLD_BLOCK, 'd',
				ModItems.ingotIronTungstenAlloy, 'e', ModBlocks.blockCrudeMachineFrame);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.disablingPress), "aaa", "bcb", "ded", 'a',
				ModItems.ingotIronTungstenAlloy, 'b', ModItems.componentBasicComponent, 'c', Blocks.GOLD_BLOCK, 'd',
				ModItems.ingotNickelTungstenAlloy, 'e', ModBlocks.blockBasicMachineFrame);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.catalystInfuser), "aaa", "bcb", "ded", 'a',
				ModItems.ingotIronTungstenAlloy, 'b', ModItems.componentBasicComponent, 'c', Blocks.GOLD_BLOCK, 'd',
				ModItems.ingotNickelTungstenAlloy, 'e', ModBlocks.blockBasicMachineFrame);
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.particleExciter), "aba", "cdc", "efe", 'a',
				ModItems.ingotNickelTungstenAlloy, 'b', Items.DIAMOND, 'c', ModItems.componentAdvancedComponent, 'd',
				Blocks.GOLD_BLOCK, 'e', ModItems.ingotCobaltTungstenAlloy, 'f', ModBlocks.blockAdvancedMachineFrame);

		// armor and tools
		armorToolRecipes(ModItems.tungstenHelmet, ModItems.tungstenChestplate, ModItems.tungstenLeggings,
				ModItems.tungstenBoots, ModItems.tungstenSword, ModItems.tungstenPickaxe, ModItems.tungstenAxe,
				ModItems.tungstenShovel, ModItems.tungstenHoe, ModItems.ingotTungsten);

		// smelting
		GameRegistry.addSmelting(ModBlocks.oreCobalt, new ItemStack(ModItems.ingotCobalt), 0.7f);
		GameRegistry.addSmelting(ModBlocks.oreNickel, new ItemStack(ModItems.ingotNickel), 0.7f);
		GameRegistry.addSmelting(ModBlocks.oreTungsten, new ItemStack(ModItems.ingotTungsten), 0.7f);

		addOreLine("Iron");
		addOreLine("Gold");
		addOreLine("Tungsten");
		addOreLine("Cobalt");
		addOreLine("Nickel");

		RecipeHolder.addPedestalRecipe(new ItemStack(Blocks.DRAGON_EGG), new FluidStack(InitFluids.fluidDragonSoul, 1),
				4);
		RecipeHolder.addCatalystInfuserRecipe(new ItemStack(Items.GHAST_TEAR), new FluidStack(FluidRegistry.LAVA, 100),
				new FluidStack(InitFluids.fluidScreamingLava, 100));
		RecipeHolder.addDualCatalystInfuserRecipe(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.QUARTZ, 10),
				new FluidStack(FluidRegistry.WATER, 100), new FluidStack(InitFluids.fluidStarWater, 100));
		RecipeHolder.addTriCatalystInfuserRecipe(new ItemStack(Items.CHORUS_FRUIT_POPPED, 3),
				new ItemStack(Items.ENDER_EYE, 2), new ItemStack(Items.SHULKER_SHELL),
				new FluidStack(InitFluids.fluidDragonSoul, 100), new FluidStack(InitFluids.fluidLiquidEnd, 100));

		// God tier cactus spines
		GameRegistry.addRecipe(new ShapedOreRecipe((ModItems.cactusSpine), "ab ", "   ", "   ", 'a', Items.IRON_SWORD,
				'b', Blocks.CACTUS));

		RecipeHolder.addActivationChamberRecipe(new ItemStack(Items.EMERALD),
				new ItemStack(Items.FERMENTED_SPIDER_EYE));
		RecipeHolder.addActivationChamberRecipe(new ItemStack(Items.ARROW, 2), new ItemStack(Items.GLOWSTONE_DUST));
		RecipeHolder.addActivationChamberRecipe(new ItemStack(Items.EGG), new ItemStack(Items.LEATHER, 9));
		RecipeHolder.addActivationChamberRecipe(new ItemStack(Items.APPLE, 2), new ItemStack(Items.POTATO, 4));

	}

}
