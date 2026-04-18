package xyz.apex.minecraft.fantasyfurniture.block.venthyr;

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

public class VenthyrTeaCupsBlock extends StackedFurnitureBlock {
    public static final IntegerProperty TEA_CUPS = IntegerProperty.create("tea_cups", 0, 2);

    private static final VoxelShape SHAPE_0 = Block.box(6, 0, 6, 10.5, 5, 10);
    private static final VoxelShape SHAPE_1 = VoxelShapeHelper.combine(
            Block.box(9, 0, 4, 13.5, 5, 8),
            Block.box(2.5, 0, 7, 8.25, 5, 12.75)
    );
    private static final VoxelShape SHAPE_2 = VoxelShapeHelper.combine(
            Block.box(10, 0, 6, 14.5, 5, 10),
            Block.box(3.5, 0, 9, 9.25, 5, 14.75),
            Block.box(1.75, 0, 1.5, 7.25, 5, 6.75)
    );

    private static final Map<Direction, VoxelShape> SHAPES_0 = VoxelShapeHelper.rotateHorizontal(SHAPE_0);
    private static final Map<Direction, VoxelShape> SHAPES_1 = VoxelShapeHelper.rotateHorizontal(SHAPE_1);
    private static final Map<Direction, VoxelShape> SHAPES_2 = VoxelShapeHelper.rotateHorizontal(SHAPE_2);

    public VenthyrTeaCupsBlock(Properties properties) {
        super(properties);
    }

    @Override
    public IntegerProperty getStackSizeProperty() {
        return TEA_CUPS;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (state.getValue(TEA_CUPS)) {
            case 2 -> SHAPES_2.getOrDefault(direction, SHAPE_2);
            case 1 -> SHAPES_1.getOrDefault(direction, SHAPE_1);
            default -> SHAPES_0.getOrDefault(direction, SHAPE_0);
        };
    }
}
