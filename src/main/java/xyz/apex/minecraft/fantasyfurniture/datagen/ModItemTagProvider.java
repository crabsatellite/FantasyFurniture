package xyz.apex.minecraft.fantasyfurniture.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTagLookup,
                              ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagLookup, FantasyFurniture.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Copy block tags to item tags
        copy(BlockTags.WOOL, ItemTags.WOOL);
        copy(BlockTags.WOOL_CARPETS, ItemTags.WOOL_CARPETS);
        copy(BlockTags.BEDS, ItemTags.BEDS);
        copy(BlockTags.DOORS, ItemTags.DOORS);

        // Custom mod tag: fantasyfurniture:furniture_station_binding_agent
        TagKey<Item> bindingAgent = ItemTags.create(
                ResourceLocation.fromNamespaceAndPath(FantasyFurniture.MOD_ID, "furniture_station_binding_agent"));
        tag(bindingAgent).add(Items.CLAY_BALL);
    }
}
