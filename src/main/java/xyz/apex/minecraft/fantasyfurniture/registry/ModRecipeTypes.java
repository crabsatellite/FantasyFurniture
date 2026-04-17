package xyz.apex.minecraft.fantasyfurniture.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.recipe.FurnitureStationRecipe;

public final class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, FantasyFurniture.MOD_ID);

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, FantasyFurniture.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<FurnitureStationRecipe>> FURNITURE_STATION_TYPE =
            RECIPE_TYPES.register("furniture_station",
                    () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(FantasyFurniture.MOD_ID, "furniture_station")));

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<FurnitureStationRecipe>> FURNITURE_STATION_SERIALIZER =
            RECIPE_SERIALIZERS.register("furniture_station",
                    () -> new FurnitureStationRecipe.Serializer());

    private ModRecipeTypes() {}
}
