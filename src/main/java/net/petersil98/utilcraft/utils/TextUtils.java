package net.petersil98.utilcraft.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.Font;

import javax.annotation.Nonnull;

public class TextUtils {

    public static void drawCenterText(@Nonnull PoseStack matrix, @Nonnull Font font, String text, int width, int scale, int color) {
        matrix.pushPose();
        matrix.translate(width / 2f - scale * font.width(text) / 2f, 15,0);
        matrix.scale(scale, scale,0);
        GuiComponent.drawString(matrix, font, text, 0, 0,color);
        matrix.popPose();
    }
}
