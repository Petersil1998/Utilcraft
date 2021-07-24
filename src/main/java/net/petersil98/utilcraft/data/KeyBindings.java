package net.petersil98.utilcraft.data;

import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.petersil98.utilcraft.Utilcraft;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final KeyMapping VEIN_MINER = new KeyMapping(String.format("key_bindings.%s.vein_miner", Utilcraft.MOD_ID), KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, String.format("key_bindings.%s.category", Utilcraft.MOD_ID));
}
