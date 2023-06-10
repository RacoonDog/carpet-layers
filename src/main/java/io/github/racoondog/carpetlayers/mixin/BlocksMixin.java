package io.github.racoondog.carpetlayers.mixin;

import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.racoondog.carpetlayers.Constants.VANILLA_REGISTRATION;

@Mixin(Blocks.class)
public class BlocksMixin {
    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void beginVanillaRegistration(CallbackInfo ci) {
        VANILLA_REGISTRATION = true;
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void endVanillaRegistration(CallbackInfo ci) {
        VANILLA_REGISTRATION = false;
    }
}
