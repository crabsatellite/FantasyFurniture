package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.SeatFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil.MultiBlockType;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicChairBlock extends SeatFurnitureBlock {
    public static final MultiBlockType MULTI_BLOCK_TYPE = MultiBlockType.MB_1x2x1;
    public static final IntegerProperty PART = MULTI_BLOCK_TYPE.getPartProperty();

    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(2, 0, 2, 4, 4, 4),
            box(2.5, 4.5, 4.5, 3.5, 5.5, 11.5),
            box(12.5, 4.5, 4.5, 13.5, 5.5, 11.5),
            box(12, 0, 2, 14, 4, 4),
            box(2, 0, 12, 4, 4, 14),
            box(2, 7, 2, 14, 9, 14),
            box(2, 9, 13, 14, 16, 14),
            box(12, 0, 12, 14, 4, 14),
            box(2, 4, 11.5, 4, 7, 13.5),
            box(12, 4, 11.5, 14, 7, 13.5),
            box(2, 4, 2.5, 4, 7, 4.5),
            box(12, 4, 2.5, 14, 7, 4.5)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    // PART=1 (top block): backrest upper half only. Legacy y=16..25 translated down 16 → y=0..9.
    private static final VoxelShape SHAPE_TOP = box(2, 0, 13, 14, 9, 14);
    private static final Map<Direction, VoxelShape> SHAPES_TOP = VoxelShapeHelper.rotateHorizontal(SHAPE_TOP);

    public NordicChairBlock(Properties properties) {
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
    protected double getSeatYOffset() {
        // Chair seat voxel top face is at pixel Y=9 (box(2, 7, 2, 14, 9, 14)).
        return 9.0 / 16.0;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int part = state.getValue(PART);
        if (part == 0) {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }

        BlockPos origin = MULTI_BLOCK_TYPE.getOriginFromPart(pos, state.getValue(FACING), part);
        BlockState originState = level.getBlockState(origin);
        if (!originState.is(this)) {
            return InteractionResult.PASS;
        }
        return super.useWithoutItem(originState, level, origin, player, hitResult);
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
}
