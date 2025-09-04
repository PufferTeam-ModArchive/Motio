package net.pufferlab.motio.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.pufferlab.motio.Utils;
import net.pufferlab.motio.tileentity.TileEntityMotion;

public abstract class BlockMotion extends BlockMetaContainer {

    public boolean debugMode = true;

    protected BlockMotion(Material material, String[] materials, String type, String[] blacklist) {
        super(material, materials, type, blacklist);
    }

    public void onMotionBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn,
        int side) {
        TileEntityMotion motionTE = (TileEntityMotion) worldIn.getTileEntity(x, y, z);
        int facing = side;
        if (motionTE != null) {
            if (motionTE.hasAxisPlacement()) {
                facing = Utils.getAxis(side);
            }
            motionTE.setFacingMeta(facing);
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        TileEntityMotion motion = (TileEntityMotion) worldIn.getTileEntity(x, y, z);
        if (debugMode) {
            System.out.println(motion.facingMeta);
        }
        for (ForgeDirection direction : motion.getConnections()) {
            if (direction.equals(ForgeDirection.getOrientation(side))) {
                if (side == 0 || side == 3 || side == 5) {
                    motion.setConnectFlag(motion.connectPos, !motion.connectNeg);
                } else {
                    motion.setConnectFlag(!motion.connectPos, motion.connectNeg);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);

        TileEntityMotion motionTE = (TileEntityMotion) world.getTileEntity(x, y, z);
        if (motionTE != null) {
            motionTE.setUpdate();
        }
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
