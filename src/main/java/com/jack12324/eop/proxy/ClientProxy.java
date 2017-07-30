package com.jack12324.eop.proxy;

import com.jack12324.eop.ExtremeOreProcessing;
import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.util.FluidStateMapper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {
    @Override
    public EntityPlayer getPlayer(MessageContext context) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            return context.getServerHandler().player;
        } else {
            return Minecraft.getMinecraft().player;
        }
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        ExtremeOreProcessing.LOGGER.info("PreInitializing ClientProxy...");

        this.registerCustomFluidBlockRenderer(InitFluids.fluidStarWater);
        this.registerCustomFluidBlockRenderer(InitFluids.fluidScreamingLava);
        this.registerCustomFluidBlockRenderer(InitFluids.fluidLiquidEnd);
        this.registerCustomFluidBlockRenderer(InitFluids.fluidDragonSoul);

    }

    /**
     * (Excerpted from Tinkers' Construct)
     */
    private void registerCustomFluidBlockRenderer(Fluid fluid) {
        Block block = fluid.getBlock();
        Item item = Item.getItemFromBlock(block);
        FluidStateMapper mapper = new FluidStateMapper(fluid);
        ModelBakery.registerItemVariants(item);
        ModelLoader.setCustomMeshDefinition(item, mapper);
        ModelLoader.setCustomStateMapper(block, mapper);
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta,
                new ModelResourceLocation(ExtremeOreProcessing.modID + ":" + id, "inventory"));
    }

}
