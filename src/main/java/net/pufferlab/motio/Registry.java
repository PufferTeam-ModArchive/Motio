package net.pufferlab.motio;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.pufferlab.motio.block.BlockAxle;
import net.pufferlab.motio.block.BlockMetaContainer;
import net.pufferlab.motio.block.BlockMotion;
import net.pufferlab.motio.block.BlockMotor;
import net.pufferlab.motio.itemblock.ItemBlockMeta;
import net.pufferlab.motio.itemblock.ItemBlockMotion;
import net.pufferlab.motio.tileentity.TileEntityAxle;
import net.pufferlab.motio.tileentity.TileEntityMotor;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class Registry {

    public static Block axle;
    public static Block motor;

    public void preInit(FMLPreInitializationEvent event) {
        axle = new BlockAxle(Constants.baseWoodTypes);
        motor = new BlockMotor(Constants.baseWoodTypes);

        register(TileEntityAxle.class, "axle");
        register(TileEntityMotor.class, "motor");

        register(axle, "axle");
        register(motor, "motor");

    }

    public void register(Class<? extends TileEntity> tileEntityClass, String id) {
        GameRegistry.registerTileEntity(tileEntityClass, Motio.MODID + "_" + id);
    }

    public void register(Block block, String name) {

        if (block instanceof BlockMotion) {
            GameRegistry.registerBlock(block, ItemBlockMotion.class, name);
        } else if (block instanceof BlockMetaContainer) {
            GameRegistry.registerBlock(block, ItemBlockMeta.class, name);
        } else {
            GameRegistry.registerBlock(block, name);
        }
    }

}
