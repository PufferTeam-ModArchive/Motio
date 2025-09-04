package net.pufferlab.motio.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.pufferlab.motio.block.BlockMetaContainer;
import net.pufferlab.motio.client.models.ModelMotion;
import net.pufferlab.motio.tileentity.TileEntityMotion;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public abstract class BlockMotionRender implements ISimpleBlockRenderingHandler {

    final int renderID;

    public BlockMotionRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    public void renderInventoryModel(Block block, int metadata, ModelMotion... models) {
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        String wood = block2.getType(metadata);
        for (ModelMotion model : models) {
            model.setAxis(0);
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        for (ModelMotion model : models) {
            model.render(wood);
        }
        GL11.glPopMatrix();
    }

    public void renderWorldModel(RenderBlocks renderblocks, Block block, int x, int y, int z, ModelMotion model) {
        Tessellator tessellator = Tessellator.instance;

        TileEntityMotion motion = (TileEntityMotion) renderblocks.blockAccess.getTileEntity(x, y, z);
        rotateModel(model, motion);

        model.render(renderblocks, tessellator, block, renderblocks.blockAccess.getBlockMetadata(x, y, z), x, y, z);
    }

    public ModelMotion rotateModel(ModelMotion model, TileEntityMotion te) {
        if (te.hasAxisPlacement()) {
            model.setAxis(te.facingMeta);
        }

        return model;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderID;
    }
}
