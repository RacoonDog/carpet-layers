package io.github.racoondog.carpetlayers;

import net.minecraft.block.Block;

public interface StackableCarpetManager {
    void carpetLayers$setStackable(boolean stackable);

    static void setStackable(Block block, boolean stackable) {
        if (block instanceof StackableCarpetManager carpetBlock)
            carpetBlock.carpetLayers$setStackable(stackable);
    }
}
