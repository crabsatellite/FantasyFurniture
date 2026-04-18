package xyz.apex.minecraft.fantasyfurniture.block.royal;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.SeatFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class RoyalFloorCushionBlock extends SeatFurnitureBlock {
    private static final VoxelShape SHAPE = box(2, 0, 2, 14, 3, 14);
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public RoyalFloorCushionBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES.getOrDefault(direction, SHAPE);
    }

    @Override
    protected double getSeatYOffset() {
        return 0.0;
    }
}
