package net.pufferlab.motio.tileentity;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.motio.block.BlockRotating;

public class TileRotating extends TileEntity {

    public float rotation = 0.0F; // The current rotation angle in degrees
    public float speed = 0.0F;
    public float baseSpeed = 0.0F;
    public boolean needUpdate;
    public float hSpeed = 0.0F;
    boolean engine;

    ArrayList<TileRotating> neighbours = new ArrayList<TileRotating>();

    public TileRotating(Block block, boolean isEngine) {
        BlockRotating block2 = (BlockRotating) block;
        this.rotation = 0.0F;
        this.needUpdate = block2.getNeedUpdate();
        this.baseSpeed = block2.getBaseSpeed();
        this.speed = baseSpeed;
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
        updateNetwork();
        if (worldObj.isRemote) {
            this.rotation = (this.rotation + this.getSpeed()) % 360.0F;
        }
    }

    public void updateNetwork() {
        World world = getWorldObj();
        Queue<TileRotating> queue = new LinkedList<>();
        Set<TileRotating> visited = new HashSet<>();
        float hSpeed = 0.0F;
        queue.offer(this);
        visited.add(this);

        while (!queue.isEmpty()) {
            TileRotating currentTR = queue.poll();

            hSpeed = Math.max(hSpeed, currentTR.baseSpeed);

            for (TileRotating neighborTR : getConnectedTiles(currentTR, world)) {
                if (!visited.contains(neighborTR)) {
                    visited.add(neighborTR);
                    queue.offer(neighborTR);
                }
            }
        }

        for (TileRotating vs : visited) {
            if (this.needUpdate) {
                if (world.isRemote) {
                    vs.setRotation(this.getRotation());
                }
            }
        }
        this.needUpdate = false;
        for (TileRotating vs : visited) {
            vs.setSpeed(hSpeed);
        }

        // visited.clear();
        // queue.clear();

    }

    public void updateEntitySpeed() {}

    public TileEntity neighbourUp;
    public TileEntity neighbourDown;

    public Set<TileRotating> getConnectedTiles(TileRotating currentTR, World world) {
        Set<TileRotating> connected = new HashSet<>();

        int x = currentTR.xCoord;
        int y = currentTR.yCoord;
        int z = currentTR.zCoord;

        int blockMetadata = world.getBlockMetadata(x, y, z);
        TileEntity teUp = null;
        TileEntity teDown = null;
        if (blockMetadata == 0) {
            if (world.getBlockMetadata(x, y + 1, z) == 0) {
                teUp = world.getTileEntity(x, y + 1, z);
            }
            if (world.getBlockMetadata(x, y - 1, z) == 0) {
                teDown = world.getTileEntity(x, y - 1, z);
            }
        } else if (blockMetadata == 1) {
            if (world.getBlockMetadata(x, y, z + 1) == 1) {
                teUp = world.getTileEntity(x, y, z + 1);
            }
            if (world.getBlockMetadata(x, y, z - 1) == 1) {
                teDown = world.getTileEntity(x, y, z - 1);
            }
        } else if (blockMetadata == 2) {
            if (world.getBlockMetadata(x + 1, y, z) == 2) {
                teUp = world.getTileEntity(x + 1, y, z);
            }
            if (world.getBlockMetadata(x - 1, y, z) == 2) {
                teDown = world.getTileEntity(x - 1, y, z);
            }
        }

        if (teUp instanceof TileRotating teUpTR) {
            connected.add(teUpTR);
        }
        if (teDown instanceof TileRotating teDownTR) {
            connected.add(teDownTR);
        }

        return connected;
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
