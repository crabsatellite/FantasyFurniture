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

public class NordicMeadBottlesBlock extends StackedFurnitureBlock {
    public static final IntegerProperty BOTTLES = IntegerProperty.create("bottles", 0, 2);

    private static final VoxelShape SHAPE_0 = Block.box(6.5, 0, 6.5, 9.5, 10.5, 9.5);
    private static final VoxelShape SHAPE_1 = VoxelShapeHelper.combine(
            Block.box(9, 0, 9, 12, 10.5, 12),
            Block.box(3.8, 0, 3.96, 7.8, 10.5, 7.96)
    );
    private static final VoxelShape SHAPE_2 = VoxelShapeHelper.combine(
            Block.box(7, 0, 10, 10, 10.5, 13),
            Block.box(1.8, 0, 4.96, 5.8, 10.5, 8.96),
            Block.box(10.46, 0, 2.46, 14.46, 10.5, 6.46)
    );

    private static final Map<Direction, VoxelShape> SHAPES_0 = VoxelShapeHelper.rotateHorizontal(SHAPE_0);
    private static final Map<Direction, VoxelShape> SHAPES_1 = VoxelShapeHelper.rotateHorizontal(SHAPE_1);
    private static final Map<Direction, VoxelShape> SHAPES_2 = VoxelShapeHelper.rotateHorizontal(SHAPE_2);

    public NordicMeadBottlesBlock(Properties properties) {
        super(properties);
    }

    @Override
    public IntegerProperty getStackSizeProperty() {
        return BOTTLES;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return switch (state.getValue(BOTTLES)) {
            case 2 -> SHAPES_2.getOrDefault(direction, SHAPE_2);
            case 1 -> SHAPES_1.getOrDefault(direction, SHAPE_1);
            default -> SHAPES_0.getOrDefault(direction, SHAPE_0);
        };
    }
}
