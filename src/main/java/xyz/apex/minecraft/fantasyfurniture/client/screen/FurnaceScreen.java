package xyz.apex.minecraft.fantasyfurniture.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.menu.FurnaceMenu;

public class FurnaceScreen extends AbstractContainerScreen<FurnaceMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(FantasyFurniture.MOD_ID, "textures/gui/container/oven.png");

    public FurnaceScreen(FurnaceMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
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
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        // Burn progress flame (14x14 sprite at texture position 176,0)
        if (menu.isLit()) {
            int burnProgress = menu.getBurnProgress();
            // Flame draws bottom-up: source at (176, 12-burnProgress), 14 pixels wide
            guiGraphics.blit(TEXTURE, x + 56, y + 36 + 12 - burnProgress, 176, 12 - burnProgress, 14, burnProgress + 1);
        }

        // Cook progress arrow (24x16 sprite at texture position 176,14)
        int cookProgress = menu.getCookProgress();
        guiGraphics.blit(TEXTURE, x + 79, y + 34, 176, 14, cookProgress + 1, 16);
    }
}
