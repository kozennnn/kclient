package fr.kozen.kclient.hacks.client;

import fr.kozen.kclient.hacks.Category;
import fr.kozen.kclient.hacks.Hack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class NoFall extends Hack {

    public NoFall() {
        this.setName("NoFall");
        this.setDescription("");
        this.setKeyBinding(new KeyBinding("key.nofall", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_M, "key.categories.kclient"));
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
