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
    public static Block blockRotatingGearbox;
    public static Block blockRotatingGearboxVertical;

    public static void preInit() {
        blockRotating = new BlockRotating(Material.rock, false, false, -1).setBlockName("rotatingBlock") // Internal
                                                                                                         // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeStone);

        blockRotatingEngine = new BlockRotating(Material.rock, true, false, -1).setBlockName("rotatingBlockEngine") // Internal
            // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeStone);

        blockRotatingGearbox = new BlockRotating(Material.rock, false, true, 0).setBlockName("rotatingBlockGearbox") // Internal
            // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeStone);

        blockRotatingGearboxVertical = new BlockRotating(Material.rock, false, true, 1)
            .setBlockName("rotatingBlockGearboxVertical") // Internal
            // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeStone);

        // Register the block with Minecraft
        GameRegistry.registerBlock(blockRotating, ItemRotating.class, "rotatingBlock");
        GameRegistry.registerBlock(blockRotatingEngine, ItemRotating.class, "rotatingBlockEngine");
        GameRegistry.registerBlock(blockRotatingGearbox, ItemRotating.class, "rotatingBlockGearbox");
        GameRegistry.registerBlock(blockRotatingGearboxVertical, ItemRotating.class, "rotatingBlockGearboxVertical");
        // argument is the unlocalized name

        // Register the Tile Entity
        GameRegistry.registerTileEntity(TileRotating.class, "TileRotating");
    }
}
