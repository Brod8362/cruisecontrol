package pw.byakuren.cruisecontrol.mixin;

import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.util.math.Vec2f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pw.byakuren.cruisecontrol.client.CruiseControlClient;

@Mixin(KeyboardInput.class)
public abstract class CruiseMovementMixin {

    @Inject(at = @At("RETURN"), method="tick")
    private void injectMethod(boolean slowDown, float f, CallbackInfo ci) {
        if (CruiseControlClient.cruiseEnabled) {
            KeyboardInput th = ((KeyboardInput)(Object)this);
            th.pressingForward = true;
            th.movementForward = 1.0f;
            if (slowDown) {
                th.movementForward *= f;
            }
        }
    }
}
