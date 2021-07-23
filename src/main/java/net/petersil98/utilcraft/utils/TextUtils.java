package net.petersil98.utilcraft.utils;

import com.mojang.blaze3d.vertex.BufferVertexConsumer;
import net.minecraft.client.color.item.package-info;
import net.minecraft.client.color.item.ItemColor;

import javax.annotation.Nonnull;

public class TextUtils {

    public static void drawCenterText(@Nonnull BufferVertexConsumer matrix, @Nonnull ItemColor font, String text, int width, int scale, int color) {
        matrix.pushPose();
        matrix.translate(width / 2f - scale * font.width(text) / 2f, 15,0);
        matrix.scale(scale, scale,0);
        package-info.drawString(matrix, font, text, 0, 0,color);
        matrix.popPose();
    }
}
