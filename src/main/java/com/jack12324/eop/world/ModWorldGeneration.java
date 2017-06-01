package com.jack12324.eop.world;

import java.util.Random;

import com.jack12324.eop.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
//ya
public class ModWorldGeneration implements IWorldGenerator {
	
	
	private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances, Block genBlock){
		int deltaY = maxY - minY;
		
		for (int i =0; i < chances; i++){
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
			
			WorldGenMinable generator = new WorldGenMinable(ore, size, BlockMatcher.forBlock(genBlock));
			generator.generate(world, random, pos);
		}
	}
	
	 
	
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider){
		switch(world.provider.getDimension()){
		case 0: //overworld
			generateOre(ModBlocks.oreTungsten.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 1, 63, 4 + random.nextInt(7), 11, Blocks.STONE); //as common as iron
			generateOre(ModBlocks.oreCobalt.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 5, 15, 1 + random.nextInt(11), 2, Blocks.STONE); //twice as common as diamond
			generateOre(ModBlocks.oreNickel.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 15, 35, 2 + random.nextInt(7), 7, Blocks.STONE); //half as common as iron
			generateOre(ModBlocks.oreFirestone.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 1, 8, 4 + random.nextInt(5), 4, Blocks.STONE); //As common as redstone, more concentrated
			break;
		case -1: //Nether
			generateOre(ModBlocks.oreRedsoul.getDefaultState(), world, random, chunkX * 16, chunkZ * 16, 1, 255, 5 + random.nextInt(11), 10, Blocks.SOUL_SAND);
			break;
		case 1: //end
			break;
		}
		
	}
	
}
