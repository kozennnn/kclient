package fr.kozen.kclient;


import fr.kozen.kclient.gui.HudManager;
import fr.kozen.kclient.hacks.HackManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KClient implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("kclient");

    private static KClient kClient;

    private HackManager hackManager;
    private HudManager hudManager;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");

        kClient = this;

        this.initialise();
    }

    public void initialise() {
        this.hackManager = new HackManager();
        this.hudManager = new HudManager();
    }

    public void tick() {
        this.hackManager.tick();
    }

    public HackManager getHackManager() { return hackManager; }

    public HudManager getHudManager() { return hudManager; }

    public static KClient getInstance() { return kClient; }
}
