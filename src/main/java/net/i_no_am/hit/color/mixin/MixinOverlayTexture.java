package net.i_no_am.hit.color.mixin;

import net.i_no_am.hit.color.accessor.OverlayTextureAccessor;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OverlayTexture.class)
public class MixinOverlayTexture implements OverlayTextureAccessor {

	@Shadow @Final private DynamicTexture texture;

	@Override
	public DynamicTexture hitColor$getTexture() {
		return this.texture;
	}
}