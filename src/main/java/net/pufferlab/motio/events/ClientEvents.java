package net.pufferlab.motio.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.pufferlab.motio.client.helper.TickHolder;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientEvents {

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (!isGameActive()) return;
        TickHolder.tick();
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {}

    @SubscribeEvent
    public void onLoadWorld(WorldEvent.Load event) {
        World world = event.world;
        if (world.isRemote && world instanceof WorldClient) {
            TickHolder.reset();
        }
    }

    @SubscribeEvent
    public void onUnloadWorld(WorldEvent.Unload event) {
        if (event.world.isRemote) {
            TickHolder.reset();
        }
    }

    protected boolean isGameActive() {
        return !(Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null);
    }
}
