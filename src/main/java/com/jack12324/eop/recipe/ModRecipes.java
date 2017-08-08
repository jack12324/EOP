package com.jack12324.eop.recipe;

import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Map;

public class ModRecipes {

    private static void addOreLine(Item base, Item x1, Item x2, Item x3, Item x4, Item x5, Item x6) {
        ItemStack ore = new ItemStack(base);
        Item ingot = x1;
        Item dust = x2;
        Item powder = x3;
        Item scraps = x4;
        Item slivers = x5;
        Item essence = x6;

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
                new ItemStack(dust), new ItemStack(ingot, 4), new ItemStack(ModItems.dustFirestone, 2));
    }

    private static void createFurnaceList() {
        Map<ItemStack, ItemStack> metaList = FurnaceRecipes.instance().getSmeltingList();
        for (Map.Entry<ItemStack, ItemStack> entry : metaList.entrySet()) {
            RecipeHolder.addVanillaFurnaceRecipe(entry.getKey(), entry.getValue());
        }
    }

    public static void init() {
        createFurnaceList();

        // smelting
        GameRegistry.addSmelting(ModBlocks.oreCobalt, new ItemStack(ModItems.ingotCobalt), 0.7f);
        GameRegistry.addSmelting(ModBlocks.oreNickel, new ItemStack(ModItems.ingotNickel), 0.7f);
        GameRegistry.addSmelting(ModBlocks.oreTungsten, new ItemStack(ModItems.ingotTungsten), 0.7f);

        addOreLine(Item.getItemFromBlock(Blocks.IRON_ORE), Items.IRON_INGOT, ModItems.activatedIronDust, ModItems.dormantIronPowder, ModItems.excitedIronScraps, ModItems.astralIronSlivers, ModItems.pureIronEssence);
        addOreLine(Item.getItemFromBlock(Blocks.GOLD_ORE), Items.GOLD_INGOT, ModItems.activatedGoldDust, ModItems.dormantGoldPowder, ModItems.excitedGoldScraps, ModItems.astralGoldSlivers, ModItems.pureGoldEssence);
        addOreLine(Item.getItemFromBlock(ModBlocks.oreTungsten), ModItems.ingotTungsten, ModItems.activatedTungstenDust, ModItems.dormantTungstenPowder, ModItems.excitedTungstenScraps, ModItems.astralTungstenSlivers, ModItems.pureTungstenEssence);
        addOreLine(Item.getItemFromBlock(ModBlocks.oreCobalt), ModItems.ingotCobalt, ModItems.activatedCobaltDust, ModItems.dormantCobaltPowder, ModItems.excitedCobaltScraps, ModItems.astralCobaltSlivers, ModItems.pureCobaltEssence);
        addOreLine(Item.getItemFromBlock(ModBlocks.oreNickel), ModItems.ingotNickel, ModItems.activatedNickelDust, ModItems.dormantNickelPowder, ModItems.excitedNickelScraps, ModItems.astralNickelSlivers, ModItems.pureNickelEssence);

        RecipeHolder.addPedestalRecipe(new ItemStack(Blocks.DRAGON_EGG), new FluidStack(InitFluids.fluidDragonSoul, 1),
                4);
        RecipeHolder.addCatalystInfuserRecipe(new ItemStack(Items.GHAST_TEAR), new FluidStack(FluidRegistry.LAVA, 200),
                new FluidStack(InitFluids.fluidScreamingLava, 100));
        RecipeHolder.addDualCatalystInfuserRecipe(new ItemStack(Items.NETHER_STAR), new ItemStack(Items.QUARTZ, 10),
                new FluidStack(FluidRegistry.WATER, 100), new FluidStack(InitFluids.fluidStarWater, 100));
        RecipeHolder.addTriCatalystInfuserRecipe(new ItemStack(Items.CHORUS_FRUIT_POPPED, 3),
                new ItemStack(Items.ENDER_EYE, 2), new ItemStack(Items.SHULKER_SHELL),
                new FluidStack(InitFluids.fluidDragonSoul, 100), new FluidStack(InitFluids.fluidLiquidEnd, 100));

        RecipeHolder.addEqualizingSmelterRecipe(new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.ingotNickel), new ItemStack(ModItems.ingotNickel), new ItemStack(ModItems.ingotNickelTungstenAlloy, 4), new ItemStack(ModItems.slag));
        RecipeHolder.addEqualizingSmelterRecipe(new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.ingotTungsten), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT), new ItemStack(ModItems.ingotIronTungstenAlloy, 4), new ItemStack(ModItems.slag));
        RecipeHolder.addEqualizingSmelterRecipe(new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.dustFirestone), new ItemStack(ModItems.dustFirestone), new ItemStack(ModItems.ingotFirestoneTungstenAlloy, 4), new ItemStack(ModItems.slag));
        RecipeHolder.addEqualizingSmelterRecipe(new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.ingotCobalt), new ItemStack(ModItems.ingotCobalt), new ItemStack(ModItems.ingotCobaltTungstenAlloy, 4), new ItemStack(ModItems.slag));
        RecipeHolder.addEqualizingSmelterRecipe(new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.ingotTungsten), new ItemStack(ModItems.dustRedsoul), new ItemStack(ModItems.dustRedsoul), new ItemStack(ModItems.ingotSoulInfusedTungsten, 4), new ItemStack(ModItems.slag));

    }

}
