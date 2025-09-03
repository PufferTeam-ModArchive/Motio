package net.pufferlab.motio.client.helper;

import net.minecraft.client.Minecraft;

public class TickHolder {

    private static int ticks;
    private static int paused_ticks;

    public static void reset() {
        ticks = 0;
        paused_ticks = 0;
    }

    public static void tick() {
        if (!Minecraft.getMinecraft()
            .isGamePaused()) {
            ticks = (ticks + 1) % 1_728_000; // wrap around every 24 hours so we maintain enough floating point
            // precision
        } else {
            paused_ticks = (paused_ticks + 1) % 1_728_000;
        }
    }

    public static int getTicks() {
        return getTicks(false);
    }

    public static int getTicks(boolean includePaused) {
        return includePaused ? ticks + paused_ticks : ticks;
    }

    public static float getRenderTime() {
        return getTicks() + getPartialTicks();
    }

    public static float getPartialTicks() {
        Minecraft mc = Minecraft.getMinecraft();
        return (mc.isGamePaused() ? mc.timer.renderPartialTicks : mc.timer.elapsedPartialTicks);
    }
}
