package fr.kozen.kclient.gui;

import fr.kozen.kclient.KClient;
import fr.kozen.kclient.hacks.Category;
import fr.kozen.kclient.utils.RenderUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.lwjgl.glfw.GLFW;

public class HudManager {

    private Integer selectedCategoryIndex = 0;
    private Integer selectedHackIndex = 0;
    private Boolean showHackList = false;
    private Boolean showHackInfo = false;
    private KeyBinding keyBindingUp;
    private KeyBinding keyBindingDown;
    private KeyBinding keyBindingLeft;
    private KeyBinding keyBindingRight;

    public HudManager() { this.initialise(); }

    public void initialise() {
        this.keyBindingUp = new KeyBinding("key.tabup", GLFW.GLFW_KEY_UP, "key.categories.kclient");
        this.keyBindingDown = new KeyBinding("key.tabdown", GLFW.GLFW_KEY_DOWN, "key.categories.kclient");
        this.keyBindingLeft = new KeyBinding("key.tableft", GLFW.GLFW_KEY_LEFT, "key.categories.kclient");
        this.keyBindingRight = new KeyBinding("key.tabright", GLFW.GLFW_KEY_RIGHT, "key.categories.kclient");
    }

    public void tick() {
        MinecraftClient client = MinecraftClient.getInstance();
        if(this.keyBindingUp.wasPressed()) {
            client.player.getWorld().playSound(client.player, client.player.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.BLOCKS, 1f, 1f);
            if(!this.showHackList) {
                if (this.selectedCategoryIndex == 0) {
                    this.selectedCategoryIndex = Category.values().length - 1;
                } else {
                    this.selectedCategoryIndex -= 1;
                }
            } else if(!this.showHackInfo) {
                if (this.selectedHackIndex == 0) {
                    this.selectedHackIndex = KClient.getInstance().getHackManager().getHacksByCategory(Category.values()[this.selectedCategoryIndex]).size() - 1;
                } else {
                    this.selectedHackIndex -= 1;
                }
            }
        } else if(this.keyBindingDown.wasPressed()) {
            client.player.getWorld().playSound(client.player, client.player.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.BLOCKS, 1f, 1f);
            if(!this.showHackList) {
                if (this.selectedCategoryIndex == Category.values().length - 1) {
                    this.selectedCategoryIndex = 0;
                } else {
                    this.selectedCategoryIndex += 1;
                }
            } else if(!this.showHackInfo) {
                if (this.selectedHackIndex == KClient.getInstance().getHackManager().getHacksByCategory(Category.values()[this.selectedCategoryIndex]).size() - 1) {
                    this.selectedHackIndex = 0;
                } else {
                    this.selectedHackIndex += 1;
                }
            }
        } else if(this.keyBindingRight.wasPressed()) {
            client.player.getWorld().playSound(client.player, client.player.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.BLOCKS, 1f, 1f);
            if(!this.showHackList) {
                this.showHackList = true;
            } else if(!this.showHackInfo) {
                this.showHackInfo = true;
            }
        } else if(this.keyBindingLeft.wasPressed()) {
            client.player.getWorld().playSound(client.player, client.player.getBlockPos(), SoundEvents.UI_BUTTON_CLICK.value(), SoundCategory.BLOCKS, 1f, 1f);
            if(this.showHackInfo) {
                this.showHackInfo = false;
            } else if(this.showHackList) {
                this.showHackList = false;
            }
        }
    }

    public void draw(MatrixStack matrices, float partialTicks) {
        MinecraftClient client = MinecraftClient.getInstance();
        InGameHud inGameHud = client.inGameHud;
        TextRenderer textRenderer = inGameHud.getTextRenderer();
        // HUD Title
        //DrawableHelper.drawStringWithShadow(matrices, textRenderer, "KClient 1.19.3", 4, 4, 0x00A3FC);

        // We draw the background box
        RenderUtils.fill(matrices, 0, 15, 76, 15 * Category.values().length, 0x80_000000);

        // And then we add all the categories
        for(int i = 0; i < Category.values().length; i++) {
            if(this.selectedCategoryIndex == i) {
                RenderUtils.fill(matrices, 4, 15 + i * 15, 72, 15, 0x80_00A3FC);
                DrawableHelper.drawStringWithShadow(matrices, textRenderer, ">", 68, 19 + i * 15, 0xFFFFFF);
            }
            DrawableHelper.drawStringWithShadow(matrices, textRenderer, Category.values()[i].name().toLowerCase().substring(0, 1).toUpperCase() + Category.values()[i].name().toLowerCase().substring(1), 8, 19 + i * 15, 0xFFFFFF);
        }
        RenderUtils.fill(matrices, 0, 15, 4, 15 * Category.values().length, 0x80_00A3FC);

        // The hack list
        if(this.showHackList) {
            RenderUtils.fill(matrices, 84, 15, 86, 15 + 15 * KClient.getInstance().getHackManager().getHacksByCategory(Category.values()[this.selectedCategoryIndex]).size(), 0x80_000000);
            RenderUtils.fill(matrices, 84, 15, 86, 15, 0x80_00A3FC);
            DrawableHelper.drawStringWithShadow(matrices, textRenderer, Category.values()[this.selectedCategoryIndex].name().toLowerCase().substring(0, 1).toUpperCase() + Category.values()[this.selectedCategoryIndex].name().toLowerCase().substring(1), 88, 19, 0xFFFFFF);

            for(int i = 0; i < KClient.getInstance().getHackManager().getHacksByCategory(Category.values()[this.selectedCategoryIndex]).size(); i++) {
                if(this.selectedHackIndex == i) {
                    RenderUtils.fill(matrices, 84, 30 + i * 15, 86, 15, 0x80_00A3FC);
                    DrawableHelper.drawStringWithShadow(matrices, textRenderer, ">", 78 + 84, 15 + 19 + i * 15, 0xFFFFFF);
                }
                int xOffset = this.selectedHackIndex == i ? -10 : 0;
                if(KClient.getInstance().getHackManager().getHacksByCategory(Category.values()[this.selectedCategoryIndex]).get(i).isEnabled()) {
                    RenderUtils.fill(matrices, 147 + xOffset, 32 + i * 15, 21, 11, 0x80_109e00);
                    DrawableHelper.drawStringWithShadow(matrices, textRenderer, "ON", 152 + xOffset, 15 + 19 + i * 15, 0xFFFFFF);
                } else {
                    RenderUtils.fill(matrices, 147 + xOffset, 32 + i * 15, 21, 11, 0x80_D10000);
                    DrawableHelper.drawStringWithShadow(matrices, textRenderer, "OFF", 149 + xOffset, 15 + 19 + i * 15, 0xFFFFFF);
                }
                DrawableHelper.drawStringWithShadow(matrices, textRenderer, KClient.getInstance().getHackManager().getHacksByCategory(Category.values()[this.selectedCategoryIndex]).get(i).getName(), 88, 15 + 19 + i * 15, 0xFFFFFF);

            }
        }

        // Selected hack info
        if(this.showHackInfo) {
            RenderUtils.fill(matrices, 84 * 2, 15, 200, 115, 0x80_000000);
        }
    }

}
