package com.jack12324.eop.proxy;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.util.FluidStateMapper;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
	//private static final Map<ItemStack, ModelResourceLocation> MODEL_LOCATIONS_FOR_REGISTERING = new HashMap<ItemStack, ModelResourceLocation>();
	@Override
	public void registerItemRenderer(Item item, int meta, String id){
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ExtremeOreProcessing.modID + ":" + id, "inventory"));
	}

	@Override
	public String localize(String unlocalized, Object... args){
		return I18n.format(unlocalized, args);
	}
	
	 
	    public void preInit(FMLPreInitializationEvent event){
	        ExtremeOreProcessing.LOGGER.info("PreInitializing ClientProxy...");

	       // for(Map.Entry<ItemStack, ModelResourceLocation> entry : MODEL_LOCATIONS_FOR_REGISTERING.entrySet()){
	       //     ModelLoader.setCustomModelResourceLocation(entry.getKey().getItem(), entry.getKey().getItemDamage(), entry.getValue());
	       // }

	        this.registerCustomFluidBlockRenderer(InitFluids.fluidStarWater);
	        this.registerCustomFluidBlockRenderer(InitFluids.fluidScreamingLava);
	        this.registerCustomFluidBlockRenderer(InitFluids.fluidLiquidEnd);
	        this.registerCustomFluidBlockRenderer(InitFluids.fluidDragonSoul);

	        
	    }

	    /**
	     * (Excerpted from Tinkers' Construct)
	     */
	    private void registerCustomFluidBlockRenderer(Fluid fluid){
	        Block block = fluid.getBlock();
	        Item item = Item.getItemFromBlock(block);
	        FluidStateMapper mapper = new FluidStateMapper(fluid);
	        ModelLoader.registerItemVariants(item);
	        ModelLoader.setCustomMeshDefinition(item, mapper);
	        ModelLoader.setCustomStateMapper(block, mapper);
	    }
	 
	
}
