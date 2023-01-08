package fr.kozen.kclient.hacks.client;

import fr.kozen.kclient.hacks.Hack;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.Timer;
import java.util.TimerTask;

public class Fly extends Hack {

        private static final KeyBinding FLY_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.fly", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F, "key.categories.movement"));

        private static Timer timer;
        private static Fly flyInstance;

        public Fly() {
                super(FLY_KEY);
                flyInstance = this;
        }

        @Override
        public void onEnabled() {
                this.getPlayerEntity().getAbilities().allowFlying = true;
                this.getPlayerEntity().sendMessage(Text.literal("Flying enabled."), false);

                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                                if (flyInstance.getPlayerEntity() == null || !flyInstance.getPlayerEntity().getAbilities().allowFlying) {
                                        flyInstance.disable();
                                        this.cancel();
                                        return;
                                }
                                if (!flyInstance.getPlayerEntity().getAbilities().flying) return;
                                // Bypass the anti-cheat by simulating a player fall.
                                if(flyInstance.getPlayerEntity().getVelocity().getY() > 0) {
                                        flyInstance.getPlayerEntity().setVelocity(flyInstance.getPlayerEntity().getVelocity().add(0, -0.03126 * (flyInstance.getPlayerEntity().getVelocity().getY() * 59), 0));
                                } else {
                                        flyInstance.getPlayerEntity().setVelocity(flyInstance.getPlayerEntity().getVelocity().add(0, -0.03126, 0));
                                }
                        }
                }, 200, 1000);
        }

        @Override
        public void onDisabled() {
                this.getPlayerEntity().getAbilities().allowFlying = false;
                this.getPlayerEntity().sendMessage(Text.literal("Flying disabled."), false);
        }

}
