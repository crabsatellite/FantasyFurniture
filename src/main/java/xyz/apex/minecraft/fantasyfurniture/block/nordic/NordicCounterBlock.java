package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.ContainerFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicCounterBlock extends ContainerFurnitureBlock {
    public static final EnumProperty<ConnectionType> CONNECTION = EnumProperty.create("connection", ConnectionType.class);

    private static final VoxelShape SHAPE_SINGLE = VoxelShapeHelper.combine(
            box(0, 0, 3, 16, 13, 16),
            box(0, 13, 0, 16, 16, 16),
            box(1, 1, 2, 15, 12, 3)
    );
    private static final Map<Direction, VoxelShape> SHAPES_SINGLE = VoxelShapeHelper.rotateHorizontal(SHAPE_SINGLE);

    private static final VoxelShape SHAPE_CORNER = VoxelShapeHelper.combine(
            box(0, 0, 0, 13, 13, 4),
            box(0, 0, 3, 16, 13, 16),
            box(0, 13, 0, 16, 16, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES_CORNER = VoxelShapeHelper.rotateHorizontal(SHAPE_CORNER);

    public NordicCounterBlock(Properties properties) {
        super(properties, ModBlockEntities.MEDIUM_CONTAINER);

        registerDefaultState(defaultBlockState().setValue(CONNECTION, ConnectionType.SINGLE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CONNECTION);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state == null) return null;

        Direction facing = state.getValue(FACING);
        BlockPos pos = context.getClickedPos();

        return state.setValue(CONNECTION, getConnection(context.getLevel(), pos, facing));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                     LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        state = super.updateShape(state, direction, neighborState, level, pos, neighborPos);

        Direction facing = state.getValue(FACING);
        return state.setValue(CONNECTION, getConnection(level, pos, facing));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        ConnectionType connection = state.getValue(CONNECTION);

        return switch (connection) {
            case SINGLE -> SHAPES_SINGLE.getOrDefault(facing, SHAPE_SINGLE);
            case CORNER -> SHAPES_CORNER.getOrDefault(facing, SHAPE_CORNER);
        };
    }

    private ConnectionType getConnection(BlockGetter level, BlockPos pos, Direction facing) {
        Direction left = facing.getClockWise();
        Direction right = facing.getCounterClockWise();

        BlockState leftState = level.getBlockState(pos.relative(left));
        BlockState rightState = level.getBlockState(pos.relative(right));

        boolean hasLeft = leftState.getBlock() instanceof NordicCounterBlock;
        boolean hasRight = rightState.getBlock() instanceof NordicCounterBlock;

        if (hasLeft || hasRight) {
            BlockState adjacentState = hasLeft ? leftState : rightState;
            Direction adjacentFacing = adjacentState.getValue(FACING);

            if (adjacentFacing != facing && adjacentFacing != facing.getOpposite()) {
                return ConnectionType.CORNER;
            }
        }

        return ConnectionType.SINGLE;
    }

    public enum ConnectionType implements StringRepresentable {
        SINGLE("single"),
        CORNER("corner");

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
