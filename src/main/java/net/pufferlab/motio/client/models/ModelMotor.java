package net.pufferlab.motio.client.models;

import net.minecraft.client.model.ModelBox;

public class ModelMotor extends ModelMotion {

    public ModelMotor() {
        super(32, 32);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -4.0F, 1.0F - 8F, -4.0F, 8, 14, 8, 0.0F));
    }

    @Override
    public String getName() {
        return "motor";
    }
}
