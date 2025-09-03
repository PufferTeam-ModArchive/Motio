package net.pufferlab.motio.client.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelAxle extends ModelMotion {

    public ModelAxle() {
        super();

        textureWidth = 32;
        textureHeight = 32;

        bb_main = new ModelRenderer(this);
        bb_main.setRotationPoint(0.0F, 0.0F, 0.0F);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 0, -2.0F, 0.0F - 8F, -2.0F, 4, 16, 4, 0.0F));
    }

    @Override
    public String getName() {
        return "axle";
    }
}
