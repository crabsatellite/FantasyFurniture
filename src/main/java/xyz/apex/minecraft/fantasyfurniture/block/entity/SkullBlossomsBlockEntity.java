package xyz.apex.minecraft.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;

public class SkullBlossomsBlockEntity extends BlockEntity {
    public SkullBlossomsBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BONE_SKULL_BLOSSOMS.get(), pos, state);
    }
}
