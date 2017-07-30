package com.jack12324.eop.recipe;

import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.item.ItemBase;
import com.jack12324.eop.item.ModArmor;
import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.item.tool.*;
import com.jack12324.eop.util.OreLineHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Map;

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

    private static void createFurnaceList() {
        Map<ItemStack, ItemStack> metaList = FurnaceRecipes.instance().getSmeltingList();
        for (Map.Entry<ItemStack, ItemStack> entry : metaList.entrySet()) {
            RecipeHolder.addVanillaFurnaceRecipe(entry.getKey(), entry.getValue());
        }
    }

    public static void init() {
        createFurnaceList();
        // machine frames
        /*
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
                ModItems.tungstenShovel, ModItems.tungstenHoe, ModItems.ingotTungsten);*/

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
        RecipeHolder.addCatalystInfuserRecipe(new ItemStack(Items.GHAST_TEAR), new FluidStack(FluidRegistry.LAVA, 200),
                new FluidStack(InitFluids.fluidScreamingLava, 100));
        RecipeHolder.addDualCatalystInfuserRecipe(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.QUARTZ, 10),
                new FluidStack(FluidRegistry.WATER, 100), new FluidStack(InitFluids.fluidStarWater, 100));
        RecipeHolder.addTriCatalystInfuserRecipe(new ItemStack(Items.CHORUS_FRUIT_POPPED, 3),
                new ItemStack(Items.ENDER_EYE, 2), new ItemStack(Items.SHULKER_SHELL),
                new FluidStack(InitFluids.fluidDragonSoul, 100), new FluidStack(InitFluids.fluidLiquidEnd, 100));

        RecipeHolder.addActivationChamberRecipe(new ItemStack(Items.EMERALD),
                new ItemStack(Items.FERMENTED_SPIDER_EYE));
        RecipeHolder.addActivationChamberRecipe(new ItemStack(Items.ARROW, 2), new ItemStack(Items.GLOWSTONE_DUST));
        RecipeHolder.addActivationChamberRecipe(new ItemStack(Items.EGG), new ItemStack(Items.LEATHER, 9));
        RecipeHolder.addActivationChamberRecipe(new ItemStack(Items.APPLE, 2), new ItemStack(Items.POTATO, 4));

        RecipeHolder.addEqualizingSmelterRecipe(new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.ingotCobalt), new ItemStack(ModItems.ingotCobalt), new ItemStack(ModItems.ingotCobaltTungstenAlloy, 4));

    }

}
