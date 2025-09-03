package net.pufferlab.motio.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.motio.network.NetworkMotionPropagate;

public class TileEntityMotion extends TileEntity {

    public int facingMeta;
    public float speed;
    public boolean isRotating = true;
    public boolean initialize = true;
    public boolean update = true;
    public final ForgeDirection[][] connections = new ForgeDirection[][] { { ForgeDirection.UP, ForgeDirection.DOWN },
        { ForgeDirection.SOUTH, ForgeDirection.NORTH }, { ForgeDirection.EAST, ForgeDirection.WEST } };

    public final float[] connectionsModifier = new float[] { 1.0F, 1.0F, 1.0F };

    public final float generatorSpeed = 0.0F;

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);

        this.facingMeta = tag.getInteger("facingMeta");
        this.initialize = tag.getBoolean("initialize");
        this.update = tag.getBoolean("update");
        if (isRotating) {
            this.speed = tag.getFloat("speed");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setInteger("facingMeta", this.facingMeta);
        tag.setBoolean("initialize", this.initialize);
        tag.setBoolean("update", this.update);
        if (isRotating) {
            tag.setFloat("speed", this.speed);
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
        NBTTagCompound dataTag = new NBTTagCompound();

        dataTag.setInteger("facingMeta", this.facingMeta);
        dataTag.setBoolean("update", this.update);
        if (isRotating) {
            dataTag.setFloat("speed", this.speed);
        }

        return (Packet) new S35PacketUpdateTileEntity(
            this.xCoord,
            this.yCoord,
            this.zCoord,
            this.blockMetadata,
            dataTag);
    }

    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        NBTTagCompound nbtData = packet.func_148857_g();
        this.facingMeta = nbtData.getInteger("facingMeta");
        this.update = nbtData.getBoolean("update");
        if (isRotating) {
            this.speed = nbtData.getFloat("speed");
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!initialize) {
            this.initialize();
            initialize = false;
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

    public void updateNetwork() {
        NetworkMotionPropagate.propagate(this);
    }
}
