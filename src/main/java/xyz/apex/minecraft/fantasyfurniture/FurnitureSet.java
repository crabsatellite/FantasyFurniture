package xyz.apex.minecraft.fantasyfurniture;

import net.minecraft.resources.ResourceLocation;

/**
 * Enumeration of all FantasyFurniture sets shipped by the mod.
 *
 * <p>Each set owns a dedicated namespace so that existing saves using per-set
 * registry IDs (from 1.20.x upstream, e.g. {@code fantasyfurniture_nordic:chair})
 * load unchanged. New sets follow the same {@code fantasyfurniture_<set>}
 * convention for consistency with Nordic/Royal.
 *
 * <p>Display prefixes feed user-visible lang strings
 * (e.g. {@code "Nordic Chair"}, {@code "Bone-Skeleton Chair"}).
 */
public enum FurnitureSet {
    NORDIC("fantasyfurniture_nordic", "Nordic", true),
    ROYAL("fantasyfurniture_royal", "Royal", true),
    VENTHYR("fantasyfurniture_venthyr", "Venthyr", false),
    DUNMER("fantasyfurniture_dunmer", "Dunmer", false),
    NECROLORD("fantasyfurniture_necrolord", "Necrolord", false),
    BONE_SKELETON("fantasyfurniture_bone_skeleton", "Bone-Skeleton", false),
    BONE_WITHER("fantasyfurniture_bone_wither", "Bone-Wither", false);

    private final String namespace;
    private final String displayPrefix;
    private final boolean implemented;

    FurnitureSet(String namespace, String displayPrefix, boolean implemented) {
        this.namespace = namespace;
        this.displayPrefix = displayPrefix;
        this.implemented = implemented;
    }

    public String namespace() {
        return namespace;
    }

    public String displayPrefix() {
        return displayPrefix;
    }

    public boolean isImplemented() {
        return implemented;
    }

    public ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(namespace, path);
    }
}
