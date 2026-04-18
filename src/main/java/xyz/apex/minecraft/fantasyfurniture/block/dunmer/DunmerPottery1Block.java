package xyz.apex.minecraft.fantasyfurniture.block.dunmer;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

public class DunmerPottery1Block extends FurnitureBlock {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            Block.box(5.5, 0, 1.5, 9.5, 3, 5.75),
            Block.box(6.75, 3, 2.75, 8.25, 4, 4.25),
            Block.box(6.15, 4, 2.15, 8.85, 5, 4.85),
            Block.box(3.1500000000000004, 7, 7.450000000000001, 5.85, 8, 10.15),
            Block.box(2.55, 0, 7.1, 6.549999999999999, 5, 11.1),
            Block.box(3.75, 5, 8.05, 5.25, 7, 9.55),
            Block.box(9.5, 5, 8.5, 12.5, 6, 11.5),
            Block.box(10, 4, 9, 12, 5, 11),
            Block.box(8, 0, 7, 14, 4, 13)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public DunmerPottery1Block(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES.getOrDefault(direction, SHAPE);
    }
}
