package net.pufferlab.motio.block;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.motio.Constants;
import net.pufferlab.motio.Motio;
import net.pufferlab.motio.tileentity.TileEntityMotor;

public class BlockMotor extends BlockMotion {

    public BlockMotor(String[] materials) {
        super(Material.wood, materials, "motor", Constants.none);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMotor();
    }

    @Override
    public int getRenderType() {
        return Motio.proxy.getMotorRenderID();
    }
}
