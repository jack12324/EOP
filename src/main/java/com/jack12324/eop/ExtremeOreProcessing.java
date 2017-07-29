package com.jack12324.eop;

import com.jack12324.eop.block.ModBlocks;
import com.jack12324.eop.client.EOPTab;
import com.jack12324.eop.fluids.InitFluids;
import com.jack12324.eop.item.ModItems;
import com.jack12324.eop.packet.PacketHandler;
import com.jack12324.eop.proxy.CommonProxy;
import com.jack12324.eop.recipe.ModRecipes;
import com.jack12324.eop.world.ModWorldGeneration;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExtremeOreProcessing.modID, name = ExtremeOreProcessing.name, version = ExtremeOreProcessing.version)
public class ExtremeOreProcessing {
    // test
    // basic Mod info
    public static final String modID = "eop";
    public static final String name = "Extreme Ore Processing";
    public static final String version = "0.2.0";
    public static final EOPTab creativeTab = new EOPTab();

    public static final Logger LOGGER = LogManager.getLogger("Extreme Ore Processing");

    // tool materials
    public static final Item.ToolMaterial tungstenToolMaterial = EnumHelper.addToolMaterial("TUNGSTEN", 3, 2000, 10, 4,
            20);
    public static final ItemArmor.ArmorMaterial tungstenArmorMaterial = EnumHelper.addArmorMaterial("TUNGSTEN",
            modID + ":tungsten", 35, new int[]{4, 9, 7, 4}, 30, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1.0f);
    public static boolean teslaLoaded;

    @Mod.Instance(modID)
    public static ExtremeOreProcessing instance;

    static {
        FluidRegistry.enableUniversalBucket(); // Must be called before preInit
    }

    @SidedProxy(serverSide = "com.jack12324.eop.proxy.CommonProxy", clientSide = "com.jack12324.eop.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // modItems needs to be before ModBlocks or drops won't work properly
        LOGGER.info(name + " is loading!");

        teslaLoaded = Loader.isModLoaded("tesla");

        ModItems.init();
        ModBlocks.init();
        InitFluids.init();
        GameRegistry.registerWorldGenerator(new ModWorldGeneration(), 3);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new ModGuiHandler());
        PacketHandler.init();
        proxy.preInit(event);
    }

}
