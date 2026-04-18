package xyz.apex.minecraft.fantasyfurniture.block.royal;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.StackedFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

public class RoyalChalicesBlock extends StackedFurnitureBlock {
    public static final IntegerProperty CHALICES = IntegerProperty.create("chalices", 0, 2);

    private static final VoxelShape SHAPE_0 = Block.box(6.5, 0, 6.5, 9.5, 8, 9.5);
    private static final VoxelShape SHAPE_1 = VoxelShapeHelper.combine(
            Block.box(9.5, 0, 5.5, 12.5, 8, 8.5),
            Block.box(2.5, 0, 8.5, 5.5, 8, 11.5)
    );
    private static final VoxelShape SHAPE_2 = VoxelShapeHelper.combine(
            Block.box(10.5, 0, 6.5, 13.5, 8, 9.5),
            Block.box(2.5, 0, 9.5, 5.5, 8, 12.5),
            Block.box(5.5, 0, 2.5, 8.5, 8, 5.5)
    );

    private static final Map<Direction, VoxelShape> SHAPES_0 = VoxelShapeHelper.rotateHorizontal(SHAPE_0);
    private static final Map<Direction, VoxelShape> SHAPES_1 = VoxelShapeHelper.rotateHorizontal(SHAPE_1);
    private static final Map<Direction, VoxelShape> SHAPES_2 = VoxelShapeHelper.rotateHorizontal(SHAPE_2);

    public RoyalChalicesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public IntegerProperty getStackSizeProperty() {
        return CHALICES;
    }

    @Override
    protected VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level,
                                  net.minecraft.core.BlockPos pos,
                                  net.minecraft.world.phys.shapes.CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (state.getValue(CHALICES)) {
            case 2 -> SHAPES_2.getOrDefault(direction, SHAPE_2);
            case 1 -> SHAPES_1.getOrDefault(direction, SHAPE_1);
            default -> SHAPES_0.getOrDefault(direction, SHAPE_0);
        };
    }
}
