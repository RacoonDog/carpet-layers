package io.github.racoondog.carpetlayers;

import net.minecraft.block.Block;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class Constants {
    public static final IntProperty CARPET_LAYERS = IntProperty.of("carpet_layers", 1, 16);
    public static final VoxelShape[] LAYERS_TO_SHAPE = new VoxelShape[17];

    static {
        LAYERS_TO_SHAPE[0] = VoxelShapes.empty();
        for (int i = 1; i < 16; i++) {
            LAYERS_TO_SHAPE[i] = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, i, 16.0);
        }
        LAYERS_TO_SHAPE[16] = VoxelShapes.fullCube();
    }
}
