package xyz.apex.minecraft.fantasyfurniture.block.royal;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import xyz.apex.minecraft.fantasyfurniture.block.StackedFurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.util.VoxelShapeHelper;

import java.util.List;
import java.util.Map;

public class RoyalPlatterBlock extends StackedFurnitureBlock {
    public static final IntegerProperty PLATTER = IntegerProperty.create("platter", 0, 15);

    private static final List<Map<Direction, VoxelShape>> SHAPES = buildShapes();

    public RoyalPlatterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public IntegerProperty getStackSizeProperty() {
        return PLATTER;
    }

    @Override
    protected VoxelShape getShape(BlockState state, net.minecraft.world.level.BlockGetter level,
                                  net.minecraft.core.BlockPos pos,
                                  net.minecraft.world.phys.shapes.CollisionContext context) {
        Direction direction = state.getValue(FACING);
        int count = state.getValue(PLATTER) % SHAPES.size();
        Map<Direction, VoxelShape> shapes = SHAPES.get(count);
        return shapes.getOrDefault(direction, shapes.values().iterator().next());
    }

    private static List<Map<Direction, VoxelShape>> buildShapes() {
        java.util.ArrayList<Map<Direction, VoxelShape>> list = new java.util.ArrayList<>(16);
        for (int i = 0; i < 16; i++) {
            VoxelShape shape = Block.box(2, 0, 2, 14, i + 1, 14);
            list.add(VoxelShapeHelper.rotateHorizontal(shape));
        }
        return java.util.Collections.unmodifiableList(list);
    }
}
