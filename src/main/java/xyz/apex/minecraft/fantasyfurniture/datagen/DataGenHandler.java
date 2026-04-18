package xyz.apex.minecraft.fantasyfurniture.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = FantasyFurniture.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Client data
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(output, existingFileHelper));

        // Server data
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(output, lookupProvider));

        ModBlockTagProvider blockTagProvider = new ModBlockTagProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagProvider);
        generator.addProvider(event.includeServer(), new ModItemTagProvider(output, lookupProvider,
                blockTagProvider.contentsGetter(), existingFileHelper));

        // Recipes
        generator.addProvider(event.includeServer(), new ModRecipeProvider(output, lookupProvider));

        // Language files — one LanguageProvider per namespace, output to
        // assets/<namespace>/lang/en_us.json. Community translations (uk_ua, zh_cn)
        // remain hand-written under src/main/resources.
        generator.addProvider(event.includeClient(), ModLanguageProvider.main(output));
        generator.addProvider(event.includeClient(), ModLanguageProvider.nordic(output));
        generator.addProvider(event.includeClient(), ModLanguageProvider.royal(output));
        generator.addProvider(event.includeClient(), ModLanguageProvider.venthyr(output));
        generator.addProvider(event.includeClient(), ModLanguageProvider.dunmer(output));
        generator.addProvider(event.includeClient(), ModLanguageProvider.necrolord(output));
        generator.addProvider(event.includeClient(), ModLanguageProvider.boneSkeleton(output));
        generator.addProvider(event.includeClient(), ModLanguageProvider.boneWither(output));
    }
}
