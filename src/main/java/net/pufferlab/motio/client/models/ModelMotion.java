package net.pufferlab.motio.client.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.pufferlab.motio.Constants;
import net.pufferlab.motio.Motio;

public abstract class ModelMotion extends ModelBase {

    public ModelRenderer bb_main;

    public ModelMotion() {
        bb_main = new ModelRenderer(this);
    }

    public String getName() {
        return "test";
    }

    public void render(String type) {
        bindTex(type + "_" + this.getName());
        bb_main.render(Constants.ModelConstant);
    }

    public ModelMotion setAxis(int meta) {
        if (meta == 1) {
            bb_main.rotateAngleX = (float) (-Math.PI / 2);
            bb_main.rotateAngleY = 0;
            bb_main.rotateAngleZ = 0;
        } else if (meta == 2) {
            bb_main.rotateAngleZ = (float) (Math.PI / 2);
            bb_main.rotateAngleY = 0;
            bb_main.rotateAngleX = 0;
        } else if (meta == 0) {
            bb_main.rotateAngleX = 0;
            bb_main.rotateAngleY = 0;
            bb_main.rotateAngleZ = 0;
        }
        return this;
    }

    public ModelMotion setAxisRotation(float rotation, int meta) {
        if (meta == 1) {
            bb_main.rotateAngleZ = rotation;
        } else if (meta == 2) {
            bb_main.rotateAngleY = -rotation;
        } else if (meta == 0) {
            bb_main.rotateAngleY = -rotation;
        }
        return this;
    }

    public void bindTex(String fileName) {
        Minecraft.getMinecraft().renderEngine.bindTexture(Motio.asResource("textures/blocks/" + fileName + ".png"));
    }

}
