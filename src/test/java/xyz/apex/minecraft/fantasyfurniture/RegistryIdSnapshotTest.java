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
    private static final String ROYAL = FantasyFurniture.ROYAL_ID;
    private static final String DUNMER = FantasyFurniture.DUNMER_ID;
    private static final String VENTHYR = FantasyFurniture.VENTHYR_ID;
    private static final String NECROLORD = FantasyFurniture.NECROLORD_ID;
    private static final String BONE_SKELETON = FantasyFurniture.BONE_SKELETON_ID;
    private static final String BONE_WITHER = FantasyFurniture.BONE_WITHER_ID;

    // --- Expected upstream 1.20.x IDs ---
    // Nordic block IDs match upstream 1.20.x save exactly.
    // Royal block IDs are our 1.21.1 forward-port of 1.19.4 Royal set.
    // NOTE: Our structure consolidates multi-variant blocks (shelf/sofa/door/counter)
    // into single entries; 1.19.4 parity for consolidated variants is tracked in
    // UpstreamFeatureParityTest (TODO).

    private static final Set<ResourceLocation> EXPECTED_NORDIC_BLOCKS = set(
            nordic("wool"), nordic("carpet"),
            nordic("wall_light"), nordic("floor_light"), nordic("chandelier"),
            nordic("table"), nordic("table_wide"), nordic("table_large"),
            nordic("chair"), nordic("bench"),
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
            nordic("oven"),
            // Decorations (1.19.4 parity)
            nordic("boiled_creme_treats"), nordic("sweetrolls"),
            nordic("mead_bottles"),
            nordic("soul_gems_light"), nordic("soul_gems_dark")
    );

    private static final Set<ResourceLocation> EXPECTED_ROYAL_BLOCKS = set(
            royal("wool"), royal("carpet"),
            royal("wall_light"), royal("floor_light"), royal("chandelier"),
            royal("table"), royal("chair"), royal("bench"),
            royal("stool"), royal("cushion"),
            royal("shelf"), royal("sofa"),
            royal("drawer"), royal("lockbox"),
            royal("desk_left"), royal("desk_right"),
            royal("dresser"), royal("counter"),
            royal("chest"),
            royal("wardrobe_bottom"), royal("wardrobe_top"),
            royal("bookshelf"),
            royal("bed_single"), royal("bed_double"),
            royal("door_single"), royal("door_double"),
            royal("painting_small"), royal("painting_wide"),
            royal("oven"),
            // Decorations (1.19.4 parity)
            royal("crown"), royal("cushioned_crown"),
            royal("candelabra"), royal("chalices"), royal("platter"),
            royal("food"), royal("floor_cushion"),
            royal("wall_mirror"), royal("wall_mirror_tall")
    );

    // Dunmer: 1.19.4 upstream forward-port. "table_small" (not "table"); oven
    // is a single-block in this port (upstream had an OvenMultiBlock cooking
    // spit — tracked as future work). Pottery decorations (pottery_0/pottery_1)
    // not yet ported.
    private static final Set<ResourceLocation> EXPECTED_DUNMER_BLOCKS = set(
            dunmer("wool"), dunmer("carpet"),
            dunmer("wall_light"), dunmer("floor_light"), dunmer("chandelier"),
            dunmer("table_small"), dunmer("table_wide"), dunmer("table_large"),
            dunmer("chair"), dunmer("bench"),
            dunmer("stool"), dunmer("cushion"),
            dunmer("shelf"), dunmer("sofa"),
            dunmer("drawer"), dunmer("lockbox"),
            dunmer("desk_left"), dunmer("desk_right"),
            dunmer("dresser"), dunmer("counter"),
            dunmer("chest"),
            dunmer("wardrobe_bottom"), dunmer("wardrobe_top"),
            dunmer("bookshelf"),
            dunmer("bed_single"), dunmer("bed_double"),
            dunmer("door_single"), dunmer("door_double"),
            dunmer("painting_small"), dunmer("painting_wide"),
            dunmer("oven"),
            dunmer("pottery_0"), dunmer("pottery_1")
    );

    // Venthyr: 1.19.4 upstream forward-port. Adds three "fancy" table
    // variants (table_small_fancy, table_wide_fancy, table_large_fancy) that
    // share block classes with the non-fancy tables (model/texture differs).
    private static final Set<ResourceLocation> EXPECTED_VENTHYR_BLOCKS = set(
            venthyr("wool"), venthyr("carpet"),
            venthyr("wall_light"), venthyr("floor_light"), venthyr("chandelier"),
            venthyr("table_small"), venthyr("table_small_fancy"),
            venthyr("table_wide"), venthyr("table_wide_fancy"),
            venthyr("table_large"), venthyr("table_large_fancy"),
            venthyr("chair"), venthyr("bench"),
            venthyr("stool"), venthyr("cushion"),
            venthyr("shelf"), venthyr("sofa"),
            venthyr("drawer"), venthyr("lockbox"),
            venthyr("desk_left"), venthyr("desk_right"),
            venthyr("dresser"), venthyr("counter"),
            venthyr("chest"),
            venthyr("wardrobe_bottom"), venthyr("wardrobe_top"),
            venthyr("bookshelf"),
            venthyr("bed_single"), venthyr("bed_double"),
            venthyr("door_single"), venthyr("door_double"),
            venthyr("painting_small"), venthyr("painting_wide"),
            venthyr("oven"),
            venthyr("food_0"), venthyr("food_1"),
            venthyr("tomes"), venthyr("tea_cups"), venthyr("platter"),
            venthyr("tea_set"), venthyr("banner"), venthyr("candles"),
            venthyr("chalices"), venthyr("widow_bloom")
    );

    private static final Set<ResourceLocation> EXPECTED_NECROLORD_BLOCKS = set(
            necrolord("wool"), necrolord("carpet"),
            necrolord("wall_light"), necrolord("floor_light"), necrolord("chandelier"),
            necrolord("table_small"),
            necrolord("table_wide"),
            necrolord("table_large"),
            necrolord("chair"), necrolord("bench"),
            necrolord("stool"), necrolord("cushion"),
            necrolord("shelf"), necrolord("sofa"),
            necrolord("drawer"), necrolord("lockbox"),
            necrolord("desk_left"), necrolord("desk_right"),
            necrolord("dresser"), necrolord("counter"),
            necrolord("chest"),
            necrolord("wardrobe_bottom"), necrolord("wardrobe_top"),
            necrolord("bookshelf"),
            necrolord("bed_single"), necrolord("bed_double"),
            necrolord("door_single"), necrolord("door_double"),
            necrolord("painting_small"), necrolord("painting_wide"),
            necrolord("oven"),
            necrolord("candelabra")
    );

    private static final Set<ResourceLocation> EXPECTED_BONE_SKELETON_BLOCKS = set(
            boneSkeleton("wool"), boneSkeleton("carpet"),
            boneSkeleton("wall_light"), boneSkeleton("floor_light"), boneSkeleton("chandelier"),
            boneSkeleton("table_small"),
            boneSkeleton("table_wide"),
            boneSkeleton("table_large"),
            boneSkeleton("chair"), boneSkeleton("bench"),
            boneSkeleton("stool"), boneSkeleton("skull"),
            boneSkeleton("shelf"), boneSkeleton("sofa"),
            boneSkeleton("drawer"), boneSkeleton("lockbox"),
            boneSkeleton("desk_left"), boneSkeleton("desk_right"),
            boneSkeleton("dresser"), boneSkeleton("counter"),
            boneSkeleton("chest"),
            boneSkeleton("wardrobe_bottom"), boneSkeleton("wardrobe_top"),
            boneSkeleton("bookshelf"),
            boneSkeleton("bed_single"), boneSkeleton("bed_double"),
            boneSkeleton("door_single"), boneSkeleton("door_double"),
            boneSkeleton("painting_small"), boneSkeleton("painting_wide"),
            boneSkeleton("oven"),
            boneSkeleton("chalices"), boneSkeleton("pile"),
            boneSkeleton("skull_blossoms")
    );

    private static final Set<ResourceLocation> EXPECTED_BONE_WITHER_BLOCKS = set(
            boneWither("wool"), boneWither("carpet"),
            boneWither("wall_light"), boneWither("floor_light"), boneWither("chandelier"),
            boneWither("table_small"),
            boneWither("table_wide"),
            boneWither("table_large"),
            boneWither("chair"), boneWither("bench"),
            boneWither("stool"), boneWither("skull"),
            boneWither("shelf"), boneWither("sofa"),
            boneWither("drawer"), boneWither("lockbox"),
            boneWither("desk_left"), boneWither("desk_right"),
            boneWither("dresser"), boneWither("counter"),
            boneWither("chest"),
            boneWither("wardrobe_bottom"), boneWither("wardrobe_top"),
            boneWither("bookshelf"),
            boneWither("bed_single"), boneWither("bed_double"),
            boneWither("door_single"), boneWither("door_double"),
            boneWither("painting_small"), boneWither("painting_wide"),
            boneWither("oven"),
            boneWither("chalices"), boneWither("pile"),
            boneWither("skull_blossoms")
    );

    private static final Set<ResourceLocation> EXPECTED_BLOCKS = union(
            set(main("furniture_station")),
            EXPECTED_NORDIC_BLOCKS,
            EXPECTED_ROYAL_BLOCKS,
            EXPECTED_DUNMER_BLOCKS,
            EXPECTED_VENTHYR_BLOCKS,
            EXPECTED_NECROLORD_BLOCKS,
            EXPECTED_BONE_SKELETON_BLOCKS,
            EXPECTED_BONE_WITHER_BLOCKS
    );

    // Items mirror blocks 1:1 (all blocks have BlockItems; no stand-alone items in this port).
    private static final Set<ResourceLocation> EXPECTED_ITEMS = EXPECTED_BLOCKS;

    private static final Set<ResourceLocation> EXPECTED_BLOCK_ENTITIES = set(
            nordic("small_container"),
            nordic("medium_container"),
            nordic("large_container"),
            nordic("bookshelf"),
            nordic("furnace"),
            // BE types for decorations rendered via Java Model + BER.
            // Kept under the main namespace since they span multiple sets / aren't tied
            // to a single save namespace.
            main("venthyr_widow_bloom"),
            main("bone_skull_blossoms")
    );

    // --- Tests ---

    @Test
    void blocksMatchUpstream() {
        Set<ResourceLocation> actual = collect(
                ModBlocks.BLOCKS,
                ModBlocks.NORDIC_BLOCKS,
                ModBlocks.ROYAL_BLOCKS,
                ModBlocks.VENTHYR_BLOCKS,
                ModBlocks.DUNMER_BLOCKS,
                ModBlocks.NECROLORD_BLOCKS,
                ModBlocks.BONE_SKELETON_BLOCKS,
                ModBlocks.BONE_WITHER_BLOCKS
        );
        assertExactMatch(EXPECTED_BLOCKS, actual, "blocks");
    }

    @Test
    void itemsMatchUpstream() {
        Set<ResourceLocation> actual = collect(
                ModItems.ITEMS,
                ModItems.NORDIC_ITEMS,
                ModItems.ROYAL_ITEMS,
                ModItems.VENTHYR_ITEMS,
                ModItems.DUNMER_ITEMS,
                ModItems.NECROLORD_ITEMS,
                ModItems.BONE_SKELETON_ITEMS,
                ModItems.BONE_WITHER_ITEMS
        );
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
        assertNoDuplicates(ModBlocks.BLOCKS);
        assertNoDuplicates(ModBlocks.NORDIC_BLOCKS);
        assertNoDuplicates(ModBlocks.ROYAL_BLOCKS);
        assertNoDuplicates(ModBlocks.VENTHYR_BLOCKS);
        assertNoDuplicates(ModBlocks.DUNMER_BLOCKS);
        assertNoDuplicates(ModBlocks.NECROLORD_BLOCKS);
        assertNoDuplicates(ModBlocks.BONE_SKELETON_BLOCKS);
        assertNoDuplicates(ModBlocks.BONE_WITHER_BLOCKS);
        assertNoDuplicates(ModItems.ITEMS);
        assertNoDuplicates(ModItems.NORDIC_ITEMS);
        assertNoDuplicates(ModItems.ROYAL_ITEMS);
        assertNoDuplicates(ModItems.VENTHYR_ITEMS);
        assertNoDuplicates(ModItems.DUNMER_ITEMS);
        assertNoDuplicates(ModItems.NECROLORD_ITEMS);
        assertNoDuplicates(ModItems.BONE_SKELETON_ITEMS);
        assertNoDuplicates(ModItems.BONE_WITHER_ITEMS);
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

    @SafeVarargs
    private static Set<ResourceLocation> union(Set<ResourceLocation>... sets) {
        Set<ResourceLocation> out = new LinkedHashSet<>();
        for (Set<ResourceLocation> s : sets) out.addAll(s);
        return out;
    }

    private static ResourceLocation main(String path) {
        return ResourceLocation.fromNamespaceAndPath(MAIN, path);
    }

    private static ResourceLocation nordic(String path) {
        return ResourceLocation.fromNamespaceAndPath(NORDIC, path);
    }

    private static ResourceLocation royal(String path) {
        return ResourceLocation.fromNamespaceAndPath(ROYAL, path);
    }

    private static ResourceLocation dunmer(String path) {
        return ResourceLocation.fromNamespaceAndPath(DUNMER, path);
    }

    private static ResourceLocation venthyr(String path) {
        return ResourceLocation.fromNamespaceAndPath(VENTHYR, path);
    }

    private static ResourceLocation necrolord(String path) {
        return ResourceLocation.fromNamespaceAndPath(NECROLORD, path);
    }

    private static ResourceLocation boneSkeleton(String path) {
        return ResourceLocation.fromNamespaceAndPath(BONE_SKELETON, path);
    }

    private static ResourceLocation boneWither(String path) {
        return ResourceLocation.fromNamespaceAndPath(BONE_WITHER, path);
    }
}
