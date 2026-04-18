package xyz.apex.minecraft.fantasyfurniture;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.junit.jupiter.api.Test;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlocks;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Verifies every registered Block has a corresponding translation key in every bundled locale.
 *
 * The en_us file is produced by {@link xyz.apex.minecraft.fantasyfurniture.datagen.ModLanguageProvider}
 * via {@code runData} and bundled from {@code src/generated/resources/}. Community locales
 * (zh_cn, uk_ua) live in {@code src/main/resources/}. This test loads those files from the
 * classpath and asserts coverage — catching missing provider entries, stale entries after a
 * block rename, and locale drift when blocks are added.
 *
 * Also checks fixed keys that aren't tied to a registered Block (itemGroup, entity.seat,
 * container.*) in the main namespace.
 */
class TranslationCoverageTest {

    private static final List<String> LOCALES = List.of("en_us", "zh_cn", "uk_ua");

    private static final Set<String> NAMESPACES = Set.of(
            FantasyFurniture.MOD_ID,
            FantasyFurniture.NORDIC_ID,
            FantasyFurniture.ROYAL_ID,
            FantasyFurniture.VENTHYR_ID,
            FantasyFurniture.DUNMER_ID,
            FantasyFurniture.NECROLORD_ID,
            FantasyFurniture.BONE_SKELETON_ID,
            FantasyFurniture.BONE_WITHER_ID
    );

    private static final List<String> MAIN_FIXED_KEYS = List.of(
            "itemGroup.fantasyfurniture",
            "entity.fantasyfurniture.seat",
            "container.fantasyfurniture.small_container",
            "container.fantasyfurniture.medium_container",
            "container.fantasyfurniture.large_container",
            "container.fantasyfurniture.bookshelf",
            "container.fantasyfurniture.furnace",
            "container.fantasyfurniture.furniture_station"
    );

    @Test
    void everyBlockHasTranslation() {
        Map<String, Set<ResourceLocation>> byNamespace = collectBlocksByNamespace(
                ModBlocks.BLOCKS,
                ModBlocks.NORDIC_BLOCKS,
                ModBlocks.ROYAL_BLOCKS,
                ModBlocks.VENTHYR_BLOCKS,
                ModBlocks.DUNMER_BLOCKS,
                ModBlocks.NECROLORD_BLOCKS,
                ModBlocks.BONE_SKELETON_BLOCKS,
                ModBlocks.BONE_WITHER_BLOCKS
        );

        Set<String> missing = new TreeSet<>();
        for (String locale : LOCALES) {
            for (Map.Entry<String, Set<ResourceLocation>> entry : byNamespace.entrySet()) {
                String namespace = entry.getKey();
                JsonObject lang = loadLang(namespace, locale);
                for (ResourceLocation id : entry.getValue()) {
                    String key = "block." + namespace + "." + id.getPath();
                    if (!lang.has(key)) {
                        missing.add(locale + " :: " + key);
                    }
                }
            }
        }

        if (!missing.isEmpty()) {
            throw new AssertionError(
                    "Registered blocks without a translation (did you forget to update ModLanguageProvider or community lang file?):\n  "
                            + String.join("\n  ", missing));
        }
    }

    @Test
    void mainNamespaceFixedKeysPresent() {
        Set<String> missing = new TreeSet<>();
        for (String locale : LOCALES) {
            JsonObject lang = loadLang(FantasyFurniture.MOD_ID, locale);
            for (String key : MAIN_FIXED_KEYS) {
                if (!lang.has(key)) {
                    missing.add(locale + " :: " + key);
                }
            }
        }
        if (!missing.isEmpty()) {
            throw new AssertionError(
                    "Main namespace fixed translation keys missing:\n  " + String.join("\n  ", missing));
        }
    }

    @Test
    void translationValuesAreNonEmpty() {
        Set<String> badEntries = new TreeSet<>();
        for (String locale : LOCALES) {
            for (String namespace : NAMESPACES) {
                JsonObject lang = loadLang(namespace, locale);
                for (Map.Entry<String, com.google.gson.JsonElement> entry : lang.entrySet()) {
                    String value = entry.getValue().isJsonPrimitive() ? entry.getValue().getAsString() : "";
                    if (value.isBlank()) {
                        badEntries.add(locale + ":" + namespace + ":" + entry.getKey());
                    }
                }
            }
        }
        if (!badEntries.isEmpty()) {
            throw new AssertionError(
                    "Translation entries with blank values:\n  " + String.join("\n  ", badEntries));
        }
    }

    // --- Helpers ---

    private static Map<String, Set<ResourceLocation>> collectBlocksByNamespace(DeferredRegister<?>... registers) {
        Map<String, Set<ResourceLocation>> out = new LinkedHashMap<>();
        for (DeferredRegister<?> register : registers) {
            for (DeferredHolder<?, ?> holder : register.getEntries()) {
                ResourceLocation id = holder.getId();
                out.computeIfAbsent(id.getNamespace(), k -> new LinkedHashSet<>()).add(id);
            }
        }
        return out;
    }

    private static JsonObject loadLang(String namespace, String locale) {
        String path = "/assets/" + namespace + "/lang/" + locale + ".json";
        try (InputStream in = TranslationCoverageTest.class.getResourceAsStream(path)) {
            assertNotNull(in, "Missing " + locale + ".json on classpath for namespace '" + namespace
                    + "' (expected at " + path + "). Did runData run for en_us? Did you add the community lang file?");
            return JsonParser.parseReader(new InputStreamReader(in, StandardCharsets.UTF_8)).getAsJsonObject();
        } catch (IOException e) {
            throw new AssertionError("Failed to read " + path, e);
        }
    }
}
