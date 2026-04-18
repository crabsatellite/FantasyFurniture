package xyz.apex.minecraft.fantasyfurniture.block.nordic;

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

public class NordicBoiledCremeTreatsBlock extends StackedFurnitureBlock {
    public static final IntegerProperty TREATS = IntegerProperty.create("treats", 0, 2);

    private static final VoxelShape SHAPE_0 = Block.box(6, 0, 6, 10, 2, 10);
    private static final VoxelShape SHAPE_1 = VoxelShapeHelper.combine(
            Block.box(9, 0, 8, 13, 2, 12),
            Block.box(2.4, 0, 3.4, 7.6, 2, 8.6)
    );
    private static final VoxelShape SHAPE_2 = VoxelShapeHelper.combine(
            Block.box(8, 0, 10, 12, 2, 14),
            Block.box(1.4, 0, 5.4, 6.6, 2, 10.6),
            Block.box(9.4, 0, 2.4, 14.6, 2, 7.6)
    );

    private static final Map<Direction, VoxelShape> SHAPES_0 = VoxelShapeHelper.rotateHorizontal(SHAPE_0);
    private static final Map<Direction, VoxelShape> SHAPES_1 = VoxelShapeHelper.rotateHorizontal(SHAPE_1);
    private static final Map<Direction, VoxelShape> SHAPES_2 = VoxelShapeHelper.rotateHorizontal(SHAPE_2);

    public NordicBoiledCremeTreatsBlock(Properties properties) {
        super(properties);
    }

    @Override
    public IntegerProperty getStackSizeProperty() {
        return TREATS;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (state.getValue(TREATS)) {
            case 2 -> SHAPES_2.getOrDefault(direction, SHAPE_2);
            case 1 -> SHAPES_1.getOrDefault(direction, SHAPE_1);
            default -> SHAPES_0.getOrDefault(direction, SHAPE_0);
        };
    }
}
