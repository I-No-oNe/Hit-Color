package net.i_no_am.hit.color.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.i_no_am.hit.color.Global;
import net.i_no_am.hit.color.HitColor;

import java.awt.*;

@Config(name = HitColor.modId)
public class ModConfig implements ConfigData, Global {

    public boolean isEnabled = true;

    @ConfigEntry.ColorPicker
    public int color = 0xFFFFFF;

    @ConfigEntry.BoundedDiscrete(min = 0, max = 255)
    public int alpha = 125;

    public boolean rainbow = false;

    @ConfigEntry.Gui.Excluded
    @Comment("Disable version check if it causes crashes")
    public boolean shouldCheck = true;


    public static Color getColor() {
        int a = 255 - config.alpha;
        return config.rainbow ? rainbowColor(a) : withAlpha(new Color(config.color), a);
    }

    private static Color withAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    private static Color rainbowColor(int alpha) {
        int step = HitColor.getIncrementStep();
        double f = 0.1;
        return new Color((int) (Math.sin(f * step) * 127 + 128), (int) (Math.sin(f * step + 2 * Math.PI / 3) * 127 + 128), (int) (Math.sin(f * step + 4 * Math.PI / 3) * 127 + 128), alpha);
    }
}
