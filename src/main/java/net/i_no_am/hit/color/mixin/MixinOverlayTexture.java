package net.i_no_am.hit.color.mixin;

import net.i_no_am.hit.color.accessor.OverlayTextureAccessor;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.texture.NativeImageBackedTexture;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OverlayTexture.class)
public class MixinOverlayTexture implements OverlayTextureAccessor {

	@Shadow @Final private NativeImageBackedTexture texture;

	@Override
	public NativeImageBackedTexture hitColor$getTexture() {
		return this.texture;
	}
}