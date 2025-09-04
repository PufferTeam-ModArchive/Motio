package net.pufferlab.motio.tileentity;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.motio.network.DirectionMotion;
import net.pufferlab.motio.network.NetworkMotionPropagate;

public class TileEntityMotion extends TileEntity {

    public int facingMeta;
    public float speed;
    public float speedSpread;
    public boolean connectPos = false;
    public boolean connectNeg = false;
    public boolean connectPosOffset = false;
    public boolean connectNegOffset = true;
    public boolean isRotating = true;
    public boolean initialize = true;
    public boolean update = true;

    public final float[] connectionsModifier = new float[] { 1.0F, 1.0F, 1.0F };

    public final float generatorSpeed = 0.0F;

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        this.initialize = tag.getBoolean("initialize");
        this.update = tag.getBoolean("update");
        if (hasFacing()) {
            this.facingMeta = tag.getInteger("facingMeta");
        }
        if (hasGears()) {
            this.connectPos = tag.getBoolean("connectPos");
            this.connectNeg = tag.getBoolean("connectNeg");
            this.connectPosOffset = tag.getBoolean("connectPosOffset");
            this.connectNegOffset = tag.getBoolean("connectNegOffset");
        }
        if (isRotating) {
            this.speed = tag.getFloat("speed");
            this.speedSpread = tag.getFloat("speedSpread");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setBoolean("initialize", this.initialize);
        tag.setBoolean("update", this.update);
        if (hasFacing()) {
            tag.setInteger("facingMeta", this.facingMeta);
        }
        if (hasGears()) {
            tag.setBoolean("connectPos", this.connectPos);
            tag.setBoolean("connectNeg", this.connectNeg);
            tag.setBoolean("connectPosOffset", this.connectPosOffset);
            tag.setBoolean("connectNegOffset", this.connectNegOffset);
        }
        if (isRotating) {
            tag.setFloat("speed", this.speed);
            tag.setFloat("speedSpread", this.speedSpread);
        }
    }

    public void updateTEData() {
        this.worldObj.notifyBlockChange(this.xCoord, this.yCoord, this.zCoord, this.blockType);
        this.markDirty();
    }

    public void setFacingMeta(int meta) {
        this.facingMeta = meta;
        updateTEData();
    }

    public void setConnectFlag(boolean pos, boolean neg) {
        this.connectPos = pos;
        this.connectNeg = neg;
        updateTEData();
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setUpdate() {
        this.update = true;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getBaseSpeed() {
        return 0.0F;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();

        tag.setBoolean("update", this.update);
        if (hasFacing()) {
            tag.setInteger("facingMeta", this.facingMeta);
        }
        if (hasGears()) {
            tag.setBoolean("connectPos", this.connectPos);
            tag.setBoolean("connectNeg", this.connectNeg);
            tag.setBoolean("connectPosOffset", this.connectPosOffset);
            tag.setBoolean("connectNegOffset", this.connectNegOffset);
        }
        if (isRotating) {
            tag.setFloat("speed", this.speed);
            tag.setFloat("speedSpread", this.speedSpread);
        }

        return (Packet) new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tag);
    }

    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound tag = packet.func_148857_g();
        this.update = tag.getBoolean("update");
        if (hasFacing()) {
            this.facingMeta = tag.getInteger("facingMeta");
        }
        if (hasGears()) {
            this.connectPos = tag.getBoolean("connectPos");
            this.connectNeg = tag.getBoolean("connectNeg");
            this.connectPosOffset = tag.getBoolean("connectPosOffset");
            this.connectNegOffset = tag.getBoolean("connectNegOffset");
        }
        if (isRotating) {
            this.speed = tag.getFloat("speed");
            this.speedSpread = tag.getFloat("speedSpread");
        }
    }

    public ArrayList<ForgeDirection> getConnections() {
        ArrayList<ForgeDirection> connections = new ArrayList<>();
        if (hasFacing()) {
            if (hasAxisPlacement()) {
                connections.addAll(Arrays.asList(DirectionMotion.axisDirections[facingMeta]));
            } else {
                connections.add(ForgeDirection.getOrientation(facingMeta));
            }
        }

        return connections;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (this.initialize) {
            this.initialize();
            this.initialize = false;
        }

        if (this.update) {
            this.updateNetwork();
            this.update = false;
        }
    }

    public void initialize() {

    }

    public boolean hasAxisPlacement() {
        return true;
    }

    public boolean hasFacing() {
        return true;
    }

    public boolean hasGears() {
        return true;
    }

    public void updateNetwork() {
        NetworkMotionPropagate.propagate(this);
    }
}
