package fr.kozen.kclient.hacks;

import fr.kozen.kclient.KClient;
import fr.kozen.kclient.hacks.client.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HackManager {

    private ArrayList<Hack> hacks = new ArrayList<Hack>();

    public HackManager() { this.initialise(); }

    public void initialise() {
        KClient.LOGGER.info("Loading hacks...");
        this.addHack(new Fly());
        this.addHack(new Jesus());
        this.addHack(new NoFall());
        this.addHack(new SafeWalk());
        this.addHack(new Speed());

        KClient.LOGGER.info(this.hacks.size() + " hacks loaded.");
    }

    public void tick() {
        for(Hack hack : this.hacks) {
            if(hack.getKeyBinding().wasPressed()) {
                hack.toggle();
            }
            hack.onTick();
        }
    }

    public void addHack(Hack hack) {
        this.hacks.add(hack);
    }

    public Hack getHackByName(String name) {
        return this.hacks.stream().filter(hack -> hack.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Hack> getHacksByCategory(Category category) {
        return this.hacks.stream().filter(hack -> hack.getCategory().equals(category)).collect(Collectors.toList());
    }

}
