package net.petersil98.utilcraft.utils;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;

import javax.annotation.Nonnull;

public class TextUtils {

    public static void drawCenterText(@Nonnull MatrixStack matrix, @Nonnull FontRenderer font, String text, int width, int scale, int color) {
        matrix.push();
        matrix.translate(width / 2f - scale * font.getStringWidth(text) / 2f, 15,0);
        matrix.scale(scale, scale,0);
        AbstractGui.drawString(matrix, font, text, 0, 0,color);
        matrix.pop();
    }
}
