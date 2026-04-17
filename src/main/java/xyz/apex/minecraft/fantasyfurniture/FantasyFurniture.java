package xyz.apex.minecraft.fantasyfurniture;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import xyz.apex.minecraft.fantasyfurniture.client.screen.FurnaceScreen;
import xyz.apex.minecraft.fantasyfurniture.client.screen.FurnitureStationScreen;
import xyz.apex.minecraft.fantasyfurniture.client.screen.LargeContainerScreen;
import xyz.apex.minecraft.fantasyfurniture.client.screen.MediumContainerScreen;
import xyz.apex.minecraft.fantasyfurniture.client.screen.SmallContainerScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.apex.minecraft.fantasyfurniture.client.renderer.NoopEntityRenderer;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlocks;
import xyz.apex.minecraft.fantasyfurniture.registry.ModCreativeTabs;
import xyz.apex.minecraft.fantasyfurniture.registry.ModEntities;
import xyz.apex.minecraft.fantasyfurniture.registry.ModItems;
import xyz.apex.minecraft.fantasyfurniture.registry.ModMenuTypes;
import xyz.apex.minecraft.fantasyfurniture.registry.ModRecipeTypes;

@Mod(FantasyFurniture.MOD_ID)
public class FantasyFurniture {
    public static final String MOD_ID = "fantasyfurniture";
    // Nordic submodule namespace — kept separate from MOD_ID so existing 1.20.x
    // worlds can load their fantasyfurniture_nordic:* blocks unchanged.
    public static final String NORDIC_ID = "fantasyfurniture_nordic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public FantasyFurniture(IEventBus modEventBus) {
        LOGGER.info("FantasyFurniture initializing for 1.21.1 NeoForge");

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.NORDIC_BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModItems.NORDIC_ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModBlockEntities.NORDIC_BLOCK_ENTITY_TYPES.register(modEventBus);
        ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
        ModRecipeTypes.RECIPE_SERIALIZERS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);

        modEventBus.addListener(this::onClientSetup);
        modEventBus.addListener(this::registerCapabilities);
        modEventBus.addListener(this::registerEntityRenderers);
        modEventBus.addListener(this::registerMenuScreens);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        // Additional client setup if needed
    }

    private void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.SMALL_CONTAINER.get(), SmallContainerScreen::new);
        event.register(ModMenuTypes.MEDIUM_CONTAINER.get(), MediumContainerScreen::new);
        event.register(ModMenuTypes.LARGE_CONTAINER.get(), LargeContainerScreen::new);
        event.register(ModMenuTypes.FURNACE.get(), FurnaceScreen::new);
        event.register(ModMenuTypes.FURNITURE_STATION.get(), FurnitureStationScreen::new);
    }

    private void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SEAT.get(), NoopEntityRenderer::new);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        // Small container block entities (drawer, lockbox, desk left, desk right)
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.SMALL_CONTAINER.get(),
                (blockEntity, side) -> new InvWrapper(blockEntity)
        );

        // Medium container block entities (dresser, counter)
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.MEDIUM_CONTAINER.get(),
                (blockEntity, side) -> new InvWrapper(blockEntity)
        );

        // Large container block entities (chest, wardrobe)
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.LARGE_CONTAINER.get(),
                (blockEntity, side) -> new InvWrapper(blockEntity)
        );

        // Bookshelf block entity
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.BOOKSHELF.get(),
                (blockEntity, side) -> new InvWrapper(blockEntity)
        );

        // Furnace block entity (oven) - sided access
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                ModBlockEntities.FURNACE.get(),
                (blockEntity, side) -> {
                    if (side != null) {
                        return new net.neoforged.neoforge.items.wrapper.SidedInvWrapper(blockEntity, side);
                    }
                    return new InvWrapper(blockEntity);
                }
        );
    }
}
