package fr.kozen.kclient.hacks;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;

public abstract class Hack {

    private String name;
    private String description;
    private KeyBinding keyBinding;
    private Category category;
    private boolean enabled;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public KeyBinding getKeyBinding() { return keyBinding; }

    public void setKeyBinding(KeyBinding keyBinding) { this.keyBinding = KeyBindingHelper.registerKeyBinding(keyBinding); }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

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

    public boolean isEnabled() {
        return enabled;
    }

    public abstract void onEnabled();

    public abstract void onDisabled();

    public abstract void onTick();

}
