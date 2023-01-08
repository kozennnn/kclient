package fr.kozen.kclient.hacks.client;

import fr.kozen.kclient.hacks.Category;
import fr.kozen.kclient.hacks.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class Fly extends Hack {

    private static Integer count;

    public Fly() {
        this.setName("Fly");
        this.setDescription("Allow you to fly like in creative mode.");
        this.setKeyBinding(new KeyBinding("key.fly", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F, "key.categories.kclient"));
        this.setCategory(Category.MOVEMENT);
    }

    @Override
    public void onEnabled() {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayerEntity playerEntity = mc.player;
        playerEntity.getAbilities().allowFlying = true;
        playerEntity.sendMessage(Text.literal("Flying enabled."), false);
    }

    @Override
    public void onDisabled() {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayerEntity playerEntity = mc.player;
        playerEntity.getAbilities().allowFlying = false;
        playerEntity.sendMessage(Text.literal("Flying disabled."), false);
    }

    @Override
    public void onTick() {
        if(this.isEnabled()) {
            if (count == 37) {
                count = 0;
                MinecraftClient mc = MinecraftClient.getInstance();
                ClientPlayerEntity playerEntity = mc.player;
                if (playerEntity == null || !playerEntity.getAbilities().allowFlying) {
                    this.disable();
                    return;
                }
                if (!playerEntity.getAbilities().flying) return;
                // Bypass the anti-cheat by simulating a player fall.
                if (playerEntity.getVelocity().getY() > 0) {
                    playerEntity.setVelocity(playerEntity.getVelocity().add(0, -0.03126 * (playerEntity.getVelocity().getY() * 59), 0));
                } else {
                    playerEntity.setVelocity(playerEntity.getVelocity().add(0, -0.03126, 0));
                }
            }
            count++;
        } else {
            count = 0;
        }
    }

}
