package xyz.apex.minecraft.fantasyfurniture.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import xyz.apex.minecraft.fantasyfurniture.block.entity.FurnaceBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.registry.ModMenuTypes;

public class FurnaceMenu extends AbstractContainerMenu {
    private static final int SLOT_SIZE = 18;

    public static final int INPUT_SLOT = 0;
    public static final int FUEL_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;
    private static final int CONTAINER_SLOTS = 3;

    private static final int DATA_LIT_TIME = 0;
    private static final int DATA_LIT_DURATION = 1;
    private static final int DATA_COOK_PROGRESS = 2;
    private static final int DATA_COOK_TOTAL = 3;

    private final Container container;
    private final ContainerData data;

    public FurnaceMenu(int containerId, Inventory playerInv, FriendlyByteBuf buf) {
        this(containerId, playerInv, new SimpleContainer(CONTAINER_SLOTS), new SimpleContainerData(4));
    }

    public FurnaceMenu(int containerId, Inventory playerInv, Container container, ContainerData data) {
        super(ModMenuTypes.FURNACE.get(), containerId);
        this.container = container;
        this.data = data;
        container.startOpen(playerInv.player);

        // Input/fuel slots delegate mayPlace to container.canPlaceItem so GUI respects
        // the BE's fuel/input predicates (hopper path already respects them via InvWrapper).
        addSlot(new Slot(container, INPUT_SLOT, 56, 17) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return container.canPlaceItem(getContainerSlot(), stack);
            }
        });
        addSlot(new Slot(container, FUEL_SLOT, 56, 53) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return container.canPlaceItem(getContainerSlot(), stack);
            }
        });
        // Output slot with custom XP award (mayPlace=false is built into ModFurnaceResultSlot)
        addSlot(new ModFurnaceResultSlot(playerInv.player, container, OUTPUT_SLOT, 116, 35));

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

        addDataSlots(data);
    }

    public boolean isLit() {
        return data.get(DATA_LIT_TIME) > 0;
    }

    public int getBurnProgress() {
        int litTime = data.get(DATA_LIT_TIME);
        int litDuration = data.get(DATA_LIT_DURATION);
        if (litDuration == 0 || litTime == 0) {
            return 0;
        }
        return litTime * 13 / litDuration;
    }

    public int getCookProgress() {
        int cookProgress = data.get(DATA_COOK_PROGRESS);
        int cookTotal = data.get(DATA_COOK_TOTAL);
        if (cookTotal == 0 || cookProgress == 0) {
            return 0;
        }
        return cookProgress * 24 / cookTotal;
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

            if (index == OUTPUT_SLOT) {
                // Move output to player inventory
                if (!moveItemStackTo(stackInSlot, CONTAINER_SLOTS, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stackInSlot, result);
            } else if (index >= CONTAINER_SLOTS) {
                // From player inventory to furnace
                boolean isSmeltable = player.level().getRecipeManager()
                        .getRecipeFor(RecipeType.SMOKING, new SingleRecipeInput(stackInSlot), player.level())
                        .isPresent();
                boolean isFuel = stackInSlot.getBurnTime(RecipeType.SMOKING) > 0;

                if (isSmeltable) {
                    if (!moveItemStackTo(stackInSlot, INPUT_SLOT, INPUT_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (isFuel) {
                    if (!moveItemStackTo(stackInSlot, FUEL_SLOT, FUEL_SLOT + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    // Move between player inventory and hotbar
                    int playerInvStart = CONTAINER_SLOTS;
                    int playerInvEnd = playerInvStart + 27;
                    int hotbarEnd = playerInvEnd + 9;
                    if (index < playerInvEnd) {
                        if (!moveItemStackTo(stackInSlot, playerInvEnd, hotbarEnd, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else {
                        if (!moveItemStackTo(stackInSlot, playerInvStart, playerInvEnd, false)) {
                            return ItemStack.EMPTY;
                        }
                    }
                }
            } else {
                // Move from input/fuel to player inventory
                if (!moveItemStackTo(stackInSlot, CONTAINER_SLOTS, slots.size(), true)) {
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

    private static class ModFurnaceResultSlot extends Slot {
        private final Player player;
        private int removeCount;

        ModFurnaceResultSlot(Player player, Container container, int slot, int x, int y) {
            super(container, slot, x, y);
            this.player = player;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false;
        }

        @Override
        public ItemStack remove(int amount) {
            if (hasItem()) {
                removeCount += Math.min(amount, getItem().getCount());
            }
            return super.remove(amount);
        }

        @Override
        public void onTake(Player taker, ItemStack stack) {
            checkTakeAchievements(stack);
            super.onTake(taker, stack);
        }

        @Override
        public void onQuickCraft(ItemStack oldStack, ItemStack newStack) {
            int delta = newStack.getCount() - oldStack.getCount();
            if (delta > 0) {
                removeCount += delta;
            }
            super.onQuickCraft(oldStack, newStack);
        }

        protected void checkTakeAchievements(ItemStack stack) {
            stack.onCraftedBy(player.level(), player, removeCount);
            if (player instanceof ServerPlayer serverPlayer && container instanceof FurnaceBlockEntity furnaceBe) {
                furnaceBe.awardUsedRecipesAndPopExperience(serverPlayer);
            }
            removeCount = 0;
            // Matches NeoForge's patch to vanilla FurnaceResultSlot.checkTakeAchievements so
            // third-party mods hooking PlayerEvent.ItemSmeltedEvent (XP multipliers, quests,
            // stat trackers) see smelts from the Nordic Oven on parity with vanilla furnaces.
            net.neoforged.neoforge.event.EventHooks.firePlayerSmeltedEvent(player, stack);
        }
    }
}
