package io.github.racoondog.carpetlayers.mixin;

import io.github.racoondog.carpetlayers.Constants;
import io.github.racoondog.carpetlayers.StackableCarpetManager;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CarpetBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/AbstractBlock;<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V", shift = At.Shift.AFTER))
    private void registerVanillaBlocks(AbstractBlock.Settings settings, CallbackInfo ci) {
        if (Constants.VANILLA_REGISTRATION) StackableCarpetManager.registerStackable((Block) (Object) this);
    }
}
