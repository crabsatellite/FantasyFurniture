package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil;
import xyz.apex.minecraft.fantasyfurniture.util.MultiBlockUtil.MultiBlockType;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Optional;

import static net.minecraft.world.level.block.Block.box;

public class NordicBedDoubleBlock extends FurnitureBlock {
    public static final MultiBlockType MULTI_BLOCK_TYPE = MultiBlockType.MB_2x1x2;
    public static final IntegerProperty PART = MULTI_BLOCK_TYPE.getPartProperty();
    public static final BooleanProperty OCCUPIED = BlockStateProperties.OCCUPIED;

    // Legacy bedDoubleShape spans world pixels x=-16..16, y=0..16, z=0..32 (2 wide, 2 long).
    // Multi-block layout MB_2x1x2 (offsets with facing=NORTH, un-rotated):
    //   P0 at (0,0,0)  — head-right (x=0..16, z=0..16 of the bed)
    //   P1 at (-1,0,0) — head-left  (x=-16..0, z=0..16)
    //   P2 at (0,0,1)  — foot-right (x=0..16, z=16..32)
    //   P3 at (-1,0,1) — foot-left  (x=-16..0, z=16..32)
    // Each part's shape = legacy boxes clipped to that part's world cube,
    // translated into the part's own block-local coordinates.

    // Part 0: head-right
    private static final VoxelShape SHAPE_P0 = VoxelShapeHelper.combine(
            box(0, 3, 2, 16, 5, 16),
            box(0, 5, 2, 14, 8, 16),
            box(0, 3, 0, 16, 5, 2),
            box(14, 0, 0, 16, 8, 2),
            box(8, 12, 0, 16, 14, 2),
            box(0, 12, 0, 10, 16, 2),
            box(0, 5, 0, 15, 12, 2)
    );
    private static final Map<Direction, VoxelShape> SHAPES_P0 = VoxelShapeHelper.rotateHorizontal(SHAPE_P0);

    // Part 1: head-left  (world x=-16..0  → local x = world_x + 16)
    private static final VoxelShape SHAPE_P1 = VoxelShapeHelper.combine(
            box(0, 3, 2, 16, 5, 16),
            box(2, 5, 2, 16, 8, 16),
            box(0, 3, 0, 16, 5, 2),
            box(0, 0, 0, 2, 8, 2),
            box(0, 12, 0, 8, 14, 2),
            box(6, 12, 0, 16, 16, 2),
            box(1, 5, 0, 16, 12, 2)
    );
    private static final Map<Direction, VoxelShape> SHAPES_P1 = VoxelShapeHelper.rotateHorizontal(SHAPE_P1);

    // Part 2: foot-right (world z=16..32 → local z = world_z - 16)
    private static final VoxelShape SHAPE_P2 = VoxelShapeHelper.combine(
            box(0, 3, 0, 16, 5, 14),
            box(0, 5, 0, 14, 8, 14),
            box(0, 5, 14, 15, 12, 16),
            box(0, 3, 14, 16, 5, 16),
            box(14, 0, 14, 16, 8, 16),
            box(8, 12, 14, 16, 14, 16),
            box(0, 12, 14, 10, 16, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES_P2 = VoxelShapeHelper.rotateHorizontal(SHAPE_P2);

    // Part 3: foot-left
    private static final VoxelShape SHAPE_P3 = VoxelShapeHelper.combine(
            box(0, 3, 0, 16, 5, 14),
            box(2, 5, 0, 16, 8, 14),
            box(1, 5, 14, 16, 12, 16),
            box(0, 3, 14, 16, 5, 16),
            box(0, 0, 14, 2, 8, 16),
            box(0, 12, 14, 8, 14, 16),
            box(6, 12, 14, 16, 16, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES_P3 = VoxelShapeHelper.rotateHorizontal(SHAPE_P3);

    public NordicBedDoubleBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState()
                .setValue(PART, 0)
                .setValue(OCCUPIED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART, OCCUPIED);
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

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.CONSUME;
        }

        Direction facing = state.getValue(FACING);
        int part = state.getValue(PART);
        BlockPos origin = MULTI_BLOCK_TYPE.getOriginFromPart(pos, facing, part);

        if (!level.dimensionType().bedWorks()) {
            BlockPos[] positions = MULTI_BLOCK_TYPE.getWorldPositions(origin, facing);
            for (BlockPos p : positions) {
                level.removeBlock(p, false);
            }
            Vec3 center = origin.getCenter();
            level.explode(null, level.damageSources().badRespawnPointExplosion(center),
                    null, center.x, center.y, center.z, 5.0F, true, Level.ExplosionInteraction.BLOCK);
            return InteractionResult.SUCCESS;
        }

        BlockState originState = level.getBlockState(origin);
        if (originState.is(this) && originState.getValue(OCCUPIED)) {
            player.displayClientMessage(Component.translatable("block.minecraft.bed.occupied"), true);
            return InteractionResult.SUCCESS;
        }

        player.startSleepInBed(origin).ifLeft(problem -> {
            if (problem.getMessage() != null) {
                player.displayClientMessage(problem.getMessage(), true);
            }
        });

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean isBed(BlockState state, BlockGetter level, BlockPos pos, LivingEntity sleeper) {
        return true;
    }

    @Override
    public Direction getBedDirection(BlockState state, LevelReader level, BlockPos pos) {
        return state.getValue(FACING);
    }

    @Override
    public void setBedOccupied(BlockState state, Level level, BlockPos pos, LivingEntity sleeper, boolean occupied) {
        Direction facing = state.getValue(FACING);
        int part = state.getValue(PART);
        BlockPos origin = MULTI_BLOCK_TYPE.getOriginFromPart(pos, facing, part);

        for (BlockPos partPos : MULTI_BLOCK_TYPE.getWorldPositions(origin, facing)) {
            BlockState partState = level.getBlockState(partPos);
            if (partState.is(this)) {
                level.setBlock(partPos, partState.setValue(OCCUPIED, occupied), Block.UPDATE_ALL);
            }
        }
    }

    @Override
    public Optional<ServerPlayer.RespawnPosAngle> getRespawnPosition(BlockState state, EntityType<?> type, LevelReader level, BlockPos pos, float orientation) {
        Direction facing = state.getValue(FACING);
        int part = state.getValue(PART);
        BlockPos origin = MULTI_BLOCK_TYPE.getOriginFromPart(pos, facing, part);
        return BedBlock.findStandUpPosition(type, level, origin, facing, orientation)
                .map(vec -> ServerPlayer.RespawnPosAngle.of(vec, origin));
    }
}
