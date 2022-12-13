package io.github.racoondog.carpetlayers.mixin;

import io.github.racoondog.carpetlayers.Constants;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CarpetBlock.class)
public abstract class CarpetBlockMixin extends Block {
    private static final IntProperty CARPET_LAYERS = Constants.CARPET_LAYERS;

    public CarpetBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void appendDefaultState(AbstractBlock.Settings settings, CallbackInfo ci) {
        this.setDefaultState(this.getDefaultState().with(CARPET_LAYERS, 1));
    }

    /** Copied from {@link net.minecraft.block.SnowBlock} */
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return type == NavigationType.LAND && state.get(CARPET_LAYERS) < 10;
    }

    /**
     * @author Crosby
     * @reason Use layer property
     */
    @Overwrite
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Constants.LAYERS_TO_SHAPE[state.get(CARPET_LAYERS)];
    }

    /** Copied from {@link net.minecraft.block.SnowBlock} */
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Constants.LAYERS_TO_SHAPE[state.get(CARPET_LAYERS)];
    }

    /** Copied from {@link net.minecraft.block.SnowBlock} */
    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return Constants.LAYERS_TO_SHAPE[state.get(CARPET_LAYERS)];
    }

    /** Copied from {@link net.minecraft.block.SnowBlock} */
    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Constants.LAYERS_TO_SHAPE[state.get(CARPET_LAYERS)];
    }

    /** Copied from {@link net.minecraft.block.SnowBlock} */
    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return state.get(CARPET_LAYERS) == 16 ? 0.2f : 1.0f;
    }


    /** Copied from {@link net.minecraft.block.SnowBlock} */
    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        int i = state.get(CARPET_LAYERS);
        if (context.getStack().isOf(this.asItem()) && i < 16) {
            if (context.canReplaceExisting()) return context.getSide() == Direction.UP;
            return true;
        }
        return i == 1;
    }

    /** Copied from {@link net.minecraft.block.SnowBlock} */
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (state.isOf(this)) {
            int i = state.get(CARPET_LAYERS);
            return state.with(CARPET_LAYERS, Math.min(16, i + 1));
        }
        return super.getPlacementState(ctx);
    }

    /** Copied from {@link net.minecraft.block.SnowBlock} */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CARPET_LAYERS);
    }
}
