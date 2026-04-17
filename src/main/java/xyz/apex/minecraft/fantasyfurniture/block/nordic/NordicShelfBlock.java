package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicShelfBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<ConnectionType> CONNECTION = EnumProperty.create("connection", ConnectionType.class);

    private static final VoxelShape SHAPE_SINGLE = VoxelShapeHelper.combine(
            box(.5, 9, 2, 2.5, 14, 13),
            box(13.5, 9, 2, 15.5, 14, 13),
            box(0, 14, 0, 16, 16, 16),
            box(13, 6, 13, 16, 14, 16),
            box(0, 6, 13, 3, 14, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES_SINGLE = VoxelShapeHelper.rotateHorizontal(SHAPE_SINGLE);

    private static final VoxelShape SHAPE_CENTER = box(0, 14, 0, 16, 16, 16);
    private static final Map<Direction, VoxelShape> SHAPES_CENTER = VoxelShapeHelper.rotateHorizontal(SHAPE_CENTER);

    private static final VoxelShape SHAPE_LEFT = VoxelShapeHelper.combine(
            box(13.5, 9, 2, 15.5, 14, 13),
            box(0, 14, 0, 16, 16, 16),
            box(13, 6, 13, 16, 14, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES_LEFT = VoxelShapeHelper.rotateHorizontal(SHAPE_LEFT);

    private static final VoxelShape SHAPE_RIGHT = VoxelShapeHelper.combine(
            box(.5, 9, 2, 2.5, 14, 13),
            box(0, 14, 0, 16, 16, 16),
            box(0, 6, 13, 3, 14, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES_RIGHT = VoxelShapeHelper.rotateHorizontal(SHAPE_RIGHT);

    public NordicShelfBlock(Properties properties) {
        super(properties);

        registerDefaultState(stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(CONNECTION, ConnectionType.SINGLE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, CONNECTION);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        Direction facing = context.getHorizontalDirection().getOpposite();
        BlockPos pos = context.getClickedPos();

        BlockState state = defaultBlockState()
                .setValue(FACING, facing)
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);

        return state.setValue(CONNECTION, getConnection(context.getLevel(), pos, facing));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                     LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        Direction facing = state.getValue(FACING);
        return state.setValue(CONNECTION, getConnection(level, pos, facing));
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        ConnectionType connection = state.getValue(CONNECTION);

        return switch (connection) {
            case SINGLE -> SHAPES_SINGLE.getOrDefault(facing, SHAPE_SINGLE);
            case CENTER -> SHAPES_CENTER.getOrDefault(facing, SHAPE_CENTER);
            case LEFT -> SHAPES_LEFT.getOrDefault(facing, SHAPE_LEFT);
            case RIGHT -> SHAPES_RIGHT.getOrDefault(facing, SHAPE_RIGHT);
        };
    }

    private ConnectionType getConnection(BlockGetter level, BlockPos pos, Direction facing) {
        Direction left = facing.getClockWise();
        Direction right = facing.getCounterClockWise();

        BlockState leftState = level.getBlockState(pos.relative(left));
        BlockState rightState = level.getBlockState(pos.relative(right));

        boolean hasLeft = leftState.getBlock() instanceof NordicShelfBlock
                && leftState.getValue(FACING) == facing;
        boolean hasRight = rightState.getBlock() instanceof NordicShelfBlock
                && rightState.getValue(FACING) == facing;

        if (hasLeft && hasRight) {
            return ConnectionType.CENTER;
        }

        if (hasLeft) {
            return ConnectionType.RIGHT;
        }

        if (hasRight) {
            return ConnectionType.LEFT;
        }

        return ConnectionType.SINGLE;
    }

    public enum ConnectionType implements StringRepresentable {
        SINGLE("single"),
        CENTER("center"),
        LEFT("left"),
        RIGHT("right");

        private final String name;

        ConnectionType(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
