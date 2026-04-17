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

    private ModItems() {}
}
