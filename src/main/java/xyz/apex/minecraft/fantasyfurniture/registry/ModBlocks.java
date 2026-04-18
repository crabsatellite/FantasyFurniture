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
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicBoiledCremeTreatsBlock;
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
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicMeadBottlesBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicOvenBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicPaintingSmallBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicPaintingWideBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicShelfBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicSofaBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicSoulGemsBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicStoolBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicSweetRollsBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicTableBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicTableLargeBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicTableWideBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicWallLightBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicWardrobeBottomBlock;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.NordicWardrobeTopBlock;
import xyz.apex.minecraft.fantasyfurniture.block.dunmer.DunmerPottery0Block;
import xyz.apex.minecraft.fantasyfurniture.block.dunmer.DunmerPottery1Block;
import xyz.apex.minecraft.fantasyfurniture.block.necrolord.NecrolordCandelabraBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalCandelabraBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalChalicesBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalCrownBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalCushionedCrownBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalFloorCushionBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalFoodBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalPlatterBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalWallMirrorBlock;
import xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalWallMirrorTallBlock;
import xyz.apex.minecraft.fantasyfurniture.block.FurnitureBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrBannerBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrCandlesBlock;
import xyz.apex.minecraft.fantasyfurniture.block.bone.BoneChalicesBlock;
import xyz.apex.minecraft.fantasyfurniture.block.bone.BonePileBlock;
import xyz.apex.minecraft.fantasyfurniture.block.bone.BoneSkullBlossomsBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrChalicesBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrTeaCupsBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrTeaSetBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrTomesBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrWidowBloomBlock;

public final class ModBlocks {
    // Main mod namespace — matches 1.20.x upstream "fantasyfurniture"
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FantasyFurniture.MOD_ID);

    // Nordic submodule namespace — matches 1.20.x upstream "fantasyfurniture_nordic"
    // Kept as a separate namespace so existing 1.20.x worlds can load their blocks unchanged.
    public static final DeferredRegister<Block> NORDIC_BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FantasyFurniture.NORDIC_ID);

    // Royal submodule namespace — 1.19.4 upstream parity.
    public static final DeferredRegister<Block> ROYAL_BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FantasyFurniture.ROYAL_ID);

    // Additional set namespaces (1.19.4 upstream parity, not yet populated).
    // Registered on the mod event bus even while empty so adding entries later
    // is a pure DeferredRegister#register call — no wiring diff.
    public static final DeferredRegister<Block> VENTHYR_BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FantasyFurniture.VENTHYR_ID);
    public static final DeferredRegister<Block> DUNMER_BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FantasyFurniture.DUNMER_ID);
    public static final DeferredRegister<Block> NECROLORD_BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FantasyFurniture.NECROLORD_ID);
    public static final DeferredRegister<Block> BONE_SKELETON_BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FantasyFurniture.BONE_SKELETON_ID);
    public static final DeferredRegister<Block> BONE_WITHER_BLOCKS =
            DeferredRegister.create(Registries.BLOCK, FantasyFurniture.BONE_WITHER_ID);

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

    // --- Nordic Decorations (1.19.4 parity) ---
    public static final DeferredHolder<Block, NordicBoiledCremeTreatsBlock> NORDIC_BOILED_CREME_TREATS =
            NORDIC_BLOCKS.register("boiled_creme_treats",
                    () -> new NordicBoiledCremeTreatsBlock(decorationProps()));

    public static final DeferredHolder<Block, NordicSweetRollsBlock> NORDIC_SWEETROLLS =
            NORDIC_BLOCKS.register("sweetrolls",
                    () -> new NordicSweetRollsBlock(decorationProps()));

    public static final DeferredHolder<Block, NordicMeadBottlesBlock> NORDIC_MEAD_BOTTLES =
            NORDIC_BLOCKS.register("mead_bottles",
                    () -> new NordicMeadBottlesBlock(decorationProps()));

    public static final DeferredHolder<Block, NordicSoulGemsBlock> NORDIC_SOUL_GEMS_LIGHT =
            NORDIC_BLOCKS.register("soul_gems_light",
                    () -> new NordicSoulGemsBlock(decorationProps().lightLevel(s -> 10)));

    public static final DeferredHolder<Block, NordicSoulGemsBlock> NORDIC_SOUL_GEMS_DARK =
            NORDIC_BLOCKS.register("soul_gems_dark",
                    () -> new NordicSoulGemsBlock(decorationProps().lightLevel(s -> 7)));

    // --- Nordic Tables (Wide/Large) ---
    public static final DeferredHolder<Block, NordicTableWideBlock> NORDIC_TABLE_WIDE =
            NORDIC_BLOCKS.register("table_wide",
                    () -> new NordicTableWideBlock(woodProps()));

    public static final DeferredHolder<Block, NordicTableLargeBlock> NORDIC_TABLE_LARGE =
            NORDIC_BLOCKS.register("table_large",
                    () -> new NordicTableLargeBlock(woodProps()));

    // ========================================================================
    // Royal set (fantasyfurniture_royal:*)
    // ------------------------------------------------------------------------
    // Royal reuses the Nordic block classes — the block-entity wiring, multi-
    // block placement, and interaction logic are identical. What differs is
    // the namespace, the texture set (see resources/assets/fantasyfurniture_royal/),
    // and, in 1.19.4 upstream, a DyeableLeatherItem tint layer. Shape data is
    // currently inherited from Nordic; per-set shape dispatch is future work
    // tracked alongside the full FurnitureSet abstraction.
    // ========================================================================

    public static final DeferredHolder<Block, Block> ROYAL_WOOL =
            ROYAL_BLOCKS.register("wool",
                    () -> new Block(Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    public static final DeferredHolder<Block, CarpetBlock> ROYAL_CARPET =
            ROYAL_BLOCKS.register("carpet",
                    () -> new CarpetBlock(Properties.ofFullCopy(Blocks.WHITE_CARPET)));

    public static final DeferredHolder<Block, NordicWallLightBlock> ROYAL_WALL_LIGHT =
            ROYAL_BLOCKS.register("wall_light",
                    () -> new NordicWallLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicFloorLightBlock> ROYAL_FLOOR_LIGHT =
            ROYAL_BLOCKS.register("floor_light",
                    () -> new NordicFloorLightBlock(lightProps().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicChandelierLightBlock> ROYAL_CHANDELIER_LIGHT =
            ROYAL_BLOCKS.register("chandelier",
                    () -> new NordicChandelierLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicTableBlock> ROYAL_TABLE =
            ROYAL_BLOCKS.register("table",
                    () -> new NordicTableBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChairBlock> ROYAL_CHAIR =
            ROYAL_BLOCKS.register("chair",
                    () -> new NordicChairBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBenchBlock> ROYAL_BENCH =
            ROYAL_BLOCKS.register("bench",
                    () -> new NordicBenchBlock(woodProps()));

    public static final DeferredHolder<Block, NordicStoolBlock> ROYAL_STOOL =
            ROYAL_BLOCKS.register("stool",
                    () -> new NordicStoolBlock(woodProps()));

    public static final DeferredHolder<Block, NordicCushionBlock> ROYAL_CUSHION =
            ROYAL_BLOCKS.register("cushion",
                    () -> new NordicCushionBlock(woodProps()));

    public static final DeferredHolder<Block, NordicShelfBlock> ROYAL_SHELF =
            ROYAL_BLOCKS.register("shelf",
                    () -> new NordicShelfBlock(woodProps()));

    public static final DeferredHolder<Block, NordicSofaBlock> ROYAL_SOFA =
            ROYAL_BLOCKS.register("sofa",
                    () -> new NordicSofaBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDrawerBlock> ROYAL_DRAWER =
            ROYAL_BLOCKS.register("drawer",
                    () -> new NordicDrawerBlock(woodProps()));

    public static final DeferredHolder<Block, NordicLockboxBlock> ROYAL_LOCKBOX =
            ROYAL_BLOCKS.register("lockbox",
                    () -> new NordicLockboxBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDeskLeftBlock> ROYAL_DESK_LEFT =
            ROYAL_BLOCKS.register("desk_left",
                    () -> new NordicDeskLeftBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDeskRightBlock> ROYAL_DESK_RIGHT =
            ROYAL_BLOCKS.register("desk_right",
                    () -> new NordicDeskRightBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDresserBlock> ROYAL_DRESSER =
            ROYAL_BLOCKS.register("dresser",
                    () -> new NordicDresserBlock(woodProps()));

    public static final DeferredHolder<Block, NordicCounterBlock> ROYAL_COUNTER =
            ROYAL_BLOCKS.register("counter",
                    () -> new NordicCounterBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChestBlock> ROYAL_CHEST =
            ROYAL_BLOCKS.register("chest",
                    () -> new NordicChestBlock(woodProps()));

    public static final DeferredHolder<Block, NordicWardrobeBottomBlock> ROYAL_WARDROBE_BOTTOM =
            ROYAL_BLOCKS.register("wardrobe_bottom",
                    () -> new NordicWardrobeBottomBlock(woodProps()));

    public static final DeferredHolder<Block, NordicWardrobeTopBlock> ROYAL_WARDROBE_TOP =
            ROYAL_BLOCKS.register("wardrobe_top",
                    () -> new NordicWardrobeTopBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBookshelfBlock> ROYAL_BOOKSHELF =
            ROYAL_BLOCKS.register("bookshelf",
                    () -> new NordicBookshelfBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBedSingleBlock> ROYAL_BED_SINGLE =
            ROYAL_BLOCKS.register("bed_single",
                    () -> new NordicBedSingleBlock(woodProps().strength(0.2F).pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicBedDoubleBlock> ROYAL_BED_DOUBLE =
            ROYAL_BLOCKS.register("bed_double",
                    () -> new NordicBedDoubleBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDoorSingleBlock> ROYAL_DOOR_SINGLE =
            ROYAL_BLOCKS.register("door_single",
                    () -> new NordicDoorSingleBlock(woodProps().noOcclusion().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicDoorDoubleBlock> ROYAL_DOOR_DOUBLE =
            ROYAL_BLOCKS.register("door_double",
                    () -> new NordicDoorDoubleBlock(woodProps().noOcclusion().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicPaintingSmallBlock> ROYAL_PAINTING_SMALL =
            ROYAL_BLOCKS.register("painting_small",
                    () -> new NordicPaintingSmallBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingWideBlock> ROYAL_PAINTING_WIDE =
            ROYAL_BLOCKS.register("painting_wide",
                    () -> new NordicPaintingWideBlock(woodProps()));

    public static final DeferredHolder<Block, NordicOvenBlock> ROYAL_OVEN =
            ROYAL_BLOCKS.register("oven",
                    () -> new NordicOvenBlock(Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(3.5F)
                            .sound(SoundType.WOOD)
                            .requiresCorrectToolForDrops()));

    // --- Royal Decorations (1.19.4 parity) ---
    public static final DeferredHolder<Block, RoyalCrownBlock> ROYAL_CROWN =
            ROYAL_BLOCKS.register("crown",
                    () -> new RoyalCrownBlock(decorationProps()));

    public static final DeferredHolder<Block, RoyalCushionedCrownBlock> ROYAL_CUSHIONED_CROWN =
            ROYAL_BLOCKS.register("cushioned_crown",
                    () -> new RoyalCushionedCrownBlock(decorationProps()));

    public static final DeferredHolder<Block, RoyalCandelabraBlock> ROYAL_CANDELABRA =
            ROYAL_BLOCKS.register("candelabra",
                    () -> new RoyalCandelabraBlock(decorationProps()));

    public static final DeferredHolder<Block, RoyalChalicesBlock> ROYAL_CHALICES =
            ROYAL_BLOCKS.register("chalices",
                    () -> new RoyalChalicesBlock(decorationProps()));

    public static final DeferredHolder<Block, RoyalPlatterBlock> ROYAL_PLATTER =
            ROYAL_BLOCKS.register("platter",
                    () -> new RoyalPlatterBlock(decorationProps()));

    public static final DeferredHolder<Block, RoyalFoodBlock> ROYAL_FOOD =
            ROYAL_BLOCKS.register("food",
                    () -> new RoyalFoodBlock(decorationProps()));

    public static final DeferredHolder<Block, RoyalFloorCushionBlock> ROYAL_FLOOR_CUSHION =
            ROYAL_BLOCKS.register("floor_cushion",
                    () -> new RoyalFloorCushionBlock(woodProps()));

    public static final DeferredHolder<Block, RoyalWallMirrorBlock> ROYAL_WALL_MIRROR =
            ROYAL_BLOCKS.register("wall_mirror",
                    () -> new RoyalWallMirrorBlock(decorationProps()));

    public static final DeferredHolder<Block, RoyalWallMirrorTallBlock> ROYAL_WALL_MIRROR_TALL =
            ROYAL_BLOCKS.register("wall_mirror_tall",
                    () -> new RoyalWallMirrorTallBlock(decorationProps()));

    // ========================================================================
    // Dunmer set (fantasyfurniture_dunmer:*)
    // ------------------------------------------------------------------------
    // 1.19.4 upstream parity. Dunmer reuses Nordic block classes (mechanics are
    // set-agnostic); only namespace, model JSONs, and textures differ. Note:
    // 1.19.4 registered "table_small" (not "table") and "oven" was a multiblock
    // cooking spit — we model the oven as a single-block NordicOvenBlock for
    // now; multiblock cooking spit is tracked separately.
    // ========================================================================

    public static final DeferredHolder<Block, Block> DUNMER_WOOL =
            DUNMER_BLOCKS.register("wool",
                    () -> new Block(Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    public static final DeferredHolder<Block, CarpetBlock> DUNMER_CARPET =
            DUNMER_BLOCKS.register("carpet",
                    () -> new CarpetBlock(Properties.ofFullCopy(Blocks.WHITE_CARPET)));

    public static final DeferredHolder<Block, NordicWallLightBlock> DUNMER_WALL_LIGHT =
            DUNMER_BLOCKS.register("wall_light",
                    () -> new NordicWallLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicFloorLightBlock> DUNMER_FLOOR_LIGHT =
            DUNMER_BLOCKS.register("floor_light",
                    () -> new NordicFloorLightBlock(lightProps().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicChandelierLightBlock> DUNMER_CHANDELIER_LIGHT =
            DUNMER_BLOCKS.register("chandelier",
                    () -> new NordicChandelierLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicTableBlock> DUNMER_TABLE_SMALL =
            DUNMER_BLOCKS.register("table_small",
                    () -> new NordicTableBlock(woodProps()));

    public static final DeferredHolder<Block, NordicTableWideBlock> DUNMER_TABLE_WIDE =
            DUNMER_BLOCKS.register("table_wide",
                    () -> new NordicTableWideBlock(woodProps()));

    public static final DeferredHolder<Block, NordicTableLargeBlock> DUNMER_TABLE_LARGE =
            DUNMER_BLOCKS.register("table_large",
                    () -> new NordicTableLargeBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChairBlock> DUNMER_CHAIR =
            DUNMER_BLOCKS.register("chair",
                    () -> new NordicChairBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBenchBlock> DUNMER_BENCH =
            DUNMER_BLOCKS.register("bench",
                    () -> new NordicBenchBlock(woodProps()));

    public static final DeferredHolder<Block, NordicStoolBlock> DUNMER_STOOL =
            DUNMER_BLOCKS.register("stool",
                    () -> new NordicStoolBlock(woodProps()));

    public static final DeferredHolder<Block, NordicCushionBlock> DUNMER_CUSHION =
            DUNMER_BLOCKS.register("cushion",
                    () -> new NordicCushionBlock(woodProps()));

    public static final DeferredHolder<Block, NordicShelfBlock> DUNMER_SHELF =
            DUNMER_BLOCKS.register("shelf",
                    () -> new NordicShelfBlock(woodProps()));

    public static final DeferredHolder<Block, NordicSofaBlock> DUNMER_SOFA =
            DUNMER_BLOCKS.register("sofa",
                    () -> new NordicSofaBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDrawerBlock> DUNMER_DRAWER =
            DUNMER_BLOCKS.register("drawer",
                    () -> new NordicDrawerBlock(woodProps()));

    public static final DeferredHolder<Block, NordicLockboxBlock> DUNMER_LOCKBOX =
            DUNMER_BLOCKS.register("lockbox",
                    () -> new NordicLockboxBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDeskLeftBlock> DUNMER_DESK_LEFT =
            DUNMER_BLOCKS.register("desk_left",
                    () -> new NordicDeskLeftBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDeskRightBlock> DUNMER_DESK_RIGHT =
            DUNMER_BLOCKS.register("desk_right",
                    () -> new NordicDeskRightBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDresserBlock> DUNMER_DRESSER =
            DUNMER_BLOCKS.register("dresser",
                    () -> new NordicDresserBlock(woodProps()));

    public static final DeferredHolder<Block, NordicCounterBlock> DUNMER_COUNTER =
            DUNMER_BLOCKS.register("counter",
                    () -> new NordicCounterBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChestBlock> DUNMER_CHEST =
            DUNMER_BLOCKS.register("chest",
                    () -> new NordicChestBlock(woodProps()));

    public static final DeferredHolder<Block, NordicWardrobeBottomBlock> DUNMER_WARDROBE_BOTTOM =
            DUNMER_BLOCKS.register("wardrobe_bottom",
                    () -> new NordicWardrobeBottomBlock(woodProps()));

    public static final DeferredHolder<Block, NordicWardrobeTopBlock> DUNMER_WARDROBE_TOP =
            DUNMER_BLOCKS.register("wardrobe_top",
                    () -> new NordicWardrobeTopBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBookshelfBlock> DUNMER_BOOKSHELF =
            DUNMER_BLOCKS.register("bookshelf",
                    () -> new NordicBookshelfBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBedSingleBlock> DUNMER_BED_SINGLE =
            DUNMER_BLOCKS.register("bed_single",
                    () -> new NordicBedSingleBlock(woodProps().strength(0.2F).pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicBedDoubleBlock> DUNMER_BED_DOUBLE =
            DUNMER_BLOCKS.register("bed_double",
                    () -> new NordicBedDoubleBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDoorSingleBlock> DUNMER_DOOR_SINGLE =
            DUNMER_BLOCKS.register("door_single",
                    () -> new NordicDoorSingleBlock(woodProps().noOcclusion().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicDoorDoubleBlock> DUNMER_DOOR_DOUBLE =
            DUNMER_BLOCKS.register("door_double",
                    () -> new NordicDoorDoubleBlock(woodProps().noOcclusion().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicPaintingSmallBlock> DUNMER_PAINTING_SMALL =
            DUNMER_BLOCKS.register("painting_small",
                    () -> new NordicPaintingSmallBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingWideBlock> DUNMER_PAINTING_WIDE =
            DUNMER_BLOCKS.register("painting_wide",
                    () -> new NordicPaintingWideBlock(woodProps()));

    public static final DeferredHolder<Block, NordicOvenBlock> DUNMER_OVEN =
            DUNMER_BLOCKS.register("oven",
                    () -> new NordicOvenBlock(Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(3.5F)
                            .sound(SoundType.WOOD)
                            .requiresCorrectToolForDrops()));

    // --- Dunmer Decorations (1.19.4 parity) ---
    public static final DeferredHolder<Block, DunmerPottery0Block> DUNMER_POTTERY_0 =
            DUNMER_BLOCKS.register("pottery_0",
                    () -> new DunmerPottery0Block(decorationProps()));

    public static final DeferredHolder<Block, DunmerPottery1Block> DUNMER_POTTERY_1 =
            DUNMER_BLOCKS.register("pottery_1",
                    () -> new DunmerPottery1Block(decorationProps()));

    // ========================================================================
    // Venthyr set (fantasyfurniture_venthyr:*)
    // ------------------------------------------------------------------------
    // 1.19.4 upstream parity. Venthyr reuses Nordic block classes; it adds
    // three "fancy" table variants (table_small_fancy, table_wide_fancy,
    // table_large_fancy) which share the same block classes as the non-fancy
    // tables (only the models/textures differ).
    // ========================================================================

    public static final DeferredHolder<Block, Block> VENTHYR_WOOL =
            VENTHYR_BLOCKS.register("wool",
                    () -> new Block(Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    public static final DeferredHolder<Block, CarpetBlock> VENTHYR_CARPET =
            VENTHYR_BLOCKS.register("carpet",
                    () -> new CarpetBlock(Properties.ofFullCopy(Blocks.WHITE_CARPET)));

    public static final DeferredHolder<Block, NordicWallLightBlock> VENTHYR_WALL_LIGHT =
            VENTHYR_BLOCKS.register("wall_light",
                    () -> new NordicWallLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicFloorLightBlock> VENTHYR_FLOOR_LIGHT =
            VENTHYR_BLOCKS.register("floor_light",
                    () -> new NordicFloorLightBlock(lightProps().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicChandelierLightBlock> VENTHYR_CHANDELIER_LIGHT =
            VENTHYR_BLOCKS.register("chandelier",
                    () -> new NordicChandelierLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicTableBlock> VENTHYR_TABLE_SMALL =
            VENTHYR_BLOCKS.register("table_small", () -> new NordicTableBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableBlock> VENTHYR_TABLE_SMALL_FANCY =
            VENTHYR_BLOCKS.register("table_small_fancy", () -> new NordicTableBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableWideBlock> VENTHYR_TABLE_WIDE =
            VENTHYR_BLOCKS.register("table_wide", () -> new NordicTableWideBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableWideBlock> VENTHYR_TABLE_WIDE_FANCY =
            VENTHYR_BLOCKS.register("table_wide_fancy", () -> new NordicTableWideBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableLargeBlock> VENTHYR_TABLE_LARGE =
            VENTHYR_BLOCKS.register("table_large", () -> new NordicTableLargeBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableLargeBlock> VENTHYR_TABLE_LARGE_FANCY =
            VENTHYR_BLOCKS.register("table_large_fancy", () -> new NordicTableLargeBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChairBlock> VENTHYR_CHAIR =
            VENTHYR_BLOCKS.register("chair", () -> new NordicChairBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBenchBlock> VENTHYR_BENCH =
            VENTHYR_BLOCKS.register("bench", () -> new NordicBenchBlock(woodProps()));
    public static final DeferredHolder<Block, NordicStoolBlock> VENTHYR_STOOL =
            VENTHYR_BLOCKS.register("stool", () -> new NordicStoolBlock(woodProps()));
    public static final DeferredHolder<Block, NordicCushionBlock> VENTHYR_CUSHION =
            VENTHYR_BLOCKS.register("cushion", () -> new NordicCushionBlock(woodProps()));

    public static final DeferredHolder<Block, NordicShelfBlock> VENTHYR_SHELF =
            VENTHYR_BLOCKS.register("shelf", () -> new NordicShelfBlock(woodProps()));
    public static final DeferredHolder<Block, NordicSofaBlock> VENTHYR_SOFA =
            VENTHYR_BLOCKS.register("sofa", () -> new NordicSofaBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDrawerBlock> VENTHYR_DRAWER =
            VENTHYR_BLOCKS.register("drawer", () -> new NordicDrawerBlock(woodProps()));
    public static final DeferredHolder<Block, NordicLockboxBlock> VENTHYR_LOCKBOX =
            VENTHYR_BLOCKS.register("lockbox", () -> new NordicLockboxBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDeskLeftBlock> VENTHYR_DESK_LEFT =
            VENTHYR_BLOCKS.register("desk_left", () -> new NordicDeskLeftBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDeskRightBlock> VENTHYR_DESK_RIGHT =
            VENTHYR_BLOCKS.register("desk_right", () -> new NordicDeskRightBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDresserBlock> VENTHYR_DRESSER =
            VENTHYR_BLOCKS.register("dresser", () -> new NordicDresserBlock(woodProps()));
    public static final DeferredHolder<Block, NordicCounterBlock> VENTHYR_COUNTER =
            VENTHYR_BLOCKS.register("counter", () -> new NordicCounterBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChestBlock> VENTHYR_CHEST =
            VENTHYR_BLOCKS.register("chest", () -> new NordicChestBlock(woodProps()));
    public static final DeferredHolder<Block, NordicWardrobeBottomBlock> VENTHYR_WARDROBE_BOTTOM =
            VENTHYR_BLOCKS.register("wardrobe_bottom", () -> new NordicWardrobeBottomBlock(woodProps()));
    public static final DeferredHolder<Block, NordicWardrobeTopBlock> VENTHYR_WARDROBE_TOP =
            VENTHYR_BLOCKS.register("wardrobe_top", () -> new NordicWardrobeTopBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBookshelfBlock> VENTHYR_BOOKSHELF =
            VENTHYR_BLOCKS.register("bookshelf", () -> new NordicBookshelfBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBedSingleBlock> VENTHYR_BED_SINGLE =
            VENTHYR_BLOCKS.register("bed_single", () -> new NordicBedSingleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBedDoubleBlock> VENTHYR_BED_DOUBLE =
            VENTHYR_BLOCKS.register("bed_double", () -> new NordicBedDoubleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDoorSingleBlock> VENTHYR_DOOR_SINGLE =
            VENTHYR_BLOCKS.register("door_single", () -> new NordicDoorSingleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDoorDoubleBlock> VENTHYR_DOOR_DOUBLE =
            VENTHYR_BLOCKS.register("door_double", () -> new NordicDoorDoubleBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingSmallBlock> VENTHYR_PAINTING_SMALL =
            VENTHYR_BLOCKS.register("painting_small",
                    () -> new NordicPaintingSmallBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingWideBlock> VENTHYR_PAINTING_WIDE =
            VENTHYR_BLOCKS.register("painting_wide",
                    () -> new NordicPaintingWideBlock(woodProps()));

    public static final DeferredHolder<Block, NordicOvenBlock> VENTHYR_OVEN =
            VENTHYR_BLOCKS.register("oven",
                    () -> new NordicOvenBlock(Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(3.5F)
                            .sound(SoundType.WOOD)
                            .requiresCorrectToolForDrops()));

    // --- Venthyr Decorations (1.19.4 parity) ---
    public static final DeferredHolder<Block, RoyalFoodBlock> VENTHYR_FOOD_0 =
            VENTHYR_BLOCKS.register("food_0",
                    () -> new RoyalFoodBlock(decorationProps()));

    public static final DeferredHolder<Block, RoyalFoodBlock> VENTHYR_FOOD_1 =
            VENTHYR_BLOCKS.register("food_1",
                    () -> new RoyalFoodBlock(decorationProps()));

    public static final DeferredHolder<Block, VenthyrTomesBlock> VENTHYR_TOMES =
            VENTHYR_BLOCKS.register("tomes",
                    () -> new VenthyrTomesBlock(decorationProps()));

    public static final DeferredHolder<Block, VenthyrTeaCupsBlock> VENTHYR_TEA_CUPS =
            VENTHYR_BLOCKS.register("tea_cups",
                    () -> new VenthyrTeaCupsBlock(decorationProps()));

    public static final DeferredHolder<Block, RoyalPlatterBlock> VENTHYR_PLATTER =
            VENTHYR_BLOCKS.register("platter",
                    () -> new RoyalPlatterBlock(decorationProps()));

    public static final DeferredHolder<Block, VenthyrTeaSetBlock> VENTHYR_TEA_SET =
            VENTHYR_BLOCKS.register("tea_set",
                    () -> new VenthyrTeaSetBlock(decorationProps()));

    public static final DeferredHolder<Block, VenthyrBannerBlock> VENTHYR_BANNER =
            VENTHYR_BLOCKS.register("banner",
                    () -> new VenthyrBannerBlock(decorationProps()));

    public static final DeferredHolder<Block, VenthyrCandlesBlock> VENTHYR_CANDLES =
            VENTHYR_BLOCKS.register("candles",
                    () -> new VenthyrCandlesBlock(decorationProps().lightLevel(s ->
                            s.getValue(VenthyrCandlesBlock.LIT) && !s.getValue(FurnitureBlock.WATERLOGGED) ? 12 : 0)));

    public static final DeferredHolder<Block, VenthyrChalicesBlock> VENTHYR_CHALICES =
            VENTHYR_BLOCKS.register("chalices",
                    () -> new VenthyrChalicesBlock(decorationProps()));

    public static final DeferredHolder<Block, VenthyrWidowBloomBlock> VENTHYR_WIDOW_BLOOM =
            VENTHYR_BLOCKS.register("widow_bloom",
                    () -> new VenthyrWidowBloomBlock(decorationProps()));

    // ========================================================================
    // Necrolord set (fantasyfurniture_necrolord:*)
    // ------------------------------------------------------------------------
    // 1.19.4 upstream parity. Necrolord reuses Nordic block classes; only the
    // namespace, model JSONs, and textures differ. The 1.19.4 decoration
    // "candelabra" is deferred to the unified decorations phase.
    // ========================================================================

    public static final DeferredHolder<Block, Block> NECROLORD_WOOL =
            NECROLORD_BLOCKS.register("wool",
                    () -> new Block(Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    public static final DeferredHolder<Block, CarpetBlock> NECROLORD_CARPET =
            NECROLORD_BLOCKS.register("carpet",
                    () -> new CarpetBlock(Properties.ofFullCopy(Blocks.WHITE_CARPET)));

    public static final DeferredHolder<Block, NordicWallLightBlock> NECROLORD_WALL_LIGHT =
            NECROLORD_BLOCKS.register("wall_light",
                    () -> new NordicWallLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicFloorLightBlock> NECROLORD_FLOOR_LIGHT =
            NECROLORD_BLOCKS.register("floor_light",
                    () -> new NordicFloorLightBlock(lightProps().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicChandelierLightBlock> NECROLORD_CHANDELIER_LIGHT =
            NECROLORD_BLOCKS.register("chandelier",
                    () -> new NordicChandelierLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicTableBlock> NECROLORD_TABLE_SMALL =
            NECROLORD_BLOCKS.register("table_small", () -> new NordicTableBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableWideBlock> NECROLORD_TABLE_WIDE =
            NECROLORD_BLOCKS.register("table_wide", () -> new NordicTableWideBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableLargeBlock> NECROLORD_TABLE_LARGE =
            NECROLORD_BLOCKS.register("table_large", () -> new NordicTableLargeBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChairBlock> NECROLORD_CHAIR =
            NECROLORD_BLOCKS.register("chair", () -> new NordicChairBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBenchBlock> NECROLORD_BENCH =
            NECROLORD_BLOCKS.register("bench", () -> new NordicBenchBlock(woodProps()));
    public static final DeferredHolder<Block, NordicStoolBlock> NECROLORD_STOOL =
            NECROLORD_BLOCKS.register("stool", () -> new NordicStoolBlock(woodProps()));
    public static final DeferredHolder<Block, NordicCushionBlock> NECROLORD_CUSHION =
            NECROLORD_BLOCKS.register("cushion", () -> new NordicCushionBlock(woodProps()));

    public static final DeferredHolder<Block, NordicShelfBlock> NECROLORD_SHELF =
            NECROLORD_BLOCKS.register("shelf", () -> new NordicShelfBlock(woodProps()));
    public static final DeferredHolder<Block, NordicSofaBlock> NECROLORD_SOFA =
            NECROLORD_BLOCKS.register("sofa", () -> new NordicSofaBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDrawerBlock> NECROLORD_DRAWER =
            NECROLORD_BLOCKS.register("drawer", () -> new NordicDrawerBlock(woodProps()));
    public static final DeferredHolder<Block, NordicLockboxBlock> NECROLORD_LOCKBOX =
            NECROLORD_BLOCKS.register("lockbox", () -> new NordicLockboxBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDeskLeftBlock> NECROLORD_DESK_LEFT =
            NECROLORD_BLOCKS.register("desk_left", () -> new NordicDeskLeftBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDeskRightBlock> NECROLORD_DESK_RIGHT =
            NECROLORD_BLOCKS.register("desk_right", () -> new NordicDeskRightBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDresserBlock> NECROLORD_DRESSER =
            NECROLORD_BLOCKS.register("dresser", () -> new NordicDresserBlock(woodProps()));
    public static final DeferredHolder<Block, NordicCounterBlock> NECROLORD_COUNTER =
            NECROLORD_BLOCKS.register("counter", () -> new NordicCounterBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChestBlock> NECROLORD_CHEST =
            NECROLORD_BLOCKS.register("chest", () -> new NordicChestBlock(woodProps()));
    public static final DeferredHolder<Block, NordicWardrobeBottomBlock> NECROLORD_WARDROBE_BOTTOM =
            NECROLORD_BLOCKS.register("wardrobe_bottom", () -> new NordicWardrobeBottomBlock(woodProps()));
    public static final DeferredHolder<Block, NordicWardrobeTopBlock> NECROLORD_WARDROBE_TOP =
            NECROLORD_BLOCKS.register("wardrobe_top", () -> new NordicWardrobeTopBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBookshelfBlock> NECROLORD_BOOKSHELF =
            NECROLORD_BLOCKS.register("bookshelf", () -> new NordicBookshelfBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBedSingleBlock> NECROLORD_BED_SINGLE =
            NECROLORD_BLOCKS.register("bed_single", () -> new NordicBedSingleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBedDoubleBlock> NECROLORD_BED_DOUBLE =
            NECROLORD_BLOCKS.register("bed_double", () -> new NordicBedDoubleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDoorSingleBlock> NECROLORD_DOOR_SINGLE =
            NECROLORD_BLOCKS.register("door_single", () -> new NordicDoorSingleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDoorDoubleBlock> NECROLORD_DOOR_DOUBLE =
            NECROLORD_BLOCKS.register("door_double", () -> new NordicDoorDoubleBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingSmallBlock> NECROLORD_PAINTING_SMALL =
            NECROLORD_BLOCKS.register("painting_small",
                    () -> new NordicPaintingSmallBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingWideBlock> NECROLORD_PAINTING_WIDE =
            NECROLORD_BLOCKS.register("painting_wide",
                    () -> new NordicPaintingWideBlock(woodProps()));

    public static final DeferredHolder<Block, NordicOvenBlock> NECROLORD_OVEN =
            NECROLORD_BLOCKS.register("oven",
                    () -> new NordicOvenBlock(Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(3.5F)
                            .sound(SoundType.WOOD)
                            .requiresCorrectToolForDrops()));

    // --- Necrolord Decorations (1.19.4 parity) ---
    public static final DeferredHolder<Block, NecrolordCandelabraBlock> NECROLORD_CANDELABRA =
            NECROLORD_BLOCKS.register("candelabra",
                    () -> new NecrolordCandelabraBlock(decorationProps()));

    // ========================================================================
    // Bone-Skeleton set (fantasyfurniture_bone_skeleton:*)
    // ------------------------------------------------------------------------
    // 1.19.4 upstream parity. Bone-Skeleton reuses Nordic block classes; only
    // the namespace, model JSONs, and textures differ. All furniture pieces
    // are pickaxe-mineable (legacy .transform(BlockTransformers::mineablePickaxe)).
    // The "cushion" slot is renamed to "skull" to match 1.19.4 block id
    // `fantasyfurniture:bone/skeleton/skull` via CushionBlock. Decorations
    // (chalices, pile, skull_blossoms) are deferred to the unified phase.
    // ========================================================================

    public static final DeferredHolder<Block, Block> BONE_SKELETON_WOOL =
            BONE_SKELETON_BLOCKS.register("wool",
                    () -> new Block(Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    public static final DeferredHolder<Block, CarpetBlock> BONE_SKELETON_CARPET =
            BONE_SKELETON_BLOCKS.register("carpet",
                    () -> new CarpetBlock(Properties.ofFullCopy(Blocks.WHITE_CARPET)));

    public static final DeferredHolder<Block, NordicWallLightBlock> BONE_SKELETON_WALL_LIGHT =
            BONE_SKELETON_BLOCKS.register("wall_light",
                    () -> new NordicWallLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicFloorLightBlock> BONE_SKELETON_FLOOR_LIGHT =
            BONE_SKELETON_BLOCKS.register("floor_light",
                    () -> new NordicFloorLightBlock(lightProps().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicChandelierLightBlock> BONE_SKELETON_CHANDELIER_LIGHT =
            BONE_SKELETON_BLOCKS.register("chandelier",
                    () -> new NordicChandelierLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicTableBlock> BONE_SKELETON_TABLE_SMALL =
            BONE_SKELETON_BLOCKS.register("table_small", () -> new NordicTableBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableWideBlock> BONE_SKELETON_TABLE_WIDE =
            BONE_SKELETON_BLOCKS.register("table_wide", () -> new NordicTableWideBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableLargeBlock> BONE_SKELETON_TABLE_LARGE =
            BONE_SKELETON_BLOCKS.register("table_large", () -> new NordicTableLargeBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChairBlock> BONE_SKELETON_CHAIR =
            BONE_SKELETON_BLOCKS.register("chair", () -> new NordicChairBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBenchBlock> BONE_SKELETON_BENCH =
            BONE_SKELETON_BLOCKS.register("bench", () -> new NordicBenchBlock(woodProps()));
    public static final DeferredHolder<Block, NordicStoolBlock> BONE_SKELETON_STOOL =
            BONE_SKELETON_BLOCKS.register("stool", () -> new NordicStoolBlock(woodProps()));
    public static final DeferredHolder<Block, NordicCushionBlock> BONE_SKELETON_SKULL =
            BONE_SKELETON_BLOCKS.register("skull", () -> new NordicCushionBlock(woodProps()));

    public static final DeferredHolder<Block, NordicShelfBlock> BONE_SKELETON_SHELF =
            BONE_SKELETON_BLOCKS.register("shelf", () -> new NordicShelfBlock(woodProps()));
    public static final DeferredHolder<Block, NordicSofaBlock> BONE_SKELETON_SOFA =
            BONE_SKELETON_BLOCKS.register("sofa", () -> new NordicSofaBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDrawerBlock> BONE_SKELETON_DRAWER =
            BONE_SKELETON_BLOCKS.register("drawer", () -> new NordicDrawerBlock(woodProps()));
    public static final DeferredHolder<Block, NordicLockboxBlock> BONE_SKELETON_LOCKBOX =
            BONE_SKELETON_BLOCKS.register("lockbox", () -> new NordicLockboxBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDeskLeftBlock> BONE_SKELETON_DESK_LEFT =
            BONE_SKELETON_BLOCKS.register("desk_left", () -> new NordicDeskLeftBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDeskRightBlock> BONE_SKELETON_DESK_RIGHT =
            BONE_SKELETON_BLOCKS.register("desk_right", () -> new NordicDeskRightBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDresserBlock> BONE_SKELETON_DRESSER =
            BONE_SKELETON_BLOCKS.register("dresser", () -> new NordicDresserBlock(woodProps()));
    public static final DeferredHolder<Block, NordicCounterBlock> BONE_SKELETON_COUNTER =
            BONE_SKELETON_BLOCKS.register("counter", () -> new NordicCounterBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChestBlock> BONE_SKELETON_CHEST =
            BONE_SKELETON_BLOCKS.register("chest", () -> new NordicChestBlock(woodProps()));
    public static final DeferredHolder<Block, NordicWardrobeBottomBlock> BONE_SKELETON_WARDROBE_BOTTOM =
            BONE_SKELETON_BLOCKS.register("wardrobe_bottom", () -> new NordicWardrobeBottomBlock(woodProps()));
    public static final DeferredHolder<Block, NordicWardrobeTopBlock> BONE_SKELETON_WARDROBE_TOP =
            BONE_SKELETON_BLOCKS.register("wardrobe_top", () -> new NordicWardrobeTopBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBookshelfBlock> BONE_SKELETON_BOOKSHELF =
            BONE_SKELETON_BLOCKS.register("bookshelf", () -> new NordicBookshelfBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBedSingleBlock> BONE_SKELETON_BED_SINGLE =
            BONE_SKELETON_BLOCKS.register("bed_single", () -> new NordicBedSingleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBedDoubleBlock> BONE_SKELETON_BED_DOUBLE =
            BONE_SKELETON_BLOCKS.register("bed_double", () -> new NordicBedDoubleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDoorSingleBlock> BONE_SKELETON_DOOR_SINGLE =
            BONE_SKELETON_BLOCKS.register("door_single", () -> new NordicDoorSingleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDoorDoubleBlock> BONE_SKELETON_DOOR_DOUBLE =
            BONE_SKELETON_BLOCKS.register("door_double", () -> new NordicDoorDoubleBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingSmallBlock> BONE_SKELETON_PAINTING_SMALL =
            BONE_SKELETON_BLOCKS.register("painting_small",
                    () -> new NordicPaintingSmallBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingWideBlock> BONE_SKELETON_PAINTING_WIDE =
            BONE_SKELETON_BLOCKS.register("painting_wide",
                    () -> new NordicPaintingWideBlock(woodProps()));

    public static final DeferredHolder<Block, NordicOvenBlock> BONE_SKELETON_OVEN =
            BONE_SKELETON_BLOCKS.register("oven",
                    () -> new NordicOvenBlock(Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(3.5F)
                            .sound(SoundType.WOOD)
                            .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, BoneChalicesBlock> BONE_SKELETON_CHALICES =
            BONE_SKELETON_BLOCKS.register("chalices",
                    () -> new BoneChalicesBlock(decorationProps()));

    public static final DeferredHolder<Block, BonePileBlock> BONE_SKELETON_PILE =
            BONE_SKELETON_BLOCKS.register("pile",
                    () -> new BonePileBlock(decorationProps()));

    public static final DeferredHolder<Block, BoneSkullBlossomsBlock> BONE_SKELETON_SKULL_BLOSSOMS =
            BONE_SKELETON_BLOCKS.register("skull_blossoms",
                    () -> new BoneSkullBlossomsBlock(decorationProps()));

    // ========================================================================
    // Bone-Wither set (fantasyfurniture_bone_wither:*)
    // ------------------------------------------------------------------------
    // 1.19.4 upstream parity. Bone-Wither reuses Nordic block classes; only
    // the namespace, model JSONs, and textures differ. Pickaxe-mineable like
    // Bone-Skeleton. "cushion" slot renamed to "skull" matching 1.19.4 id
    // `fantasyfurniture:bone/wither/skull`. Decorations deferred.
    // ========================================================================

    public static final DeferredHolder<Block, Block> BONE_WITHER_WOOL =
            BONE_WITHER_BLOCKS.register("wool",
                    () -> new Block(Properties.ofFullCopy(Blocks.WHITE_WOOL)));

    public static final DeferredHolder<Block, CarpetBlock> BONE_WITHER_CARPET =
            BONE_WITHER_BLOCKS.register("carpet",
                    () -> new CarpetBlock(Properties.ofFullCopy(Blocks.WHITE_CARPET)));

    public static final DeferredHolder<Block, NordicWallLightBlock> BONE_WITHER_WALL_LIGHT =
            BONE_WITHER_BLOCKS.register("wall_light",
                    () -> new NordicWallLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicFloorLightBlock> BONE_WITHER_FLOOR_LIGHT =
            BONE_WITHER_BLOCKS.register("floor_light",
                    () -> new NordicFloorLightBlock(lightProps().pushReaction(PushReaction.BLOCK)));

    public static final DeferredHolder<Block, NordicChandelierLightBlock> BONE_WITHER_CHANDELIER_LIGHT =
            BONE_WITHER_BLOCKS.register("chandelier",
                    () -> new NordicChandelierLightBlock(lightProps()));

    public static final DeferredHolder<Block, NordicTableBlock> BONE_WITHER_TABLE_SMALL =
            BONE_WITHER_BLOCKS.register("table_small", () -> new NordicTableBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableWideBlock> BONE_WITHER_TABLE_WIDE =
            BONE_WITHER_BLOCKS.register("table_wide", () -> new NordicTableWideBlock(woodProps()));
    public static final DeferredHolder<Block, NordicTableLargeBlock> BONE_WITHER_TABLE_LARGE =
            BONE_WITHER_BLOCKS.register("table_large", () -> new NordicTableLargeBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChairBlock> BONE_WITHER_CHAIR =
            BONE_WITHER_BLOCKS.register("chair", () -> new NordicChairBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBenchBlock> BONE_WITHER_BENCH =
            BONE_WITHER_BLOCKS.register("bench", () -> new NordicBenchBlock(woodProps()));
    public static final DeferredHolder<Block, NordicStoolBlock> BONE_WITHER_STOOL =
            BONE_WITHER_BLOCKS.register("stool", () -> new NordicStoolBlock(woodProps()));
    public static final DeferredHolder<Block, NordicCushionBlock> BONE_WITHER_SKULL =
            BONE_WITHER_BLOCKS.register("skull", () -> new NordicCushionBlock(woodProps()));

    public static final DeferredHolder<Block, NordicShelfBlock> BONE_WITHER_SHELF =
            BONE_WITHER_BLOCKS.register("shelf", () -> new NordicShelfBlock(woodProps()));
    public static final DeferredHolder<Block, NordicSofaBlock> BONE_WITHER_SOFA =
            BONE_WITHER_BLOCKS.register("sofa", () -> new NordicSofaBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDrawerBlock> BONE_WITHER_DRAWER =
            BONE_WITHER_BLOCKS.register("drawer", () -> new NordicDrawerBlock(woodProps()));
    public static final DeferredHolder<Block, NordicLockboxBlock> BONE_WITHER_LOCKBOX =
            BONE_WITHER_BLOCKS.register("lockbox", () -> new NordicLockboxBlock(woodProps()));

    public static final DeferredHolder<Block, NordicDeskLeftBlock> BONE_WITHER_DESK_LEFT =
            BONE_WITHER_BLOCKS.register("desk_left", () -> new NordicDeskLeftBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDeskRightBlock> BONE_WITHER_DESK_RIGHT =
            BONE_WITHER_BLOCKS.register("desk_right", () -> new NordicDeskRightBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDresserBlock> BONE_WITHER_DRESSER =
            BONE_WITHER_BLOCKS.register("dresser", () -> new NordicDresserBlock(woodProps()));
    public static final DeferredHolder<Block, NordicCounterBlock> BONE_WITHER_COUNTER =
            BONE_WITHER_BLOCKS.register("counter", () -> new NordicCounterBlock(woodProps()));

    public static final DeferredHolder<Block, NordicChestBlock> BONE_WITHER_CHEST =
            BONE_WITHER_BLOCKS.register("chest", () -> new NordicChestBlock(woodProps()));
    public static final DeferredHolder<Block, NordicWardrobeBottomBlock> BONE_WITHER_WARDROBE_BOTTOM =
            BONE_WITHER_BLOCKS.register("wardrobe_bottom", () -> new NordicWardrobeBottomBlock(woodProps()));
    public static final DeferredHolder<Block, NordicWardrobeTopBlock> BONE_WITHER_WARDROBE_TOP =
            BONE_WITHER_BLOCKS.register("wardrobe_top", () -> new NordicWardrobeTopBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBookshelfBlock> BONE_WITHER_BOOKSHELF =
            BONE_WITHER_BLOCKS.register("bookshelf", () -> new NordicBookshelfBlock(woodProps()));

    public static final DeferredHolder<Block, NordicBedSingleBlock> BONE_WITHER_BED_SINGLE =
            BONE_WITHER_BLOCKS.register("bed_single", () -> new NordicBedSingleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicBedDoubleBlock> BONE_WITHER_BED_DOUBLE =
            BONE_WITHER_BLOCKS.register("bed_double", () -> new NordicBedDoubleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDoorSingleBlock> BONE_WITHER_DOOR_SINGLE =
            BONE_WITHER_BLOCKS.register("door_single", () -> new NordicDoorSingleBlock(woodProps()));
    public static final DeferredHolder<Block, NordicDoorDoubleBlock> BONE_WITHER_DOOR_DOUBLE =
            BONE_WITHER_BLOCKS.register("door_double", () -> new NordicDoorDoubleBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingSmallBlock> BONE_WITHER_PAINTING_SMALL =
            BONE_WITHER_BLOCKS.register("painting_small",
                    () -> new NordicPaintingSmallBlock(woodProps()));

    public static final DeferredHolder<Block, NordicPaintingWideBlock> BONE_WITHER_PAINTING_WIDE =
            BONE_WITHER_BLOCKS.register("painting_wide",
                    () -> new NordicPaintingWideBlock(woodProps()));

    public static final DeferredHolder<Block, NordicOvenBlock> BONE_WITHER_OVEN =
            BONE_WITHER_BLOCKS.register("oven",
                    () -> new NordicOvenBlock(Properties.of()
                            .mapColor(MapColor.STONE)
                            .strength(3.5F)
                            .sound(SoundType.WOOD)
                            .requiresCorrectToolForDrops()));

    public static final DeferredHolder<Block, BoneChalicesBlock> BONE_WITHER_CHALICES =
            BONE_WITHER_BLOCKS.register("chalices",
                    () -> new BoneChalicesBlock(decorationProps()));

    public static final DeferredHolder<Block, BonePileBlock> BONE_WITHER_PILE =
            BONE_WITHER_BLOCKS.register("pile",
                    () -> new BonePileBlock(decorationProps()));

    public static final DeferredHolder<Block, BoneSkullBlossomsBlock> BONE_WITHER_SKULL_BLOSSOMS =
            BONE_WITHER_BLOCKS.register("skull_blossoms",
                    () -> new BoneSkullBlossomsBlock(decorationProps()));

    // --- Helpers ---
    private static Properties woodProps() {
        return Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
                .noOcclusion()
                .pushReaction(PushReaction.BLOCK);
    }

    private static Properties decorationProps() {
        return Properties.of()
                .mapColor(MapColor.WOOD)
                .strength(1.0F, 2.0F)
                .sound(SoundType.WOOD)
                .noOcclusion()
                .pushReaction(PushReaction.DESTROY);
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
