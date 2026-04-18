package xyz.apex.minecraft.fantasyfurniture.block.bone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.StackedFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.Map;

public class BoneChalicesBlock extends StackedFurnitureBlock {
    public static final IntegerProperty CHALICES = IntegerProperty.create("chalices", 0, 2);

    private static final VoxelShape SHAPE_0 = VoxelShapeHelper.combine(
            Block.box(6.5, 0, 6.5, 9.5, 1, 9.5),
            Block.box(7.25, 1, 7.25, 8.75, 4, 8.75),
            Block.box(6, 4, 6, 10, 8, 10)
    );
    private static final VoxelShape SHAPE_1 = VoxelShapeHelper.combine(
            Block.box(3.5, 0, 8.5, 6.5, 1, 11.5),
            Block.box(4.25, 1, 9.25, 5.75, 4, 10.75),
            Block.box(3, 4, 8, 7, 8, 12),
            Block.box(8.25, 4, 4.25, 13.75, 8, 9.75),
            Block.box(9, 0, 5, 13, 1, 9),
            Block.box(10, 1, 6, 12, 4, 8)
    );
    private static final VoxelShape SHAPE_2 = VoxelShapeHelper.combine(
            Block.box(4.5, 0, 10.5, 7.5, 1, 13.5),
            Block.box(5.25, 1, 11.25, 6.75, 4, 12.75),
            Block.box(4, 4, 10, 8, 8, 14),
            Block.box(9.25, 4, 5.25, 14.75, 8, 10.75),
            Block.box(10, 0, 6, 14, 1, 10),
            Block.box(11, 1, 7, 13, 4, 9),
            Block.box(1.25, 4, 2.25, 6.75, 8, 7.75),
            Block.box(2, 0, 3, 6, 1, 7),
            Block.box(3, 1, 4, 5, 4, 6)
    );

    private static final Map<Direction, VoxelShape> SHAPES_0 = VoxelShapeHelper.rotateHorizontal(SHAPE_0);
    private static final Map<Direction, VoxelShape> SHAPES_1 = VoxelShapeHelper.rotateHorizontal(SHAPE_1);
    private static final Map<Direction, VoxelShape> SHAPES_2 = VoxelShapeHelper.rotateHorizontal(SHAPE_2);

    public BoneChalicesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public IntegerProperty getStackSizeProperty() {
        return CHALICES;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (state.getValue(CHALICES)) {
            case 2 -> SHAPES_2.getOrDefault(direction, SHAPE_2);
            case 1 -> SHAPES_1.getOrDefault(direction, SHAPE_1);
            default -> SHAPES_0.getOrDefault(direction, SHAPE_0);
        };
    }
}
