package net.pufferlab.motio.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.motio.Constants;
import net.pufferlab.motio.Motio;
import net.pufferlab.motio.tileentity.TileEntityAxle;

public class BlockAxle extends BlockMotion {

    public BlockAxle(String[] materials) {
        super(Material.wood, materials, "axle", Constants.none);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityAxle();
    }

    @Override
    public int getRenderType() {
        return Motio.proxy.getAxleRenderID();
    }
}
