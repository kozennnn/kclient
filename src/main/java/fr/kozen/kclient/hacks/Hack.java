package fr.kozen.kclient.hacks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;

public class Hack {

    public boolean enabled = false;
    public KeyBinding keyBinding;
    public ClientPlayerEntity playerEntity;

    public Hack(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            this.playerEntity = mc.player;
            while (keyBinding.wasPressed()) {
                this.toggle();
            }
        });
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (this.enabled) this.onEnabled();
        if (!this.enabled) this.onDisabled();
    }

    public void enable() {
        this.enabled = true;
        this.onEnabled();
    }

    public void disable() {
        this.enabled = false;
        this.onDisabled();
    }

    public void onEnabled() {}

    public void onDisabled() {}

    public ClientPlayerEntity getPlayerEntity() {
        return this.playerEntity;
    }
}
