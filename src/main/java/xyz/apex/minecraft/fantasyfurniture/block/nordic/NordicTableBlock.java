package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import static net.minecraft.world.level.block.Block.box;

public class NordicTableBlock extends Block implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;

    private static final VoxelShape SHAPE_TOP = box(0, 13, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_LEG_FL = box(1, 0, 1, 3, 13, 3);
    private static final VoxelShape SHAPE_LEG_FR = box(13, 0, 1, 15, 13, 3);
    private static final VoxelShape SHAPE_LEG_BL = box(1, 0, 13, 3, 13, 15);
    private static final VoxelShape SHAPE_LEG_BR = box(13, 0, 13, 15, 13, 15);

    private static final VoxelShape SHAPE_FULL = VoxelShapeHelper.combine(
            SHAPE_TOP, SHAPE_LEG_FL, SHAPE_LEG_FR, SHAPE_LEG_BL, SHAPE_LEG_BR
    );

    // Precomputed shape per (NORTH,EAST,SOUTH,WEST) bitmask — 16 entries
    private static final VoxelShape[] SHAPES_BY_CONNECTIONS = new VoxelShape[16];
    static {
        for (int mask = 0; mask < 16; mask++) {
            boolean north = (mask & 1) != 0;
            boolean east = (mask & 2) != 0;
            boolean south = (mask & 4) != 0;
            boolean west = (mask & 8) != 0;

            if (!north && !east && !south && !west) {
                SHAPES_BY_CONNECTIONS[mask] = SHAPE_FULL;
                continue;
            }

            VoxelShape shape = SHAPE_TOP;
            if (!north && !west) shape = VoxelShapeHelper.combine(shape, SHAPE_LEG_FL);
            if (!north && !east) shape = VoxelShapeHelper.combine(shape, SHAPE_LEG_FR);
            if (!south && !west) shape = VoxelShapeHelper.combine(shape, SHAPE_LEG_BL);
            if (!south && !east) shape = VoxelShapeHelper.combine(shape, SHAPE_LEG_BR);
            SHAPES_BY_CONNECTIONS[mask] = shape;
        }
    }

    public NordicTableBlock(Properties properties) {
        super(properties);

        registerDefaultState(stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, NORTH, EAST, SOUTH, WEST);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        BlockPos pos = context.getClickedPos();
        BlockGetter level = context.getLevel();

        return defaultBlockState()
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER)
                .setValue(NORTH, isTable(level, pos.north()))
                .setValue(EAST, isTable(level, pos.east()))
                .setValue(SOUTH, isTable(level, pos.south()))
                .setValue(WEST, isTable(level, pos.west()));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                     LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        if (direction.getAxis().isHorizontal()) {
            BooleanProperty prop = getPropertyForDirection(direction);
            if (prop != null) {
                state = state.setValue(prop, neighborState.getBlock() instanceof NordicTableBlock);
            }
        }

        return state;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int mask = (state.getValue(NORTH) ? 1 : 0)
                | (state.getValue(EAST) ? 2 : 0)
                | (state.getValue(SOUTH) ? 4 : 0)
                | (state.getValue(WEST) ? 8 : 0);
        return SHAPES_BY_CONNECTIONS[mask];
    }

    private boolean isTable(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos).getBlock() instanceof NordicTableBlock;
    }

    private static BooleanProperty getPropertyForDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> null;
        };
    }
}
