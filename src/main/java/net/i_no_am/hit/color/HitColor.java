package net.i_no_am.hit.color;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.i_no_am.hit.color.accessor.OverlayTextureAccessor;
import net.i_no_am.hit.color.config.Config;
import net.i_no_am.hit.color.utls.Utils;

public class HitColor implements ModInitializer, Global {

    @Override
    public void onInitialize() {
        Config.init(modId, Config.class);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            var overlay = ((OverlayTextureAccessor) client.gameRenderer.getOverlayTexture()).hitColor$getTexture();
            if (Config.isEnabled) Utils.applyOverlayColor(overlay);
            else Utils.resetOverlayColor(overlay);
        });
    }
}