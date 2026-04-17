package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.SeatFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicStoolBlock extends SeatFurnitureBlock {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(2, 0, 2, 4, 3, 4),
            box(12, 0, 12, 14, 3, 14),
            box(12, 0, 2, 14, 3, 4),
            box(2, 0, 12, 4, 3, 14),
            box(2, 3, 11.5, 4, 5, 13.5),
            box(12, 3, 11.5, 14, 5, 13.5),
            box(12, 3, 2.5, 14, 5, 4.5),
            box(1.5, 5, 1.75, 14.5, 7, 14.25),
            box(2, 3, 2.5, 4, 5, 4.5),
            box(2.5, 3.5, 4.5, 3.5, 4.5, 11.5),
            box(12.5, 3.5, 4.5, 13.5, 4.5, 11.5)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public NordicStoolBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES.getOrDefault(direction, SHAPE);
    }
}
