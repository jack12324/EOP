package com.jack12324.eop.block;

import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.activationChamber.BlockActivationChamber;
import com.jack12324.eop.machine.catalystInfuser.BlockCatalystInfuser;
import com.jack12324.eop.machine.disablingPress.BlockDisablingPress;
import com.jack12324.eop.machine.dualCatalystInfuser.BlockDualCatalystInfuser;
import com.jack12324.eop.machine.endericPurifier.BlockEndericPurifier;
import com.jack12324.eop.machine.equalizingSmelter.BlockEqualizingSmelter;
import com.jack12324.eop.machine.particleExciter.BlockParticleExciter;
import com.jack12324.eop.machine.pedestal.BlockPedestal;
import com.jack12324.eop.machine.starHardener.BlockStarHardener;
import com.jack12324.eop.machine.triCatalystInfuser.BlockTriCatalystInfuser;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static BlockOreDict oreTungsten = new BlockOreDict("ore_tungsten", "oreTungsten", 3);
    public static BlockOreDict oreNickel = new BlockOreDict("ore_nickel", "oreNickel", 2);
    public static BlockOreDict oreCobalt = new BlockOreDict("ore_cobalt", "oreCobalt", 3);

    public static BlockModOre oreFirestone = new BlockDustOre(Material.ROCK, "ore_firestone", "pickaxe", 2, ModItems.dustFirestone,
            4, 5, 3.0f, 15f, SoundType.STONE);
    public static BlockBase oreRedsoul = new BlockDustOre(Material.SAND, "ore_redsoul", "shovel", 0, ModItems.dustRedsoul, 4, 5,
            0.5f, 2.5f, SoundType.SAND);

    public static BlockBase blockTungsten = new BlockBase(Material.IRON, "block_tungsten");
    public static BlockBase blockNickel = new BlockBase(Material.IRON, "block_nickel");
    public static BlockBase blockCobalt = new BlockBase(Material.IRON, "block_cobalt");
    public static BlockBase blockFirestone = new BlockBase(Material.IRON, "block_firestone");
    public static BlockBase blockRedsoul = new BlockBase(Material.IRON, "block_redsoul");

    public static BlockBase blockCrudeMachineFrame = new BlockBase(Material.IRON, "block_crude_machine_frame");
    public static BlockBase blockBasicMachineFrame = new BlockBase(Material.IRON, "block_basic_machine_frame");
    public static BlockBase blockAdvancedMachineFrame = new BlockBase(Material.IRON, "block_advanced_machine_frame");
    public static BlockBase blockEliteMachineFrame = new BlockBase(Material.IRON, "block_elite_machine_frame");

    public static BlockActivationChamber activationChamber = new BlockActivationChamber();
    public static BlockEqualizingSmelter equalizingSmelter = new BlockEqualizingSmelter();
    public static BlockDisablingPress disablingPress = new BlockDisablingPress();
    public static BlockParticleExciter particleExciter = new BlockParticleExciter();
    public static BlockPedestal pedestal = new BlockPedestal();
    public static BlockCatalystInfuser catalystInfuser = new BlockCatalystInfuser();
    public static BlockDualCatalystInfuser dualCatalystInfuser = new BlockDualCatalystInfuser();
    public static BlockEndericPurifier endericPurifier = new BlockEndericPurifier();
    public static BlockStarHardener starHardener = new BlockStarHardener();
    public static BlockTriCatalystInfuser triCatalystInfuser = new BlockTriCatalystInfuser();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                oreTungsten,
                oreNickel,
                oreCobalt,
                oreFirestone,
                oreRedsoul,
                blockTungsten,
                blockNickel,
                blockCobalt,
                blockFirestone,
                blockRedsoul,
                blockCrudeMachineFrame,
                blockBasicMachineFrame,
                blockAdvancedMachineFrame,
                blockEliteMachineFrame,
                activationChamber,
                equalizingSmelter,
                disablingPress,
                particleExciter,
                pedestal,
                catalystInfuser,
                dualCatalystInfuser,
                endericPurifier,
                starHardener,
                triCatalystInfuser
        );
        GameRegistry.registerTileEntity(activationChamber.getTileEntityClass(), activationChamber.getRegistryName().toString());
        GameRegistry.registerTileEntity(equalizingSmelter.getTileEntityClass(), equalizingSmelter.getRegistryName().toString());
        GameRegistry.registerTileEntity(disablingPress.getTileEntityClass(), disablingPress.getRegistryName().toString());
        GameRegistry.registerTileEntity(particleExciter.getTileEntityClass(), particleExciter.getRegistryName().toString());
        GameRegistry.registerTileEntity(pedestal.getTileEntityClass(), pedestal.getRegistryName().toString());
        GameRegistry.registerTileEntity(catalystInfuser.getTileEntityClass(), catalystInfuser.getRegistryName().toString());
        GameRegistry.registerTileEntity(dualCatalystInfuser.getTileEntityClass(), dualCatalystInfuser.getRegistryName().toString());
        GameRegistry.registerTileEntity(endericPurifier.getTileEntityClass(), endericPurifier.getRegistryName().toString());
        GameRegistry.registerTileEntity(starHardener.getTileEntityClass(), starHardener.getRegistryName().toString());
        GameRegistry.registerTileEntity(triCatalystInfuser.getTileEntityClass(), triCatalystInfuser.getRegistryName().toString());

    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                oreTungsten.createItemBlock(),
                oreNickel.createItemBlock(),
                oreCobalt.createItemBlock(),
                oreFirestone.createItemBlock(),
                oreRedsoul.createItemBlock(),
                blockTungsten.createItemBlock(),
                blockNickel.createItemBlock(),
                blockCobalt.createItemBlock(),
                blockFirestone.createItemBlock(),
                blockRedsoul.createItemBlock(),
                blockCrudeMachineFrame.createItemBlock(),
                blockBasicMachineFrame.createItemBlock(),
                blockAdvancedMachineFrame.createItemBlock(),
                blockEliteMachineFrame.createItemBlock(),
                activationChamber.createItemBlock(),
                equalizingSmelter.createItemBlock(),
                disablingPress.createItemBlock(),
                particleExciter.createItemBlock(),
                pedestal.createItemBlock(),
                catalystInfuser.createItemBlock(),
                dualCatalystInfuser.createItemBlock(),
                endericPurifier.createItemBlock(),
                starHardener.createItemBlock(),
                triCatalystInfuser.createItemBlock()
        );

    }

    public static void registerModels() {
            oreTungsten.registerItemModel();
            oreNickel.registerItemModel();
            oreCobalt.registerItemModel();
            oreFirestone.registerItemModel();
            oreRedsoul.registerItemModel();
            blockTungsten.registerItemModel();
            blockNickel.registerItemModel();
            blockCobalt.registerItemModel();
            blockFirestone.registerItemModel();
            blockRedsoul.registerItemModel();
            blockCrudeMachineFrame.registerItemModel();
            blockBasicMachineFrame.registerItemModel();
            blockAdvancedMachineFrame.registerItemModel();
            blockEliteMachineFrame.registerItemModel();
            activationChamber.registerItemModel();
            equalizingSmelter.registerItemModel();
            disablingPress.registerItemModel();
            particleExciter.registerItemModel();
            pedestal.registerItemModel();
            catalystInfuser.registerItemModel();
            dualCatalystInfuser.registerItemModel();
            endericPurifier.registerItemModel();
            starHardener.registerItemModel();
            triCatalystInfuser.registerItemModel();

    }

}