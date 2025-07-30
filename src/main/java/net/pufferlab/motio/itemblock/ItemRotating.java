package net.pufferlab.motio.itemblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.pufferlab.motio.Utils;
import net.pufferlab.motio.block.BlockRotating;
import net.pufferlab.motio.tileentity.TileRotating;

public class ItemRotating extends ItemBlock {

    BlockRotating block2;

    public ItemRotating(Block block) {
        super(block);

        block2 = (BlockRotating) block;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        int x2 = Utils.getBlockX(side, x);
        int y2 = Utils.getBlockY(side, y);
        int z2 = Utils.getBlockZ(side, z);

        boolean canBePlaced = false;
        if (block2.getGearboxType() == 0) {
            if (place(stack, world, x2, y2, z2, this.field_150939_a, 3)) {
                place(stack, world, x2, y2, z2, this.field_150939_a, 3);
                canBePlaced = true;
            }
        } else if (block2.getGearboxType() == 1) {
            if (place(stack, world, x2, y2, z2, this.field_150939_a, Utils.getDirectionVerticalMeta(side))) {
                place(stack, world, x2, y2, z2, this.field_150939_a, Utils.getDirectionVerticalMeta(side));
                canBePlaced = true;
            }
        } else if (place(stack, world, x2, y2, z2, this.field_150939_a, Utils.getDirectionMeta(side))) {
            place(stack, world, x2, y2, z2, this.field_150939_a, Utils.getDirectionMeta(side));
            canBePlaced = true;
        }

        if (world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            TileEntity tileEntityToBePlaced = world.getTileEntity(x2, y2, z2);
            TileRotating teplaced = (TileRotating) tileEntityToBePlaced;
            if (tileEntity instanceof TileRotating te) {
                teplaced.setRotation(te.getRotation());
            }
        }

        return canBePlaced;
    }

    private boolean place(ItemStack stack, World world, int x, int y, int z, Block toPlace, int metadata) {
        if (world.checkNoEntityCollision(toPlace.getCollisionBoundingBoxFromPool(world, x, y, z))
            && world.setBlock(x, y, z, toPlace, metadata, 3)) {
            world.setBlock(x, y, z, toPlace, metadata, 2);
            stack.stackSize -= 1;
            world.playSoundEffect(
                x + 0.5f,
                y + 0.5f,
                z + 0.5f,
                this.field_150939_a.stepSound.func_150496_b(),
                (this.field_150939_a.stepSound.getVolume() + 1.0F) / 2.0F,
                this.field_150939_a.stepSound.getPitch() * 0.8F);
            return true;
        } else {
            return false;
        }
    }
}
