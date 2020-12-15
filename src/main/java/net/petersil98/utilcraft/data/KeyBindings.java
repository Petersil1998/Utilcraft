package net.petersil98.utilcraft.data;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.petersil98.utilcraft.Main;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final KeyBinding VEIN_MINER = new KeyBinding(String.format("key_bindings.%s.vein_miner", Main.MOD_ID), KeyConflictContext.UNIVERSAL, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_X, String.format("key_bindings.%s.category", Main.MOD_ID));
}
