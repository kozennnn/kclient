package fr.kozen.kclient.hacks.client;

import fr.kozen.kclient.hacks.Category;
import fr.kozen.kclient.hacks.Hack;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Jesus extends Hack {

    public Jesus() {
        this.setName("Jesus");
        this.setDescription("");
        this.setKeyBinding(new KeyBinding("key.jesus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "key.categories.kclient"));
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
