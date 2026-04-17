package xyz.apex.minecraft.fantasyfurniture.block.entity;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.menu.FurnaceMenu;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;

import javax.annotation.Nullable;
import java.util.List;

public class FurnaceBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {
    public static final int SLOT_INPUT = 0;
    public static final int SLOT_FUEL = 1;
    public static final int SLOT_OUTPUT = 2;
    public static final int SLOT_COUNT = 3;

    private static final int[] SLOTS_UP = {SLOT_INPUT};
    private static final int[] SLOTS_DOWN = {SLOT_OUTPUT, SLOT_FUEL};
    private static final int[] SLOTS_SIDES = {SLOT_FUEL};

    protected NonNullList<ItemStack> items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    private int litTime;
    private int litDuration;
    private int cookingProgress;
    private int cookingTotalTime;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();

    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> litTime;
                case 1 -> litDuration;
                case 2 -> cookingProgress;
                case 3 -> cookingTotalTime;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> litTime = value;
                case 1 -> litDuration = value;
                case 2 -> cookingProgress = value;
                case 3 -> cookingTotalTime = value;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public FurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.FURNACE.get(), pos, blockState);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container." + FantasyFurniture.MOD_ID + ".furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new FurnaceMenu(containerId, playerInventory, this, dataAccess);
    }

    @Override
    public int getContainerSize() {
        return SLOT_COUNT;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(items, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack existing = items.get(slot);
        boolean sameItem = !stack.isEmpty() && ItemStack.isSameItemSameComponents(stack, existing);
        items.set(slot, stack);
        stack.limitSize(getMaxStackSize(stack));

        if (slot == SLOT_INPUT && !sameItem) {
            if (level != null) {
                cookingTotalTime = getTotalCookTime(level, new SingleRecipeInput(stack));
            }
            cookingProgress = 0;
        }
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public void clearContent() {
        items.clear();
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.UP) {
            return SLOTS_UP;
        } else if (side == Direction.DOWN) {
            return SLOTS_DOWN;
        }
        return SLOTS_SIDES;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction direction) {
        return canPlaceItem(slot, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && slot == SLOT_FUEL) {
            return stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET);
        }
        return true;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        if (slot == SLOT_OUTPUT) {
            return false;
        }
        if (slot == SLOT_FUEL) {
            ItemStack fuelStack = items.get(SLOT_FUEL);
            return stack.getBurnTime(RecipeType.SMOKING) > 0
                    || (stack.is(Items.BUCKET) && !fuelStack.is(Items.BUCKET));
        }
        return true;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        items = NonNullList.withSize(SLOT_COUNT, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, items, registries);
        litTime = tag.getInt("BurnTime");
        litDuration = tag.getInt("BurnDuration");
        cookingProgress = tag.getInt("CookTime");
        cookingTotalTime = tag.getInt("CookTimeTotal");

        recipesUsed.clear();
        CompoundTag recipesUsedTag = tag.getCompound("RecipesUsed");
        for (String key : recipesUsedTag.getAllKeys()) {
            recipesUsed.put(ResourceLocation.parse(key), recipesUsedTag.getInt(key));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("BurnTime", litTime);
        tag.putInt("BurnDuration", litDuration);
        tag.putInt("CookTime", cookingProgress);
        tag.putInt("CookTimeTotal", cookingTotalTime);
        ContainerHelper.saveAllItems(tag, items, registries);

        CompoundTag recipesUsedTag = new CompoundTag();
        recipesUsed.forEach((id, count) -> recipesUsedTag.putInt(id.toString(), count));
        tag.put("RecipesUsed", recipesUsedTag);
    }

    @Override
    public void fillStackedContents(StackedContents contents) {
        for (ItemStack stack : items) {
            contents.accountStack(stack);
        }
    }

    @Override
    public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
        if (recipe != null) {
            ResourceLocation id = recipe.id();
            recipesUsed.addTo(id, 1);
        }
    }

    @Nullable
    @Override
    public RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
        List<RecipeHolder<?>> recipes = getRecipesToAwardAndPopExperience(player.serverLevel(), player.position());
        player.awardRecipes(recipes);
        for (RecipeHolder<?> recipe : recipes) {
            if (recipe != null) {
                player.triggerRecipeCrafted(recipe, items);
            }
        }
        recipesUsed.clear();
    }

    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 popVec) {
        List<RecipeHolder<?>> list = new java.util.ArrayList<>();
        for (var entry : recipesUsed.object2IntEntrySet()) {
            level.getRecipeManager().byKey(entry.getKey()).ifPresent(recipe -> {
                list.add(recipe);
                createExperience(level, popVec, entry.getIntValue(),
                        ((AbstractCookingRecipe) recipe.value()).getExperience());
            });
        }
        return list;
    }

    private static void createExperience(ServerLevel level, Vec3 pos, int count, float experience) {
        int total = (int) (count * experience);
        float frac = count * experience - total;
        if (frac != 0.0F && level.random.nextFloat() < frac) {
            total++;
        }
        ExperienceOrb.award(level, pos, total);
    }

    private boolean isLit() {
        return litTime > 0;
    }

    private int getTotalCookTime(Level level, SingleRecipeInput input) {
        return level.getRecipeManager().getRecipeFor(RecipeType.SMOKING, input, level)
                .map(holder -> ((AbstractCookingRecipe) holder.value()).getCookingTime())
                .orElse(100);
    }

    private static boolean canBurn(HolderLookup.Provider registries, @Nullable RecipeHolder<?> recipe, ItemStack inputStack, ItemStack outputStack, int maxStackSize) {
        if (recipe == null || inputStack.isEmpty()) return false;
        ItemStack resultStack = ((AbstractCookingRecipe) recipe.value()).assemble(new SingleRecipeInput(inputStack), registries);
        if (resultStack.isEmpty()) return false;
        if (outputStack.isEmpty()) return true;
        if (!ItemStack.isSameItemSameComponents(outputStack, resultStack)) return false;
        return outputStack.getCount() + resultStack.getCount() <= Math.min(maxStackSize, outputStack.getMaxStackSize());
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, FurnaceBlockEntity blockEntity) {
        boolean wasLit = blockEntity.isLit();
        boolean changed = false;

        if (blockEntity.isLit()) {
            blockEntity.litTime--;
        }

        ItemStack inputStack = blockEntity.items.get(SLOT_INPUT);
        ItemStack fuelStack = blockEntity.items.get(SLOT_FUEL);
        boolean hasInput = !inputStack.isEmpty();
        boolean hasFuel = !fuelStack.isEmpty();

        if (blockEntity.isLit() || (hasFuel && hasInput)) {
            RecipeHolder<?> recipe = null;
            SingleRecipeInput recipeInput = null;
            if (hasInput) {
                recipeInput = new SingleRecipeInput(inputStack);
                var optional = level.getRecipeManager().getRecipeFor(
                        RecipeType.SMOKING,
                        recipeInput,
                        level
                );
                recipe = optional.orElse(null);
            }

            ItemStack outputStack = blockEntity.items.get(SLOT_OUTPUT);

            if (!blockEntity.isLit() && canBurn(level.registryAccess(), recipe, inputStack, outputStack, blockEntity.getMaxStackSize())) {
                int burnTime = fuelStack.getBurnTime(RecipeType.SMOKING);
                blockEntity.litTime = burnTime;
                blockEntity.litDuration = burnTime;

                if (blockEntity.isLit()) {
                    changed = true;
                    if (fuelStack.hasCraftingRemainingItem()) {
                        blockEntity.items.set(SLOT_FUEL, fuelStack.getCraftingRemainingItem());
                    } else {
                        fuelStack.shrink(1);
                        if (fuelStack.isEmpty()) {
                            blockEntity.items.set(SLOT_FUEL, ItemStack.EMPTY);
                        }
                    }
                }
            }

            if (blockEntity.isLit() && canBurn(level.registryAccess(), recipe, inputStack, outputStack, blockEntity.getMaxStackSize())) {
                blockEntity.cookingProgress++;

                if (blockEntity.cookingProgress >= blockEntity.cookingTotalTime) {
                    blockEntity.cookingProgress = 0;
                    blockEntity.cookingTotalTime = recipeInput != null ?
                            blockEntity.getTotalCookTime(level, recipeInput) : 100;

                    ItemStack resultStack = ((AbstractCookingRecipe) recipe.value()).assemble(recipeInput, level.registryAccess());
                    outputStack = blockEntity.items.get(SLOT_OUTPUT);

                    if (outputStack.isEmpty()) {
                        blockEntity.items.set(SLOT_OUTPUT, resultStack.copy());
                    } else if (ItemStack.isSameItemSameComponents(outputStack, resultStack)) {
                        outputStack.grow(resultStack.getCount());
                    }

                    inputStack.shrink(1);
                    blockEntity.setRecipeUsed(recipe);
                    changed = true;
                }
            } else {
                blockEntity.cookingProgress = 0;
            }
        } else if (blockEntity.cookingProgress > 0) {
            blockEntity.cookingProgress = Math.max(blockEntity.cookingProgress - 2, 0);
            changed = true;
        }

        if (wasLit != blockEntity.isLit()) {
            changed = true;
            state = state.setValue(BlockStateProperties.LIT, blockEntity.isLit());
            level.setBlock(pos, state, 3);
        }

        if (changed) {
            setChanged(level, pos, state);
        }
    }
}
