package net.pufferlab.motio.tileentity;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
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
    boolean gearbox;
    boolean special;
    int gearboxT;
    int engineT;

    public TileRotating(Block block) {
        BlockRotating block2 = (BlockRotating) block;
        this.rotation = 0.0F;
        this.needUpdate = block2.getNeedUpdate();
        this.baseSpeed = block2.getBaseSpeed();
        this.speed = baseSpeed;
        this.engine = block2.isEngine();
        this.engineT = block2.getEngineType();
        this.gearbox = block2.isGearbox();
        this.gearboxT = block2.getGearboxType();
        this.special = block2.isSpecial();
    }

    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    public boolean isEngine() {
        return this.engine;
    }

    public boolean isGearbox() {
        return this.gearbox;
    }

    public boolean isSpecial() {
        return this.special;
    }

    public int getEngineType() {
        return this.engineT;
    }

    public int getGearboxType() {
        return this.gearboxT;
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
        if (this.getEngineType() == 1) {
            updateWaterwheelSpeed();
        }
        if (!isSpecial()) {
            updateNetwork();
            if (worldObj.isRemote) {
                this.rotation = (this.rotation + this.getSpeed()) % 360.0F;
            }
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

            if (Math.abs(hSpeed) < Math.abs(currentTR.baseSpeed)) {
                hSpeed = currentTR.baseSpeed;
            }

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

    public void updateWaterwheelSpeed() {
        float bs = 0.0F;
        int x = this.xCoord;
        int y = this.yCoord;
        int z = this.zCoord;

        int x1 = x;
        int z1 = z;
        int x2 = x;
        int z2 = z;
        if (blockMetadata == 1) {
            x1 = x1 - 2;
            x2 = x2 + 2;
        }
        if (blockMetadata == 2) {
            z1 = z1 + 2;
            z2 = z2 - 2;
        }
        if (worldObj.getBlock(x1, y, z1) == Blocks.flowing_water || worldObj.getBlock(x1, y, z1) == Blocks.water) {
            bs = 2.0F;
        }
        if (worldObj.getBlock(x2, y, z2) == Blocks.flowing_water || worldObj.getBlock(x2, y, z2) == Blocks.water) {
            bs = -2.0F;
        }

        float m = 1.0F;
        if (blockMetadata == 2) {
            m = -1.0F;
        }
        this.baseSpeed = bs * m;
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
        TileEntity teLeft = null;
        TileEntity teRight = null;
        if (blockMetadata == 0) {
            if (world.getBlockMetadata(x, y + 1, z) == 0 || world.getBlockMetadata(x, y + 1, z) == 4
                || world.getBlockMetadata(x, y + 1, z) == 5) {
                teUp = world.getTileEntity(x, y + 1, z);
            }
            if (world.getBlockMetadata(x, y - 1, z) == 0 || world.getBlockMetadata(x, y - 1, z) == 4
                || world.getBlockMetadata(x, y - 1, z) == 5) {
                teDown = world.getTileEntity(x, y - 1, z);
            }
        } else if (blockMetadata == 1) {
            if (world.getBlockMetadata(x, y, z + 1) == 1 || world.getBlockMetadata(x, y, z + 1) == 3
                || world.getBlockMetadata(x, y, z + 1) == 4) {
                teUp = world.getTileEntity(x, y, z + 1);
            }
            if (world.getBlockMetadata(x, y, z - 1) == 1 || world.getBlockMetadata(x, y, z - 1) == 3
                || world.getBlockMetadata(x, y, z - 1) == 4) {
                teDown = world.getTileEntity(x, y, z - 1);
            }
        } else if (blockMetadata == 2) {
            if (world.getBlockMetadata(x + 1, y, z) == 2 || world.getBlockMetadata(x + 1, y, z) == 3
                || world.getBlockMetadata(x + 1, y, z) == 5) {
                teUp = world.getTileEntity(x + 1, y, z);
            }
            if (world.getBlockMetadata(x - 1, y, z) == 2 || world.getBlockMetadata(x - 1, y, z) == 3
                || world.getBlockMetadata(x - 1, y, z) == 5) {
                teDown = world.getTileEntity(x - 1, y, z);
            }
        } else if (blockMetadata == 3) {
            if (world.getBlockMetadata(x, y, z + 1) == 1 || world.getBlockMetadata(x, y, z + 1) == 3
                || world.getBlockMetadata(x, y, z + 1) == 4) {
                teLeft = world.getTileEntity(x, y, z + 1);
            }
            if (world.getBlockMetadata(x, y, z - 1) == 1 || world.getBlockMetadata(x, y, z - 1) == 3
                || world.getBlockMetadata(x, y, z - 1) == 4) {
                teRight = world.getTileEntity(x, y, z - 1);
            }
            if (world.getBlockMetadata(x + 1, y, z) == 2 || world.getBlockMetadata(x + 1, y, z) == 3
                || world.getBlockMetadata(x + 1, y, z) == 5) {
                teUp = world.getTileEntity(x + 1, y, z);
            }
            if (world.getBlockMetadata(x - 1, y, z) == 2 || world.getBlockMetadata(x - 1, y, z) == 3
                || world.getBlockMetadata(x - 1, y, z) == 5) {
                teDown = world.getTileEntity(x - 1, y, z);
            }
        } else if (blockMetadata == 4) {
            if (world.getBlockMetadata(x, y + 1, z) == 0 || world.getBlockMetadata(x, y + 1, z) == 4
                || world.getBlockMetadata(x, y + 1, z) == 5) {
                teUp = world.getTileEntity(x, y + 1, z);
            }
            if (world.getBlockMetadata(x, y - 1, z) == 0 || world.getBlockMetadata(x, y - 1, z) == 4
                || world.getBlockMetadata(x, y - 1, z) == 5) {
                teDown = world.getTileEntity(x, y - 1, z);
            }
            if (world.getBlockMetadata(x, y, z + 1) == 1 || world.getBlockMetadata(x, y, z + 1) == 3
                || world.getBlockMetadata(x, y, z + 1) == 4) {
                teLeft = world.getTileEntity(x, y, z + 1);
            }
            if (world.getBlockMetadata(x, y, z - 1) == 1 || world.getBlockMetadata(x, y, z - 1) == 3
                || world.getBlockMetadata(x, y, z - 1) == 4) {
                teRight = world.getTileEntity(x, y, z - 1);
            }
        } else if (blockMetadata == 5) {
            if (world.getBlockMetadata(x, y + 1, z) == 0 || world.getBlockMetadata(x, y + 1, z) == 4
                || world.getBlockMetadata(x, y + 1, z) == 5) {
                teUp = world.getTileEntity(x, y + 1, z);
            }
            if (world.getBlockMetadata(x, y - 1, z) == 0 || world.getBlockMetadata(x, y - 1, z) == 4
                || world.getBlockMetadata(x, y - 1, z) == 5) {
                teDown = world.getTileEntity(x, y - 1, z);
            }
            if (world.getBlockMetadata(x + 1, y, z) == 2 || world.getBlockMetadata(x + 1, y, z) == 3
                || world.getBlockMetadata(x + 1, y, z) == 5) {
                teLeft = world.getTileEntity(x + 1, y, z);
            }
            if (world.getBlockMetadata(x - 1, y, z) == 2 || world.getBlockMetadata(x - 1, y, z) == 3
                || world.getBlockMetadata(x - 1, y, z) == 5) {
                teRight = world.getTileEntity(x - 1, y, z);
            }
        }

        if (teUp instanceof TileRotating teUpTR) {
            connected.add(teUpTR);
        }
        if (teDown instanceof TileRotating teDownTR) {
            connected.add(teDownTR);
        }
        if (teRight instanceof TileRotating teRightTR) {
            connected.add(teRightTR);
        }
        if (teLeft instanceof TileRotating teLeftTR) {
            connected.add(teLeftTR);
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
