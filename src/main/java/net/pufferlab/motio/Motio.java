package net.pufferlab.motio;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.pufferlab.motio.events.ClientEvents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Motio.MODID, version = Tags.VERSION, name = "Motio", acceptedMinecraftVersions = "[1.7.10]")
public class Motio {

    public static final String MODID = "motio";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = "net.pufferlab.motio.ClientProxy", serverSide = "net.pufferlab.motio.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance(Motio.MODID)
    public static Motio instance;

    public static Registry registry = new Registry();
    ClientEvents clientEvents = new ClientEvents();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        registry.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

        // event listeners
        MinecraftForge.EVENT_BUS.register(clientEvents);
        FMLCommonHandler.instance()
            .bus()
            .register(clientEvents);

        proxy.registerRenders();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
