package net.pufferlab.motio.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.motio.block.BlockMetaContainer;
import net.pufferlab.motio.client.models.ModelAxle;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockAxleRender implements ISimpleBlockRenderingHandler {

    final int renderID;
    ModelAxle model = new ModelAxle();

    public BlockAxleRender(int blockComplexRenderID) {
        this.renderID = blockComplexRenderID;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        BlockMetaContainer block2 = (BlockMetaContainer) block;
        String wood = block2.getType(metadata);
        model.setAxis(0);

        GL11.glPushMatrix();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        model.render(wood);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        return false;
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
