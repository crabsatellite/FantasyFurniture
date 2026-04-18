package xyz.apex.minecraft.fantasyfurniture.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;

import java.util.LinkedHashSet;
import java.util.function.Supplier;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, FantasyFurniture.MOD_ID);

    public static final DeferredRegister<Item> NORDIC_ITEMS =
            DeferredRegister.create(Registries.ITEM, FantasyFurniture.NORDIC_ID);

    public static final DeferredRegister<Item> ROYAL_ITEMS =
            DeferredRegister.create(Registries.ITEM, FantasyFurniture.ROYAL_ID);

    public static final DeferredRegister<Item> VENTHYR_ITEMS =
            DeferredRegister.create(Registries.ITEM, FantasyFurniture.VENTHYR_ID);
    public static final DeferredRegister<Item> DUNMER_ITEMS =
            DeferredRegister.create(Registries.ITEM, FantasyFurniture.DUNMER_ID);
    public static final DeferredRegister<Item> NECROLORD_ITEMS =
            DeferredRegister.create(Registries.ITEM, FantasyFurniture.NECROLORD_ID);
    public static final DeferredRegister<Item> BONE_SKELETON_ITEMS =
            DeferredRegister.create(Registries.ITEM, FantasyFurniture.BONE_SKELETON_ID);
    public static final DeferredRegister<Item> BONE_WITHER_ITEMS =
            DeferredRegister.create(Registries.ITEM, FantasyFurniture.BONE_WITHER_ID);

    /**
     * Ordered set of item suppliers for creative tab population.
     * Items are added in registration order.
     */
    public static final LinkedHashSet<Supplier<? extends Item>> CREATIVE_TAB_ITEMS = new LinkedHashSet<>();

    // --- Furniture Station (fantasyfurniture:furniture_station) ---
    public static final DeferredHolder<Item, BlockItem> FURNITURE_STATION =
            registerMainBlockItem("furniture_station", () -> new BlockItem(ModBlocks.FURNITURE_STATION.get(), new Item.Properties()));

    // --- Nordic Wool / Carpet ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_WOOL =
            registerNordicBlockItem("wool", () -> new BlockItem(ModBlocks.NORDIC_WOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_CARPET =
            registerNordicBlockItem("carpet", () -> new BlockItem(ModBlocks.NORDIC_CARPET.get(), new Item.Properties()));

    // --- Nordic Light Blocks ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_WALL_LIGHT =
            registerNordicBlockItem("wall_light", () -> new BlockItem(ModBlocks.NORDIC_WALL_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_FLOOR_LIGHT =
            registerNordicBlockItem("floor_light", () -> new BlockItem(ModBlocks.NORDIC_FLOOR_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_CHANDELIER_LIGHT =
            registerNordicBlockItem("chandelier", () -> new BlockItem(ModBlocks.NORDIC_CHANDELIER_LIGHT.get(), new Item.Properties()));

    // --- Nordic Seating / Tables ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_TABLE =
            registerNordicBlockItem("table", () -> new BlockItem(ModBlocks.NORDIC_TABLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_CHAIR =
            registerNordicBlockItem("chair", () -> new BlockItem(ModBlocks.NORDIC_CHAIR.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_BENCH =
            registerNordicBlockItem("bench", () -> new BlockItem(ModBlocks.NORDIC_BENCH.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_STOOL =
            registerNordicBlockItem("stool", () -> new BlockItem(ModBlocks.NORDIC_STOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_CUSHION =
            registerNordicBlockItem("cushion", () -> new BlockItem(ModBlocks.NORDIC_CUSHION.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_SHELF =
            registerNordicBlockItem("shelf", () -> new BlockItem(ModBlocks.NORDIC_SHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_SOFA =
            registerNordicBlockItem("sofa", () -> new BlockItem(ModBlocks.NORDIC_SOFA.get(), new Item.Properties()));

    // --- Nordic Storage (Small) ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_DRAWER =
            registerNordicBlockItem("drawer", () -> new BlockItem(ModBlocks.NORDIC_DRAWER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_LOCKBOX =
            registerNordicBlockItem("lockbox", () -> new BlockItem(ModBlocks.NORDIC_LOCKBOX.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_DESK_LEFT =
            registerNordicBlockItem("desk_left", () -> new BlockItem(ModBlocks.NORDIC_DESK_LEFT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_DESK_RIGHT =
            registerNordicBlockItem("desk_right", () -> new BlockItem(ModBlocks.NORDIC_DESK_RIGHT.get(), new Item.Properties()));

    // --- Nordic Storage (Medium) ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_DRESSER =
            registerNordicBlockItem("dresser", () -> new BlockItem(ModBlocks.NORDIC_DRESSER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_COUNTER =
            registerNordicBlockItem("counter", () -> new BlockItem(ModBlocks.NORDIC_COUNTER.get(), new Item.Properties()));

    // --- Nordic Storage (Large) ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_CHEST =
            registerNordicBlockItem("chest", () -> new BlockItem(ModBlocks.NORDIC_CHEST.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_WARDROBE_BOTTOM =
            registerNordicBlockItem("wardrobe_bottom", () -> new BlockItem(ModBlocks.NORDIC_WARDROBE_BOTTOM.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_WARDROBE_TOP =
            registerNordicBlockItem("wardrobe_top", () -> new BlockItem(ModBlocks.NORDIC_WARDROBE_TOP.get(), new Item.Properties()));

    // --- Nordic Storage (Bookshelf) ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_BOOKSHELF =
            registerNordicBlockItem("bookshelf", () -> new BlockItem(ModBlocks.NORDIC_BOOKSHELF.get(), new Item.Properties()));

    // --- Nordic Beds ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_BED_SINGLE =
            registerNordicBlockItem("bed_single", () -> new BlockItem(ModBlocks.NORDIC_BED_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_BED_DOUBLE =
            registerNordicBlockItem("bed_double", () -> new BlockItem(ModBlocks.NORDIC_BED_DOUBLE.get(), new Item.Properties()));

    // --- Nordic Doors ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_DOOR_SINGLE =
            registerNordicBlockItem("door_single", () -> new BlockItem(ModBlocks.NORDIC_DOOR_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_DOOR_DOUBLE =
            registerNordicBlockItem("door_double", () -> new BlockItem(ModBlocks.NORDIC_DOOR_DOUBLE.get(), new Item.Properties()));

    // --- Nordic Paintings ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_PAINTING_SMALL =
            registerNordicBlockItem("painting_small", () -> new BlockItem(ModBlocks.NORDIC_PAINTING_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_PAINTING_WIDE =
            registerNordicBlockItem("painting_wide", () -> new BlockItem(ModBlocks.NORDIC_PAINTING_WIDE.get(), new Item.Properties()));

    // --- Nordic Oven ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_OVEN =
            registerNordicBlockItem("oven", () -> new BlockItem(ModBlocks.NORDIC_OVEN.get(), new Item.Properties()));

    // --- Nordic Decorations ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_BOILED_CREME_TREATS =
            registerNordicBlockItem("boiled_creme_treats", () -> new BlockItem(ModBlocks.NORDIC_BOILED_CREME_TREATS.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_SWEETROLLS =
            registerNordicBlockItem("sweetrolls", () -> new BlockItem(ModBlocks.NORDIC_SWEETROLLS.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_MEAD_BOTTLES =
            registerNordicBlockItem("mead_bottles", () -> new BlockItem(ModBlocks.NORDIC_MEAD_BOTTLES.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_SOUL_GEMS_LIGHT =
            registerNordicBlockItem("soul_gems_light", () -> new BlockItem(ModBlocks.NORDIC_SOUL_GEMS_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_SOUL_GEMS_DARK =
            registerNordicBlockItem("soul_gems_dark", () -> new BlockItem(ModBlocks.NORDIC_SOUL_GEMS_DARK.get(), new Item.Properties()));

    // --- Nordic Tables (Wide/Large) ---
    public static final DeferredHolder<Item, BlockItem> NORDIC_TABLE_WIDE =
            registerNordicBlockItem("table_wide", () -> new BlockItem(ModBlocks.NORDIC_TABLE_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NORDIC_TABLE_LARGE =
            registerNordicBlockItem("table_large", () -> new BlockItem(ModBlocks.NORDIC_TABLE_LARGE.get(), new Item.Properties()));

    // ========================================================================
    // Royal BlockItems (fantasyfurniture_royal:*)
    // ========================================================================

    public static final DeferredHolder<Item, BlockItem> ROYAL_WOOL =
            registerRoyalBlockItem("wool", () -> new BlockItem(ModBlocks.ROYAL_WOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_CARPET =
            registerRoyalBlockItem("carpet", () -> new BlockItem(ModBlocks.ROYAL_CARPET.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_WALL_LIGHT =
            registerRoyalBlockItem("wall_light", () -> new BlockItem(ModBlocks.ROYAL_WALL_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_FLOOR_LIGHT =
            registerRoyalBlockItem("floor_light", () -> new BlockItem(ModBlocks.ROYAL_FLOOR_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_CHANDELIER_LIGHT =
            registerRoyalBlockItem("chandelier", () -> new BlockItem(ModBlocks.ROYAL_CHANDELIER_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_TABLE =
            registerRoyalBlockItem("table", () -> new BlockItem(ModBlocks.ROYAL_TABLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_CHAIR =
            registerRoyalBlockItem("chair", () -> new BlockItem(ModBlocks.ROYAL_CHAIR.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_BENCH =
            registerRoyalBlockItem("bench", () -> new BlockItem(ModBlocks.ROYAL_BENCH.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_STOOL =
            registerRoyalBlockItem("stool", () -> new BlockItem(ModBlocks.ROYAL_STOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_CUSHION =
            registerRoyalBlockItem("cushion", () -> new BlockItem(ModBlocks.ROYAL_CUSHION.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_SHELF =
            registerRoyalBlockItem("shelf", () -> new BlockItem(ModBlocks.ROYAL_SHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_SOFA =
            registerRoyalBlockItem("sofa", () -> new BlockItem(ModBlocks.ROYAL_SOFA.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_DRAWER =
            registerRoyalBlockItem("drawer", () -> new BlockItem(ModBlocks.ROYAL_DRAWER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_LOCKBOX =
            registerRoyalBlockItem("lockbox", () -> new BlockItem(ModBlocks.ROYAL_LOCKBOX.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_DESK_LEFT =
            registerRoyalBlockItem("desk_left", () -> new BlockItem(ModBlocks.ROYAL_DESK_LEFT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_DESK_RIGHT =
            registerRoyalBlockItem("desk_right", () -> new BlockItem(ModBlocks.ROYAL_DESK_RIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_DRESSER =
            registerRoyalBlockItem("dresser", () -> new BlockItem(ModBlocks.ROYAL_DRESSER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_COUNTER =
            registerRoyalBlockItem("counter", () -> new BlockItem(ModBlocks.ROYAL_COUNTER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_CHEST =
            registerRoyalBlockItem("chest", () -> new BlockItem(ModBlocks.ROYAL_CHEST.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_WARDROBE_BOTTOM =
            registerRoyalBlockItem("wardrobe_bottom", () -> new BlockItem(ModBlocks.ROYAL_WARDROBE_BOTTOM.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_WARDROBE_TOP =
            registerRoyalBlockItem("wardrobe_top", () -> new BlockItem(ModBlocks.ROYAL_WARDROBE_TOP.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_BOOKSHELF =
            registerRoyalBlockItem("bookshelf", () -> new BlockItem(ModBlocks.ROYAL_BOOKSHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_BED_SINGLE =
            registerRoyalBlockItem("bed_single", () -> new BlockItem(ModBlocks.ROYAL_BED_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_BED_DOUBLE =
            registerRoyalBlockItem("bed_double", () -> new BlockItem(ModBlocks.ROYAL_BED_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_DOOR_SINGLE =
            registerRoyalBlockItem("door_single", () -> new BlockItem(ModBlocks.ROYAL_DOOR_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_DOOR_DOUBLE =
            registerRoyalBlockItem("door_double", () -> new BlockItem(ModBlocks.ROYAL_DOOR_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_PAINTING_SMALL =
            registerRoyalBlockItem("painting_small", () -> new BlockItem(ModBlocks.ROYAL_PAINTING_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_PAINTING_WIDE =
            registerRoyalBlockItem("painting_wide", () -> new BlockItem(ModBlocks.ROYAL_PAINTING_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_OVEN =
            registerRoyalBlockItem("oven", () -> new BlockItem(ModBlocks.ROYAL_OVEN.get(), new Item.Properties()));

    // --- Royal Decorations ---
    public static final DeferredHolder<Item, BlockItem> ROYAL_CROWN =
            registerRoyalBlockItem("crown", () -> new BlockItem(ModBlocks.ROYAL_CROWN.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_CUSHIONED_CROWN =
            registerRoyalBlockItem("cushioned_crown", () -> new BlockItem(ModBlocks.ROYAL_CUSHIONED_CROWN.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_CANDELABRA =
            registerRoyalBlockItem("candelabra", () -> new BlockItem(ModBlocks.ROYAL_CANDELABRA.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_CHALICES =
            registerRoyalBlockItem("chalices", () -> new BlockItem(ModBlocks.ROYAL_CHALICES.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_PLATTER =
            registerRoyalBlockItem("platter", () -> new BlockItem(ModBlocks.ROYAL_PLATTER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_FOOD =
            registerRoyalBlockItem("food", () -> new BlockItem(ModBlocks.ROYAL_FOOD.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_FLOOR_CUSHION =
            registerRoyalBlockItem("floor_cushion", () -> new BlockItem(ModBlocks.ROYAL_FLOOR_CUSHION.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_WALL_MIRROR =
            registerRoyalBlockItem("wall_mirror", () -> new BlockItem(ModBlocks.ROYAL_WALL_MIRROR.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> ROYAL_WALL_MIRROR_TALL =
            registerRoyalBlockItem("wall_mirror_tall", () -> new BlockItem(ModBlocks.ROYAL_WALL_MIRROR_TALL.get(), new Item.Properties()));

    // ========================================================================
    // Dunmer set (fantasyfurniture_dunmer:*)
    // ========================================================================

    public static final DeferredHolder<Item, BlockItem> DUNMER_WOOL =
            registerDunmerBlockItem("wool", () -> new BlockItem(ModBlocks.DUNMER_WOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_CARPET =
            registerDunmerBlockItem("carpet", () -> new BlockItem(ModBlocks.DUNMER_CARPET.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_WALL_LIGHT =
            registerDunmerBlockItem("wall_light", () -> new BlockItem(ModBlocks.DUNMER_WALL_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_FLOOR_LIGHT =
            registerDunmerBlockItem("floor_light", () -> new BlockItem(ModBlocks.DUNMER_FLOOR_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_CHANDELIER_LIGHT =
            registerDunmerBlockItem("chandelier", () -> new BlockItem(ModBlocks.DUNMER_CHANDELIER_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_TABLE_SMALL =
            registerDunmerBlockItem("table_small", () -> new BlockItem(ModBlocks.DUNMER_TABLE_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_TABLE_WIDE =
            registerDunmerBlockItem("table_wide", () -> new BlockItem(ModBlocks.DUNMER_TABLE_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_TABLE_LARGE =
            registerDunmerBlockItem("table_large", () -> new BlockItem(ModBlocks.DUNMER_TABLE_LARGE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_CHAIR =
            registerDunmerBlockItem("chair", () -> new BlockItem(ModBlocks.DUNMER_CHAIR.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_BENCH =
            registerDunmerBlockItem("bench", () -> new BlockItem(ModBlocks.DUNMER_BENCH.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_STOOL =
            registerDunmerBlockItem("stool", () -> new BlockItem(ModBlocks.DUNMER_STOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_CUSHION =
            registerDunmerBlockItem("cushion", () -> new BlockItem(ModBlocks.DUNMER_CUSHION.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_SHELF =
            registerDunmerBlockItem("shelf", () -> new BlockItem(ModBlocks.DUNMER_SHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_SOFA =
            registerDunmerBlockItem("sofa", () -> new BlockItem(ModBlocks.DUNMER_SOFA.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_DRAWER =
            registerDunmerBlockItem("drawer", () -> new BlockItem(ModBlocks.DUNMER_DRAWER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_LOCKBOX =
            registerDunmerBlockItem("lockbox", () -> new BlockItem(ModBlocks.DUNMER_LOCKBOX.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_DESK_LEFT =
            registerDunmerBlockItem("desk_left", () -> new BlockItem(ModBlocks.DUNMER_DESK_LEFT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_DESK_RIGHT =
            registerDunmerBlockItem("desk_right", () -> new BlockItem(ModBlocks.DUNMER_DESK_RIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_DRESSER =
            registerDunmerBlockItem("dresser", () -> new BlockItem(ModBlocks.DUNMER_DRESSER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_COUNTER =
            registerDunmerBlockItem("counter", () -> new BlockItem(ModBlocks.DUNMER_COUNTER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_CHEST =
            registerDunmerBlockItem("chest", () -> new BlockItem(ModBlocks.DUNMER_CHEST.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_WARDROBE_BOTTOM =
            registerDunmerBlockItem("wardrobe_bottom", () -> new BlockItem(ModBlocks.DUNMER_WARDROBE_BOTTOM.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_WARDROBE_TOP =
            registerDunmerBlockItem("wardrobe_top", () -> new BlockItem(ModBlocks.DUNMER_WARDROBE_TOP.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_BOOKSHELF =
            registerDunmerBlockItem("bookshelf", () -> new BlockItem(ModBlocks.DUNMER_BOOKSHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_BED_SINGLE =
            registerDunmerBlockItem("bed_single", () -> new BlockItem(ModBlocks.DUNMER_BED_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_BED_DOUBLE =
            registerDunmerBlockItem("bed_double", () -> new BlockItem(ModBlocks.DUNMER_BED_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_DOOR_SINGLE =
            registerDunmerBlockItem("door_single", () -> new BlockItem(ModBlocks.DUNMER_DOOR_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_DOOR_DOUBLE =
            registerDunmerBlockItem("door_double", () -> new BlockItem(ModBlocks.DUNMER_DOOR_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_PAINTING_SMALL =
            registerDunmerBlockItem("painting_small", () -> new BlockItem(ModBlocks.DUNMER_PAINTING_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_PAINTING_WIDE =
            registerDunmerBlockItem("painting_wide", () -> new BlockItem(ModBlocks.DUNMER_PAINTING_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_OVEN =
            registerDunmerBlockItem("oven", () -> new BlockItem(ModBlocks.DUNMER_OVEN.get(), new Item.Properties()));

    // --- Dunmer Decorations ---
    public static final DeferredHolder<Item, BlockItem> DUNMER_POTTERY_0 =
            registerDunmerBlockItem("pottery_0", () -> new BlockItem(ModBlocks.DUNMER_POTTERY_0.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> DUNMER_POTTERY_1 =
            registerDunmerBlockItem("pottery_1", () -> new BlockItem(ModBlocks.DUNMER_POTTERY_1.get(), new Item.Properties()));

    // ========================================================================
    // Venthyr set (fantasyfurniture_venthyr:*)
    // ========================================================================

    public static final DeferredHolder<Item, BlockItem> VENTHYR_WOOL =
            registerVenthyrBlockItem("wool", () -> new BlockItem(ModBlocks.VENTHYR_WOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_CARPET =
            registerVenthyrBlockItem("carpet", () -> new BlockItem(ModBlocks.VENTHYR_CARPET.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_WALL_LIGHT =
            registerVenthyrBlockItem("wall_light", () -> new BlockItem(ModBlocks.VENTHYR_WALL_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_FLOOR_LIGHT =
            registerVenthyrBlockItem("floor_light", () -> new BlockItem(ModBlocks.VENTHYR_FLOOR_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_CHANDELIER_LIGHT =
            registerVenthyrBlockItem("chandelier", () -> new BlockItem(ModBlocks.VENTHYR_CHANDELIER_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_TABLE_SMALL =
            registerVenthyrBlockItem("table_small", () -> new BlockItem(ModBlocks.VENTHYR_TABLE_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_TABLE_SMALL_FANCY =
            registerVenthyrBlockItem("table_small_fancy", () -> new BlockItem(ModBlocks.VENTHYR_TABLE_SMALL_FANCY.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_TABLE_WIDE =
            registerVenthyrBlockItem("table_wide", () -> new BlockItem(ModBlocks.VENTHYR_TABLE_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_TABLE_WIDE_FANCY =
            registerVenthyrBlockItem("table_wide_fancy", () -> new BlockItem(ModBlocks.VENTHYR_TABLE_WIDE_FANCY.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_TABLE_LARGE =
            registerVenthyrBlockItem("table_large", () -> new BlockItem(ModBlocks.VENTHYR_TABLE_LARGE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_TABLE_LARGE_FANCY =
            registerVenthyrBlockItem("table_large_fancy", () -> new BlockItem(ModBlocks.VENTHYR_TABLE_LARGE_FANCY.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_CHAIR =
            registerVenthyrBlockItem("chair", () -> new BlockItem(ModBlocks.VENTHYR_CHAIR.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_BENCH =
            registerVenthyrBlockItem("bench", () -> new BlockItem(ModBlocks.VENTHYR_BENCH.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_STOOL =
            registerVenthyrBlockItem("stool", () -> new BlockItem(ModBlocks.VENTHYR_STOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_CUSHION =
            registerVenthyrBlockItem("cushion", () -> new BlockItem(ModBlocks.VENTHYR_CUSHION.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_SHELF =
            registerVenthyrBlockItem("shelf", () -> new BlockItem(ModBlocks.VENTHYR_SHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_SOFA =
            registerVenthyrBlockItem("sofa", () -> new BlockItem(ModBlocks.VENTHYR_SOFA.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_DRAWER =
            registerVenthyrBlockItem("drawer", () -> new BlockItem(ModBlocks.VENTHYR_DRAWER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_LOCKBOX =
            registerVenthyrBlockItem("lockbox", () -> new BlockItem(ModBlocks.VENTHYR_LOCKBOX.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_DESK_LEFT =
            registerVenthyrBlockItem("desk_left", () -> new BlockItem(ModBlocks.VENTHYR_DESK_LEFT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_DESK_RIGHT =
            registerVenthyrBlockItem("desk_right", () -> new BlockItem(ModBlocks.VENTHYR_DESK_RIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_DRESSER =
            registerVenthyrBlockItem("dresser", () -> new BlockItem(ModBlocks.VENTHYR_DRESSER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_COUNTER =
            registerVenthyrBlockItem("counter", () -> new BlockItem(ModBlocks.VENTHYR_COUNTER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_CHEST =
            registerVenthyrBlockItem("chest", () -> new BlockItem(ModBlocks.VENTHYR_CHEST.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_WARDROBE_BOTTOM =
            registerVenthyrBlockItem("wardrobe_bottom", () -> new BlockItem(ModBlocks.VENTHYR_WARDROBE_BOTTOM.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_WARDROBE_TOP =
            registerVenthyrBlockItem("wardrobe_top", () -> new BlockItem(ModBlocks.VENTHYR_WARDROBE_TOP.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_BOOKSHELF =
            registerVenthyrBlockItem("bookshelf", () -> new BlockItem(ModBlocks.VENTHYR_BOOKSHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_BED_SINGLE =
            registerVenthyrBlockItem("bed_single", () -> new BlockItem(ModBlocks.VENTHYR_BED_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_BED_DOUBLE =
            registerVenthyrBlockItem("bed_double", () -> new BlockItem(ModBlocks.VENTHYR_BED_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_DOOR_SINGLE =
            registerVenthyrBlockItem("door_single", () -> new BlockItem(ModBlocks.VENTHYR_DOOR_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_DOOR_DOUBLE =
            registerVenthyrBlockItem("door_double", () -> new BlockItem(ModBlocks.VENTHYR_DOOR_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_PAINTING_SMALL =
            registerVenthyrBlockItem("painting_small", () -> new BlockItem(ModBlocks.VENTHYR_PAINTING_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_PAINTING_WIDE =
            registerVenthyrBlockItem("painting_wide", () -> new BlockItem(ModBlocks.VENTHYR_PAINTING_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_OVEN =
            registerVenthyrBlockItem("oven", () -> new BlockItem(ModBlocks.VENTHYR_OVEN.get(), new Item.Properties()));

    // --- Venthyr Decorations ---
    public static final DeferredHolder<Item, BlockItem> VENTHYR_FOOD_0 =
            registerVenthyrBlockItem("food_0", () -> new BlockItem(ModBlocks.VENTHYR_FOOD_0.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_FOOD_1 =
            registerVenthyrBlockItem("food_1", () -> new BlockItem(ModBlocks.VENTHYR_FOOD_1.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_TOMES =
            registerVenthyrBlockItem("tomes", () -> new BlockItem(ModBlocks.VENTHYR_TOMES.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_TEA_CUPS =
            registerVenthyrBlockItem("tea_cups", () -> new BlockItem(ModBlocks.VENTHYR_TEA_CUPS.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_PLATTER =
            registerVenthyrBlockItem("platter", () -> new BlockItem(ModBlocks.VENTHYR_PLATTER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_TEA_SET =
            registerVenthyrBlockItem("tea_set", () -> new BlockItem(ModBlocks.VENTHYR_TEA_SET.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_BANNER =
            registerVenthyrBlockItem("banner", () -> new BlockItem(ModBlocks.VENTHYR_BANNER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_CANDLES =
            registerVenthyrBlockItem("candles", () -> new BlockItem(ModBlocks.VENTHYR_CANDLES.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_CHALICES =
            registerVenthyrBlockItem("chalices", () -> new BlockItem(ModBlocks.VENTHYR_CHALICES.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> VENTHYR_WIDOW_BLOOM =
            registerVenthyrBlockItem("widow_bloom", () -> new BlockItem(ModBlocks.VENTHYR_WIDOW_BLOOM.get(), new Item.Properties()));

    // ========================================================================
    // Necrolord set (fantasyfurniture_necrolord:*)
    // ========================================================================

    public static final DeferredHolder<Item, BlockItem> NECROLORD_WOOL =
            registerNecrolordBlockItem("wool", () -> new BlockItem(ModBlocks.NECROLORD_WOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_CARPET =
            registerNecrolordBlockItem("carpet", () -> new BlockItem(ModBlocks.NECROLORD_CARPET.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_WALL_LIGHT =
            registerNecrolordBlockItem("wall_light", () -> new BlockItem(ModBlocks.NECROLORD_WALL_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_FLOOR_LIGHT =
            registerNecrolordBlockItem("floor_light", () -> new BlockItem(ModBlocks.NECROLORD_FLOOR_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_CHANDELIER_LIGHT =
            registerNecrolordBlockItem("chandelier", () -> new BlockItem(ModBlocks.NECROLORD_CHANDELIER_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_TABLE_SMALL =
            registerNecrolordBlockItem("table_small", () -> new BlockItem(ModBlocks.NECROLORD_TABLE_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_TABLE_WIDE =
            registerNecrolordBlockItem("table_wide", () -> new BlockItem(ModBlocks.NECROLORD_TABLE_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_TABLE_LARGE =
            registerNecrolordBlockItem("table_large", () -> new BlockItem(ModBlocks.NECROLORD_TABLE_LARGE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_CHAIR =
            registerNecrolordBlockItem("chair", () -> new BlockItem(ModBlocks.NECROLORD_CHAIR.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_BENCH =
            registerNecrolordBlockItem("bench", () -> new BlockItem(ModBlocks.NECROLORD_BENCH.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_STOOL =
            registerNecrolordBlockItem("stool", () -> new BlockItem(ModBlocks.NECROLORD_STOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_CUSHION =
            registerNecrolordBlockItem("cushion", () -> new BlockItem(ModBlocks.NECROLORD_CUSHION.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_SHELF =
            registerNecrolordBlockItem("shelf", () -> new BlockItem(ModBlocks.NECROLORD_SHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_SOFA =
            registerNecrolordBlockItem("sofa", () -> new BlockItem(ModBlocks.NECROLORD_SOFA.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_DRAWER =
            registerNecrolordBlockItem("drawer", () -> new BlockItem(ModBlocks.NECROLORD_DRAWER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_LOCKBOX =
            registerNecrolordBlockItem("lockbox", () -> new BlockItem(ModBlocks.NECROLORD_LOCKBOX.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_DESK_LEFT =
            registerNecrolordBlockItem("desk_left", () -> new BlockItem(ModBlocks.NECROLORD_DESK_LEFT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_DESK_RIGHT =
            registerNecrolordBlockItem("desk_right", () -> new BlockItem(ModBlocks.NECROLORD_DESK_RIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_DRESSER =
            registerNecrolordBlockItem("dresser", () -> new BlockItem(ModBlocks.NECROLORD_DRESSER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_COUNTER =
            registerNecrolordBlockItem("counter", () -> new BlockItem(ModBlocks.NECROLORD_COUNTER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_CHEST =
            registerNecrolordBlockItem("chest", () -> new BlockItem(ModBlocks.NECROLORD_CHEST.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_WARDROBE_BOTTOM =
            registerNecrolordBlockItem("wardrobe_bottom", () -> new BlockItem(ModBlocks.NECROLORD_WARDROBE_BOTTOM.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_WARDROBE_TOP =
            registerNecrolordBlockItem("wardrobe_top", () -> new BlockItem(ModBlocks.NECROLORD_WARDROBE_TOP.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_BOOKSHELF =
            registerNecrolordBlockItem("bookshelf", () -> new BlockItem(ModBlocks.NECROLORD_BOOKSHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_BED_SINGLE =
            registerNecrolordBlockItem("bed_single", () -> new BlockItem(ModBlocks.NECROLORD_BED_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_BED_DOUBLE =
            registerNecrolordBlockItem("bed_double", () -> new BlockItem(ModBlocks.NECROLORD_BED_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_DOOR_SINGLE =
            registerNecrolordBlockItem("door_single", () -> new BlockItem(ModBlocks.NECROLORD_DOOR_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_DOOR_DOUBLE =
            registerNecrolordBlockItem("door_double", () -> new BlockItem(ModBlocks.NECROLORD_DOOR_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_PAINTING_SMALL =
            registerNecrolordBlockItem("painting_small", () -> new BlockItem(ModBlocks.NECROLORD_PAINTING_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_PAINTING_WIDE =
            registerNecrolordBlockItem("painting_wide", () -> new BlockItem(ModBlocks.NECROLORD_PAINTING_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> NECROLORD_OVEN =
            registerNecrolordBlockItem("oven", () -> new BlockItem(ModBlocks.NECROLORD_OVEN.get(), new Item.Properties()));

    // --- Necrolord Decorations ---
    public static final DeferredHolder<Item, BlockItem> NECROLORD_CANDELABRA =
            registerNecrolordBlockItem("candelabra", () -> new BlockItem(ModBlocks.NECROLORD_CANDELABRA.get(), new Item.Properties()));

    // ========================================================================
    // Bone-Skeleton set BlockItems (fantasyfurniture_bone_skeleton:*)
    // ========================================================================

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_WOOL =
            registerBoneSkeletonBlockItem("wool", () -> new BlockItem(ModBlocks.BONE_SKELETON_WOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_CARPET =
            registerBoneSkeletonBlockItem("carpet", () -> new BlockItem(ModBlocks.BONE_SKELETON_CARPET.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_WALL_LIGHT =
            registerBoneSkeletonBlockItem("wall_light", () -> new BlockItem(ModBlocks.BONE_SKELETON_WALL_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_FLOOR_LIGHT =
            registerBoneSkeletonBlockItem("floor_light", () -> new BlockItem(ModBlocks.BONE_SKELETON_FLOOR_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_CHANDELIER_LIGHT =
            registerBoneSkeletonBlockItem("chandelier", () -> new BlockItem(ModBlocks.BONE_SKELETON_CHANDELIER_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_TABLE_SMALL =
            registerBoneSkeletonBlockItem("table_small", () -> new BlockItem(ModBlocks.BONE_SKELETON_TABLE_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_TABLE_WIDE =
            registerBoneSkeletonBlockItem("table_wide", () -> new BlockItem(ModBlocks.BONE_SKELETON_TABLE_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_TABLE_LARGE =
            registerBoneSkeletonBlockItem("table_large", () -> new BlockItem(ModBlocks.BONE_SKELETON_TABLE_LARGE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_CHAIR =
            registerBoneSkeletonBlockItem("chair", () -> new BlockItem(ModBlocks.BONE_SKELETON_CHAIR.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_BENCH =
            registerBoneSkeletonBlockItem("bench", () -> new BlockItem(ModBlocks.BONE_SKELETON_BENCH.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_STOOL =
            registerBoneSkeletonBlockItem("stool", () -> new BlockItem(ModBlocks.BONE_SKELETON_STOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_SKULL =
            registerBoneSkeletonBlockItem("skull", () -> new BlockItem(ModBlocks.BONE_SKELETON_SKULL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_SHELF =
            registerBoneSkeletonBlockItem("shelf", () -> new BlockItem(ModBlocks.BONE_SKELETON_SHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_SOFA =
            registerBoneSkeletonBlockItem("sofa", () -> new BlockItem(ModBlocks.BONE_SKELETON_SOFA.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_DRAWER =
            registerBoneSkeletonBlockItem("drawer", () -> new BlockItem(ModBlocks.BONE_SKELETON_DRAWER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_LOCKBOX =
            registerBoneSkeletonBlockItem("lockbox", () -> new BlockItem(ModBlocks.BONE_SKELETON_LOCKBOX.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_DESK_LEFT =
            registerBoneSkeletonBlockItem("desk_left", () -> new BlockItem(ModBlocks.BONE_SKELETON_DESK_LEFT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_DESK_RIGHT =
            registerBoneSkeletonBlockItem("desk_right", () -> new BlockItem(ModBlocks.BONE_SKELETON_DESK_RIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_DRESSER =
            registerBoneSkeletonBlockItem("dresser", () -> new BlockItem(ModBlocks.BONE_SKELETON_DRESSER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_COUNTER =
            registerBoneSkeletonBlockItem("counter", () -> new BlockItem(ModBlocks.BONE_SKELETON_COUNTER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_CHEST =
            registerBoneSkeletonBlockItem("chest", () -> new BlockItem(ModBlocks.BONE_SKELETON_CHEST.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_WARDROBE_BOTTOM =
            registerBoneSkeletonBlockItem("wardrobe_bottom", () -> new BlockItem(ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_WARDROBE_TOP =
            registerBoneSkeletonBlockItem("wardrobe_top", () -> new BlockItem(ModBlocks.BONE_SKELETON_WARDROBE_TOP.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_BOOKSHELF =
            registerBoneSkeletonBlockItem("bookshelf", () -> new BlockItem(ModBlocks.BONE_SKELETON_BOOKSHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_BED_SINGLE =
            registerBoneSkeletonBlockItem("bed_single", () -> new BlockItem(ModBlocks.BONE_SKELETON_BED_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_BED_DOUBLE =
            registerBoneSkeletonBlockItem("bed_double", () -> new BlockItem(ModBlocks.BONE_SKELETON_BED_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_DOOR_SINGLE =
            registerBoneSkeletonBlockItem("door_single", () -> new BlockItem(ModBlocks.BONE_SKELETON_DOOR_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_DOOR_DOUBLE =
            registerBoneSkeletonBlockItem("door_double", () -> new BlockItem(ModBlocks.BONE_SKELETON_DOOR_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_PAINTING_SMALL =
            registerBoneSkeletonBlockItem("painting_small", () -> new BlockItem(ModBlocks.BONE_SKELETON_PAINTING_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_PAINTING_WIDE =
            registerBoneSkeletonBlockItem("painting_wide", () -> new BlockItem(ModBlocks.BONE_SKELETON_PAINTING_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_OVEN =
            registerBoneSkeletonBlockItem("oven", () -> new BlockItem(ModBlocks.BONE_SKELETON_OVEN.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_CHALICES =
            registerBoneSkeletonBlockItem("chalices", () -> new BlockItem(ModBlocks.BONE_SKELETON_CHALICES.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_PILE =
            registerBoneSkeletonBlockItem("pile", () -> new BlockItem(ModBlocks.BONE_SKELETON_PILE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_SKELETON_SKULL_BLOSSOMS =
            registerBoneSkeletonBlockItem("skull_blossoms", () -> new BlockItem(ModBlocks.BONE_SKELETON_SKULL_BLOSSOMS.get(), new Item.Properties()));

    // ========================================================================
    // Bone-Wither set BlockItems (fantasyfurniture_bone_wither:*)
    // ========================================================================

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_WOOL =
            registerBoneWitherBlockItem("wool", () -> new BlockItem(ModBlocks.BONE_WITHER_WOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_CARPET =
            registerBoneWitherBlockItem("carpet", () -> new BlockItem(ModBlocks.BONE_WITHER_CARPET.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_WALL_LIGHT =
            registerBoneWitherBlockItem("wall_light", () -> new BlockItem(ModBlocks.BONE_WITHER_WALL_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_FLOOR_LIGHT =
            registerBoneWitherBlockItem("floor_light", () -> new BlockItem(ModBlocks.BONE_WITHER_FLOOR_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_CHANDELIER_LIGHT =
            registerBoneWitherBlockItem("chandelier", () -> new BlockItem(ModBlocks.BONE_WITHER_CHANDELIER_LIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_TABLE_SMALL =
            registerBoneWitherBlockItem("table_small", () -> new BlockItem(ModBlocks.BONE_WITHER_TABLE_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_TABLE_WIDE =
            registerBoneWitherBlockItem("table_wide", () -> new BlockItem(ModBlocks.BONE_WITHER_TABLE_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_TABLE_LARGE =
            registerBoneWitherBlockItem("table_large", () -> new BlockItem(ModBlocks.BONE_WITHER_TABLE_LARGE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_CHAIR =
            registerBoneWitherBlockItem("chair", () -> new BlockItem(ModBlocks.BONE_WITHER_CHAIR.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_BENCH =
            registerBoneWitherBlockItem("bench", () -> new BlockItem(ModBlocks.BONE_WITHER_BENCH.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_STOOL =
            registerBoneWitherBlockItem("stool", () -> new BlockItem(ModBlocks.BONE_WITHER_STOOL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_SKULL =
            registerBoneWitherBlockItem("skull", () -> new BlockItem(ModBlocks.BONE_WITHER_SKULL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_SHELF =
            registerBoneWitherBlockItem("shelf", () -> new BlockItem(ModBlocks.BONE_WITHER_SHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_SOFA =
            registerBoneWitherBlockItem("sofa", () -> new BlockItem(ModBlocks.BONE_WITHER_SOFA.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_DRAWER =
            registerBoneWitherBlockItem("drawer", () -> new BlockItem(ModBlocks.BONE_WITHER_DRAWER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_LOCKBOX =
            registerBoneWitherBlockItem("lockbox", () -> new BlockItem(ModBlocks.BONE_WITHER_LOCKBOX.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_DESK_LEFT =
            registerBoneWitherBlockItem("desk_left", () -> new BlockItem(ModBlocks.BONE_WITHER_DESK_LEFT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_DESK_RIGHT =
            registerBoneWitherBlockItem("desk_right", () -> new BlockItem(ModBlocks.BONE_WITHER_DESK_RIGHT.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_DRESSER =
            registerBoneWitherBlockItem("dresser", () -> new BlockItem(ModBlocks.BONE_WITHER_DRESSER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_COUNTER =
            registerBoneWitherBlockItem("counter", () -> new BlockItem(ModBlocks.BONE_WITHER_COUNTER.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_CHEST =
            registerBoneWitherBlockItem("chest", () -> new BlockItem(ModBlocks.BONE_WITHER_CHEST.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_WARDROBE_BOTTOM =
            registerBoneWitherBlockItem("wardrobe_bottom", () -> new BlockItem(ModBlocks.BONE_WITHER_WARDROBE_BOTTOM.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_WARDROBE_TOP =
            registerBoneWitherBlockItem("wardrobe_top", () -> new BlockItem(ModBlocks.BONE_WITHER_WARDROBE_TOP.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_BOOKSHELF =
            registerBoneWitherBlockItem("bookshelf", () -> new BlockItem(ModBlocks.BONE_WITHER_BOOKSHELF.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_BED_SINGLE =
            registerBoneWitherBlockItem("bed_single", () -> new BlockItem(ModBlocks.BONE_WITHER_BED_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_BED_DOUBLE =
            registerBoneWitherBlockItem("bed_double", () -> new BlockItem(ModBlocks.BONE_WITHER_BED_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_DOOR_SINGLE =
            registerBoneWitherBlockItem("door_single", () -> new BlockItem(ModBlocks.BONE_WITHER_DOOR_SINGLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_DOOR_DOUBLE =
            registerBoneWitherBlockItem("door_double", () -> new BlockItem(ModBlocks.BONE_WITHER_DOOR_DOUBLE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_PAINTING_SMALL =
            registerBoneWitherBlockItem("painting_small", () -> new BlockItem(ModBlocks.BONE_WITHER_PAINTING_SMALL.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_PAINTING_WIDE =
            registerBoneWitherBlockItem("painting_wide", () -> new BlockItem(ModBlocks.BONE_WITHER_PAINTING_WIDE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_OVEN =
            registerBoneWitherBlockItem("oven", () -> new BlockItem(ModBlocks.BONE_WITHER_OVEN.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_CHALICES =
            registerBoneWitherBlockItem("chalices", () -> new BlockItem(ModBlocks.BONE_WITHER_CHALICES.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_PILE =
            registerBoneWitherBlockItem("pile", () -> new BlockItem(ModBlocks.BONE_WITHER_PILE.get(), new Item.Properties()));

    public static final DeferredHolder<Item, BlockItem> BONE_WITHER_SKULL_BLOSSOMS =
            registerBoneWitherBlockItem("skull_blossoms", () -> new BlockItem(ModBlocks.BONE_WITHER_SKULL_BLOSSOMS.get(), new Item.Properties()));

    // --- Helpers ---
    private static DeferredHolder<Item, BlockItem> registerMainBlockItem(String name, Supplier<BlockItem> supplier) {
        DeferredHolder<Item, BlockItem> holder = ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(holder);
        return holder;
    }

    private static DeferredHolder<Item, BlockItem> registerNordicBlockItem(String name, Supplier<BlockItem> supplier) {
        DeferredHolder<Item, BlockItem> holder = NORDIC_ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(holder);
        return holder;
    }

    private static DeferredHolder<Item, BlockItem> registerRoyalBlockItem(String name, Supplier<BlockItem> supplier) {
        DeferredHolder<Item, BlockItem> holder = ROYAL_ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(holder);
        return holder;
    }

    private static DeferredHolder<Item, BlockItem> registerDunmerBlockItem(String name, Supplier<BlockItem> supplier) {
        DeferredHolder<Item, BlockItem> holder = DUNMER_ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(holder);
        return holder;
    }

    private static DeferredHolder<Item, BlockItem> registerVenthyrBlockItem(String name, Supplier<BlockItem> supplier) {
        DeferredHolder<Item, BlockItem> holder = VENTHYR_ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(holder);
        return holder;
    }

    private static DeferredHolder<Item, BlockItem> registerNecrolordBlockItem(String name, Supplier<BlockItem> supplier) {
        DeferredHolder<Item, BlockItem> holder = NECROLORD_ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(holder);
        return holder;
    }

    private static DeferredHolder<Item, BlockItem> registerBoneSkeletonBlockItem(String name, Supplier<BlockItem> supplier) {
        DeferredHolder<Item, BlockItem> holder = BONE_SKELETON_ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(holder);
        return holder;
    }

    private static DeferredHolder<Item, BlockItem> registerBoneWitherBlockItem(String name, Supplier<BlockItem> supplier) {
        DeferredHolder<Item, BlockItem> holder = BONE_WITHER_ITEMS.register(name, supplier);
        CREATIVE_TAB_ITEMS.add(holder);
        return holder;
    }

    private ModItems() {}
}
