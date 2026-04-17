package xyz.apex.minecraft.fantasyfurniture.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureStationBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicBedDoubleBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicBedSingleBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicBenchBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicBookshelfBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicChairBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicChandelierLightBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicChestBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicCounterBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicCushionBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDeskLeftBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDeskRightBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDoorDoubleBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDoorSingleBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDrawerBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicDresserBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicFloorLightBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicLockboxBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicOvenBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicPaintingSmallBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicPaintingWideBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicShelfBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicSofaBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicStoolBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicTableBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicWallLightBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicWardrobeBottomBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicWardrobeTopBlock;

public final class ModBlocks {
    // Main mod namespace — matches 1.20.x upstream "fantasyfurniture"
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FantasyFurniture.MOD_ID);

    // Nordic submodule namespace — matches 1.20.x upstream "fantasyfurniture_nordic"
    // Kept as a separate namespace so existing 1.20.x worlds can load their blocks unchanged.
    public static final DeferredRegister<Block> NORDIC_BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FantasyFurniture.NORDIC_ID);

    // --- Furniture Station (fantasyfurniture:furniture_station) ---
    public static final DeferredHolder<Block, FurnitureStationBlock> FURNITURE_STATION =
            BLOCKS.register("furniture_station",
                    () -> new FurnitureStationBlock(Properties.ofFullCopy(Blocks.CRAFTING_TABLE).noOcclusion()));

    // --- Nordic Wool / Carpet ---
    public static final DeferredHolder<Block, Block> NORDIC_WOOL =
            NORDIC_BLOCKS.register("wool",
                    () -> new Block(Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    public static final DeferredHolder<Block, CarpetBlock> NORDIC_CARPET =
            NORDIC_BLOCKS.register("carpet",
                    () -> new CarpetBlock(Properties.ofFullCopy(Blocks.WHITE_CARPET)));

    // --- Nordic Light Blocks (torch-like: instabreak, destroyed by pistons) ---
    public static final DeferredHolder<Block, NordicWallLightBlock> NORDIC_WALL_LIGHT =
            NORDIC_BLOCKS.register("wall_light",
                    () -> new NordicWallLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicFloorLightBlock> NORDIC_FLOOR_LIGHT =
            NORDIC_BLOCKS.register("floor_light",
                    () -> new NordicFloorLightBlock(lightProps().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicChandelierLightBlock> NORDIC_CHANDELIER_LIGHT =
            NORDIC_BLOCKS.register("chandelier",
                    () -> new NordicChandelierLightBlock(lightProps()));

    // --- Nordic Seating / Tables ---
    public static final DeferredHolder<Block, NordicTableBlock> NORDIC_TABLE =
            NORDIC_BLOCKS.register("table",
                    () -> new NordicTableBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChairBlock> NORDIC_CHAIR =
            NORDIC_BLOCKS.register("chair",
                    () -> new NordicChairBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBenchBlock> NORDIC_BENCH =
            NORDIC_BLOCKS.register("bench",
                    () -> new NordicBenchBlock(woodProps()));

    public static final DeferredHolder<Block, NordicStoolBlock> NORDIC_STOOL =
            NORDIC_BLOCKS.register("stool",
                    () -> new NordicStoolBlock(woodProps()));

    public static final DeferredHolder<Block, NordicCushionBlock> NORDIC_CUSHION =
            NORDIC_BLOCKS.register("cushion",
                    () -> new NordicCushionBlock(woodProps()));

    public static final DeferredHolder<Block, NordicShelfBlock> NORDIC_SHELF =
            NORDIC_BLOCKS.register("shelf",
                    () -> new NordicShelfBlock(woodProps()));

    public static final DeferredHolder<Block, NordicSofaBlock> NORDIC_SOFA =
            NORDIC_BLOCKS.register("sofa",
                    () -> new NordicSofaBlock(woodProps()));

    // --- Nordic Storage (Small) ---
    public static final DeferredHolder<Block, NordicDrawerBlock> NORDIC_DRAWER =
            NORDIC_BLOCKS.register("drawer",
                    () -> new NordicDrawerBlock(woodProps()));

    public static final DeferredHolder<Block, NordicLockboxBlock> NORDIC_LOCKBOX =
            NORDIC_BLOCKS.register("lockbox",
                    () -> new NordicLockboxBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDeskLeftBlock> NORDIC_DESK_LEFT =
            NORDIC_BLOCKS.register("desk_left",
                    () -> new NordicDeskLeftBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDeskRightBlock> NORDIC_DESK_RIGHT =
            NORDIC_BLOCKS.register("desk_right",
                    () -> new NordicDeskRightBlock(woodProps()));

    // --- Nordic Storage (Medium) ---
    public static final DeferredHolder<Block, NordicDresserBlock> NORDIC_DRESSER =
            NORDIC_BLOCKS.register("dresser",
                    () -> new NordicDresserBlock(woodProps()));

    public static final DeferredHolder<Block, NordicCounterBlock> NORDIC_COUNTER =
            NORDIC_BLOCKS.register("counter",
                    () -> new NordicCounterBlock(woodProps()));

    // --- Nordic Storage (Large) ---
    public static final DeferredHolder<Block, NordicChestBlock> NORDIC_CHEST =
            NORDIC_BLOCKS.register("chest",
                    () -> new NordicChestBlock(woodProps()));

    public static final DeferredHolder<Block, NordicWardrobeBottomBlock> NORDIC_WARDROBE_BOTTOM =
            NORDIC_BLOCKS.register("wardrobe_bottom",
                    () -> new NordicWardrobeBottomBlock(woodProps()));

    public static final DeferredHolder<Block, NordicWardrobeTopBlock> NORDIC_WARDROBE_TOP =
            NORDIC_BLOCKS.register("wardrobe_top",
                    () -> new NordicWardrobeTopBlock(woodProps()));

    // --- Nordic Storage (Bookshelf) ---
    public static final DeferredHolder<Block, NordicBookshelfBlock> NORDIC_BOOKSHELF =
            NORDIC_BLOCKS.register("bookshelf",
                    () -> new NordicBookshelfBlock(woodProps()));

    // --- Nordic Beds ---
    public static final DeferredHolder<Block, NordicBedSingleBlock> NORDIC_BED_SINGLE =
            NORDIC_BLOCKS.register("bed_single",
                    () -> new NordicBedSingleBlock(woodProps().strength(0.2F).pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicBedDoubleBlock> NORDIC_BED_DOUBLE =
            NORDIC_BLOCKS.register("bed_double",
                    () -> new NordicBedDoubleBlock(woodProps()));

    // --- Nordic Doors ---
    public static final DeferredHolder<Block, NordicDoorSingleBlock> NORDIC_DOOR_SINGLE =
            NORDIC_BLOCKS.register("door_single",
                    () -> new NordicDoorSingleBlock(woodProps().noOcclusion().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicDoorDoubleBlock> NORDIC_DOOR_DOUBLE =
            NORDIC_BLOCKS.register("door_double",
                    () -> new NordicDoorDoubleBlock(woodProps().noOcclusion().pushReaction(PushReaction.BLOCK)));

    // --- Nordic Paintings ---
    public static final DeferredHolder<Block, NordicPaintingSmallBlock> NORDIC_PAINTING_SMALL =
            NORDIC_BLOCKS.register("painting_small",
                    () -> new NordicPaintingSmallBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingWideBlock> NORDIC_PAINTING_WIDE =
            NORDIC_BLOCKS.register("painting_wide",
                    () -> new NordicPaintingWideBlock(woodProps()));

    // --- Nordic Oven ---
    public static final DeferredHolder<Block, NordicOvenBlock> NORDIC_OVEN =
            NORDIC_BLOCKS.register("oven",
                    () -> new NordicOvenBlock(Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(3.5F)
                            .sound(SoundType.WOOD)
                            .requiresCorrectToolForDrops()));

    // --- Helpers ---
    private static Properties woodProps() {
        return Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
                .noOcclusion()
                .pushReaction(PushReaction.BLOCK);
    }

    private static Properties lightProps() {
        return Properties.of()
                .mapColor(MapColor.WOOD)
                .instabreak()
                .sound(SoundType.WOOD)
                .noOcclusion()
                .lightLevel(s -> 14)
                .pushReaction(PushReaction.DESTROY);
    }

    private ModBlocks() {}
}
