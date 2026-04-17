package xyz.apex.minecraft.fantasyfurniture.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import xyz.apex.minecraft.fantasyfurniture.recipe.FurnitureStationRecipe;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlocks;
import xyz.apex.minecraft.fantasyfurniture.registry.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        // Furniture Station: crafting_table + leather (shapeless)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.FURNITURE_STATION.get())
                .requires(Items.CRAFTING_TABLE)
                .requires(Items.LEATHER)
                .unlockedBy("has_crafting_table", has(Items.CRAFTING_TABLE))
                .unlockedBy("has_leather", has(Items.LEATHER))
                .save(output);

        // All Nordic furniture: oak_planks + brown_wool + binding_agent (clay_ball)
        Ingredient ingredientA = Ingredient.of(Items.OAK_PLANKS);
        Ingredient ingredientB = Ingredient.of(Items.BROWN_WOOL);

        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_WOOL.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_CARPET.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_WALL_LIGHT.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_FLOOR_LIGHT.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_CHANDELIER_LIGHT.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_TABLE.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_CHAIR.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_BENCH.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_STOOL.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_CUSHION.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_SHELF.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_SOFA.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_DRAWER.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_LOCKBOX.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_DESK_LEFT.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_DESK_RIGHT.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_DRESSER.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_COUNTER.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_CHEST.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_WARDROBE_BOTTOM.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_WARDROBE_TOP.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_BOOKSHELF.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_BED_SINGLE.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_BED_DOUBLE.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_DOOR_SINGLE.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_DOOR_DOUBLE.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_PAINTING_SMALL.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_PAINTING_WIDE.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_OVEN.get());
    }

    private void nordicRecipe(RecipeOutput recipeOutput, Ingredient ingredientA, Ingredient ingredientB, ItemLike result) {
        // Match the recipe id's namespace to the result item's namespace (fantasyfurniture_nordic for Nordic blocks).
        ResourceLocation id = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(result.asItem());
        FurnitureStationRecipe recipe = new FurnitureStationRecipe(
                "furniture_set/nordic",
                ingredientA,
                ingredientB,
                ModTags.FURNITURE_STATION_BINDING_AGENT,
                new ItemStack(result)
        );
        recipeOutput.accept(id, recipe, null);
    }
}
