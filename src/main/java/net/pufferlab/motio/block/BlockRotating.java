package net.pufferlab.motio.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.motio.tileentity.TileRotating;

public class BlockRotating extends BlockContainer {

    public float baseSpeed;
    public boolean needUpdate;
    boolean engine;
    boolean gearbox;
    int gearboxT;

    public BlockRotating(Material material, boolean isEngine, boolean isGearbox, int gearboxType) {
        super(material);
        engine = isEngine;
        gearbox = isGearbox;
        gearboxT = gearboxType;
        if (engine) {
            this.setBlockTextureName("minecraft:iron_block");
            this.baseSpeed = 2.0F;
        } else {
            this.setBlockTextureName("minecraft:planks_oak");
            this.baseSpeed = 0.0F;
        }
    }

    public boolean isEngine() {
        return this.engine;
    }

    public boolean isGearbox() {
        return this.gearbox;
    }

    public int getGearboxType() {
        return this.gearboxT;
    }

    public float getBaseSpeed() {
        return this.baseSpeed;
    }

    public boolean getNeedUpdate() {
        return this.needUpdate;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileRotating(this);
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public void onNeighborBlockChange(World worldIn, int x, int y, int z, Block neighbor) {
        TileRotating te = (TileRotating) worldIn.getTileEntity(x, y, z);
        te.setNeedUpdate(true);
    }

    @Override
    public void onBlockAdded(World worldIn, int x, int y, int z) {
        TileRotating te = (TileRotating) worldIn.getTileEntity(x, y, z);
        this.needUpdate = true;
        te.setNeedUpdate(true);

        super.onBlockAdded(worldIn, x, y, z);
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, int x, int y, int z, int meta) {}

    @Override
    public void breakBlock(World worldIn, int x, int y, int z, Block blockBroken, int meta) {
        TileRotating te = (TileRotating) worldIn.getTileEntity(x, y, z);
        te.setNeedUpdate(true);

        super.breakBlock(worldIn, x, y, z, blockBroken, meta);
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
