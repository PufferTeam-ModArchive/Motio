package net.pufferlab.motio.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.pufferlab.motio.client.helper.TickHolder;

public abstract class TileEntityMotionRenderer extends TileEntitySpecialRenderer {

    public static float getInterpolatedRotation(float speed) {
        float time = TickHolder.getRenderTime();
        float offset = 0.0F;
        float angle = ((time * speed * 3f / 10 + offset) % 360) / 180 * (float) Math.PI;
        return angle;
    }
}
