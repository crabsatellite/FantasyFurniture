package xyz.apex.minecraft.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.menu.LargeContainerMenu;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;

public class BookshelfBlockEntity extends FurnitureContainerBlockEntity {
    public static final int SLOT_COUNT = 54;

    public BookshelfBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.BOOKSHELF.get(), pos, blockState, SLOT_COUNT);
    }

    @Override
    protected String getContainerTranslationKey() {
        return "bookshelf";
    }

    @Override
    protected AbstractContainerMenu createContainerMenu(int containerId, Inventory playerInventory) {
        return new LargeContainerMenu(containerId, playerInventory, this);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return stack.is(ItemTags.BOOKSHELF_BOOKS);
    }
}
