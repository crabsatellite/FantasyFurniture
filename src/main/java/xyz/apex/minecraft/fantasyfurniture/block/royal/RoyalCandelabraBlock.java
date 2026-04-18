package xyz.apex.minecraft.fantasyfurniture.block.royal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

public class RoyalCandelabraBlock extends FurnitureBlock {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            Block.box(6, 0, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 14, 9),
            Block.box(3, 5, 7, 13, 7, 9),
            Block.box(11, 7, 7, 13, 13, 9),
            Block.box(3, 7, 7, 5, 13, 9),
            Block.box(2.5, 9, 6.5, 5.5, 10, 9.5),
            Block.box(6.5, 10, 6.5, 9.5, 11, 9.5),
            Block.box(10.5, 9, 6.5, 13.5, 10, 9.5)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public RoyalCandelabraBlock(Properties properties) {
        super(properties.lightLevel(state -> state.getValue(WATERLOGGED) ? 0 : 12));
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES.getOrDefault(direction, SHAPE);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rng) {
        if (state.getValue(WATERLOGGED)) return;

        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5 + 0.45 + 0.075;
        double z = pos.getZ() + 0.5;

        Direction facing = state.getValue(FACING).getClockWise();
        double stepX = facing.getStepX();
        double stepZ = facing.getStepZ();

        spawnFlame(level, x, y, z);
        spawnFlame(level, x + stepX * 0.25, y - 0.05, z + stepZ * 0.25);
        spawnFlame(level, x - stepX * 0.25, y - 0.05, z - stepZ * 0.25);
    }

    private static void spawnFlame(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SMALL_FLAME, x, y, z, 0, 0, 0);
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0, 0);
    }
}
