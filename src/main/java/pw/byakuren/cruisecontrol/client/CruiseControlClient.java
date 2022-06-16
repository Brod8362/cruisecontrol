package pw.byakuren.cruisecontrol.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class CruiseControlClient implements ClientModInitializer {

    private static KeyBinding keyBinding;
    public static boolean cruiseEnabled = false;

    @Override
    public void onInitializeClient() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.cruisecontrol.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "category.cruisecontrol.bindings"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                if (client.player != null) {
                    if (!cruiseEnabled) {
                        client.player.sendMessage(Text.translatable("cruisecontrol.enabled"), true);
                        cruiseEnabled = true;
                    } else {
                        client.player.sendMessage(Text.translatable("cruisecontrol.disabled"), true);
                        cruiseEnabled = false;
                    }
                }
            }
        });
    }
}
