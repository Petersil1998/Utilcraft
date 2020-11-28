package net.petersil98.utilcraft.data;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.petersil98.utilcraft.Main;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final KeyBinding VEIN_MINER = new KeyBinding("key_bindings."+Main.MOD_ID+".vein_miner", KeyConflictContext.UNIVERSAL, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_X, "key_bindings."+Main.MOD_ID+".category");
}
