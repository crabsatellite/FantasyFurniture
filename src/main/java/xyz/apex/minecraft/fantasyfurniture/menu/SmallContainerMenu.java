package xyz.apex.minecraft.fantasyfurniture.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import xyz.apex.minecraft.fantasyfurniture.registry.ModMenuTypes;

public class SmallContainerMenu extends AbstractContainerMenu {
    private static final int SLOT_SIZE = 18;
    private static final int COLUMNS = 5;
    private static final int ROWS = 3;
    private static final int CONTAINER_SLOTS = COLUMNS * ROWS;

    private final Container container;

    public SmallContainerMenu(int containerId, Inventory playerInv, FriendlyByteBuf data) {
        this(containerId, playerInv, getContainer(playerInv, data));
    }

    public SmallContainerMenu(int containerId, Inventory playerInv, Container container) {
        super(ModMenuTypes.SMALL_CONTAINER.get(), containerId);
        this.container = container;
        container.startOpen(playerInv.player);

        // Container slots: 5 columns x 3 rows, starting at x=44 y=18.
        // Subclass Slot so mayPlace delegates to container.canPlaceItem — vanilla Slot ignores it.
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                addSlot(new Slot(container, col + row * COLUMNS, 44 + col * SLOT_SIZE, 18 + row * SLOT_SIZE) {
                    @Override
                    public boolean mayPlace(ItemStack stack) {
                        return container.canPlaceItem(getContainerSlot(), stack);
                    }
                });
            }
        }

        // Player inventory (3 rows x 9 cols)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * SLOT_SIZE, 84 + row * SLOT_SIZE));
            }
        }

        // Player hotbar
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInv, col, 8 + col * SLOT_SIZE, 142));
        }
    }

    private static Container getContainer(Inventory playerInv, FriendlyByteBuf data) {
        BlockPos pos = data.readBlockPos();
        BlockEntity blockEntity = playerInv.player.level().getBlockEntity(pos);
        if (blockEntity instanceof Container c) {
            return c;
        }
        return new SimpleContainer(CONTAINER_SLOTS);
    }

    @Override
    public boolean stillValid(Player player) {
        return container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            result = stackInSlot.copy();

            if (index < CONTAINER_SLOTS) {
                // Move from container to player inventory
                if (!moveItemStackTo(stackInSlot, CONTAINER_SLOTS, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Move from player inventory to container
                if (!moveItemStackTo(stackInSlot, 0, CONTAINER_SLOTS, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stackInSlot.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stackInSlot.getCount() == result.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stackInSlot);
        }

        return result;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        container.stopOpen(player);
    }
}
