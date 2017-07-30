package com.jack12324.eop.fluids;

import com.jack12324.eop.util.FluidStateMapper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class InitFluids {

    public static Fluid fluidScreamingLava = registerFluid( new ModFluid("screaming_lava", "block_screaming_lava").setLuminosity(10)
            .setViscosity(1000));
    public static Fluid fluidStarWater = registerFluid( new ModFluid("star_water", "block_star_water").setViscosity(200).setLuminosity(15));
    public static Fluid fluidLiquidEnd= registerFluid( new ModFluid("liquid_end", "block_liquid_end").setViscosity(4000));
    public static Fluid fluidDragonSoul = registerFluid( fluidDragonSoul = new ModFluid("dragon_soul", "block_dragon_soul").setGaseous(true).setDensity(-1)
            .setViscosity(500));

    public static BlockFluidFlowing blockScreamingLava= new BlockFluidFlowing(fluidScreamingLava, Material.LAVA, "block_screaming_lava");
    private static BlockFluidFlowing blockStarWater= new BlockFluidFlowing(fluidStarWater, Material.WATER, "block_star_water");
    private static BlockFluidFlowing blockLiquidEnd= new BlockFluidFlowing(fluidLiquidEnd, Material.WATER, "block_liquid_end");
    private static BlockFluidFlowing blockDragonSoul= new BlockFluidFlowing(fluidDragonSoul, Material.WATER, "block_dragon_soul");


    private static Fluid registerFluid(Fluid fluid) {
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        return fluid;
    }

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                blockScreamingLava,
                blockStarWater,
                blockLiquidEnd,
                blockDragonSoul
        );
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
        blockScreamingLava.createItemBlock(),
        blockStarWater.createItemBlock(),
        blockLiquidEnd.createItemBlock(),
        blockDragonSoul.createItemBlock()
        );
    }
}