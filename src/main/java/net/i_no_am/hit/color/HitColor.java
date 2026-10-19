package net.i_no_am.hit.color;

import me.shedaniel.autoconfig.AutoConfigClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents;
import net.i_no_am.hit.color.accessor.OverlayTextureAccessor;
import net.i_no_am.hit.color.config.ModConfig;
import net.i_no_am.hit.color.utils.Utils;
import net.i_no_am.hit.color.version.Version;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.resources.Identifier;

public class HitColor implements ClientModInitializer, Global {

    public static final KeyMapping BIND = KeyMappingHelper.registerKeyMapping(new KeyMapping("hit-color.i_no_am.menu", InputConstants.KEY_H, new KeyMapping.Category(Identifier.parse("hit-color.i_no_am.category"))));

    @Override
    public void onInitializeClient() {
        LevelRenderEvents.END_MAIN.register((context) ->
                Version.builder()
                        .name("Hit Color")
                        .modId(modId)
                        .gitUsername("I-No-oNe")
                        .downloadSource("https://modrinth.com/mod/no-ones-hit-color")
                        .printVersions(false)
                        .condition(() -> config.shouldCheck)
                        .build()
                        .notifyUpdates());

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.gameRenderer == null) return;
            var overlay = ((OverlayTextureAccessor) client.gameRenderer.overlayTexture()).hitColor$getTexture();

            if (config.isEnabled)
                Utils.applyOverlayColor(overlay);

            else Utils.resetOverlayColor(overlay);

            while (BIND.consumeClick())
                mc.gui.setScreen(AutoConfigClient.getConfigScreen(ModConfig.class, mc.gui.screen()).get());
        });
    }

    private static int step = 0;

    public static int getIncrementStep() {
        return step++;
    }
}
