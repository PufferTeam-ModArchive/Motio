package net.pufferlab.motio;

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

    public static int getDirectionMeta(int side) {
        if (side == 0 || side == 1) {
            return 0;
        } else if (side == 2 || side == 3) {
            return 1;
        } else if (side == 4 || side == 5) {
            return 2;
        }
        return 0;
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
}
