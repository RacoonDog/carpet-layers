package io.github.racoondog.carpetlayers;

import net.minecraft.block.Block;

public interface StackableCarpetManager {
    void carpetLayers$setStackable(boolean stackable);
    boolean carpetLayers$getStackable();

    static void registerStackable(Block block) {
        if (block instanceof StackableCarpetManager carpetBlock)
            carpetBlock.carpetLayers$setStackable(true);
    }

    static boolean isStackable(Block block) {
        return block instanceof StackableCarpetManager carpetBlock
                && carpetBlock.carpetLayers$getStackable();
    }
}
