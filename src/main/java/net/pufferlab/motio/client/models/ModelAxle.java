package net.pufferlab.motio.client.models;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelAxle extends ModelMotion {

    public ModelAxle() {
        super(64, 64);
        bb_main.cubeList.add(new ModelBox(bb_main, 0, 22, -2.0F, 0.0F - 8F, -2.0F, 4, 16, 4, 0.0F));

        addGearModel(true);
        addGearModel(false);
    }

    public ModelRenderer gearUp = new ModelRenderer(this);
    public ModelRenderer gearDown = new ModelRenderer(this);

    public void addGearModel(boolean isPos) {
        ModelRenderer gear;
        if (isPos) {
            gear = gearUp;
        } else {
            gear = gearDown;
        }
        gear.setRotationPoint(0.0F, 0.0F, 0.0F);
        gear.showModel = false;
        gear.cubeList.add(new ModelBox(gear, 26, 17, -5.0F, 20.0F - 13F, -7.0F, 10, 2, 3, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 26, 17, -5.0F, 20.0F - 13F, 4.0F, 10, 2, 3, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 10, 4.0F, 20.0F - 13F, -5.0F, 3, 2, 10, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 10, -7.0F, 20.0F - 13F, -5.0F, 3, 2, 10, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, -4.0F, 20.0F - 13F, -4.0F, 8, 2, 8, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, -6.0F, 22.0F - 13F, -4.0F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, 5.0F, 22.0F - 13F, -4.0F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, 3.0F, 22.0F - 13F, -6.0F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, 3.0F, 22.0F - 13F, 5.0F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, -4.0F, 22.0F - 13F, -6.0F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, -4.0F, 22.0F - 13F, 5.0F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, -0.5F, 22.0F - 13F, -7.0F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, -0.5F, 22.0F - 13F, 6.0F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, -6.0F, 22.0F - 13F, 3.0F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, 5.0F, 22.0F - 13F, 3.0F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, -7.0F, 22.0F - 13F, -0.5F, 1, 2, 1, 0.0F));
        gear.cubeList.add(new ModelBox(gear, 0, 0, 6.0F, 22.0F - 13F, -0.5F, 1, 2, 1, 0.0F));
        if (!isPos) {
            gear.rotateAngleX = (float) Math.toRadians(180);
        }
        bb_main.addChild(gear);
    }

    public ModelRenderer offsetGearModel(boolean isPos, boolean isOffset) {
        ModelRenderer gear;
        if (isPos) {
            gear = gearUp;
        } else {
            gear = gearDown;
        }
        if (isOffset) {
            gear.rotateAngleY = (float) Math.toRadians(45);
        }
        return gear;
    }

    @Override
    public String getName() {
        return "axle";
    }
}
