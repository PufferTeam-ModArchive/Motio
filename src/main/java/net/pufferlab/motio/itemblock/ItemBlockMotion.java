package net.pufferlab.motio.itemblock;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pufferlab.motio.Utils;
import net.pufferlab.motio.block.BlockMotion;

public class ItemBlockMotion extends ItemBlockMeta {

    public ItemBlockMotion(Block block) {
        super(block);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
        float hitX, float hitY, float hitZ) {
        int x2 = Utils.getBlockX(side, x);
        int y2 = Utils.getBlockY(side, y);
        int z2 = Utils.getBlockZ(side, z);

        int damage = stack.getItemDamage();
        if (damage < 0) {
            damage = 0;
        }
        if (place(stack, world, x2, y2, z2, this.field_150939_a, damage, player, side)) {
            place(stack, world, x2, y2, z2, this.field_150939_a, damage, player, side);
            return true;
        }
        return false;
    }

    private boolean place(ItemStack stack, World world, int x, int y, int z, Block toPlace, int metadata,
        EntityPlayer player, int side) {
        if (world.checkNoEntityCollision(toPlace.getCollisionBoundingBoxFromPool(world, x, y, z))
            && world.setBlock(x, y, z, toPlace, metadata, 3)) {
            world.setBlock(x, y, z, toPlace, metadata, 2);
            stack.stackSize -= 1;
            field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
            if (field_150939_a instanceof BlockMotion block2) {
                block2.onMotionBlockPlacedBy(world, x, y, z, player, stack, side);
            }
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
