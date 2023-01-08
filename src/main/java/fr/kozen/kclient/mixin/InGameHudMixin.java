package fr.kozen.kclient.mixin;

import fr.kozen.kclient.KClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {

    @Inject(at = {@At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V", ordinal = 4) }, method = {"render(Lnet/minecraft/client/util/math/MatrixStack;F)V" })
    private void onRender(MatrixStack matrices, float partialTicks, CallbackInfo ci) {
        if (MinecraftClient.getInstance().options.debugEnabled)
            return;
        KClient.getInstance().getHudManager().draw(matrices, partialTicks);
    }

}
