package xyz.apex.minecraft.fantasyfurniture.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.menu.FurnitureStationMenu;
import xyz.apex.minecraft.fantasyfurniture.recipe.FurnitureStationRecipe;

import java.util.List;

public class FurnitureStationScreen extends AbstractContainerScreen<FurnitureStationMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(FantasyFurniture.MOD_ID, "textures/gui/container/furniture_station.png");

    private static final int RECIPES_COLUMNS = 6;
    private static final int RECIPES_ROWS = 4;
    private static final int RECIPE_SLOT_SIZE = 18;
    private static final int RECIPE_GRID_X = 17;
    private static final int RECIPE_GRID_Y = 46;
    private static final int SCROLL_X = 127;
    private static final int SCROLL_Y = 45;
    private static final int SCROLL_TRACK_HEIGHT = 72;

    private float scrollOffset;
    private boolean scrolling;
    private int startIndex;

    public FurnitureStationScreen(FurnitureStationMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 222;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight, 256, 256);

        int scrollerY = (int) ((SCROLL_TRACK_HEIGHT - 15) * scrollOffset);
        int scrollerU = isScrollBarActive() ? 194 : 206;
        guiGraphics.blit(TEXTURE, x + SCROLL_X, y + SCROLL_Y + scrollerY, scrollerU, 0, 12, 15, 256, 256);

        renderRecipes(guiGraphics, x + RECIPE_GRID_X, y + RECIPE_GRID_Y, mouseX, mouseY);
    }

    @Override
    protected void renderTooltip(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderTooltip(guiGraphics, mouseX, mouseY);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        List<RecipeHolder<FurnitureStationRecipe>> recipes = menu.getRecipes();
        int endIndex = Math.min(startIndex + RECIPES_COLUMNS * RECIPES_ROWS, recipes.size());

        for (int i = startIndex; i < endIndex; i++) {
            int offset = i - startIndex;
            int rx = x + RECIPE_GRID_X + (offset % RECIPES_COLUMNS) * RECIPE_SLOT_SIZE;
            int ry = y + RECIPE_GRID_Y + (offset / RECIPES_COLUMNS) * RECIPE_SLOT_SIZE;

            if (mouseX >= rx && mouseX < rx + RECIPE_SLOT_SIZE && mouseY >= ry && mouseY < ry + RECIPE_SLOT_SIZE) {
                ItemStack result = recipes.get(i).value().getResultItem(minecraft.level.registryAccess());
                guiGraphics.renderTooltip(font, result, mouseX, mouseY);
            }
        }
    }

    private void renderRecipes(GuiGraphics guiGraphics, int gridX, int gridY, int mouseX, int mouseY) {
        List<RecipeHolder<FurnitureStationRecipe>> recipes = menu.getRecipes();
        int endIndex = Math.min(startIndex + RECIPES_COLUMNS * RECIPES_ROWS, recipes.size());

        for (int i = startIndex; i < endIndex; i++) {
            int offset = i - startIndex;
            int rx = gridX + (offset % RECIPES_COLUMNS) * RECIPE_SLOT_SIZE;
            int ry = gridY + (offset / RECIPES_COLUMNS) * RECIPE_SLOT_SIZE;

            int vOffset = 0;
            if (i == menu.getSelectedRecipeIndex()) {
                vOffset = 18;
            } else if (mouseX >= rx && mouseX < rx + RECIPE_SLOT_SIZE && mouseY >= ry && mouseY < ry + RECIPE_SLOT_SIZE) {
                vOffset = 36;
            }

            guiGraphics.blit(TEXTURE, rx - 1, ry - 1, 176, vOffset, 18, 18, 256, 256);

            ItemStack result = recipes.get(i).value().getResultItem(minecraft.level.registryAccess());
            guiGraphics.renderItem(result, rx, ry);
            guiGraphics.renderItemDecorations(font, result, rx, ry);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        scrolling = false;
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        List<RecipeHolder<FurnitureStationRecipe>> recipes = menu.getRecipes();
        int endIndex = Math.min(startIndex + RECIPES_COLUMNS * RECIPES_ROWS, recipes.size());

        for (int i = startIndex; i < endIndex; i++) {
            int offset = i - startIndex;
            int rx = x + RECIPE_GRID_X + (offset % RECIPES_COLUMNS) * RECIPE_SLOT_SIZE;
            int ry = y + RECIPE_GRID_Y + (offset / RECIPES_COLUMNS) * RECIPE_SLOT_SIZE;

            if (mouseX >= rx && mouseX < rx + RECIPE_SLOT_SIZE && mouseY >= ry && mouseY < ry + RECIPE_SLOT_SIZE) {
                if (minecraft != null && minecraft.gameMode != null && menu.clickMenuButton(minecraft.player, i)) {
                    minecraft.gameMode.handleInventoryButtonClick(menu.containerId, i);
                    minecraft.getSoundManager().play(
                            SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    return true;
                }
            }
        }

        int scrollLeft = x + SCROLL_X;
        int scrollTop = y + SCROLL_Y;
        if (mouseX >= scrollLeft && mouseX < scrollLeft + 12
                && mouseY >= scrollTop && mouseY < scrollTop + SCROLL_TRACK_HEIGHT) {
            scrolling = true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (scrolling && isScrollBarActive()) {
            int y = (height - imageHeight) / 2;
            int scrollTop = y + SCROLL_Y;
            scrollOffset = ((float) mouseY - scrollTop - 7.5F) / (SCROLL_TRACK_HEIGHT - 15.0F);
            scrollOffset = Mth.clamp(scrollOffset, 0.0F, 1.0F);
            startIndex = (int) (scrollOffset * getOffscreenRows() + 0.5) * RECIPES_COLUMNS;
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (isScrollBarActive()) {
            int offscreenRows = getOffscreenRows();
            float delta = (float) scrollY / offscreenRows;
            scrollOffset = Mth.clamp(scrollOffset - delta, 0.0F, 1.0F);
            startIndex = (int) (scrollOffset * offscreenRows + 0.5) * RECIPES_COLUMNS;
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    private boolean isScrollBarActive() {
        return menu.getNumRecipes() > RECIPES_COLUMNS * RECIPES_ROWS;
    }

    private int getOffscreenRows() {
        int totalRows = (menu.getNumRecipes() + RECIPES_COLUMNS - 1) / RECIPES_COLUMNS;
        return Math.max(0, totalRows - RECIPES_ROWS);
    }
}
