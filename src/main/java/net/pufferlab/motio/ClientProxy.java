package net.pufferlab.motio;

import net.pufferlab.motio.client.renderer.TileRotatingRenderer;
import net.pufferlab.motio.tileentity.TileRotating;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    public int axleRendererID = RenderingRegistry.getNextAvailableRenderId();

    @Override
    public void registerRenderers() {
        // Register your Tile Entity Special Renderer here
        ClientRegistry.bindTileEntitySpecialRenderer(TileRotating.class, new TileRotatingRenderer());
    }

}
