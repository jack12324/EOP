package com.jack12324.eop;

import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.client.EOPTab;
import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.machine.pedestal.TESRPedestal;
import com.jack12324.eop.machine.pedestal.TileEntityPedestal;
import com.jack12324.eop.packet.PacketHandler;
import com.jack12324.eop.proxy.CommonProxy;
import com.jack12324.eop.recipe.ModRecipes;
import com.jack12324.eop.util.FluidStateMapper;
import com.jack12324.eop.world.ModWorldGeneration;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.jack12324.eop.fluids.InitFluids.*;

@Mod(modid = ExtremeOreProcessing.modID, name = ExtremeOreProcessing.name, version = ExtremeOreProcessing.version, acceptedMinecraftVersions = ExtremeOreProcessing.mcversion, canBeDeactivated = true)
public class ExtremeOreProcessing {
    // test
    // basic Mod info
    public static final String modID = "eop";
    public static final String name = "Extreme Ore Processing";
    public static final String version = "1.12-0.9.1";
    public static final EOPTab creativeTab = new EOPTab();
    public static final String mcversion = "@MCVERSION@";

    public static final Logger LOGGER = LogManager.getLogger("Extreme Ore Processing");

    // tool materials
    public static final Item.ToolMaterial tungstenToolMaterial = EnumHelper.addToolMaterial("TUNGSTEN", 3, 2000, 10, 4,
            20);
    public static final ItemArmor.ArmorMaterial tungstenArmorMaterial = EnumHelper.addArmorMaterial("TUNGSTEN",
            modID + ":tungsten", 35, new int[]{4, 9, 7, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0f);
    public static boolean teslaLoaded;

    @SidedProxy(serverSide = "com.jack12324.eop.proxy.CommonProxy", clientSide = "com.jack12324.eop.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(modID)
    public static ExtremeOreProcessing instance;

    static {
        FluidRegistry.enableUniversalBucket(); // Must be called before preInit
    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler{
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
            InitFluids.registerItemBlocks(event.getRegistry());
        }
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
            InitFluids.register(event.getRegistry());
        }
    }
    @Mod.EventBusSubscriber(value = Side.CLIENT, modid = modID)
     public static class ModelHandler{
        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
            registerFluidModels();
            ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new TESRPedestal());

        }
        public static void registerFluidModels() {
             registerFluidModels(fluidDragonSoul);
              registerFluidModels(fluidScreamingLava);
             registerFluidModels(fluidStarWater);
             registerFluidModels(fluidLiquidEnd);
        }

    private static void registerFluidModels(final Fluid fluid){

        Block block = fluid.getBlock();
        Item item = Item.getItemFromBlock(block);
        FluidStateMapper mapper = new FluidStateMapper(fluid);
        ModelBakery.registerItemVariants(item);
        ModelLoader.setCustomMeshDefinition(item, mapper);
        ModelLoader.setCustomStateMapper(block, mapper);
    }


    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // modItems needs to be before ModBlocks or drops won't work properly
        LOGGER.info(name + " is loading!");

        teslaLoaded = Loader.isModLoaded("tesla");

        GameRegistry.registerWorldGenerator(new ModWorldGeneration(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
        PacketHandler.init();

        LOGGER.info(name + ": preInit complete");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        ModRecipes.init();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }



}
