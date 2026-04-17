package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
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

public class NordicWallLightBlock extends FurnitureBlock {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(6, 5, 15, 10, 11, 16),
            box(6, 2, 8, 10, 15, 15)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public NordicWallLightBlock(Properties properties) {
        super(properties.lightLevel(state -> 14));
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

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Direction wallDir = state.getValue(FACING).getOpposite();
        double cx = pos.getX() + 0.5 + wallDir.getStepX() * 0.1;
        double cy = pos.getY() + 0.8;
        double cz = pos.getZ() + 0.5 + wallDir.getStepZ() * 0.1;

        if (random.nextDouble() < 0.1) {
            level.addParticle(ParticleTypes.SMOKE, cx, cy, cz, 0.0, 0.0, 0.0);
        }
        level.addParticle(ParticleTypes.FLAME, cx, cy, cz, 0.0, 0.0, 0.0);
    }
}
