package xyz.apex.minecraft.fantasyfurniture.block.dunmer;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

public class DunmerPottery0Block extends FurnitureBlock {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            Block.box(2, 0, 2, 7, 3, 7),
            Block.box(3.5, 3, 3.5, 5.5, 4, 5.5),
            Block.box(3, 4, 3, 6, 5, 6),
            Block.box(7, 0, 6, 15, 6, 14),
            Block.box(9.5, 6, 8.5, 12.5, 8, 11.5),
            Block.box(9, 8, 8, 13, 9, 12)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public DunmerPottery0Block(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES.getOrDefault(direction, SHAPE);
    }
}
