package net.i_no_am.hit.color.config;

import eu.midnightdust.lib.config.MidnightConfig;

import java.awt.*;

public class Config extends MidnightConfig {

    @Entry(name = "Enable Hit Color")
    public static boolean isEnabled = true;

    @Entry(width = 7, min = 7, isColor = true, name = "Color of a hit")
    public static String color = "#ffffff";

    @Entry(name = "Transparency Level", isSlider = true, max = 255, min = 0)
    public static int alpha = 125;

    @Entry(name = "Rainbow Mode")
    public static boolean rainbow = false;

    @Hidden
    @Entry(name = "Disable version check if it causes crashes")
    public static boolean shouldCheck = true;

    private static int step = 0;

    public static Color getColor() {
        int finalAlpha = 255 - alpha;

        if (rainbow) return getRainbowColor(step++, finalAlpha);

        var finalColor = getColor(color);
        return new Color(finalColor.getRed(), finalColor.getGreen(), finalColor.getBlue(), finalAlpha);
    }

    public static Color getColor(String raw) {
        if (raw == null || raw.isEmpty()) return Color.WHITE;

        String cleaned = raw.trim();
        if (cleaned.startsWith("#")) {
            cleaned = "#" + cleaned.substring(1).replace("#", "");
        } else {
            cleaned = cleaned.replace("#", "");
        }

        try {
            return Color.decode(cleaned);
        } catch (NumberFormatException e) {
            return Color.WHITE;
        }
    }

    public static Color getRainbowColor(int step, int alpha) {
        double freq = 0.1;
        int red = (int) (Math.sin(freq * step + 0) * 127 + 128);
        int green = (int) (Math.sin(freq * step + 2 * Math.PI / 3) * 127 + 128);
        int blue = (int) (Math.sin(freq * step + 4 * Math.PI / 3) * 127 + 128);

        return new Color(red, green, blue, alpha);
    }
}
