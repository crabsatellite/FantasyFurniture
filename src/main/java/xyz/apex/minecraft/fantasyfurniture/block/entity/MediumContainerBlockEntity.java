package xyz.apex.minecraft.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.menu.MediumContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;

public class MediumContainerBlockEntity extends FurnitureContainerBlockEntity {
    public static final int SLOT_COUNT = 27;

    public MediumContainerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.MEDIUM_CONTAINER.get(), pos, blockState, SLOT_COUNT);
    }

    @Override
    protected String getContainerTranslationKey() {
        return "medium_container";
    }

    @Override
    protected AbstractContainerMenu createContainerMenu(int containerId, Inventory playerInventory) {
        return new MediumContainerMenu(containerId, playerInventory, this);
    }
}
