package net.pufferlab.motio.network;

import net.minecraftforge.common.util.ForgeDirection;

public class DirectionMotion {

    public static final ForgeDirection[][] axisDirections = new ForgeDirection[][] {
        { ForgeDirection.UP, ForgeDirection.DOWN }, { ForgeDirection.SOUTH, ForgeDirection.NORTH },
        { ForgeDirection.EAST, ForgeDirection.WEST } };
}
