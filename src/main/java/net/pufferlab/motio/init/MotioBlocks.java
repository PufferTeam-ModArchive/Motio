package net.pufferlab.motio.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.pufferlab.motio.block.BlockRotating;
import net.pufferlab.motio.itemblock.ItemRotating;
import net.pufferlab.motio.tileentity.TileRotating;

import cpw.mods.fml.common.registry.GameRegistry;

public class MotioBlocks {

    public static Block blockRotating;
    public static Block blockRotatingEngine;

    public static void preInit() {
        blockRotating = new BlockRotating(Material.rock, false).setBlockName("rotatingBlock") // Internal name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeStone);

        blockRotatingEngine = new BlockRotating(Material.rock, true).setBlockName("rotatingBlockEngine") // Internal
                                                                                                         // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeStone);

        // Register the block with Minecraft
        GameRegistry.registerBlock(blockRotating, ItemRotating.class, "rotatingBlock"); // The second argument is the
                                                                                        // unlocalized name
        GameRegistry.registerBlock(blockRotatingEngine, ItemRotating.class, "rotatingBlockEngine"); // The second
                                                                                                    // argument is the
                                                                                                    // unlocalized name
        // GameRegistry.registerBlock(blockRotatingEngine, ItemRotating.class, "rotatingBlockEngine"); // The second
        // argument is the unlocalized name

        // Register the Tile Entity
        GameRegistry.registerTileEntity(TileRotating.class, "TileRotating");
    }
}
