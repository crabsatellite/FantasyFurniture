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

public class NordicTableLargeBlock extends FurnitureBlock {
    public static final MultiBlockType MULTI_BLOCK_TYPE = MultiBlockType.MB_2x1x2;
    public static final IntegerProperty PART = MULTI_BLOCK_TYPE.getPartProperty();

    // 1.19.4 shape (combined, world coords) has x=-16..16, z=0..32. Each part renders a 16x16 slice.
    // Table top: box(-16, 13, 0, 16, 16, 32) → per-part local: box(0, 13, 0, 16, 16, 16).
    // Legs: PART 0 origin is NE, PART 1 is NW (x-1), PART 2 is SE (z+1), PART 3 is SW (x-1, z+1).
    private static final VoxelShape SHAPE_TOP = box(0, 13, 0, 16, 16, 16);

    private static final VoxelShape SHAPE_PART0 = VoxelShapeHelper.combine(
            SHAPE_TOP,
            box(12, 0, 2, 14, 13, 4)
    );
    private static final Map<Direction, VoxelShape> SHAPES_PART0 = VoxelShapeHelper.rotateHorizontal(SHAPE_PART0);

    private static final VoxelShape SHAPE_PART1 = VoxelShapeHelper.combine(
            SHAPE_TOP,
            box(2, 0, 2, 4, 13, 4)
    );
    private static final Map<Direction, VoxelShape> SHAPES_PART1 = VoxelShapeHelper.rotateHorizontal(SHAPE_PART1);

    private static final VoxelShape SHAPE_PART2 = VoxelShapeHelper.combine(
            SHAPE_TOP,
            box(12, 0, 12, 14, 13, 14)
    );
    private static final Map<Direction, VoxelShape> SHAPES_PART2 = VoxelShapeHelper.rotateHorizontal(SHAPE_PART2);

    private static final VoxelShape SHAPE_PART3 = VoxelShapeHelper.combine(
            SHAPE_TOP,
            box(2, 0, 12, 4, 13, 14)
    );
    private static final Map<Direction, VoxelShape> SHAPES_PART3 = VoxelShapeHelper.rotateHorizontal(SHAPE_PART3);

    public NordicTableLargeBlock(Properties properties) {
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

        return switch (part) {
            case 1 -> SHAPES_PART1.getOrDefault(facing, SHAPE_PART1);
            case 2 -> SHAPES_PART2.getOrDefault(facing, SHAPE_PART2);
            case 3 -> SHAPES_PART3.getOrDefault(facing, SHAPE_PART3);
            default -> SHAPES_PART0.getOrDefault(facing, SHAPE_PART0);
        };
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES_PART0.getOrDefault(direction, SHAPE_PART0);
    }
}
