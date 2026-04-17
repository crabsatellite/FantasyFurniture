package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.ContainerFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil.MultiBlockType;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicBookshelfBlock extends ContainerFurnitureBlock {
    public static final MultiBlockType MULTI_BLOCK_TYPE = MultiBlockType.MB_2x2x1;
    public static final IntegerProperty PART = MULTI_BLOCK_TYPE.getPartProperty();

    // Full shape spans 2 wide, 2 tall
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(-15, 0, 1, 15, 30, 15),
            box(-16, 30, 0, 16, 32, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    // Part 0: origin (bottom-left)
    private static final VoxelShape SHAPE_P0 = VoxelShapeHelper.combine(
            box(-15, 0, 1, 15, 16, 15)
    );
    private static final Map<Direction, VoxelShape> SHAPES_P0 = VoxelShapeHelper.rotateHorizontal(SHAPE_P0);

    // Part 1: bottom-right
    private static final VoxelShape SHAPE_P1 = box(0, 0, 1, 16, 16, 15);
    private static final Map<Direction, VoxelShape> SHAPES_P1 = VoxelShapeHelper.rotateHorizontal(SHAPE_P1);

    // Part 2: top-left
    private static final VoxelShape SHAPE_P2 = VoxelShapeHelper.combine(
            box(-15, 0, 1, 15, 14, 15),
            box(-16, 14, 0, 16, 16, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES_P2 = VoxelShapeHelper.rotateHorizontal(SHAPE_P2);

    // Part 3: top-right
    private static final VoxelShape SHAPE_P3 = VoxelShapeHelper.combine(
            box(0, 0, 1, 16, 14, 15),
            box(0, 14, 0, 16, 16, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES_P3 = VoxelShapeHelper.rotateHorizontal(SHAPE_P3);

    public NordicBookshelfBlock(Properties properties) {
        super(properties, ModBlockEntities.BOOKSHELF);

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

        return switch (part) {
            case 1 -> SHAPES_P1.getOrDefault(facing, SHAPE_P1);
            case 2 -> SHAPES_P2.getOrDefault(facing, SHAPE_P2);
            case 3 -> SHAPES_P3.getOrDefault(facing, SHAPE_P3);
            default -> SHAPES_P0.getOrDefault(facing, SHAPE_P0);
        };
    }
}
