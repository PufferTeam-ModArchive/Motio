package net.pufferlab.motio.client.renderer;

import net.minecraft.tileentity.TileEntity;
import net.pufferlab.motio.client.models.ModelAxle;
import net.pufferlab.motio.tileentity.TileEntityMotion;

public class TileEntityAxleRenderer extends TileEntityMotionRenderer {

    ModelAxle model = new ModelAxle();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        if (tileEntity instanceof TileEntityMotion te) {

            rotateModel(model, te);
            model.gearUp.showModel = te.connectPos;
            model.gearDown.showModel = te.connectNeg;
            model.offsetGearModel(te.connectPos, te.connectPosOffset);
            model.offsetGearModel(te.connectNeg, te.connectNegOffset);

            renderModel(model, te, x, y, z);
        }
    }
}
