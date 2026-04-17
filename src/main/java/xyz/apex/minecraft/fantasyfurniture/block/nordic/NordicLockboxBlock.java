package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.ContainerFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicLockboxBlock extends ContainerFurnitureBlock {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(2, 0, 3, 14, 9, 13),
            box(2, 9, 5, 14, 10, 11)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public NordicLockboxBlock(Properties properties) {
        super(properties, ModBlockEntities.SMALL_CONTAINER);
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES.getOrDefault(direction, SHAPE);
    }
}
