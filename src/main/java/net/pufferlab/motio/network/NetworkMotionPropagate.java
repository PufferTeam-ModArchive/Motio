package net.pufferlab.motio.network;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.motio.tileentity.TileEntityMotion;

public class NetworkMotionPropagate {

    public static void propagate(TileEntityMotion tile) {
        NetworkMotion network = new NetworkMotion();
        network = getNetwork(tile, network);
        for (TileEntityMotion te : network.tiles) {
            te.setSpeed(network.maxSpeed);
        }

    }

    public static NetworkMotion getNetwork(TileEntityMotion tile, NetworkMotion network) {
        if (Math.abs(network.maxSpeed) < Math.abs(tile.getBaseSpeed())) {
            network.maxSpeed = tile.getBaseSpeed();
        }
        network.tiles.add(tile);
        for (TileEntityMotion tile0 : getConnectedTiles(tile)) {
            if (!network.tiles.contains(tile0)) {
                getNetwork(tile0, network);
            }
        }
        return network;
    }

    public static ArrayList<TileEntityMotion> getConnectedTiles(TileEntityMotion tile) {
        ArrayList<TileEntityMotion> list = new ArrayList<>();
        int meta = tile.facingMeta;
        ForgeDirection[] validConnections = tile.connections[meta];
        World world = tile.getWorldObj();
        int x = tile.xCoord;
        int y = tile.yCoord;
        int z = tile.zCoord;
        for (ForgeDirection direction : validConnections) {
            TileEntity te2 = world.getTileEntity(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
            if (te2 instanceof TileEntityMotion te3) {
                list.add(te3);
            }
        }

        return list;
    }
}
