package com.jack12324.eop.block;

import com.jack12324.eop.item.ItemModelProvider;
import com.jack12324.eop.item.ItemOreDictionary;
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
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static BlockModOre oreTungsten;
	public static BlockOreDict oreNickel;
	public static BlockOreDict oreCobalt;
	public static BlockModOre oreFirestone;
	public static BlockBase oreRedsoul;
	public static BlockBase blockTungsten;// TODO oredict
	public static BlockBase blockNickel;
	public static BlockBase blockCobalt;
	public static BlockBase blockFirestone;
	public static BlockBase blockRedsoul;
	public static BlockBase blockCrudeMachineFrame;
	public static BlockBase blockBasicMachineFrame;
	public static BlockBase blockAdvancedMachineFrame;
	public static BlockBase blockEliteMachineFrame;
	public static BlockCropPea cropPea;
	public static BlockActivationChamber activationChamber;
	public static BlockEqualizingSmelter equalizingSmelter;
	public static BlockDisablingPress disablingPress;
	public static BlockParticleExciter particleExciter;
	public static BlockPedestal pedestal;
	public static BlockCatalystInfuser catalystInfuser;
	private static BlockDualCatalystInfuser dualCatalystInfuser;
	private static BlockEndericPurifier endericPurifier;
	private static BlockStarHardener starHardener;
	private static BlockTriCatalystInfuser triCatalystInfuser;

	public static void init() {

		// ore with dust drops
		oreFirestone = register(new BlockDustOre(Material.ROCK, "ore_firestone", "pickaxe", 2, ModItems.dustFirestone,
				4, 5, 3.0f, 15f, SoundType.STONE));
		oreRedsoul = register(new BlockDustOre(Material.SAND, "ore_redsoul", "shovel", 0, ModItems.dustRedsoul, 4, 5,
				0.5f, 2.5f, SoundType.SAND));

		// ore dictionary ore
		oreNickel = register(new BlockOreDict("ore_nickel", "oreNickel", 2));
		oreCobalt = register(new BlockOreDict("ore_cobalt", "oreCobalt", 3));

		// general ore
		oreTungsten = register(new BlockModOre("ore_tungsten", 3));

		// ingot blocks
		blockTungsten = register(new BlockBase(Material.IRON, "block_tungsten"));
		blockFirestone = register(new BlockBase(Material.IRON, "block_firestone"));
		blockRedsoul = register(new BlockBase(Material.IRON, "block_redsoul"));
		blockNickel = register(new BlockBase(Material.IRON, "block_nickel"));
		blockCobalt = register(new BlockBase(Material.IRON, "block_cobalt"));

		// crafting blocks
		blockCrudeMachineFrame = register(new BlockBase(Material.IRON, "block_crude_machine_frame"));
		blockBasicMachineFrame = register(new BlockBase(Material.IRON, "block_basic_machine_frame"));
		blockAdvancedMachineFrame = register(new BlockBase(Material.IRON, "block_advanced_machine_frame"));
		blockEliteMachineFrame = register(new BlockBase(Material.IRON, "block_elite_machine_frame"));

		// God Tier Crops
		cropPea = register(new BlockCropPea(), null);

		// tile entities
		activationChamber = register(new BlockActivationChamber());
		equalizingSmelter = register(new BlockEqualizingSmelter());
		disablingPress = register(new BlockDisablingPress());
		particleExciter = register(new BlockParticleExciter());
		catalystInfuser = register(new BlockCatalystInfuser());
		dualCatalystInfuser = register(new BlockDualCatalystInfuser());
		endericPurifier = register(new BlockEndericPurifier());
		starHardener = register(new BlockStarHardener());
		triCatalystInfuser = register(new BlockTriCatalystInfuser());

		// God tier tiled entities
		pedestal = register(new BlockPedestal());

	}

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}

	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);

		if (itemBlock != null) {
			GameRegistry.register(itemBlock);

			if (block instanceof ItemModelProvider) {
				((ItemModelProvider) block).registerItemModel(itemBlock);
			}
			if (block instanceof ItemOreDictionary) {
				((ItemOreDictionary) block).initOreDict();
			}
			if (itemBlock instanceof ItemOreDictionary) {
				((ItemOreDictionary) itemBlock).initOreDict();
			}
			if (block instanceof BlockTileEntity) {
				GameRegistry.registerTileEntity(((BlockTileEntity<?>) block).getTileEntityClass(),
						block.getRegistryName().toString());
			}
		}
		return block;

	}

}