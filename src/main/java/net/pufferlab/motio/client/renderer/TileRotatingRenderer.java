package net.pufferlab.motio.client.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.pufferlab.motio.tileentity.TileRotating;

import org.lwjgl.opengl.GL11;

public class TileRotatingRenderer extends TileEntitySpecialRenderer {

    // Define the texture for your rotating block
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft:textures/blocks/planks_oak.png");
    private static final ResourceLocation TEXTURE_ENGINE = new ResourceLocation(
        "minecraft:textures/blocks/iron_block.png");

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        TileRotating tile = (TileRotating) tileEntity;

        GL11.glPushMatrix(); // Save the current OpenGL state

        int meta = tile.getBlockMetadata();
        float speed = tile.getSpeed();
        // Translate to the center of the block.
        // x, y, z are the coordinates of the block's corner. We add 0.5 to center it.
        float tX = (float) x + 0.5F;
        float tY = (float) y + 0.5F;
        float tZ = (float) z + 0.5F;
        if (tile.getGearboxType() == 2) {
            if (meta == 0) {
                tY = tY + 0.25F;
            }
            if (meta == 1) {
                tZ = tZ + 0.25F;
            }
            if (meta == 2) {
                tX = tX + 0.25F;
            }
        }
        GL11.glTranslatef(tX, tY, tZ);

        if (tile.getEngineType() == 0) {
            this.bindTexture(TEXTURE_ENGINE);
        } else {
            this.bindTexture(TEXTURE);
        }

        boolean isGearbox = tile.isGearbox();
        if (isGearbox || (tile.getEngineType() == 0)) {
            if ((tile.getGearboxType() != 2)) {
                renderCube();
            }
        }

        if (meta == 1 || meta == 4) {
            GL11.glRotatef(90, 1.0F, 0.0F, 0.0F);
        } else if (meta == 2 || meta == 5) {
            GL11.glRotatef(90, 0.0F, 0.0F, 1.0F);
        } else if (meta == 3) {
            GL11.glRotatef(90, 1.0F, 0.0F, 0.0F);
        }

        float partialRotation = tile.rotation;

        if (speed > 0) {
            partialRotation = tile.rotation + partialTicks;
        }

        GL11.glRotatef(partialRotation, 0.0F, 1.0F, 0.0F);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        if (tile.getGearboxType() == 2) {
            testRender3();

            float m = 1.0F;
            if (meta == 2) {
                m = -1.0F;
            }
            GL11.glTranslatef(0.0F, -0.5F * m, 0.0F);
            GL11.glRotatef(partialRotation, 0.0F, -1.0F, 0.0F);
            GL11.glRotatef(-partialRotation, 0.0F, 1.0F, 0.0F);

            testRender3();
        } else if(tile.isSpecial()) {
        } else {
            testRender();
        }
        if (tile.getEngineType() == 1) {
            double waterwheelSegment1 = 2.5D;
            double waterwheelSegment2 = 1.1D;

            float m = 1.0F;
            if (meta == 2) {
                m = -1.0F;
            }
            
            testRender2(waterwheelSegment1);

                GL11.glTranslatef(-1.3F * m, 0.0F, 0.55F * m);
                GL11.glRotatef(22, 0.0F, 1.0F, 0.0F);
                testRender2(waterwheelSegment2);
                GL11.glRotatef(-22, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(1.3F * m, 0.0F, -0.55F * m);

            GL11.glRotatef(45, 0.0F, 1.0F, 0.0F);
            testRender2(waterwheelSegment1);

                GL11.glTranslatef(-1.3F * m, 0.0F, 0.55F * m);
                GL11.glRotatef(22, 0.0F, 1.0F, 0.0F);
                testRender2(waterwheelSegment2);
                GL11.glRotatef(-22, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(1.3F * m, 0.0F, -0.55F * m);

            GL11.glRotatef(45, 0.0F, 1.0F, 0.0F);
            testRender2(waterwheelSegment1);

                GL11.glTranslatef(-1.3F * m, 0.0F, 0.55F * m);
                GL11.glRotatef(22, 0.0F, 1.0F, 0.0F);
                testRender2(waterwheelSegment2);
                GL11.glRotatef(-22, 0.0F, 1.0F, 0.0F);
                GL11.glTranslatef(1.3F * m, 0.0F, -0.55F * m);

            GL11.glRotatef(45, 0.0F, 1.0F, 0.0F);
            testRender2(waterwheelSegment1);

            GL11.glTranslatef(-1.3F * m, 0.0F, 0.55F * m);
            GL11.glRotatef(22, 0.0F, 1.0F, 0.0F);
            testRender2(waterwheelSegment2);
            GL11.glRotatef(-22, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.3F * m, 0.0F, -0.55F * m);

            GL11.glRotatef(45, 0.0F, 1.0F, 0.0F);

            GL11.glTranslatef(-1.3F * m, 0.0F, 0.55F * m);
            GL11.glRotatef(22, 0.0F, 1.0F, 0.0F);
            testRender2(waterwheelSegment2);
            GL11.glRotatef(-22, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.3F * m, 0.0F, -0.55F * m);

            GL11.glRotatef(45, 0.0F, 1.0F, 0.0F);

            GL11.glTranslatef(-1.3F * m, 0.0F, 0.55F * m);
            GL11.glRotatef(22, 0.0F, 1.0F, 0.0F);
            testRender2(waterwheelSegment2);
            GL11.glRotatef(-22, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.3F * m, 0.0F, -0.55F * m);

            GL11.glRotatef(45, 0.0F, 1.0F, 0.0F);
        
            GL11.glTranslatef(-1.3F * m, 0.0F, 0.55F * m);
            GL11.glRotatef(22, 0.0F, 1.0F, 0.0F);
            testRender2(waterwheelSegment2);
            GL11.glRotatef(-22, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.3F * m, 0.0F, -0.55F * m);

            GL11.glRotatef(45, 0.0F, 1.0F, 0.0F);
        
            GL11.glTranslatef(-1.3F * m, 0.0F, 0.55F * m);
            GL11.glRotatef(22, 0.0F, 1.0F, 0.0F);
            testRender2(waterwheelSegment2);
            GL11.glRotatef(-22, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.3F * m, 0.0F, -0.55F * m);

            GL11.glRotatef(45, 0.0F, 1.0F, 0.0F);
        
            GL11.glTranslatef(-1.3F * m, 0.0F, 0.55F * m);
            GL11.glRotatef(22, 0.0F, 1.0F, 0.0F);
            testRender2(waterwheelSegment2);
            GL11.glRotatef(-22, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.3F * m, 0.0F, -0.55F * m);

            /*
            GL11.glRotatef(30, 0.0F, 1.0F, 0.0F);
            testRender2();
            GL11.glRotatef(30, 0.0F, 1.0F, 0.0F);
            testRender2();
            */
        }
        if (meta == 3) {
            GL11.glRotatef(partialRotation, 0.0F, -1.0F, 0.0F);
            GL11.glRotatef(90, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(partialRotation, 0.0F, 1.0F, 0.0F);
            testRender();
        }
        if (meta == 4 || meta == 5) {
            GL11.glRotatef(partialRotation, 0.0F, -1.0F, 0.0F);
            if (meta == 4) {
                GL11.glRotatef(90, -1.0F, 0.0F, 0.0F);
            } else {
                GL11.glRotatef(90, 0.0F, 0.0F, -1.0F);
            }
            GL11.glRotatef(partialRotation, 0.0F, 1.0F, 0.0F);
            testRender();
        }

        // Re-enable lighting
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix(); // Restore the previous OpenGL state
    }

    private void testRender3() {
        Tessellator tessellator = Tessellator.instance;

        float uMin = 0.0F, uMax = 1.0F, vMin = 0.0F, vMax = 1.0F;
        tessellator.startDrawingQuads();

        // Define the offset for the pole
        final double baseX = 0.5D;
        final double baseY = 0.25D;
        final double baseZ = 0.5D;
        final double offsetX = 0.3D;
        final double offsetZ = 0.3D;
        final double offsetY = 0.0D;

        // Front face (+Z)
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(-baseX + offsetX, -baseY + offsetY, baseZ - offsetZ, uMin, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, -baseY + offsetY, baseZ - offsetZ, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, baseY - offsetY, baseZ - offsetZ, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(-baseX + offsetX, baseY - offsetY, baseZ - offsetZ, uMin, vMin); // Adjusted X, Z

        // Back face (-Z)
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        tessellator.addVertexWithUV(-baseX + offsetX, baseY - offsetY, -baseZ + offsetZ, uMin, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, baseY - offsetY, -baseZ + offsetZ, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, -baseY + offsetY, -baseZ + offsetZ, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(-baseX + offsetX, -baseY + offsetY, -baseZ + offsetZ, uMin, vMax); // Adjusted X, Z

        // Top face (+Y)
        // X and Z coordinates for the top face need to reflect the pole's narrower width
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-baseX + offsetX, baseY - offsetY, baseZ - offsetZ, uMin, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, baseY - offsetY, baseZ - offsetZ, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, baseY - offsetY, -baseZ + offsetZ, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(-baseX + offsetX, baseY - offsetY, -baseZ + offsetZ, uMin, vMin); // Adjusted X, Z

        // Bottom face (-Y)
        // X and Z coordinates for the bottom face need to reflect the pole's narrower width
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        tessellator.addVertexWithUV(-baseX + offsetX, -baseY + offsetY, -baseZ + offsetZ, uMin, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, -baseY + offsetY, -baseZ + offsetZ, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, -baseY + offsetY, baseZ - offsetZ, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(-baseX + offsetX, -baseY + offsetY, baseZ - offsetZ, uMin, vMax); // Adjusted X, Z

        // Right face (+X)
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(baseX - offsetX, -baseY + offsetY, baseZ - offsetZ, uMin, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, -baseY + offsetY, -baseZ + offsetZ, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, baseY - offsetY, -baseZ + offsetZ, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(baseX - offsetX, baseY - offsetY, baseZ - offsetZ, uMin, vMin); // Adjusted X, Z

        // Left face (-X)
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(-baseX + offsetX, baseY - offsetY, baseZ - offsetZ, uMin, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(-baseX + offsetX, baseY - offsetY, -baseZ + offsetZ, uMax, vMin); // Adjusted X, Z
        tessellator.addVertexWithUV(-baseX + offsetX, -baseY + offsetY, -baseZ + offsetZ, uMax, vMax); // Adjusted X, Z
        tessellator.addVertexWithUV(-baseX + offsetX, -baseY + offsetY, baseZ - offsetZ, uMin, vMax); // Adjusted X, Z

        tessellator.draw();
    }

    private void testRender2(double offsetL) {
        Tessellator tessellator = Tessellator.instance;

        float uMin = 0.0F, uMax = 1.0F, vMin = 0.0F, vMax = 1.0F;
        final double offset = 0.45D;

        tessellator.startDrawingQuads();

        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(-0.5D + offset, -0.45D, offsetL - offset, uMin, vMax); // Adjusted Z to 1.0D
        tessellator.addVertexWithUV(0.5D - offset, -0.45D, offsetL - offset, uMax, vMax); // Adjusted Z to 1.0D
        tessellator.addVertexWithUV(0.5D - offset, 0.45D, offsetL - offset, uMax, vMin); // Adjusted Z to 1.0D
        tessellator.addVertexWithUV(-0.5D + offset, 0.45D, offsetL - offset, uMin, vMin); // Adjusted Z to 1.0D

        // Back face (-Z)
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        tessellator.addVertexWithUV(-0.5D + offset, 0.45D, -offsetL + offset, uMin, vMin); // Adjusted Z to -1.0D
        tessellator.addVertexWithUV(0.5D - offset, 0.45D, -offsetL + offset, uMax, vMin); // Adjusted Z to -1.0D
        tessellator.addVertexWithUV(0.5D - offset, -0.45D, -offsetL + offset, uMax, vMax); // Adjusted Z to -1.0D
        tessellator.addVertexWithUV(-0.5D + offset, -0.45D, -offsetL + offset, uMin, vMax); // Adjusted Z to -1.0D

        // Top face (+Y)
        // X and Z coordinates for the top face need to reflect the pole's narrower width and extended length
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5D + offset, 0.45D, offsetL - offset, uMin, vMax); // Adjusted Z to 1.0D
        tessellator.addVertexWithUV(0.5D - offset, 0.45D, offsetL - offset, uMax, vMax); // Adjusted Z to 1.0D
        tessellator.addVertexWithUV(0.5D - offset, 0.45D, -offsetL + offset, uMax, vMin); // Adjusted Z to -1.0D
        tessellator.addVertexWithUV(-0.5D + offset, 0.45D, -offsetL + offset, uMin, vMin); // Adjusted Z to -1.0D

        // Bottom face (-Y)
        // X and Z coordinates for the bottom face need to reflect the pole's narrower width and extended length
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5D + offset, -0.45D, -offsetL + offset, uMin, vMin); // Adjusted Z to -1.0D
        tessellator.addVertexWithUV(0.5D - offset, -0.45D, -offsetL + offset, uMax, vMin); // Adjusted Z to -1.0D
        tessellator.addVertexWithUV(0.5D - offset, -0.45D, offsetL - offset, uMax, vMax); // Adjusted Z to 1.0D
        tessellator.addVertexWithUV(-0.5D + offset, -0.45D, offsetL - offset, uMin, vMax); // Adjusted Z to 1.0D

        // Right face (+X)
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(0.5D - offset, -0.45D, offsetL - offset, uMin, vMax); // Adjusted Z to 1.0D
        tessellator.addVertexWithUV(0.5D - offset, -0.45D, -offsetL + offset, uMax, vMax); // Adjusted Z to -1.0D
        tessellator.addVertexWithUV(0.5D - offset, 0.45D, -offsetL + offset, uMax, vMin); // Adjusted Z to -1.0D
        tessellator.addVertexWithUV(0.5D - offset, 0.45D, offsetL - offset, uMin, vMin); // Adjusted Z to 1.0D

        // Left face (-X)
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(-0.5D + offset, 0.45D, offsetL - offset, uMin, vMin); // Adjusted Z to 1.0D
        tessellator.addVertexWithUV(-0.5D + offset, 0.45D, -offsetL + offset, uMax, vMin); // Adjusted Z to -1.0D
        tessellator.addVertexWithUV(-0.5D + offset, -0.45D, -offsetL + offset, uMax, vMax); // Adjusted Z to -1.0D
        tessellator.addVertexWithUV(-0.5D + offset, -0.45D, offsetL - offset, uMin, vMax); // Adjusted Z to 1.0D

        tessellator.draw();
    }

    private void testRender() {
        Tessellator tessellator = Tessellator.instance;

        float uMin = 0.0F, uMax = 1.0F, vMin = 0.0F, vMax = 1.0F;
        tessellator.startDrawingQuads();

        // Define the offset for the pole
        final double offset = 0.3D; // 0.25 closer to the middle from each side

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
     */
    private void renderCube() {
        Tessellator tessellator = Tessellator.instance;
        float uMin = 0.0F, uMax = 1.0F, vMin = 0.0F, vMax = 1.0F; // Full texture range

        tessellator.startDrawingQuads();

        // Front face (+Z)
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.addVertexWithUV(-0.40D, -0.40D, 0.40D, uMin, vMax);
        tessellator.addVertexWithUV(0.40D, -0.40D, 0.40D, uMax, vMax);
        tessellator.addVertexWithUV(0.40D, 0.40D, 0.40D, uMax, vMin);
        tessellator.addVertexWithUV(-0.40D, 0.40D, 0.40D, uMin, vMin);

        // Back face (-Z)
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        tessellator.addVertexWithUV(-0.40D, 0.40D, -0.40D, uMin, vMin);
        tessellator.addVertexWithUV(0.40D, 0.40D, -0.40D, uMax, vMin);
        tessellator.addVertexWithUV(0.40D, -0.40D, -0.40D, uMax, vMax);
        tessellator.addVertexWithUV(-0.40D, -0.40D, -0.40D, uMin, vMax);

        // Top face (+Y)
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.40D, 0.40D, 0.40D, uMin, vMax);
        tessellator.addVertexWithUV(0.40D, 0.40D, 0.40D, uMax, vMax);
        tessellator.addVertexWithUV(0.40D, 0.40D, -0.40D, uMax, vMin);
        tessellator.addVertexWithUV(-0.40D, 0.40D, -0.40D, uMin, vMin);

        // Bottom face (-Y)
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        tessellator.addVertexWithUV(-0.40D, -0.40D, -0.40D, uMin, vMin);
        tessellator.addVertexWithUV(0.40D, -0.40D, -0.40D, uMax, vMin);
        tessellator.addVertexWithUV(0.40D, -0.40D, 0.40D, uMax, vMax);
        tessellator.addVertexWithUV(-0.40D, -0.40D, 0.40D, uMin, vMax);

        // Right face (+X)
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(0.40D, -0.40D, 0.40D, uMin, vMax);
        tessellator.addVertexWithUV(0.40D, -0.40D, -0.40D, uMax, vMax);
        tessellator.addVertexWithUV(0.40D, 0.40D, -0.40D, uMax, vMin);
        tessellator.addVertexWithUV(0.40D, 0.40D, 0.40D, uMin, vMin);

        // Left face (-X)
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        tessellator.addVertexWithUV(-0.40D, 0.40D, 0.40D, uMin, vMin);
        tessellator.addVertexWithUV(-0.40D, 0.40D, -0.40D, uMax, vMin);
        tessellator.addVertexWithUV(-0.40D, -0.40D, -0.40D, uMax, vMax);
        tessellator.addVertexWithUV(-0.40D, -0.40D, 0.40D, uMin, vMax);

        tessellator.draw();
    }
}
