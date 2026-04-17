package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicPaintingSmallBlock extends FurnitureBlock {
    private static final VoxelShape SHAPE = box(0, 0, 14, 16, 16, 16);
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public NordicPaintingSmallBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES.getOrDefault(direction, SHAPE);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction facing = state.getValue(FACING);
        BlockPos supportPos = pos.relative(facing.getOpposite());
        return level.getBlockState(supportPos).isFaceSturdy(level, supportPos, facing);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                     LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (!canSurvive(state, level, pos)) {
            return state.getValue(WATERLOGGED) ? Fluids.WATER.defaultFluidState().createLegacyBlock() : Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }
}
