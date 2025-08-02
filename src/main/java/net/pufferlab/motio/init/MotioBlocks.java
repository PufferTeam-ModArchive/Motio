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
    public static Block blockRotatingWaterwheel;
    public static Block blockRotatingGearbox;
    public static Block blockRotatingGearboxVertical;
    public static Block blockRotatingGearshift;
    public static Block blockRotatingWaterwheelExtension;

    public static void preInit() {
        blockRotating = new BlockRotating(Material.wood, false, false, false, -1, -1).setBlockName("rotatingBlock") // Internal
            // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeWood);

        blockRotatingEngine = new BlockRotating(Material.rock, true, false, false, -1, 0)
            .setBlockName("rotatingBlockEngine") // Internal
            // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeMetal);

        blockRotatingWaterwheel = new BlockRotating(Material.wood, true, false, false, -1, 1)
            .setBlockName("rotatingBlockWaterwheel") // Internal
            // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeWood);

        blockRotatingGearbox = new BlockRotating(Material.wood, false, true, false, 0, -1)
            .setBlockName("rotatingBlockGearbox") // Internal
            // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeWood);

        blockRotatingGearboxVertical = new BlockRotating(Material.wood, false, true, false, 1, -1)
            .setBlockName("rotatingBlockGearboxVertical") // Internal
            // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeWood);

        blockRotatingGearshift = new BlockRotating(Material.wood, false, true, false, 2, -1)
            .setBlockName("rotatingBlockGearshift") // Internal
            // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeWood);

        blockRotatingWaterwheelExtension = new BlockRotating(Material.wood, false, true, false, 2, -1)
            .setBlockName("rotatingBlockGearshift") // Internal
            // name
            .setCreativeTab(CreativeTabs.tabBlock) // Assign to creative tab
            .setHardness(2.0F)
            .setStepSound(Block.soundTypeWood);

        // Register the block with Minecraft
        GameRegistry.registerBlock(blockRotating, ItemRotating.class, "rotatingBlock");
        GameRegistry.registerBlock(blockRotatingEngine, ItemRotating.class, "rotatingBlockEngine");
        GameRegistry.registerBlock(blockRotatingWaterwheel, ItemRotating.class, "rotatingBlockWaterwheel");
        GameRegistry.registerBlock(blockRotatingGearbox, ItemRotating.class, "rotatingBlockGearbox");
        GameRegistry.registerBlock(blockRotatingGearboxVertical, ItemRotating.class, "rotatingBlockGearboxVertical");
        GameRegistry.registerBlock(blockRotatingGearshift, ItemRotating.class, "rotatingBlockGearshift");
        GameRegistry
            .registerBlock(blockRotatingWaterwheelExtension, ItemRotating.class, "rotatingBlockWaterwheelExtension");
        // argument is the unlocalized name

        // Register the Tile Entity
        GameRegistry.registerTileEntity(TileRotating.class, "TileRotating");
    }
}
