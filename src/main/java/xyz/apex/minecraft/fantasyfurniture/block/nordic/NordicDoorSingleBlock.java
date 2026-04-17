package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil.MultiBlockType;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicDoorSingleBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final EnumProperty<DoorHingeSide> HINGE = BlockStateProperties.DOOR_HINGE;
    public static final MultiBlockType MULTI_BLOCK_TYPE = MultiBlockType.MB_1x2x1;
    public static final IntegerProperty PART = MULTI_BLOCK_TYPE.getPartProperty();

    // Closed shapes - RIGHT hinge (hinge on right side when facing)
    private static final VoxelShape SHAPE_BOTTOM = VoxelShapeHelper.combine(
            box(0, 0, 0, 13, 2, 3),
            box(0, 10, 0, 13, 12, 3),
            box(13, 0, 0, 16, 16, 3),
            box(0, 2, .5, 13, 16, 2.5)
    );
    private static final Map<Direction, VoxelShape> SHAPES_BOTTOM = VoxelShapeHelper.rotateHorizontal(SHAPE_BOTTOM);

    private static final VoxelShape SHAPE_TOP = VoxelShapeHelper.combine(
            box(0, 4, 0, 13, 6, 3),
            box(0, 14, 0, 13, 16, 3),
            box(13, 0, 0, 16, 16, 3),
            box(0, 0, .5, 13, 14, 2.5)
    );
    private static final Map<Direction, VoxelShape> SHAPES_TOP = VoxelShapeHelper.rotateHorizontal(SHAPE_TOP);

    // Closed shapes - LEFT hinge (hinge on left side when facing)
    private static final VoxelShape SHAPE_BOTTOM_LEFT = VoxelShapeHelper.combine(
            box(3, 0, 0, 16, 2, 3),
            box(3, 10, 0, 16, 12, 3),
            box(0, 0, 0, 3, 16, 3),
            box(3, 2, .5, 16, 16, 2.5)
    );
    private static final Map<Direction, VoxelShape> SHAPES_BOTTOM_LEFT = VoxelShapeHelper.rotateHorizontal(SHAPE_BOTTOM_LEFT);

    private static final VoxelShape SHAPE_TOP_LEFT = VoxelShapeHelper.combine(
            box(3, 4, 0, 16, 6, 3),
            box(3, 14, 0, 16, 16, 3),
            box(0, 0, 0, 3, 16, 3),
            box(3, 0, .5, 16, 14, 2.5)
    );
    private static final Map<Direction, VoxelShape> SHAPES_TOP_LEFT = VoxelShapeHelper.rotateHorizontal(SHAPE_TOP_LEFT);

    // Open shapes - RIGHT hinge
    private static final VoxelShape SHAPE_OPEN_BOTTOM = VoxelShapeHelper.combine(
            box(13, 0, 0, 16, 16, 16),
            box(13.5, 2, 0, 15.5, 16, 13)
    );
    private static final Map<Direction, VoxelShape> SHAPES_OPEN_BOTTOM = VoxelShapeHelper.rotateHorizontal(SHAPE_OPEN_BOTTOM);

    private static final VoxelShape SHAPE_OPEN_TOP = VoxelShapeHelper.combine(
            box(13, 0, 0, 16, 16, 16),
            box(13.5, 0, 0, 15.5, 14, 13)
    );
    private static final Map<Direction, VoxelShape> SHAPES_OPEN_TOP = VoxelShapeHelper.rotateHorizontal(SHAPE_OPEN_TOP);

    // Open shapes - LEFT hinge
    private static final VoxelShape SHAPE_OPEN_BOTTOM_LEFT = VoxelShapeHelper.combine(
            box(0, 0, 0, 3, 16, 16),
            box(.5, 2, 0, 2.5, 16, 13)
    );
    private static final Map<Direction, VoxelShape> SHAPES_OPEN_BOTTOM_LEFT = VoxelShapeHelper.rotateHorizontal(SHAPE_OPEN_BOTTOM_LEFT);

    private static final VoxelShape SHAPE_OPEN_TOP_LEFT = VoxelShapeHelper.combine(
            box(0, 0, 0, 3, 16, 16),
            box(.5, 0, 0, 2.5, 14, 13)
    );
    private static final Map<Direction, VoxelShape> SHAPES_OPEN_TOP_LEFT = VoxelShapeHelper.rotateHorizontal(SHAPE_OPEN_TOP_LEFT);

    public NordicDoorSingleBlock(Properties properties) {
        super(properties);

        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(OPEN, false)
                .setValue(POWERED, false)
                .setValue(HINGE, DoorHingeSide.LEFT)
                .setValue(PART, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, OPEN, POWERED, HINGE, PART);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        Direction facing = context.getHorizontalDirection().getOpposite();
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();

        if (!MultiBlockUtil.canPlace(level, pos, facing, MULTI_BLOCK_TYPE)) {
            return null;
        }

        boolean powered = level.hasNeighborSignal(pos) || level.hasNeighborSignal(pos.above());
        DoorHingeSide hinge = determineHinge(level, pos, facing);

        return defaultBlockState()
                .setValue(FACING, facing)
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                .setValue(OPEN, powered)
                .setValue(POWERED, powered)
                .setValue(HINGE, hinge)
                .setValue(PART, 0);
    }

    private DoorHingeSide determineHinge(Level level, BlockPos pos, Direction facing) {
        Direction ccw = facing.getCounterClockWise();
        Direction cw = facing.getClockWise();
        BlockState leftState = level.getBlockState(pos.relative(ccw));
        BlockState rightState = level.getBlockState(pos.relative(cw));
        int leftScore = leftState.isCollisionShapeFullBlock(level, pos.relative(ccw)) ? -1 : 0;
        int rightScore = rightState.isCollisionShapeFullBlock(level, pos.relative(cw)) ? -1 : 0;
        if (leftState.is(this)) leftScore--;
        if (rightState.is(this)) rightScore--;
        return leftScore > rightScore ? DoorHingeSide.RIGHT : DoorHingeSide.LEFT;
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
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide() && state.getValue(PART) != 0) {
            BlockPos originPos = MULTI_BLOCK_TYPE.getOriginFromPart(pos, state.getValue(FACING), state.getValue(PART));
            BlockState originState = level.getBlockState(originPos);
            if (originState.is(this)) {
                if (!player.isCreative()) {
                    Block.dropResources(originState, level, originPos, null, player, player.getMainHandItem());
                }
                level.removeBlock(originPos, false);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        state = state.cycle(OPEN);
        level.setBlock(pos, state, Block.UPDATE_ALL);

        // Toggle both parts
        int part = state.getValue(PART);
        BlockPos otherPos = part == 0 ? pos.above() : pos.below();
        BlockState otherState = level.getBlockState(otherPos);
        if (otherState.is(this)) {
            level.setBlock(otherPos, otherState.setValue(OPEN, state.getValue(OPEN)), Block.UPDATE_ALL);
        }

        boolean open = state.getValue(OPEN);
        playDoorSound(level, pos, open, player);
        level.gameEvent(player, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        boolean powered = level.hasNeighborSignal(pos);
        // Also check the other part
        int part = state.getValue(PART);
        BlockPos otherPos = part == 0 ? pos.above() : pos.below();
        powered = powered || level.hasNeighborSignal(otherPos);

        if (!defaultBlockState().is(neighborBlock) && powered != state.getValue(POWERED)) {
            if (powered != state.getValue(OPEN)) {
                playDoorSound(level, pos, powered, null);
                level.gameEvent(null, powered ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
            }

            level.setBlock(pos, state.setValue(POWERED, powered).setValue(OPEN, powered), Block.UPDATE_ALL);

            // Sync the other part
            BlockState otherState = level.getBlockState(otherPos);
            if (otherState.is(this)) {
                level.setBlock(otherPos, otherState.setValue(POWERED, powered).setValue(OPEN, powered), Block.UPDATE_ALL);
            }
        }
    }

    private void playDoorSound(Level level, BlockPos pos, boolean open, @Nullable Player player) {
        level.playSound(player, pos,
                open ? SoundEvents.WOODEN_DOOR_OPEN : SoundEvents.WOODEN_DOOR_CLOSE,
                SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType type) {
        return switch (type) {
            case LAND, AIR -> state.getValue(OPEN);
            case WATER -> false;
        };
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                     LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        boolean open = state.getValue(OPEN);
        int part = state.getValue(PART);
        boolean leftHinge = state.getValue(HINGE) == DoorHingeSide.LEFT;

        if (open) {
            if (leftHinge) {
                return part == 0 ? SHAPES_OPEN_BOTTOM_LEFT.getOrDefault(facing, SHAPE_OPEN_BOTTOM_LEFT)
                        : SHAPES_OPEN_TOP_LEFT.getOrDefault(facing, SHAPE_OPEN_TOP_LEFT);
            }
            return part == 0 ? SHAPES_OPEN_BOTTOM.getOrDefault(facing, SHAPE_OPEN_BOTTOM)
                    : SHAPES_OPEN_TOP.getOrDefault(facing, SHAPE_OPEN_TOP);
        }

        if (leftHinge) {
            return part == 0 ? SHAPES_BOTTOM_LEFT.getOrDefault(facing, SHAPE_BOTTOM_LEFT)
                    : SHAPES_TOP_LEFT.getOrDefault(facing, SHAPE_TOP_LEFT);
        }
        return part == 0 ? SHAPES_BOTTOM.getOrDefault(facing, SHAPE_BOTTOM)
                : SHAPES_TOP.getOrDefault(facing, SHAPE_TOP);
    }
}
