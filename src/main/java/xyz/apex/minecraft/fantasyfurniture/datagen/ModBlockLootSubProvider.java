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

        // PART 0-3 (MB_2x2x1)
        multiBlockDrop(ModBlocks.NORDIC_WARDROBE_BOTTOM.get(), NordicWardrobeBottomBlock.PART);
        multiBlockDrop(ModBlocks.NORDIC_BOOKSHELF.get(), NordicBookshelfBlock.PART);
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
        return java.util.stream.Stream.concat(
                ModBlocks.BLOCKS.getEntries().stream(),
                ModBlocks.NORDIC_BLOCKS.getEntries().stream()
        ).map(holder -> (Block) holder.get()).toList();
    }
}
