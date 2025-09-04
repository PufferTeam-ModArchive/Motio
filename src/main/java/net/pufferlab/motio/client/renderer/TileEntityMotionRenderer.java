package net.pufferlab.motio.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.pufferlab.motio.block.BlockMetaContainer;
import net.pufferlab.motio.client.helper.TickHolder;
import net.pufferlab.motio.client.models.ModelMotion;
import net.pufferlab.motio.tileentity.TileEntityMotion;

import org.lwjgl.opengl.GL11;

public abstract class TileEntityMotionRenderer extends TileEntitySpecialRenderer {

    public static float getInterpolatedRotation(float speed) {
        float time = TickHolder.getRenderTime();
        float offset = 0.0F;
        float angle = ((time * speed * 3f / 10 + offset) % 360) / 180 * (float) Math.PI;
        return angle;
    }

    public static String getWood(TileEntityMotion te) {
        Block block = te.blockType;
        int metadata = te.blockMetadata;
        if (te.blockMetadata < 0) {
            metadata = 0;
        }
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        String wood = block2.getType(metadata);
        return wood;
    }

    public ModelMotion rotateModel(ModelMotion model, TileEntityMotion te) {
        if (te.hasAxisPlacement()) {
            model.setAxis(te.facingMeta);
            model.setAxisRotation(getInterpolatedRotation(te.getSpeed()), te.facingMeta);
        }

        return model;
    }

    public void renderModel(ModelMotion model, TileEntityMotion te, double x, double y, double z) {
        GL11.glEnable(GL11.GL_LIGHTING);

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
        model.render(getWood(te));
        GL11.glPopMatrix();
    }
}
