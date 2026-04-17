package xyz.apex.minecraft.fantasyfurniture.util;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.EnumMap;
import java.util.Map;

public final class VoxelShapeHelper {

    private VoxelShapeHelper() {
    }

    public static VoxelShape combine(VoxelShape... shapes) {
        if (shapes.length == 0) {
            return Shapes.empty();
        }

        VoxelShape result = shapes[0];

        for (int i = 1; i < shapes.length; i++) {
            result = Shapes.or(result, shapes[i]);
        }

        return result;
    }

    public static Map<Direction, VoxelShape> rotateHorizontal(VoxelShape shape) {
        Map<Direction, VoxelShape> rotations = new EnumMap<>(Direction.class);

        rotations.put(Direction.NORTH, shape);
        rotations.put(Direction.EAST, rotateShape(shape, 90));
        rotations.put(Direction.SOUTH, rotateShape(shape, 180));
        rotations.put(Direction.WEST, rotateShape(shape, 270));

        return rotations;
    }

    private static VoxelShape rotateShape(VoxelShape shape, int degrees) {
        VoxelShape[] result = { Shapes.empty() };

        for (AABB aabb : shape.toAabbs()) {
            double x1 = aabb.minX * 16.0;
            double y1 = aabb.minY * 16.0;
            double z1 = aabb.minZ * 16.0;
            double x2 = aabb.maxX * 16.0;
            double y2 = aabb.maxY * 16.0;
            double z2 = aabb.maxZ * 16.0;

            double newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ;

            switch (degrees) {
                // Minecraft blockstate y=90 (EAST) → θ=-90° around Y.
                // For a vertex (x, z) centered at (8, 8): (x, z) → (16-z, x).
                case 90 -> {
                    newMinX = 16.0 - z2;
                    newMinY = y1;
                    newMinZ = x1;
                    newMaxX = 16.0 - z1;
                    newMaxY = y2;
                    newMaxZ = x2;
                }
                case 180 -> {
                    newMinX = 16.0 - x2;
                    newMinY = y1;
                    newMinZ = 16.0 - z2;
                    newMaxX = 16.0 - x1;
                    newMaxY = y2;
                    newMaxZ = 16.0 - z1;
                }
                // y=270 (WEST) → θ=-270° ≡ +90°. (x, z) → (z, 16-x).
                case 270 -> {
                    newMinX = z1;
                    newMinY = y1;
                    newMinZ = 16.0 - x2;
                    newMaxX = z2;
                    newMaxY = y2;
                    newMaxZ = 16.0 - x1;
                }
                default -> throw new IllegalArgumentException("Unsupported rotation: " + degrees);
            }

            result[0] = Shapes.or(result[0], Shapes.box(
                    Math.min(newMinX, newMaxX) / 16.0,
                    Math.min(newMinY, newMaxY) / 16.0,
                    Math.min(newMinZ, newMaxZ) / 16.0,
                    Math.max(newMinX, newMaxX) / 16.0,
                    Math.max(newMinY, newMaxY) / 16.0,
                    Math.max(newMinZ, newMaxZ) / 16.0
            ));
        }

        return result[0];
    }
}
