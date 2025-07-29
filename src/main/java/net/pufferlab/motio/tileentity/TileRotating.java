package net.pufferlab.motio.tileentity;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.motio.block.BlockRotating;

public class TileRotating extends TileEntity {

    public float rotation = 0.0F; // The current rotation angle in degrees
    public float speed = 0.0F;
    public boolean needUpdate;

    boolean engine;

    ArrayList<TileRotating> neighbours = new ArrayList<TileRotating>();

    public TileRotating(Block block, boolean isEngine) {
        BlockRotating block2 = (BlockRotating) block;
        this.rotation = 0.0F;
        this.speed = block2.getBaseSpeed();
        this.needUpdate = block2.getNeedUpdate();
        this.engine = isEngine;
    }

    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    public boolean isEngine() {
        return this.engine;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getRotation() {
        return this.rotation;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return this.speed;
    }

    @Override
    public void updateEntity() {
        if (this.needUpdate) {
            this.updateNeighbourSpeed(false);
            this.updateNeighbourSpeed(true);

            this.needUpdate = false;
        } else {
            this.rotation = (this.rotation + this.speed) % 360.0F;
        }
    }

    public void updateEntitySpeed() {}

    public TileEntity neighbourUp;
    public TileEntity neighbourDown;

    boolean canMove = false;

    public void updateNeighbourSpeed(boolean side) {
        World world = getWorldObj();
        int x = this.xCoord;
        int y = this.yCoord;
        int z = this.zCoord;

        int blockMetadata = world.getBlockMetadata(x, y, z);
        TileEntity teUp = null;
        TileEntity teDown = null;
        TileEntity te = world.getTileEntity(x, y, z);
        if (blockMetadata == 0) {
            if (world.getBlockMetadata(x, y + 1, z) == 0 || world.getBlockMetadata(x, y - 1, z) == 0) {
                teUp = world.getTileEntity(x, y + 1, z);
                teDown = world.getTileEntity(x, y - 1, z);
            }
        } else if (blockMetadata == 1) {
            if (world.getBlockMetadata(x, y, z + 1) == 1 || world.getBlockMetadata(x, y, z - 1) == 1) {
                teUp = world.getTileEntity(x, y, z + 1);
                teDown = world.getTileEntity(x, y, z - 1);
            }
        } else if (blockMetadata == 2) {
            if (world.getBlockMetadata(x + 1, y, z) == 2 || world.getBlockMetadata(x - 1, y, z) == 2) {
                teUp = world.getTileEntity(x + 1, y, z);
                teDown = world.getTileEntity(x - 1, y, z);
            }
        }
        TileRotating tr = null;
        TileRotating teUpTR = null;
        TileRotating teDownTR = null;
        float speedTR = 0.0F;
        float speedUpTR = 0.0F;
        float speedDownTR = 0.0F;

        if (te instanceof TileRotating te2) {
            tr = te2;
            speedTR = tr.getSpeed();
            if (tr.isEngine()) {
                canMove = true;
            }
        }
        if (teUp instanceof TileRotating teUp2) {
            teUpTR = teUp2;
            speedUpTR = teUpTR.getSpeed();
            if (teUpTR.isEngine()) {
                canMove = true;
            }
        }
        if (teDown instanceof TileRotating teDown2) {
            teDownTR = teDown2;
            speedDownTR = teDownTR.getSpeed();
            if (teDownTR.isEngine()) {
                canMove = true;
            }
        }

        if (side) {
            if (teUpTR != null) {
                if (speedUpTR < speedTR) {
                    teUpTR.setSpeed(speedTR);
                } else {
                    tr.setSpeed(speedUpTR);
                }

                teUpTR.updateNeighbourSpeed(true);
            }
        } else {
            if (teDownTR != null) {
                if (speedDownTR < speedTR) {
                    teDownTR.setSpeed(speedTR);
                } else {
                    tr.setSpeed(speedDownTR);
                }

                teDownTR.updateNeighbourSpeed(false);
            }
        }
    }

    // Save the tile entity's data to NBT when the chunk is saved
    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setFloat("rotation", this.rotation);
    }

    // Load the tile entity's data from NBT when the chunk is loaded
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.rotation = compound.getFloat("rotation");
    }
}
