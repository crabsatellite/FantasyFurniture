package xyz.apex.minecraft.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.menu.LargeContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;

public class LargeContainerBlockEntity extends FurnitureContainerBlockEntity {
    public static final int SLOT_COUNT = 54;

    public LargeContainerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.LARGE_CONTAINER.get(), pos, blockState, SLOT_COUNT);
    }

    @Override
    protected String getContainerTranslationKey() {
        return "large_container";
    }

    @Override
    protected AbstractContainerMenu createContainerMenu(int containerId, Inventory playerInventory) {
        return new LargeContainerMenu(containerId, playerInventory, this);
    }
}
