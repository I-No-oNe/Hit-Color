package net.i_no_am.hit.color.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gl.RenderPipelines;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(RenderPipelines.class)
public class MixinArmorRenderPipelines {

    // Default UVs remain transparent; only opted-in hurt armor receives an overlay.
    @WrapOperation(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;withShaderDefine(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;"),
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=pipeline/armor_cutout_no_cull"),
                    to = @At(value = "FIELD", target = "Lnet/minecraft/client/gl/RenderPipelines;ARMOR_DECAL_CUTOUT_NO_CULL:Lcom/mojang/blaze3d/pipeline/RenderPipeline;", opcode = Opcodes.PUTSTATIC)
            )
    )
    private static RenderPipeline.Builder hitColor$enableArmorOverlay(RenderPipeline.Builder builder, String define, Operation<RenderPipeline.Builder> original) {
        return define.equals("NO_OVERLAY") ? builder : original.call(builder, define);
    }
}
