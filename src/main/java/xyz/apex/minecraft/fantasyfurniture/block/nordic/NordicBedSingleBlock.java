package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicBedSingleBlock extends BedBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    // Bed shapes are defined from SOUTH perspective and flipped
    // HEAD part (the pillow end). Headboard at far end (local z=14..16).
    private static final VoxelShape SHAPE_HEAD = VoxelShapeHelper.combine(
            box(0, 0, 14, 16, 14, 16),
            box(0, 3, 0, 16, 5, 14),
            box(1, 5, 0, 15, 8, 14)
    );
    private static final Map<Direction, VoxelShape> SHAPES_HEAD = VoxelShapeHelper.rotateHorizontal(SHAPE_HEAD);

    // FOOT part
    private static final VoxelShape SHAPE_FOOT = VoxelShapeHelper.combine(
            box(0, 0, 0, 16, 14, 2),
            box(0, 3, 2, 16, 5, 16),
            box(1, 5, 2, 15, 8, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES_FOOT = VoxelShapeHelper.rotateHorizontal(SHAPE_FOOT);

    public NordicBedSingleBlock(Properties properties) {
        super(DyeColor.RED, properties);

        registerDefaultState(defaultBlockState()
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state == null) return null;

        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        return state.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        // Vanilla BedBlock.setPlacedBy blindly copies FOOT state (PART→HEAD) to head-pos, which
        // includes the FOOT's WATERLOGGED value. We must sync HEAD's WATERLOGGED to head-pos's
        // actual fluid in both directions (FOOT-water/HEAD-air AND FOOT-air/HEAD-water).
        BlockPos headPos = pos.relative(state.getValue(FACING));
        boolean headWater = !level.isClientSide() && level.getFluidState(headPos).getType() == Fluids.WATER;
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide()) {
            BlockState headState = level.getBlockState(headPos);
            if (headState.is(this) && headState.getValue(WATERLOGGED) != headWater) {
                level.setBlock(headPos, headState.setValue(WATERLOGGED, headWater), Block.UPDATE_ALL);
                if (headWater) {
                    level.scheduleTick(headPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
                }
            }
        }
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
        BedPart part = state.getValue(PART);

        if (part == BedPart.HEAD) {
            return SHAPES_HEAD.getOrDefault(facing, SHAPE_HEAD);
        }

        return SHAPES_FOOT.getOrDefault(facing, SHAPE_FOOT);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
