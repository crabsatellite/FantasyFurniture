package xyz.apex.minecraft.fantasyfurniture.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FantasyFurniture.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // minecraft:mineable/axe - all wood furniture blocks
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.FURNITURE_STATION.get())
                .add(ModBlocks.NORDIC_WALL_LIGHT.get())
                .add(ModBlocks.NORDIC_FLOOR_LIGHT.get())
                .add(ModBlocks.NORDIC_CHANDELIER_LIGHT.get())
                .add(ModBlocks.NORDIC_TABLE.get())
                .add(ModBlocks.NORDIC_CHAIR.get())
                .add(ModBlocks.NORDIC_BENCH.get())
                .add(ModBlocks.NORDIC_STOOL.get())
                .add(ModBlocks.NORDIC_CUSHION.get())
                .add(ModBlocks.NORDIC_SHELF.get())
                .add(ModBlocks.NORDIC_SOFA.get())
                .add(ModBlocks.NORDIC_DRAWER.get())
                .add(ModBlocks.NORDIC_LOCKBOX.get())
                .add(ModBlocks.NORDIC_DESK_LEFT.get())
                .add(ModBlocks.NORDIC_DESK_RIGHT.get())
                .add(ModBlocks.NORDIC_DRESSER.get())
                .add(ModBlocks.NORDIC_COUNTER.get())
                .add(ModBlocks.NORDIC_CHEST.get())
                .add(ModBlocks.NORDIC_WARDROBE_BOTTOM.get())
                .add(ModBlocks.NORDIC_WARDROBE_TOP.get())
                .add(ModBlocks.NORDIC_BOOKSHELF.get())
                .add(ModBlocks.NORDIC_BED_SINGLE.get())
                .add(ModBlocks.NORDIC_BED_DOUBLE.get())
                .add(ModBlocks.NORDIC_DOOR_SINGLE.get())
                .add(ModBlocks.NORDIC_DOOR_DOUBLE.get())
                .add(ModBlocks.NORDIC_PAINTING_SMALL.get())
                .add(ModBlocks.NORDIC_PAINTING_WIDE.get());

        // minecraft:mineable/pickaxe - stone blocks
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.NORDIC_OVEN.get());

        // minecraft:wool
        tag(BlockTags.WOOL)
                .add(ModBlocks.NORDIC_WOOL.get());

        // minecraft:wool_carpets
        tag(BlockTags.WOOL_CARPETS)
                .add(ModBlocks.NORDIC_CARPET.get());

        // minecraft:beds
        tag(BlockTags.BEDS)
                .add(ModBlocks.NORDIC_BED_SINGLE.get())
                .add(ModBlocks.NORDIC_BED_DOUBLE.get());

        // minecraft:doors
        tag(BlockTags.DOORS)
                .add(ModBlocks.NORDIC_DOOR_SINGLE.get())
                .add(ModBlocks.NORDIC_DOOR_DOUBLE.get());
    }
}
