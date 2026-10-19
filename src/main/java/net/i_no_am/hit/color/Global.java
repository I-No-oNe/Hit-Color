package net.i_no_am.hit.color;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.i_no_am.hit.color.config.ModConfig;
import net.minecraft.client.Minecraft;

public interface Global {
    String modId = "hit-color";
    ModConfig config = AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new).get();
    Minecraft mc = Minecraft.getInstance();
}
