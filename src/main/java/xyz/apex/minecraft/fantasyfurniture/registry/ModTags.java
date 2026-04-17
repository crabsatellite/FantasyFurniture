package xyz.apex.minecraft.fantasyfurniture.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;

public final class ModTags {
    public static final TagKey<Item> FURNITURE_STATION_BINDING_AGENT = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(FantasyFurniture.MOD_ID, "furniture_station_binding_agent")
    );

    public static final TagKey<EntityType<?>> SEAT_BLACKLIST = TagKey.create(
            Registries.ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(FantasyFurniture.MOD_ID, "seat_blacklist")
    );

    private ModTags() {}
}
