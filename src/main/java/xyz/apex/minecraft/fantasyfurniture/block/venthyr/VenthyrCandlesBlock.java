package xyz.apex.minecraft.fantasyfurniture.block.venthyr;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class VenthyrCandlesBlock extends FurnitureBlock {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(5, 0, 4, 8, 3, 7),
            box(9, 0, 5, 12, 6, 8),
            box(8, 0, 10, 11, 8, 13),
            box(4, 0, 9, 7, 5, 12)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public VenthyrCandlesBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(LIT);
    }

    protected ParticleOptions getFlameParticle() {
        return ParticleTypes.SMALL_FLAME;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        return SHAPES.getOrDefault(facing, SHAPE);
    }

    @Override
    public void onProjectileHit(Level level, BlockState state, BlockHitResult result, Projectile projectile) {
        super.onProjectileHit(level, state, result, projectile);

        if (!level.isClientSide && projectile.isOnFire() && canBeLit(state)) {
            level.setBlock(result.getBlockPos(), state.setValue(LIT, true), UPDATE_ALL_IMMEDIATE);
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rng) {
        if (!state.getValue(WATERLOGGED) && state.getValue(LIT)) {
            spawnLightParticles(state, level, pos, rng);
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        if (player.getAbilities().mayBuild && state.getValue(LIT)) {
            extinguish(player, state, level, pos);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    private boolean canBeLit(BlockState state) {
        if (state.getValue(WATERLOGGED)) return false;
        return !state.getValue(LIT);
    }

    private void spawnLightParticles(BlockState state, Level level, BlockPos pos, RandomSource rng) {
        Direction facing = state.getValue(FACING);
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        float smokeChance = rng.nextFloat();
        double[][] offsets = getFlameOffsets(facing);

        for (double[] offset : offsets) {
            onLightParticle(level, x + offset[0], y + offset[1], z + offset[2], smokeChance);
        }

        if (smokeChance < .17F) {
            level.playLocalSound(x, y, z, SoundEvents.CANDLE_AMBIENT, SoundSource.BLOCKS, 1F + rng.nextFloat(), rng.nextFloat() * .7F + .3F, false);
        }
    }

    private static double[][] getFlameOffsets(Direction facing) {
        return switch (facing) {
            case EAST -> new double[][]{
                    {.65, .313, .4}, {.6, .5, .65}, {.35, .45, .35}, {.3, .65, .6}
            };
            case SOUTH -> new double[][]{
                    {.6, .313, .65}, {.35, .5, .6}, {.4, .65, .3}, {.65, .45, .3}
            };
            case WEST -> new double[][]{
                    {.35, .313, .6}, {.4, .5, .35}, {.7, .65, .4}, {.65, .45, .65}
            };
            default -> new double[][]{
                    {.4, .313, .35}, {.65, .5, .4}, {.375, .45, .65}, {.6, .65, .725}
            };
        };
    }

    private void onLightParticle(Level level, double pX, double pY, double pZ, float chance) {
        level.addParticle(getFlameParticle(), pX, pY, pZ, 0D, 0D, 0D);
        if (chance < .3F) {
            level.addParticle(ParticleTypes.SMOKE, pX, pY, pZ, 0D, 0D, 0D);
        }
    }

    public static void extinguish(@Nullable Player player, BlockState state, LevelAccessor level, BlockPos pos) {
        if (state.getBlock() instanceof VenthyrCandlesBlock candle) {
            level.setBlock(pos, state.setValue(LIT, false), UPDATE_ALL_IMMEDIATE);
            double x = pos.getX();
            double y = pos.getY();
            double z = pos.getZ();
            Direction facing = state.getValue(FACING);
            double[][] offsets = getFlameOffsets(facing);
            for (double[] offset : offsets) {
                level.addParticle(ParticleTypes.SMOKE, x + offset[0], y + offset[1], z + offset[2], 0D, 0D, 0D);
            }
            level.playSound(null, pos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1F, 1F);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
        }
    }
}
