package xyz.apex.minecraft.fantasyfurniture.block.nordic;

import net.minecraft.core.Direction;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.ContainerFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

import static net.minecraft.world.level.block.Block.box;

public class NordicDrawerBlock extends ContainerFurnitureBlock {
    private static final VoxelShape SHAPE = VoxelShapeHelper.combine(
            box(1, 0, 1, 15, 13, 15),
            box(0, 13, 0, 16, 16, 16)
    );
    private static final Map<Direction, VoxelShape> SHAPES = VoxelShapeHelper.rotateHorizontal(SHAPE);

    public NordicDrawerBlock(Properties properties) {
        super(properties, ModBlockEntities.SMALL_CONTAINER);
    }

    @Override
    protected VoxelShape getShapeForDirection(Direction direction) {
        return SHAPES.getOrDefault(direction, SHAPE);
    }
}
