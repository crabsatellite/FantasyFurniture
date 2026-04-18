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
import xyz.apex.minecraft.fantasyfurniture.client.renderer.SkullBlossomsBlockEntityRenderer;
import xyz.apex.minecraft.fantasyfurniture.client.renderer.WidowBloomBlockEntityRenderer;
import xyz.apex.minecraft.fantasyfurniture.client.renderer.model.SkullBlossomsModel;
import xyz.apex.minecraft.fantasyfurniture.client.renderer.model.WidowBloomModel;
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
    // Per-set namespaces. Each FurnitureSet owns its own mod id so existing
    // 1.20.x worlds load unchanged (Nordic) and the remaining sets follow the
    // same pattern. These string constants exist for backwards-reference; new
    // code should prefer FurnitureSet#namespace().
    public static final String NORDIC_ID = FurnitureSet.NORDIC.namespace();
    public static final String ROYAL_ID = FurnitureSet.ROYAL.namespace();
    public static final String VENTHYR_ID = FurnitureSet.VENTHYR.namespace();
    public static final String DUNMER_ID = FurnitureSet.DUNMER.namespace();
    public static final String NECROLORD_ID = FurnitureSet.NECROLORD.namespace();
    public static final String BONE_SKELETON_ID = FurnitureSet.BONE_SKELETON.namespace();
    public static final String BONE_WITHER_ID = FurnitureSet.BONE_WITHER.namespace();
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public FantasyFurniture(IEventBus modEventBus) {
        LOGGER.info("FantasyFurniture initializing for 1.21.1 NeoForge");

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.NORDIC_BLOCKS.register(modEventBus);
        ModBlocks.ROYAL_BLOCKS.register(modEventBus);
        ModBlocks.VENTHYR_BLOCKS.register(modEventBus);
        ModBlocks.DUNMER_BLOCKS.register(modEventBus);
        ModBlocks.NECROLORD_BLOCKS.register(modEventBus);
        ModBlocks.BONE_SKELETON_BLOCKS.register(modEventBus);
        ModBlocks.BONE_WITHER_BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModItems.NORDIC_ITEMS.register(modEventBus);
        ModItems.ROYAL_ITEMS.register(modEventBus);
        ModItems.VENTHYR_ITEMS.register(modEventBus);
        ModItems.DUNMER_ITEMS.register(modEventBus);
        ModItems.NECROLORD_ITEMS.register(modEventBus);
        ModItems.BONE_SKELETON_ITEMS.register(modEventBus);
        ModItems.BONE_WITHER_ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModBlockEntities.NORDIC_BLOCK_ENTITY_TYPES.register(modEventBus);
        modEventBus.addListener(this::registerLayerDefinitions);
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
        event.registerBlockEntityRenderer(ModBlockEntities.VENTHYR_WIDOW_BLOOM.get(), WidowBloomBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BONE_SKULL_BLOSSOMS.get(), SkullBlossomsBlockEntityRenderer::new);
    }

    private void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WidowBloomModel.LAYER_LOCATION, WidowBloomModel::createBodyLayer);
        event.registerLayerDefinition(SkullBlossomsModel.LAYER_LOCATION, SkullBlossomsModel::createBodyLayer);
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
