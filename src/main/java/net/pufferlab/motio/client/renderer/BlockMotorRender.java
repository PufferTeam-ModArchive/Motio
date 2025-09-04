package net.pufferlab.motio.client.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.pufferlab.motio.client.models.ModelAxle;
import net.pufferlab.motio.client.models.ModelMotor;

public class BlockMotorRender extends BlockMotionRender {

    ModelAxle model = new ModelAxle();
    ModelMotor model2 = new ModelMotor();

    public BlockMotorRender(int blockComplexRenderID) {
        super(blockComplexRenderID);
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        renderInventoryModel(block, metadata, model, model2);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
        RenderBlocks renderer) {
        renderWorldModel(renderer, block, x, y, z, model2);
        return true;
    }

}
