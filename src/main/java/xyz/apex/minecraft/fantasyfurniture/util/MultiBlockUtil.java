package xyz.apex.minecraft.fantasyfurniture.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluids;
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureBlock;

import java.util.Locale;

public final class MultiBlockUtil {

    private MultiBlockUtil() {
    }

    public enum MultiBlockType {
        // Local offsets are defined in model-authoring coordinates: un-rotated = FACING=NORTH.
        // Models extend to -X when un-rotated; rotation maps them through the four horizontals.
        MB_2x1x1(new BlockPos[]{
                BlockPos.ZERO,
                new BlockPos(-1, 0, 0)
        }),
        MB_1x2x1(new BlockPos[]{
                BlockPos.ZERO,
                new BlockPos(0, 1, 0)
        }),
        MB_2x2x1(new BlockPos[]{
                BlockPos.ZERO,
                new BlockPos(-1, 0, 0),
                new BlockPos(0, 1, 0),
                new BlockPos(-1, 1, 0)
        }),
        MB_1x1x2(new BlockPos[]{
                BlockPos.ZERO,
                new BlockPos(0, 0, 1)
        }),
        MB_2x1x2(new BlockPos[]{
                BlockPos.ZERO,
                new BlockPos(-1, 0, 0),
                new BlockPos(0, 0, 1),
                new BlockPos(-1, 0, 1)
        });

        private final BlockPos[] localPositions;
        private final IntegerProperty partProperty;

        MultiBlockType(BlockPos[] localPositions) {
            this.localPositions = localPositions;
            this.partProperty = IntegerProperty.create("part", 0, localPositions.length - 1);
        }

        public int getPartCount() {
            return localPositions.length;
        }

        public IntegerProperty getPartProperty() {
            return partProperty;
        }

        public BlockPos[] getLocalPositions() {
            return localPositions.clone();
        }

        public BlockPos[] getWorldPositions(BlockPos origin, Direction facing) {
            BlockPos[] result = new BlockPos[localPositions.length];

            for (int i = 0; i < localPositions.length; i++) {
                result[i] = rotateToWorld(localPositions[i], origin, facing);
            }

            return result;
        }

        public BlockPos getWorldPos(BlockPos origin, Direction facing, int partIndex) {
            return rotateToWorld(localPositions[partIndex], origin, facing);
        }

        public BlockPos getOriginFromPart(BlockPos partPos, Direction facing, int partIndex) {
            BlockPos localOffset = localPositions[partIndex];
            BlockPos rotatedOffset = rotateToWorld(localOffset, BlockPos.ZERO, facing);
            return partPos.subtract(rotatedOffset);
        }

        private static BlockPos rotateToWorld(BlockPos local, BlockPos origin, Direction facing) {
            int x = local.getX();
            int y = local.getY();
            int z = local.getZ();

            // Rotation matches Minecraft blockstate y-rotation (θ=-y around Y axis):
            // y=0 NORTH identity, y=90 EAST (x,z)→(-z,x), y=180 SOUTH (-x,-z), y=270 WEST (z,-x).
            return switch (facing) {
                case NORTH -> origin.offset(x, y, z);
                case EAST -> origin.offset(-z, y, x);
                case SOUTH -> origin.offset(-x, y, -z);
                case WEST -> origin.offset(z, y, -x);
                default -> origin.offset(x, y, z);
            };
        }
    }

    public static boolean canPlace(Level level, BlockPos origin, Direction facing, MultiBlockType type) {
        BlockPos[] worldPositions = type.getWorldPositions(origin, facing);

        for (BlockPos pos : worldPositions) {
            BlockState existing = level.getBlockState(pos);

            if (!existing.canBeReplaced()) {
                return false;
            }
        }

        return true;
    }

    public static void placeMultiBlock(Level level, BlockPos origin, BlockState baseState, Direction facing, MultiBlockType type) {
        IntegerProperty partProp = type.getPartProperty();
        BlockPos[] worldPositions = type.getWorldPositions(origin, facing);

        for (int i = 0; i < worldPositions.length; i++) {
            BlockPos pos = worldPositions[i];
            boolean waterlogged = level.getFluidState(pos).getType() == Fluids.WATER;

            BlockState state = baseState
                    .setValue(FurnitureBlock.FACING, facing)
                    .setValue(FurnitureBlock.WATERLOGGED, waterlogged)
                    .setValue(partProp, i);

            level.setBlock(pos, state, Block.UPDATE_ALL);

            if (waterlogged) {
                level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
            }
        }
    }

    public static void removeMultiBlock(LevelAccessor level, BlockPos brokenPos, BlockState brokenState, MultiBlockType type) {
        IntegerProperty partProp = type.getPartProperty();
        int partIndex = brokenState.getValue(partProp);
        Direction facing = brokenState.getValue(FurnitureBlock.FACING);

        BlockPos origin = type.getOriginFromPart(brokenPos, facing, partIndex);
        BlockPos[] worldPositions = type.getWorldPositions(origin, facing);

        for (BlockPos pos : worldPositions) {
            if (pos.equals(brokenPos)) {
                continue;
            }

            BlockState state = level.getBlockState(pos);

            if (state.is(brokenState.getBlock()) && state.hasProperty(partProp)) {
                level.removeBlock(pos, false);
            }
        }
    }
}
