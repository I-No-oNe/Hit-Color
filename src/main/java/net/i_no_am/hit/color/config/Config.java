package net.i_no_am.hit.color.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.i_no_am.hit.color.Global;

import java.awt.*;

public class Config extends MidnightConfig implements Global {

    @Entry(category = "GENERAL", name = "Enable Hit Color")
    public static boolean isEnabled = true;

    @Entry(category = "GENERAL", width = 7, min = 7, isColor = true, name = "I am a color!")
    public static String color = "#ffffff";


    @Entry(category = "GENERAL", name = "Transparency Level", isSlider = true, max = 255, min = 0)
    public int alpha = 125;

    public static Color getColor() {
        return Color.decode(color);
    }

    @Hidden
    @Entry(category = "GENERAL", name = "Disable version check if it causes crashes")
    public static boolean shouldCheck = true;
}
