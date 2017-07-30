package com.jack12324.eop.fluids;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.block.ModItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Locale;

public final class InitFluids {

    public static Fluid fluidScreamingLava = new ModFluid("screaminglava", "block_screaming_lava").setLuminosity(10)
            .setViscosity(1000);
    public static Fluid fluidStarWater = new ModFluid("starwater", "block_star_water").setViscosity(200).setLuminosity(15);
    public static Fluid fluidLiquidEnd= new ModFluid("liquidend", "block_liquid_end").setViscosity(4000);
    public static Fluid fluidDragonSoul = fluidDragonSoul = new ModFluid("dragonsoul", "block_dragon_soul").setGaseous(true).setDensity(-1)
            .setViscosity(500);

    private static BlockFluidFlowing blockScreamingLava= new BlockFluidFlowing(fluidScreamingLava, Material.LAVA, "block_screaming_lava");
    private static BlockFluidFlowing blockStarWater= new BlockFluidFlowing(fluidStarWater, Material.WATER, "block_star_water");
    private static BlockFluidFlowing blockLiquidEnd= new BlockFluidFlowing(fluidLiquidEnd, Material.WATER, "block_liquid_end");
    private static BlockFluidFlowing blockDragonSoul= new BlockFluidFlowing(fluidDragonSoul, Material.WATER, "block_dragon_soul");


    public static void registerFluid() {
        FluidRegistry.registerFluid(fluidScreamingLava);
        FluidRegistry.addBucketForFluid(fluidScreamingLava);

        FluidRegistry.registerFluid(fluidStarWater);
        FluidRegistry.addBucketForFluid(fluidStarWater);

        FluidRegistry.registerFluid(fluidLiquidEnd);
        FluidRegistry.addBucketForFluid(fluidLiquidEnd);

        FluidRegistry.registerFluid(fluidDragonSoul);
        FluidRegistry.addBucketForFluid(fluidDragonSoul);


    }

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                blockScreamingLava,
                blockStarWater,
                blockLiquidEnd,
                blockDragonSoul
        );
        registerFluid();
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        blockScreamingLava.createItemBlock();
        blockStarWater.createItemBlock();
        blockLiquidEnd.createItemBlock();
        blockDragonSoul.createItemBlock();
    }

    public static void registerModels() {
        blockScreamingLava.registerItemModel();
        blockStarWater.registerItemModel();
        blockLiquidEnd.registerItemModel();
        blockDragonSoul.registerItemModel();

    }
}