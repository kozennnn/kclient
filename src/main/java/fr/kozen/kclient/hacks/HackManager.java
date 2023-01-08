package fr.kozen.kclient.hacks;

import fr.kozen.kclient.KClient;
import fr.kozen.kclient.hacks.client.Fly;

import java.util.ArrayList;

public class HackManager {

    private ArrayList<Hack> hacks = new ArrayList<Hack>();

    public HackManager() {
        KClient.LOGGER.info("Loading hacks...");
        this.addHack(new Fly());
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

}
