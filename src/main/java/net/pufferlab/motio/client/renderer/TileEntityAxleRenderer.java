package net.pufferlab.motio.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.pufferlab.motio.block.BlockMetaContainer;
import net.pufferlab.motio.client.models.ModelAxle;
import net.pufferlab.motio.tileentity.TileEntityMotion;

import org.lwjgl.opengl.GL11;

public class TileEntityAxleRenderer extends TileEntityMotionRenderer {

    ModelAxle model = new ModelAxle();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        if (tileEntity instanceof TileEntityMotion te) {

            model.setAxis(te.facingMeta);

            model.setAxisRotation(getInterpolatedRotation(te.getSpeed()), te.facingMeta);

            Block block = te.blockType;
            int metadata = te.blockMetadata;
            if (te.blockMetadata < 0) {
                metadata = 0;
            }

            GL11.glEnable(GL11.GL_LIGHTING);
            BlockMetaContainer block2 = (BlockMetaContainer) block;
            String wood = block2.getType(metadata);

            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
            model.render(wood);
            GL11.glPopMatrix();
        }
    }
}
