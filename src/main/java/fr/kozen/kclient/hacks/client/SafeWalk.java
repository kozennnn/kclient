package fr.kozen.kclient.hacks.client;

import fr.kozen.kclient.hacks.Category;
import fr.kozen.kclient.hacks.Hack;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SafeWalk extends Hack {

    public SafeWalk() {
        this.setName("Safe Walk");
        this.setDescription("");
        this.setKeyBinding(new KeyBinding("key.safewalk", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_I, "key.categories.kclient"));
        this.setCategory(Category.MOVEMENT);
    }

    @Override
    public void onEnabled() {

    }

    @Override
    public void onDisabled() {

    }

    @Override
    public void onTick() {

    }

}
