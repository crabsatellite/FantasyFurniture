package xyz.apex.minecraft.fantasyfurniture.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import xyz.apex.minecraft.fantasyfurniture.recipe.FurnitureStationRecipe;
import xyz.apex.minecraft.fantasyfurniture.registry.ModMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.registry.ModRecipeTypes;

import java.util.List;

public class FurnitureStationMenu extends AbstractContainerMenu {
    private static final int SLOT_SIZE = 18;

    public static final int INGREDIENT_A = 0;
    public static final int INGREDIENT_B = 1;
    public static final int BINDING_AGENT = 2;
    public static final int OUTPUT = 3;
    private static final int INPUT_SLOTS = 3;

    private final Container inputContainer;
    private final ResultContainer outputContainer;
    private final ContainerLevelAccess access;
    private final Player player;

    private List<RecipeHolder<FurnitureStationRecipe>> recipes = List.of();
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private ItemStack lastA = ItemStack.EMPTY;
    private ItemStack lastB = ItemStack.EMPTY;
    private ItemStack lastC = ItemStack.EMPTY;

    public FurnitureStationMenu(int containerId, Inventory playerInv, FriendlyByteBuf data) {
        this(containerId, playerInv, ContainerLevelAccess.NULL);
    }

    public FurnitureStationMenu(int containerId, Inventory playerInv, ContainerLevelAccess access) {
        super(ModMenuTypes.FURNITURE_STATION.get(), containerId);
        this.access = access;
        this.player = playerInv.player;
        this.inputContainer = new SimpleContainer(INPUT_SLOTS) {
            @Override
            public void setChanged() {
                super.setChanged();
                slotsChanged(this);
            }
        };
        this.outputContainer = new ResultContainer();

        selectedRecipeIndex.set(-1);

        addSlot(new Slot(inputContainer, INGREDIENT_A, 16, 21));
        addSlot(new Slot(inputContainer, INGREDIENT_B, 34, 21));
        addSlot(new Slot(inputContainer, BINDING_AGENT, 52, 21));

        addSlot(new Slot(outputContainer, 0, 148, 72) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player takingPlayer, ItemStack stack) {
                stack.onCraftedBy(takingPlayer.level(), takingPlayer, stack.getCount());
                outputContainer.awardUsedRecipes(takingPlayer, getIngredientItemList());

                inputContainer.getItem(INGREDIENT_A).shrink(1);
                inputContainer.getItem(INGREDIENT_B).shrink(1);
                inputContainer.getItem(BINDING_AGENT).shrink(1);

                inputContainer.setChanged();
                setupResultSlot();

                super.onTake(takingPlayer, stack);
            }
        });

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * SLOT_SIZE, 140 + row * SLOT_SIZE));
            }
        }

        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInv, col, 8 + col * SLOT_SIZE, 198));
        }

        addDataSlot(selectedRecipeIndex);
    }

    public List<RecipeHolder<FurnitureStationRecipe>> getRecipes() {
        return recipes;
    }

    public int getNumRecipes() {
        return recipes.size();
    }

    public int getSelectedRecipeIndex() {
        return selectedRecipeIndex.get();
    }

    public boolean hasInputs() {
        return !inputContainer.getItem(INGREDIENT_A).isEmpty()
                && !inputContainer.getItem(INGREDIENT_B).isEmpty()
                && !inputContainer.getItem(BINDING_AGENT).isEmpty()
                && !recipes.isEmpty();
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (isValidRecipeIndex(id)) {
            selectedRecipeIndex.set(id);
            setupResultSlot();
            return true;
        }
        return false;
    }

    private boolean isValidRecipeIndex(int index) {
        return index >= 0 && index < recipes.size();
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);

        if (container == inputContainer) {
            ItemStack a = inputContainer.getItem(INGREDIENT_A);
            ItemStack b = inputContainer.getItem(INGREDIENT_B);
            ItemStack c = inputContainer.getItem(BINDING_AGENT);

            if (!ItemStack.isSameItemSameComponents(a, lastA)
                    || !ItemStack.isSameItemSameComponents(b, lastB)
                    || !ItemStack.isSameItemSameComponents(c, lastC)) {
                lastA = a.copy();
                lastB = b.copy();
                lastC = c.copy();
                setupRecipeList();
            }
        }
    }

    private List<ItemStack> getIngredientItemList() {
        return List.of(
                inputContainer.getItem(INGREDIENT_A),
                inputContainer.getItem(INGREDIENT_B),
                inputContainer.getItem(BINDING_AGENT)
        );
    }

    private RecipeInput createRecipeInput() {
        return new RecipeInput() {
            @Override
            public ItemStack getItem(int index) {
                return inputContainer.getItem(index);
            }

            @Override
            public int size() {
                return inputContainer.getContainerSize();
            }
        };
    }

    private void setupRecipeList() {
        recipes = List.of();
        selectedRecipeIndex.set(-1);
        outputContainer.setItem(0, ItemStack.EMPTY);

        ItemStack a = inputContainer.getItem(INGREDIENT_A);
        ItemStack b = inputContainer.getItem(INGREDIENT_B);
        ItemStack c = inputContainer.getItem(BINDING_AGENT);

        if (!a.isEmpty() && !b.isEmpty() && !c.isEmpty()) {
            Level level = player.level();
            recipes = level.getRecipeManager()
                    .getRecipesFor(ModRecipeTypes.FURNITURE_STATION_TYPE.get(), createRecipeInput(), level);
        }

        broadcastChanges();
    }

    private void setupResultSlot() {
        if (!recipes.isEmpty() && isValidRecipeIndex(selectedRecipeIndex.get())) {
            RecipeHolder<FurnitureStationRecipe> recipe = recipes.get(selectedRecipeIndex.get());
            ItemStack result = recipe.value().assemble(createRecipeInput(), player.level().registryAccess());
            outputContainer.setRecipeUsed(recipe);
            outputContainer.setItem(0, result);
        } else {
            outputContainer.setItem(0, ItemStack.EMPTY);
        }
        broadcastChanges();
    }

    @Override
    public boolean stillValid(Player player) {
        return access.evaluate((level, pos) ->
                player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) <= 64.0, true);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            result = stackInSlot.copy();

            if (index == OUTPUT) {
                if (!moveItemStackTo(stackInSlot, OUTPUT + 1, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stackInSlot, result);
            } else if (index >= OUTPUT + 1) {
                if (!moveItemStackTo(stackInSlot, 0, INPUT_SLOTS, false)) {
                    int playerInvStart = OUTPUT + 1;
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
                if (!moveItemStackTo(stackInSlot, OUTPUT + 1, slots.size(), true)) {
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
        outputContainer.removeItemNoUpdate(0);
        access.execute((level, pos) -> clearContainer(player, inputContainer));
    }
}
