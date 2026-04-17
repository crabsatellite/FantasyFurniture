package xyz.apex.minecraft.fantasyfurniture;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.junit.jupiter.api.Test;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlockEntities;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlocks;
import xyz.apex.minecraft.fantasyfurniture.registry.ModItems;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Save-compatibility guard: the set of registered block, item, and block-entity
 * IDs MUST exactly match the upstream 1.20.x FantasyFurniture registration so that
 * existing worlds load unchanged after upgrading to this 1.21.1 port.
 *
 * Expected sets are hard-coded from the upstream 1.20.x source
 * (FurnitureSets.java nordic registrations + main fantasyfurniture furniture_station).
 *
 * If you intentionally add or rename a registry entry, update the expected set below
 * and call out the save-break in the release notes.
 */
class RegistryIdSnapshotTest {

    private static final String MAIN = FantasyFurniture.MOD_ID;
    private static final String NORDIC = FantasyFurniture.NORDIC_ID;

    // --- Expected upstream 1.20.x IDs ---

    private static final Set<ResourceLocation> EXPECTED_BLOCKS = set(
            main("furniture_station"),
            nordic("wool"), nordic("carpet"),
            nordic("wall_light"), nordic("floor_light"), nordic("chandelier"),
            nordic("table"), nordic("chair"), nordic("bench"),
            nordic("stool"), nordic("cushion"),
            nordic("shelf"), nordic("sofa"),
            nordic("drawer"), nordic("lockbox"),
            nordic("desk_left"), nordic("desk_right"),
            nordic("dresser"), nordic("counter"),
            nordic("chest"),
            nordic("wardrobe_bottom"), nordic("wardrobe_top"),
            nordic("bookshelf"),
            nordic("bed_single"), nordic("bed_double"),
            nordic("door_single"), nordic("door_double"),
            nordic("painting_small"), nordic("painting_wide"),
            nordic("oven")
    );

    // Items mirror blocks 1:1 (all blocks have BlockItems; no stand-alone items in this port).
    private static final Set<ResourceLocation> EXPECTED_ITEMS = EXPECTED_BLOCKS;

    private static final Set<ResourceLocation> EXPECTED_BLOCK_ENTITIES = set(
            nordic("small_container"),
            nordic("medium_container"),
            nordic("large_container"),
            nordic("bookshelf"),
            nordic("furnace")
    );

    // --- Tests ---

    @Test
    void blocksMatchUpstream() {
        Set<ResourceLocation> actual = collect(ModBlocks.BLOCKS, ModBlocks.NORDIC_BLOCKS);
        assertExactMatch(EXPECTED_BLOCKS, actual, "blocks");
    }

    @Test
    void itemsMatchUpstream() {
        Set<ResourceLocation> actual = collect(ModItems.ITEMS, ModItems.NORDIC_ITEMS);
        assertExactMatch(EXPECTED_ITEMS, actual, "items");
    }

    @Test
    void blockEntitiesMatchUpstream() {
        Set<ResourceLocation> actual = collect(
                ModBlockEntities.BLOCK_ENTITY_TYPES,
                ModBlockEntities.NORDIC_BLOCK_ENTITY_TYPES
        );
        assertExactMatch(EXPECTED_BLOCK_ENTITIES, actual, "block entities");
    }

    @Test
    void noNamespaceCollisions() {
        // Each id must appear in exactly one DeferredRegister.
        // (If the same path were registered under both MAIN and NORDIC registers it would
        // still be two distinct ResourceLocations — but a path must not be duplicated within
        // any single register.)
        assertNoDuplicates(ModBlocks.BLOCKS);
        assertNoDuplicates(ModBlocks.NORDIC_BLOCKS);
        assertNoDuplicates(ModItems.ITEMS);
        assertNoDuplicates(ModItems.NORDIC_ITEMS);
        assertNoDuplicates(ModBlockEntities.BLOCK_ENTITY_TYPES);
        assertNoDuplicates(ModBlockEntities.NORDIC_BLOCK_ENTITY_TYPES);
    }

    // --- Helpers ---

    private static Set<ResourceLocation> collect(DeferredRegister<?>... registers) {
        Set<ResourceLocation> ids = new LinkedHashSet<>();
        for (DeferredRegister<?> register : registers) {
            for (DeferredHolder<?, ?> holder : register.getEntries()) {
                ids.add(holder.getId());
            }
        }
        return ids;
    }

    private static void assertNoDuplicates(DeferredRegister<?> register) {
        long total = register.getEntries().size();
        long unique = register.getEntries().stream()
                .map(DeferredHolder::getId)
                .distinct()
                .count();
        assertEquals(total, unique,
                "Duplicate ids inside register (total=" + total + ", unique=" + unique + ")");
    }

    private static void assertExactMatch(Set<ResourceLocation> expected, Set<ResourceLocation> actual, String label) {
        Set<ResourceLocation> missing = diff(expected, actual);
        Set<ResourceLocation> extra = diff(actual, expected);
        if (!missing.isEmpty() || !extra.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Registered ").append(label).append(" diverge from 1.20.x upstream snapshot.\n");
            if (!missing.isEmpty()) sb.append("  Missing (present upstream, not registered): ").append(sorted(missing)).append('\n');
            if (!extra.isEmpty())   sb.append("  Extra   (registered, absent upstream):      ").append(sorted(extra)).append('\n');
            sb.append("If this change is intentional, update the EXPECTED_* constants and note the save-break in release notes.");
            throw new AssertionError(sb.toString());
        }
    }

    private static Set<ResourceLocation> diff(Set<ResourceLocation> a, Set<ResourceLocation> b) {
        return a.stream().filter(id -> !b.contains(id)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static TreeSet<String> sorted(Set<ResourceLocation> ids) {
        return ids.stream().map(ResourceLocation::toString)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private static Set<ResourceLocation> set(ResourceLocation... ids) {
        return Set.of(ids);
    }

    private static ResourceLocation main(String path) {
        return ResourceLocation.fromNamespaceAndPath(MAIN, path);
    }

    private static ResourceLocation nordic(String path) {
        return ResourceLocation.fromNamespaceAndPath(NORDIC, path);
    }
}
