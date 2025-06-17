package net.i_no_am.hit.color.utls;

import com.mojang.blaze3d.systems.RenderSystem;
import net.i_no_am.hit.color.Global;
import net.i_no_am.hit.color.config.Config;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.math.ColorHelper;

import java.awt.*;

public class Utils implements Global {

    public static void applyOverlayColor(NativeImageBackedTexture originalTexture) {
        NativeImage nativeImage = originalTexture.getImage();
        if (nativeImage == null) return;
        Color color = Config.getColor();

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i < 8) {
                    nativeImage.setColorArgb(j, i, toArgb(color));
                } else {
                    int alpha = (int) ((1.0F - (float) j / 15.0F * 0.75F) * 255.0F);
                    nativeImage.setColorArgb(j, i, ColorHelper.withAlpha(alpha, -1));
                }
                RenderSystem.setupOverlayColor(originalTexture.getGlTexture());
            }
        }

        uploadTexture(originalTexture);
    }

    public static void resetOverlayColor(NativeImageBackedTexture originalTexture) {
        NativeImage nativeImage = originalTexture.getImage();
        if (nativeImage == null) return;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (i < 8) {
                    // Reset to default vanilla hit color
                    nativeImage.setColorArgb(j, i, -1291911168);
                } else {
                    int alpha = (int) ((1.0F - (float) j / 15.0F * 0.75F) * 255.0F);
                    nativeImage.setColorArgb(j, i, ColorHelper.withAlpha(alpha, -1));
                }
            }
        }
        uploadTexture(originalTexture);
        RenderSystem.setupOverlayColor(originalTexture.getGlTexture());
    }

    private static void uploadTexture(NativeImageBackedTexture texture) {
        texture.setFilter(false, false);
        texture.setClamp(true);
        texture.upload();
    }


    private static int toArgb(Color color) {
        return (color.getAlpha() << 24) | (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
    }
}