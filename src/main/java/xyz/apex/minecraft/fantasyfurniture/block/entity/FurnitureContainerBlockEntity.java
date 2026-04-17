package xyz.apex.minecraft.fantasyfurniture.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;

public abstract class FurnitureContainerBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> items;
    private final int slotCount;

    protected FurnitureContainerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, int slotCount) {
        super(type, pos, blockState);
        this.slotCount = slotCount;
        this.items = NonNullList.withSize(slotCount, ItemStack.EMPTY);
    }

    @Override
    public int getContainerSize() {
        return slotCount;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container." + FantasyFurniture.MOD_ID + "." + getContainerTranslationKey());
    }

    protected String getContainerTranslationKey() {
        return "generic";
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return createContainerMenu(containerId, playerInventory);
    }

    protected abstract AbstractContainerMenu createContainerMenu(int containerId, Inventory playerInventory);

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if (!tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items, registries);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items, registries);
        }
    }
}
