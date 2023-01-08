package fr.kozen.kclient.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;

public class HudManager {

    public HudManager() {

    }

    public void draw(MatrixStack matrices, float partialTicks) {
        MinecraftClient client = MinecraftClient.getInstance();
        InGameHud inGameHud = client.inGameHud;
        TextRenderer textRenderer = inGameHud.getTextRenderer();

        DrawableHelper.drawStringWithShadow(matrices, textRenderer, "KClient 1.19.3", 4, 4, 0x00A3FC);
        DrawableHelper.fill(matrices, 0, 15, 76, 15 * 6, 0x80_000000);
        DrawableHelper.drawStringWithShadow(matrices, textRenderer, "> Combat", 4, 19, 0x00A3FC);
        DrawableHelper.drawStringWithShadow(matrices, textRenderer, ">>", 63, 19, 0x00A3FC);

        DrawableHelper.drawStringWithShadow(matrices, textRenderer, "Movement", 4, 34, 0xFFFFFF);
        DrawableHelper.drawStringWithShadow(matrices, textRenderer, ">>", 63, 34, 0x00A3FC);

        DrawableHelper.drawStringWithShadow(matrices, textRenderer, "Render", 4, 49, 0xFFFFFF);
        DrawableHelper.drawStringWithShadow(matrices, textRenderer, ">>", 63, 49, 0x00A3FC);

        DrawableHelper.drawStringWithShadow(matrices, textRenderer, "World", 4, 64, 0xFFFFFF);
        DrawableHelper.drawStringWithShadow(matrices, textRenderer, ">>", 63, 64, 0x00A3FC);

        DrawableHelper.drawStringWithShadow(matrices, textRenderer, "Misc", 4, 79, 0xFFFFFF);
        DrawableHelper.drawStringWithShadow(matrices, textRenderer, ">>", 63, 79, 0x00A3FC);
    }

}
