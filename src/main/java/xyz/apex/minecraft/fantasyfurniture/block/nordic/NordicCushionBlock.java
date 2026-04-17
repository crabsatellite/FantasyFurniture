package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.SeatFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicCushionBlock extends SeatFurnitureBlock {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(2, 0, 2, 4, 2, 4),
            box(2, 0, 12, 4, 2, 14),
            box(12, 0, 12, 14, 2, 14),
            box(12, 0, 2, 14, 2, 4),
            box(2, 5, 2.25, 14, 7, 13.75),
            box(1.75, 4, 2, 14.25, 5, 14),
            box(2, 2, 2.5, 4, 4, 4.5),
            box(12, 2, 2.5, 14, 4, 4.5),
            box(12, 2, 11.5, 14, 4, 13.5),
            box(2, 2, 11.5, 4, 4, 13.5),
            box(2.5, 2.5, 4.5, 3.5, 3.5, 11.5),
            box(12.5, 2.5, 4.5, 13.5, 3.5, 11.5)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public NordicCushionBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES.getOrDefault(direction, SHAPE);
    }
}
