package net.i_no_am.hit.color.utils;

import net.i_no_am.hit.color.config.ModConfig;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ARGB;

import java.awt.*;

public class Utils {

    public static void applyOverlayColor(DynamicTexture originalTexture) {
        NativeImage nativeImage = originalTexture.getPixels();
        if (nativeImage == null) return;
        Color color = ModConfig.getColor();

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (i < 8) {
                    nativeImage.setPixel(j, i, toArgb(color));
                } else {
                    int k = (int) ((1.0F - (float) j / 15.0F * 0.75F) * 255.0F);
                    nativeImage.setPixel(j, i, ARGB.white(k));
                }
            }
        }
        originalTexture.upload();
    }

    public static void resetOverlayColor(DynamicTexture originalTexture) {
        NativeImage nativeImage = originalTexture.getPixels();
        if (nativeImage == null) return;

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                if (i < 8) {
                    // Reset to default vanilla hit color
                    nativeImage.setPixel(j, i, -1291911168);
                } else {
                    int k = (int) ((1.0F - (float) j / 15.0F * 0.75F) * 255.0F);
                    nativeImage.setPixel(j, i, ARGB.white(k));
                }
            }
        }
        originalTexture.upload();
    }

    private static int toArgb(Color color) {
        return (color.getAlpha() << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
    }
}