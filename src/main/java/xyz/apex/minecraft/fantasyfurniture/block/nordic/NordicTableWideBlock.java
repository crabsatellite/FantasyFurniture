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
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil.MultiBlockType;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicTableWideBlock extends FurnitureBlock {
    public static final MultiBlockType MULTI_BLOCK_TYPE = MultiBlockType.MB_2x1x1;
    public static final IntegerProperty PART = MULTI_BLOCK_TYPE.getPartProperty();

    // PART=0 (origin, east half). 1.19.4 reference uses the east half of a -16..16 model.
    private static final VoxelShape SHAPE_PART0 = VoxelShapeHelper.combine(
            box(0, 13, 0, 16, 16, 16),
            box(13, 0, 0, 15, 9, 2),
            box(13, 0, 14, 15, 9, 16),
            box(13, 7, 1, 15, 13, 3),
            box(13, 7, 13, 15, 13, 15)
    );
    private static final Map<Direction, VoxelShape> SHAPES_PART0 = VoxelShapeHelper.rotateHorizontal(SHAPE_PART0);

    // PART=1 (west half). Legs translated from world x=-15..-13 into local x=1..3.
    private static final VoxelShape SHAPE_PART1 = VoxelShapeHelper.combine(
            box(0, 13, 0, 16, 16, 16),
            box(1, 0, 0, 3, 9, 2),
            box(1, 0, 14, 3, 9, 16),
            box(1, 7, 1, 3, 13, 3),
            box(1, 7, 13, 3, 13, 15)
    );
    private static final Map<Direction, VoxelShape> SHAPES_PART1 = VoxelShapeHelper.rotateHorizontal(SHAPE_PART1);

    public NordicTableWideBlock(Properties properties) {
        super(properties);

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
            return SHAPES_PART0.getOrDefault(facing, SHAPE_PART0);
        }

        return SHAPES_PART1.getOrDefault(facing, SHAPE_PART1);
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES_PART0.getOrDefault(direction, SHAPE_PART0);
    }
}
