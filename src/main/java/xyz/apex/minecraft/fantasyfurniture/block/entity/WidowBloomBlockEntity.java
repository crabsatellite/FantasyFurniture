package xyz.apex.minecraft.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;

public class WidowBloomBlockEntity extends BlockEntity {
    public WidowBloomBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VENTHYR_WIDOW_BLOOM.get(), pos, state);
    }
}
