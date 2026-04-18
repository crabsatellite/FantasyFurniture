package xyz.apex.minecraft.fantasyfurniture.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import xyz.apex.minecraft.fantasyfurniture.block.nordic.*;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrBannerBlock;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrTeaSetBlock;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlocks;

import java.util.Set;

public class ModBlockLootSubProvider extends BlockLootSubProvider {

    protected ModBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        // --- Simple self-drop blocks (no multi-block PART property) ---
        dropSelf(ModBlocks.FURNITURE_STATION.get());
        dropSelf(ModBlocks.NORDIC_WOOL.get());
        dropSelf(ModBlocks.NORDIC_CARPET.get());
        dropSelf(ModBlocks.NORDIC_WALL_LIGHT.get());
        dropSelf(ModBlocks.NORDIC_CHANDELIER_LIGHT.get());
        dropSelf(ModBlocks.NORDIC_TABLE.get());
        dropSelf(ModBlocks.NORDIC_STOOL.get());
        dropSelf(ModBlocks.NORDIC_CUSHION.get());
        dropSelf(ModBlocks.NORDIC_SHELF.get());
        dropSelf(ModBlocks.NORDIC_SOFA.get());
        dropSelf(ModBlocks.NORDIC_DRAWER.get());
        dropSelf(ModBlocks.NORDIC_LOCKBOX.get());
        dropSelf(ModBlocks.NORDIC_COUNTER.get());
        dropSelf(ModBlocks.NORDIC_PAINTING_SMALL.get());
        dropSelf(ModBlocks.NORDIC_OVEN.get());

        // --- Nordic decorations ---
        dropSelf(ModBlocks.NORDIC_BOILED_CREME_TREATS.get());
        dropSelf(ModBlocks.NORDIC_SWEETROLLS.get());
        dropSelf(ModBlocks.NORDIC_MEAD_BOTTLES.get());
        dropSelf(ModBlocks.NORDIC_SOUL_GEMS_LIGHT.get());
        dropSelf(ModBlocks.NORDIC_SOUL_GEMS_DARK.get());

        // --- NordicBedSingleBlock extends BedBlock ---
        // Visible model (bed_single) renders on FOOT (origin). When the player
        // breaks the visible block, the state at break time has PART=FOOT, so the
        // loot condition must also be FOOT or breaking the visible half yields no drop.
        add(ModBlocks.NORDIC_BED_SINGLE.get(), block ->
                createSinglePropConditionTable(block, BedBlock.PART, BedPart.FOOT));

        // --- Multi-block: drop only from part=0 ---

        // PART 0-3 (MB_2x1x2)
        multiBlockDrop(ModBlocks.NORDIC_BED_DOUBLE.get(), NordicBedDoubleBlock.PART);

        // PART 0-1 (MB_1x2x1)
        multiBlockDrop(ModBlocks.NORDIC_DOOR_SINGLE.get(), NordicDoorSingleBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_DOOR_DOUBLE.get(), NordicDoorDoubleBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_CHAIR.get(), NordicChairBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART);

        // PART 0-1 (MB_2x1x1)
        multiBlockDrop(ModBlocks.NORDIC_BENCH.get(), NordicBenchBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_CHEST.get(), NordicChestBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_DRESSER.get(), NordicDresserBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_DESK_LEFT.get(), NordicDeskLeftBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_DESK_RIGHT.get(), NordicDeskRightBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_TABLE_WIDE.get(), NordicTableWideBlock.PART);

        // PART 0-3 (MB_2x2x1 or MB_2x1x2)
        multiBlockDrop(ModBlocks.NORDIC_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_BOOKSHELF.get(), NordicBookshelfBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_TABLE_LARGE.get(), NordicTableLargeBlock.PART);

        // --- Royal set (mirrors Nordic loot structure) ---
        dropSelf(ModBlocks.ROYAL_WOOL.get());
        dropSelf(ModBlocks.ROYAL_CARPET.get());
        dropSelf(ModBlocks.ROYAL_WALL_LIGHT.get());
        dropSelf(ModBlocks.ROYAL_CHANDELIER_LIGHT.get());
        dropSelf(ModBlocks.ROYAL_TABLE.get());
        dropSelf(ModBlocks.ROYAL_STOOL.get());
        dropSelf(ModBlocks.ROYAL_CUSHION.get());
        dropSelf(ModBlocks.ROYAL_SHELF.get());
        dropSelf(ModBlocks.ROYAL_SOFA.get());
        dropSelf(ModBlocks.ROYAL_DRAWER.get());
        dropSelf(ModBlocks.ROYAL_LOCKBOX.get());
        dropSelf(ModBlocks.ROYAL_COUNTER.get());
        dropSelf(ModBlocks.ROYAL_PAINTING_SMALL.get());
        dropSelf(ModBlocks.ROYAL_OVEN.get());

        add(ModBlocks.ROYAL_BED_SINGLE.get(), block ->
                createSinglePropConditionTable(block, BedBlock.PART, BedPart.FOOT));

        multiBlockDrop(ModBlocks.ROYAL_BED_DOUBLE.get(), NordicBedDoubleBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_DOOR_SINGLE.get(), NordicDoorSingleBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_DOOR_DOUBLE.get(), NordicDoorDoubleBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_CHAIR.get(), NordicChairBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_BENCH.get(), NordicBenchBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_CHEST.get(), NordicChestBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_DRESSER.get(), NordicDresserBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_DESK_LEFT.get(), NordicDeskLeftBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_DESK_RIGHT.get(), NordicDeskRightBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART);
        multiBlockDrop(ModBlocks.ROYAL_BOOKSHELF.get(), NordicBookshelfBlock.PART);

        // --- Royal decorations ---
        dropSelf(ModBlocks.ROYAL_CROWN.get());
        dropSelf(ModBlocks.ROYAL_CUSHIONED_CROWN.get());
        dropSelf(ModBlocks.ROYAL_CANDELABRA.get());
        dropSelf(ModBlocks.ROYAL_CHALICES.get());
        dropSelf(ModBlocks.ROYAL_PLATTER.get());
        dropSelf(ModBlocks.ROYAL_FOOD.get());
        dropSelf(ModBlocks.ROYAL_FLOOR_CUSHION.get());
        dropSelf(ModBlocks.ROYAL_WALL_MIRROR.get());
        multiBlockDrop(ModBlocks.ROYAL_WALL_MIRROR_TALL.get(),
                xyz.apex.minecraft.fantasyfurniture.block.royal.RoyalWallMirrorTallBlock.PART);

        // --- Dunmer set (mirrors Nordic loot structure) ---
        dropSelf(ModBlocks.DUNMER_WOOL.get());
        dropSelf(ModBlocks.DUNMER_CARPET.get());
        dropSelf(ModBlocks.DUNMER_WALL_LIGHT.get());
        dropSelf(ModBlocks.DUNMER_CHANDELIER_LIGHT.get());
        dropSelf(ModBlocks.DUNMER_TABLE_SMALL.get());
        dropSelf(ModBlocks.DUNMER_STOOL.get());
        dropSelf(ModBlocks.DUNMER_CUSHION.get());
        dropSelf(ModBlocks.DUNMER_SHELF.get());
        dropSelf(ModBlocks.DUNMER_SOFA.get());
        dropSelf(ModBlocks.DUNMER_DRAWER.get());
        dropSelf(ModBlocks.DUNMER_LOCKBOX.get());
        dropSelf(ModBlocks.DUNMER_COUNTER.get());
        dropSelf(ModBlocks.DUNMER_PAINTING_SMALL.get());
        dropSelf(ModBlocks.DUNMER_OVEN.get());
        dropSelf(ModBlocks.DUNMER_POTTERY_0.get());
        dropSelf(ModBlocks.DUNMER_POTTERY_1.get());

        add(ModBlocks.DUNMER_BED_SINGLE.get(), block ->
                createSinglePropConditionTable(block, BedBlock.PART, BedPart.FOOT));

        multiBlockDrop(ModBlocks.DUNMER_BED_DOUBLE.get(), NordicBedDoubleBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_DOOR_SINGLE.get(), NordicDoorSingleBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_DOOR_DOUBLE.get(), NordicDoorDoubleBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_CHAIR.get(), NordicChairBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_BENCH.get(), NordicBenchBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_CHEST.get(), NordicChestBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_DRESSER.get(), NordicDresserBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_DESK_LEFT.get(), NordicDeskLeftBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_DESK_RIGHT.get(), NordicDeskRightBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_BOOKSHELF.get(), NordicBookshelfBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_TABLE_WIDE.get(), NordicTableWideBlock.PART);
        multiBlockDrop(ModBlocks.DUNMER_TABLE_LARGE.get(), NordicTableLargeBlock.PART);

        // --- Venthyr set (mirrors Nordic loot structure) ---
        dropSelf(ModBlocks.VENTHYR_WOOL.get());
        dropSelf(ModBlocks.VENTHYR_CARPET.get());
        dropSelf(ModBlocks.VENTHYR_WALL_LIGHT.get());
        dropSelf(ModBlocks.VENTHYR_CHANDELIER_LIGHT.get());
        dropSelf(ModBlocks.VENTHYR_TABLE_SMALL.get());
        dropSelf(ModBlocks.VENTHYR_TABLE_SMALL_FANCY.get());
        dropSelf(ModBlocks.VENTHYR_STOOL.get());
        dropSelf(ModBlocks.VENTHYR_CUSHION.get());
        dropSelf(ModBlocks.VENTHYR_SHELF.get());
        dropSelf(ModBlocks.VENTHYR_SOFA.get());
        dropSelf(ModBlocks.VENTHYR_DRAWER.get());
        dropSelf(ModBlocks.VENTHYR_LOCKBOX.get());
        dropSelf(ModBlocks.VENTHYR_COUNTER.get());
        dropSelf(ModBlocks.VENTHYR_PAINTING_SMALL.get());
        dropSelf(ModBlocks.VENTHYR_OVEN.get());
        dropSelf(ModBlocks.VENTHYR_FOOD_0.get());
        dropSelf(ModBlocks.VENTHYR_FOOD_1.get());
        dropSelf(ModBlocks.VENTHYR_TOMES.get());
        dropSelf(ModBlocks.VENTHYR_TEA_CUPS.get());
        dropSelf(ModBlocks.VENTHYR_PLATTER.get());

        multiBlockDrop(ModBlocks.VENTHYR_TEA_SET.get(), VenthyrTeaSetBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_BANNER.get(), VenthyrBannerBlock.PART);
        dropSelf(ModBlocks.VENTHYR_CANDLES.get());
        dropSelf(ModBlocks.VENTHYR_CHALICES.get());
        dropSelf(ModBlocks.VENTHYR_WIDOW_BLOOM.get());

        add(ModBlocks.VENTHYR_BED_SINGLE.get(), block ->
                createSinglePropConditionTable(block, BedBlock.PART, BedPart.FOOT));

        multiBlockDrop(ModBlocks.VENTHYR_BED_DOUBLE.get(), NordicBedDoubleBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_DOOR_SINGLE.get(), NordicDoorSingleBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_DOOR_DOUBLE.get(), NordicDoorDoubleBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_CHAIR.get(), NordicChairBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_BENCH.get(), NordicBenchBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_CHEST.get(), NordicChestBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_DRESSER.get(), NordicDresserBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_DESK_LEFT.get(), NordicDeskLeftBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_DESK_RIGHT.get(), NordicDeskRightBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_BOOKSHELF.get(), NordicBookshelfBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_TABLE_WIDE.get(), NordicTableWideBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_TABLE_WIDE_FANCY.get(), NordicTableWideBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_TABLE_LARGE.get(), NordicTableLargeBlock.PART);
        multiBlockDrop(ModBlocks.VENTHYR_TABLE_LARGE_FANCY.get(), NordicTableLargeBlock.PART);

        // --- Necrolord set (mirrors Nordic loot structure) ---
        dropSelf(ModBlocks.NECROLORD_WOOL.get());
        dropSelf(ModBlocks.NECROLORD_CARPET.get());
        dropSelf(ModBlocks.NECROLORD_WALL_LIGHT.get());
        dropSelf(ModBlocks.NECROLORD_CHANDELIER_LIGHT.get());
        dropSelf(ModBlocks.NECROLORD_TABLE_SMALL.get());
        dropSelf(ModBlocks.NECROLORD_STOOL.get());
        dropSelf(ModBlocks.NECROLORD_CUSHION.get());
        dropSelf(ModBlocks.NECROLORD_SHELF.get());
        dropSelf(ModBlocks.NECROLORD_SOFA.get());
        dropSelf(ModBlocks.NECROLORD_DRAWER.get());
        dropSelf(ModBlocks.NECROLORD_LOCKBOX.get());
        dropSelf(ModBlocks.NECROLORD_COUNTER.get());
        dropSelf(ModBlocks.NECROLORD_PAINTING_SMALL.get());
        dropSelf(ModBlocks.NECROLORD_OVEN.get());
        dropSelf(ModBlocks.NECROLORD_CANDELABRA.get());

        add(ModBlocks.NECROLORD_BED_SINGLE.get(), block ->
                createSinglePropConditionTable(block, BedBlock.PART, BedPart.FOOT));

        multiBlockDrop(ModBlocks.NECROLORD_BED_DOUBLE.get(), NordicBedDoubleBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_DOOR_SINGLE.get(), NordicDoorSingleBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_DOOR_DOUBLE.get(), NordicDoorDoubleBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_CHAIR.get(), NordicChairBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_BENCH.get(), NordicBenchBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_CHEST.get(), NordicChestBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_DRESSER.get(), NordicDresserBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_DESK_LEFT.get(), NordicDeskLeftBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_DESK_RIGHT.get(), NordicDeskRightBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_BOOKSHELF.get(), NordicBookshelfBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_TABLE_WIDE.get(), NordicTableWideBlock.PART);
        multiBlockDrop(ModBlocks.NECROLORD_TABLE_LARGE.get(), NordicTableLargeBlock.PART);

        // --- Bone-Skeleton set (mirrors Nordic loot structure) ---
        dropSelf(ModBlocks.BONE_SKELETON_WOOL.get());
        dropSelf(ModBlocks.BONE_SKELETON_CARPET.get());
        dropSelf(ModBlocks.BONE_SKELETON_WALL_LIGHT.get());
        dropSelf(ModBlocks.BONE_SKELETON_CHANDELIER_LIGHT.get());
        dropSelf(ModBlocks.BONE_SKELETON_TABLE_SMALL.get());
        dropSelf(ModBlocks.BONE_SKELETON_STOOL.get());
        dropSelf(ModBlocks.BONE_SKELETON_SKULL.get());
        dropSelf(ModBlocks.BONE_SKELETON_SHELF.get());
        dropSelf(ModBlocks.BONE_SKELETON_SOFA.get());
        dropSelf(ModBlocks.BONE_SKELETON_DRAWER.get());
        dropSelf(ModBlocks.BONE_SKELETON_LOCKBOX.get());
        dropSelf(ModBlocks.BONE_SKELETON_COUNTER.get());
        dropSelf(ModBlocks.BONE_SKELETON_PAINTING_SMALL.get());
        dropSelf(ModBlocks.BONE_SKELETON_OVEN.get());
        dropSelf(ModBlocks.BONE_SKELETON_CHALICES.get());
        dropSelf(ModBlocks.BONE_SKELETON_PILE.get());
        dropSelf(ModBlocks.BONE_SKELETON_SKULL_BLOSSOMS.get());

        add(ModBlocks.BONE_SKELETON_BED_SINGLE.get(), block ->
                createSinglePropConditionTable(block, BedBlock.PART, BedPart.FOOT));

        multiBlockDrop(ModBlocks.BONE_SKELETON_BED_DOUBLE.get(), NordicBedDoubleBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_DOOR_SINGLE.get(), NordicDoorSingleBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_DOOR_DOUBLE.get(), NordicDoorDoubleBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_CHAIR.get(), NordicChairBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_BENCH.get(), NordicBenchBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_CHEST.get(), NordicChestBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_DRESSER.get(), NordicDresserBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_DESK_LEFT.get(), NordicDeskLeftBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_DESK_RIGHT.get(), NordicDeskRightBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_BOOKSHELF.get(), NordicBookshelfBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_TABLE_WIDE.get(), NordicTableWideBlock.PART);
        multiBlockDrop(ModBlocks.BONE_SKELETON_TABLE_LARGE.get(), NordicTableLargeBlock.PART);

        // --- Bone-Wither set (mirrors Nordic loot structure) ---
        dropSelf(ModBlocks.BONE_WITHER_WOOL.get());
        dropSelf(ModBlocks.BONE_WITHER_CARPET.get());
        dropSelf(ModBlocks.BONE_WITHER_WALL_LIGHT.get());
        dropSelf(ModBlocks.BONE_WITHER_CHANDELIER_LIGHT.get());
        dropSelf(ModBlocks.BONE_WITHER_TABLE_SMALL.get());
        dropSelf(ModBlocks.BONE_WITHER_STOOL.get());
        dropSelf(ModBlocks.BONE_WITHER_SKULL.get());
        dropSelf(ModBlocks.BONE_WITHER_SHELF.get());
        dropSelf(ModBlocks.BONE_WITHER_SOFA.get());
        dropSelf(ModBlocks.BONE_WITHER_DRAWER.get());
        dropSelf(ModBlocks.BONE_WITHER_LOCKBOX.get());
        dropSelf(ModBlocks.BONE_WITHER_COUNTER.get());
        dropSelf(ModBlocks.BONE_WITHER_PAINTING_SMALL.get());
        dropSelf(ModBlocks.BONE_WITHER_OVEN.get());
        dropSelf(ModBlocks.BONE_WITHER_CHALICES.get());
        dropSelf(ModBlocks.BONE_WITHER_PILE.get());
        dropSelf(ModBlocks.BONE_WITHER_SKULL_BLOSSOMS.get());

        add(ModBlocks.BONE_WITHER_BED_SINGLE.get(), block ->
                createSinglePropConditionTable(block, BedBlock.PART, BedPart.FOOT));

        multiBlockDrop(ModBlocks.BONE_WITHER_BED_DOUBLE.get(), NordicBedDoubleBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_DOOR_SINGLE.get(), NordicDoorSingleBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_DOOR_DOUBLE.get(), NordicDoorDoubleBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_CHAIR.get(), NordicChairBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_FLOOR_LIGHT.get(), NordicFloorLightBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_BENCH.get(), NordicBenchBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_CHEST.get(), NordicChestBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_DRESSER.get(), NordicDresserBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_DESK_LEFT.get(), NordicDeskLeftBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_DESK_RIGHT.get(), NordicDeskRightBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_PAINTING_WIDE.get(), NordicPaintingWideBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_WARDROBE_TOP.get(), NordicWardrobeTopBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_BOOKSHELF.get(), NordicBookshelfBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_TABLE_WIDE.get(), NordicTableWideBlock.PART);
        multiBlockDrop(ModBlocks.BONE_WITHER_TABLE_LARGE.get(), NordicTableLargeBlock.PART);
    }

    /**
     * Creates a loot table that only drops the block when the PART integer property equals 0.
     */
    private void multiBlockDrop(Block block, IntegerProperty partProperty) {
        add(block, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(partProperty, 0)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return java.util.stream.Stream.of(
                ModBlocks.BLOCKS.getEntries().stream(),
                ModBlocks.NORDIC_BLOCKS.getEntries().stream(),
                ModBlocks.ROYAL_BLOCKS.getEntries().stream(),
                ModBlocks.DUNMER_BLOCKS.getEntries().stream(),
                ModBlocks.VENTHYR_BLOCKS.getEntries().stream(),
                ModBlocks.NECROLORD_BLOCKS.getEntries().stream(),
                ModBlocks.BONE_SKELETON_BLOCKS.getEntries().stream(),
                ModBlocks.BONE_WITHER_BLOCKS.getEntries().stream()
        ).flatMap(s -> s).map(holder -> (Block) holder.get()).toList();
    }
}
