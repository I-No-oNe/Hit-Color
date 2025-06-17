package net.i_no_am.hit.color;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.i_no_am.hit.color.accessor.OverlayTextureAccessor;
import net.i_no_am.hit.color.config.Config;
import net.i_no_am.hit.color.utls.Utils;
import net.i_no_am.hit.color.version.Version;

public class HitColor implements ModInitializer {

   final static String modId = "hit-color";

    @Override
    public void onInitialize() {

        MidnightConfig.init(modId, Config.class);

        WorldRenderEvents.AFTER_SETUP.register((context) -> Version.builder()
                .name("Hit Color")
                .modId(modId)
                .gitUsername("I-No-oNe")
                .downloadSource("https://modrinth.com/mod/hit-color")
                .printVersions(true)
                .condition(() -> Config.shouldCheck)
                .build()
                .notifyUpdates());

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.gameRenderer == null) return;
            var overlay = ((OverlayTextureAccessor) client.gameRenderer.getOverlayTexture()).hitColor$getTexture();
            if (Config.isEnabled) Utils.applyOverlayColor(overlay);
            else Utils.resetOverlayColor(overlay);
        });
    }
}