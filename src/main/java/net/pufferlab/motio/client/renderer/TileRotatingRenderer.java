package net.pufferlab.motio.client.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.pufferlab.motio.tileentity.TileRotating;

import org.lwjgl.opengl.GL11;

public class TileRotatingRenderer extends TileEntitySpecialRenderer {

    // Define the texture for your rotating block
    private static final ResourceLocation TEXTURE = new ResourceLocation(
        "rotatingblock:textures/blocks/rotating_block.png");

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        TileRotating tile = (TileRotating) tileEntity;

        GL11.glPushMatrix(); // Save the current OpenGL state

        // Translate to the center of the block.
        // x, y, z are the coordinates of the block's corner. We add 0.5 to center it.
        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);

        int meta = tile.getBlockMetadata();
        float speed = tile.getSpeed();
        if (meta == 1) {
            GL11.glRotatef(90, 1.0F, 0.0F, 0.0F);
        } else if (meta == 2) {
            GL11.glRotatef(90, 0.0F, 0.0F, 1.0F);
        }

        float partialRotation = tile.rotation;

        if (speed > 0) {
            partialRotation = tile.rotation + partialTicks;
        }

        GL11.glRotatef(partialRotation, 0.0F, 1.0F, 0.0F);

        // Apply rotation around the Y-axis (up/down axis in Minecraft)

        // Bind your custom texture. This is important!
        // You can also bind the block's own texture map if you get its IIcon.
        this.bindTexture(TEXTURE);

        // Enable alpha blending for transparency if your texture has it
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // Disable lighting so the cube is always brightly lit
        GL11.glDisable(GL11.GL_LIGHTING);

        // Draw a simple textured cube
        // renderCube();

        testRender();

        // Re-enable lighting
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix(); // Restore the previous OpenGL state
    }

    private void testRender() {
        Tessellator tessellator = Tessellator.instance;

        float uMin = 0.0F, uMax = 1.0F, vMin = 0.0F, vMax = 1.0F;
        tessellator.startDrawingQuads();

        // Define the offset for the pole
        final double offset = 0.25D; // 0.25 closer to the middle from each side

        // Front face (+Z)
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(-0.5D + offset, -0.5D, 0.5D - offset, uMin, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, -0.5D, 0.5D - offset, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, 0.5D, 0.5D - offset, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(-0.5D + offset, 0.5D, 0.5D - offset, uMin, vMin); // Adjusted X, Z

        // Back face (-Z)
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        tessellator.addVertexWithUV(-0.5D + offset, 0.5D, -0.5D + offset, uMin, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, 0.5D, -0.5D + offset, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, -0.5D, -0.5D + offset, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(-0.5D + offset, -0.5D, -0.5D + offset, uMin, vMax); // Adjusted X, Z

        // Top face (+Y)
        // X and Z coordinates for the top face need to reflect the pole's narrower width
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5D + offset, 0.5D, 0.5D - offset, uMin, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, 0.5D, 0.5D - offset, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, 0.5D, -0.5D + offset, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(-0.5D + offset, 0.5D, -0.5D + offset, uMin, vMin); // Adjusted X, Z

        // Bottom face (-Y)
        // X and Z coordinates for the bottom face need to reflect the pole's narrower width
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5D + offset, -0.5D, -0.5D + offset, uMin, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, -0.5D, -0.5D + offset, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, -0.5D, 0.5D - offset, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(-0.5D + offset, -0.5D, 0.5D - offset, uMin, vMax); // Adjusted X, Z

        // Right face (+X)
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(0.5D - offset, -0.5D, 0.5D - offset, uMin, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, -0.5D, -0.5D + offset, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, 0.5D, -0.5D + offset, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(0.5D - offset, 0.5D, 0.5D - offset, uMin, vMin); // Adjusted X, Z

        // Left face (-X)
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5D + offset, 0.5D, 0.5D - offset, uMin, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(-0.5D + offset, 0.5D, -0.5D + offset, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(-0.5D + offset, -0.5D, -0.5D + offset, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(-0.5D + offset, -0.5D, 0.5D - offset, uMin, vMax); // Adjusted X, Z

        tessellator.draw();
    }

    /**
     * Helper method to render a unit cube (1x1x1) with texture coordinates.
     * The cube is centered at (0,0,0) and extends from -0.5 to +0.5 on each axis.
     */
    private void renderCube() {
        Tessellator tessellator = Tessellator.instance;
        float uMin = 0.0F, uMax = 1.0F, vMin = 0.0F, vMax = 1.0F; // Full texture range

        tessellator.startDrawingQuads();

        // Front face (+Z)
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(-0.5D, -0.5D, 0.5D, uMin, vMax);
        tessellator.addVertexWithUV(0.5D, -0.5D, 0.5D, uMax, vMax);
        tessellator.addVertexWithUV(0.5D, 0.5D, 0.5D, uMax, vMin);
        tessellator.addVertexWithUV(-0.5D, 0.5D, 0.5D, uMin, vMin);

        // Back face (-Z)
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        tessellator.addVertexWithUV(-0.5D, 0.5D, -0.5D, uMin, vMin);
        tessellator.addVertexWithUV(0.5D, 0.5D, -0.5D, uMax, vMin);
        tessellator.addVertexWithUV(0.5D, -0.5D, -0.5D, uMax, vMax);
        tessellator.addVertexWithUV(-0.5D, -0.5D, -0.5D, uMin, vMax);

        // Top face (+Y)
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5D, 0.5D, 0.5D, uMin, vMax);
        tessellator.addVertexWithUV(0.5D, 0.5D, 0.5D, uMax, vMax);
        tessellator.addVertexWithUV(0.5D, 0.5D, -0.5D, uMax, vMin);
        tessellator.addVertexWithUV(-0.5D, 0.5D, -0.5D, uMin, vMin);

        // Bottom face (-Y)
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5D, -0.5D, -0.5D, uMin, vMin);
        tessellator.addVertexWithUV(0.5D, -0.5D, -0.5D, uMax, vMin);
        tessellator.addVertexWithUV(0.5D, -0.5D, 0.5D, uMax, vMax);
        tessellator.addVertexWithUV(-0.5D, -0.5D, 0.5D, uMin, vMax);

        // Right face (+X)
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(0.5D, -0.5D, 0.5D, uMin, vMax);
        tessellator.addVertexWithUV(0.5D, -0.5D, -0.5D, uMax, vMax);
        tessellator.addVertexWithUV(0.5D, 0.5D, -0.5D, uMax, vMin);
        tessellator.addVertexWithUV(0.5D, 0.5D, 0.5D, uMin, vMin);

        // Left face (-X)
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5D, 0.5D, 0.5D, uMin, vMin);
        tessellator.addVertexWithUV(-0.5D, 0.5D, -0.5D, uMax, vMin);
        tessellator.addVertexWithUV(-0.5D, -0.5D, -0.5D, uMax, vMax);
        tessellator.addVertexWithUV(-0.5D, -0.5D, 0.5D, uMin, vMax);

        tessellator.draw();
    }
}
