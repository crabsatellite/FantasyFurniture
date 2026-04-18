package xyz.apex.minecraft.fantasyfurniture.block.necrolord;

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

public class NecrolordCandelabraBlock extends FurnitureBlock {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            Block.box(6, 0, 6, 10, 2, 10),
            Block.box(7, 2, 7, 9, 5, 9),
            Block.box(1.25, 5, 6.5, 14.75, 12, 9.5),
            Block.box(12.25, 12, 7, 14.25, 15, 9),
            Block.box(1.75, 12, 7, 3.75, 15, 9),
            Block.box(7, 12, 7, 9, 16, 9)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public NecrolordCandelabraBlock(Properties properties) {
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
        double y = pos.getY() + 0.5 + 0.6;
        double z = pos.getZ() + 0.5;

        Direction facing = state.getValue(FACING).getClockWise();
        double stepX = facing.getStepX();
        double stepZ = facing.getStepZ();

        spawnFlame(level, x, y, z);
        spawnFlame(level, x + stepX * 0.3, y - 0.05, z + stepZ * 0.3);
        spawnFlame(level, x - stepX * 0.3, y - 0.05, z - stepZ * 0.3);
    }

    private static void spawnFlame(Level level, double x, double y, double z) {
        level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 0, 0, 0);
        level.addParticle(ParticleTypes.SMOKE, x, y, z, 0, 0, 0);
    }
}
