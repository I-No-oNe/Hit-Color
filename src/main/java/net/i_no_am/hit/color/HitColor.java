package net.i_no_am.hit.color;

import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderEvents;
import net.i_no_am.hit.color.accessor.OverlayTextureAccessor;
import net.i_no_am.hit.color.config.ModConfig;
import net.i_no_am.hit.color.utils.Utils;
import net.i_no_am.hit.color.version.Version;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class HitColor implements ClientModInitializer, Global {

    public static final KeyBinding BIND = KeyBindingHelper.registerKeyBinding(new KeyBinding("hit-color.i_no_am.menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, new KeyBinding.Category(Identifier.of("hit-color.i_no_am.category"))));

    @Override
    public void onInitializeClient() {
        WorldRenderEvents.END_MAIN.register((context) ->
                Version.builder()
                        .name("Hit Color")
                        .modId(modId)
                        .gitUsername("I-No-oNe")
                        .downloadSource("https://modrinth.com/mod/no-ones-hit-color")
                        .printVersions(true)
                        .condition(() -> config.shouldCheck)
                        .build()
                        .notifyUpdates());

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.gameRenderer == null) return;
            var overlay = ((OverlayTextureAccessor) client.gameRenderer.getOverlayTexture()).hitColor$getTexture();

            if (config.isEnabled)
                Utils.applyOverlayColor(overlay);

            else Utils.resetOverlayColor(overlay);

            while (BIND.isPressed())
                mc.setScreen(AutoConfig.getConfigScreen(ModConfig.class, mc.currentScreen).get());
        });
    }

    private static int step = 0;

    public static int getIncrementStep() {
        return step++;
    }
}