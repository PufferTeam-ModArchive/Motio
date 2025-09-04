package net.pufferlab.motio;

import net.pufferlab.motio.client.renderer.BlockAxleRender;
import net.pufferlab.motio.client.renderer.BlockMotorRender;
import net.pufferlab.motio.client.renderer.TileEntityAxleRenderer;
import net.pufferlab.motio.tileentity.TileEntityAxle;
import net.pufferlab.motio.tileentity.TileEntityGear;
import net.pufferlab.motio.tileentity.TileEntityMotor;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    int axleRenderID;
    int motorRenderID;

    @Override
    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAxle.class, new TileEntityAxleRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMotor.class, new TileEntityAxleRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGear.class, new TileEntityAxleRenderer());

        axleRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockAxleRender(axleRenderID));
        motorRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockMotorRender(motorRenderID));
    }

    @Override
    public int getAxleRenderID() {
        return axleRenderID;
    }

    @Override
    public int getMotorRenderID() {
        return motorRenderID;
    }
}
