package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil.MultiBlockType;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicFloorLightBlock extends FurnitureBlock {
    public static final MultiBlockType MULTI_BLOCK_TYPE = MultiBlockType.MB_1x2x1;
    public static final IntegerProperty PART = MULTI_BLOCK_TYPE.getPartProperty();

    // PART=0 (bottom block): base + pole clipped at block top (y=16).
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(6, 0, 6, 10, 2, 10),
            box(7, 2, 7, 9, 16, 9)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    // PART=1 (top block): all geometry above y=16 in the original model, translated down by 16 pixels.
    private static final VoxelShape SHAPE_TOP = VoxelShapeHelper.combine(
            box(7, 0, 7, 9, 4, 9),
            box(3, 0.75, 7, 7, 4.75, 9),
            box(9, 0.75, 7, 13, 4.75, 9),
            box(7, 0.75, 3, 9, 4.75, 7),
            box(7, 0.75, 9, 9, 4.75, 13),
            box(6.5, 4.75, 2.5, 9.5, 6.75, 5.5),
            box(2.5, 4.75, 6.5, 5.5, 6.75, 9.5),
            box(10.5, 4.75, 6.5, 13.5, 6.75, 9.5),
            box(6.5, 4.75, 10.5, 9.5, 6.75, 13.5),
            box(7.25, 6.75, 3.25, 8.75, 10.75, 4.75),
            box(3.25, 6.75, 7.25, 4.75, 10.75, 8.75),
            box(7.25, 6.75, 11.25, 8.75, 10.75, 12.75),
            box(11.25, 6.75, 7.25, 12.75, 10.75, 8.75)
    );
    private static final Map<Direction, VoxelShape> SHAPES_TOP = VoxelShapeHelper.rotateHorizontal(SHAPE_TOP);

    public NordicFloorLightBlock(Properties properties) {
        super(properties.lightLevel(state -> 14));

        registerDefaultState(defaultBlockState().setValue(PART, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state == null) return null;

        BlockPos pos = context.getClickedPos();
        Direction facing = state.getValue(FACING);
        Level level = context.getLevel();

        if (!MultiBlockUtil.canPlace(level, pos, facing, MULTI_BLOCK_TYPE)) {
            return null;
        }

        return state.setValue(PART, 0);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!level.isClientSide() && state.getValue(PART) == 0 && !oldState.is(this)) {
            MultiBlockUtil.placeMultiBlock(level, pos, state, state.getValue(FACING), MULTI_BLOCK_TYPE);
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!newState.is(this)) {
            MultiBlockUtil.removeMultiBlock(level, pos, state, MULTI_BLOCK_TYPE);
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected boolean isMultiBlockOrigin(BlockState state) {
        return state.getValue(PART) == 0;
    }

    @Override
    protected BlockPos getOriginPos(BlockState state, BlockPos pos) {
        return MULTI_BLOCK_TYPE.getOriginFromPart(pos, state.getValue(FACING), state.getValue(PART));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        int part = state.getValue(PART);

        if (part == 0) {
            return SHAPES.getOrDefault(facing, SHAPE);
        }

        return SHAPES_TOP.getOrDefault(facing, SHAPE_TOP);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        // Nordic floor light has 4 candle heads on the TOP part. Match legacy coords:
        // y = pos.Y + 0.5 + 0.34 = 0.84; four flames at (x\u00b10.27, y, z) and (x, y, z\u00b10.27).
        if (state.getValue(PART) != 1) {
            return;
        }
        double cx = pos.getX() + 0.5;
        double cy = pos.getY() + 0.84;
        double cz = pos.getZ() + 0.5;

        spawnCandleFlame(level, cx + 0.27, cy, cz);
        spawnCandleFlame(level, cx - 0.27, cy, cz);
        spawnCandleFlame(level, cx, cy, cz + 0.27);
        spawnCandleFlame(level, cx, cy, cz - 0.27);
    }

    private static void spawnCandleFlame(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
        level.addParticle(ParticleTypes.FLAME, x, y, z, 0.0, 0.0, 0.0);
    }
}
