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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Verifies every registered Block has a corresponding en_us translation key.
 *
 * The translation files are produced by {@link xyz.apex.minecraft.fantasyfurniture.datagen.ModLanguageProvider}
 * via {@code runData} and bundled from {@code src/generated/resources/}. This test loads
 * those files from the classpath and asserts coverage — catching both missing provider
 * entries and stale provider entries after a block rename.
 *
 * Also checks fixed keys that aren't tied to a registered Block (itemGroup, entity.seat,
 * container.*).
 */
class TranslationCoverageTest {

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
        for (Map.Entry<String, Set<ResourceLocation>> entry : byNamespace.entrySet()) {
            String namespace = entry.getKey();
            JsonObject lang = loadLang(namespace);
            for (ResourceLocation id : entry.getValue()) {
                String key = "block." + namespace + "." + id.getPath();
                if (!lang.has(key)) {
                    missing.add(key);
                }
            }
        }

        if (!missing.isEmpty()) {
            throw new AssertionError(
                    "Registered blocks without an en_us translation (did you forget to update ModLanguageProvider?):\n  "
                            + String.join("\n  ", missing));
        }
    }

    @Test
    void mainNamespaceFixedKeysPresent() {
        JsonObject lang = loadLang(FantasyFurniture.MOD_ID);
        List<String> required = List.of(
                "itemGroup.fantasyfurniture",
                "entity.fantasyfurniture.seat",
                "container.fantasyfurniture.small_container",
                "container.fantasyfurniture.medium_container",
                "container.fantasyfurniture.large_container",
                "container.fantasyfurniture.bookshelf",
                "container.fantasyfurniture.furnace",
                "container.fantasyfurniture.furniture_station"
        );
        Set<String> missing = required.stream()
                .filter(key -> !lang.has(key))
                .collect(Collectors.toCollection(TreeSet::new));
        if (!missing.isEmpty()) {
            throw new AssertionError(
                    "Main namespace fixed translation keys missing:\n  " + String.join("\n  ", missing));
        }
    }

    @Test
    void translationValuesAreNonEmpty() {
        Set<String> namespaces = Set.of(
                FantasyFurniture.MOD_ID,
                FantasyFurniture.NORDIC_ID,
                FantasyFurniture.ROYAL_ID,
                FantasyFurniture.VENTHYR_ID,
                FantasyFurniture.DUNMER_ID,
                FantasyFurniture.NECROLORD_ID,
                FantasyFurniture.BONE_SKELETON_ID,
                FantasyFurniture.BONE_WITHER_ID
        );
        Set<String> badEntries = new TreeSet<>();
        for (String namespace : namespaces) {
            JsonObject lang = loadLang(namespace);
            for (Map.Entry<String, com.google.gson.JsonElement> entry : lang.entrySet()) {
                String value = entry.getValue().isJsonPrimitive() ? entry.getValue().getAsString() : "";
                if (value.isBlank()) {
                    badEntries.add(namespace + ":" + entry.getKey());
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

    private static JsonObject loadLang(String namespace) {
        String path = "/assets/" + namespace + "/lang/en_us.json";
        try (InputStream in = TranslationCoverageTest.class.getResourceAsStream(path)) {
            assertNotNull(in, "Missing en_us.json on classpath for namespace '" + namespace
                    + "' (expected at " + path + "). Did runData run?");
            return JsonParser.parseReader(new InputStreamReader(in, StandardCharsets.UTF_8)).getAsJsonObject();
        } catch (IOException e) {
            throw new AssertionError("Failed to read " + path, e);
        }
    }
}
