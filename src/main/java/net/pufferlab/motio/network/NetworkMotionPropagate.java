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

        if (tile.connectPos) {
            if (tile.facingMeta == 0) {

            }
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
        World world = tile.getWorldObj();
        int x = tile.xCoord;
        int y = tile.yCoord;
        int z = tile.zCoord;
        for (ForgeDirection direction : tile.getConnections()) {
            int nX = direction.offsetX;
            int nY = direction.offsetY;
            int nZ = direction.offsetZ;
            TileEntity nTileTE = world.getTileEntity(x + nX, y + nY, z + nZ);
            if (nTileTE instanceof TileEntityMotion nTile) {
                for (ForgeDirection nDirection : nTile.getConnections()) {
                    ForgeDirection connected = nDirection.getOpposite();
                    if (direction.equals(connected)) {
                        list.add(nTile);
                    }
                }
            }
        }

        return list;
    }
}
