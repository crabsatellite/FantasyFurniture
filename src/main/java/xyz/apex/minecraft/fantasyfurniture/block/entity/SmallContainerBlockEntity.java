package xyz.apex.minecraft.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.menu.SmallContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;

public class SmallContainerBlockEntity extends FurnitureContainerBlockEntity {
    public static final int SLOT_COUNT = 15;

    public SmallContainerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SMALL_CONTAINER.get(), pos, blockState, SLOT_COUNT);
    }

    @Override
    protected String getContainerTranslationKey() {
        return "small_container";
    }

    @Override
    protected AbstractContainerMenu createContainerMenu(int containerId, Inventory playerInventory) {
        return new SmallContainerMenu(containerId, playerInventory, this);
    }
}
