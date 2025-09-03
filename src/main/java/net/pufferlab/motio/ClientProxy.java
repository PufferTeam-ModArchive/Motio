package net.pufferlab.motio;

import net.pufferlab.motio.client.renderer.BlockAxleRender;
import net.pufferlab.motio.client.renderer.TileEntityAxleRenderer;
import net.pufferlab.motio.tileentity.TileEntityAxle;
import net.pufferlab.motio.tileentity.TileEntityMotor;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    int axleRenderID;

    @Override
    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAxle.class, new TileEntityAxleRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMotor.class, new TileEntityAxleRenderer());

        axleRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockAxleRender(axleRenderID));
    }

    @Override
    public int getAxleRenderID() {
        return axleRenderID;
    }
}
