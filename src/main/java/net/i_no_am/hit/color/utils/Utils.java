package net.i_no_am.hit.color.utils;

import net.i_no_am.hit.color.config.ModConfig;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.math.ColorHelper;

import java.awt.*;

public class Utils {

    public static void applyOverlayColor(NativeImageBackedTexture originalTexture) {
        NativeImage nativeImage = originalTexture.getImage();
        if (nativeImage == null) return;
        Color color = ModConfig.getColor();

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (i < 8) {
                    nativeImage.setColorArgb(j, i, toArgb(color));
                } else {
                    int k = (int) ((1.0F - (float) j / 15.0F * 0.75F) * 255.0F);
                    nativeImage.setColorArgb(j, i, ColorHelper.whiteWithAlpha(k));
                }
            }
        }
        originalTexture.upload();
    }

    public static void resetOverlayColor(NativeImageBackedTexture originalTexture) {
        NativeImage nativeImage = originalTexture.getImage();
        if (nativeImage == null) return;

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (i < 8) {
                    // Reset to default vanilla hit color
                    nativeImage.setColorArgb(j, i, -1291911168);
                } else {
                    int k = (int) ((1.0F - (float) j / 15.0F * 0.75F) * 255.0F);
                    nativeImage.setColorArgb(j, i, ColorHelper.whiteWithAlpha(k));
                }
            }
        }
        originalTexture.upload();
    }

    private static int toArgb(Color color) {
        return (color.getAlpha() << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
    }
}