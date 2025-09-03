package net.pufferlab.motio;

import java.util.ArrayList;

import net.pufferlab.motio.tileentity.TileEntityMotion;

public class Utils {

    public static char getDirection(int side) {
        if (side == 0 || side == 1) {
            return 'y';
        } else if (side == 2 || side == 3) {
            return 'z';
        } else if (side == 4 || side == 5) {
            return 'x';
        }
        return 'y';
    }

    public static int getAxis(int side) {
        if (side == 0 || side == 1) {
            return 0;
        } else if (side == 2 || side == 3) {
            return 1;
        } else if (side == 4 || side == 5) {
            return 2;
        }
        return 0;
    }

    public static int getDirectionVerticalMeta(int side) {
        if (side == 2 || side == 3) {
            return 4;
        } else if (side == 4 || side == 5) {
            return 5;
        }
        return 4;
    }

    public static int getBlockX(int side, int x) {
        if (side == 4) {
            x--;
        }
        if (side == 5) {
            x++;
        }
        return x;
    }

    public static int getBlockY(int side, int y) {
        if (side == 0) {
            y--;
        }
        if (side == 1) {
            y++;
        }
        return y;
    }

    public static int getBlockZ(int side, int z) {
        if (side == 2) {
            z--;
        }
        if (side == 3) {
            z++;
        }
        return z;
    }

    public static boolean containsExactMatch(String[] array, String targetString) {
        for (String element : array) {
            if (element.equals(targetString)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsExactMatch(ArrayList<TileEntityMotion> tiles, TileEntityMotion tile) {
        for (TileEntityMotion te : tiles) {
            if (equals(te, tile)) {
                return true;
            }
        }
        return false;
    }

    public static boolean equals(TileEntityMotion tile, TileEntityMotion tile2) {
        if ((tile.xCoord == tile2.xCoord) && (tile.yCoord == tile2.yCoord) && (tile.zCoord == tile2.zCoord)) {
            return true;
        }
        return false;
    }
}
