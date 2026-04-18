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
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_TABLE_WIDE.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_TABLE_LARGE.get());
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

        // Nordic decorations
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_BOILED_CREME_TREATS.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_SWEETROLLS.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_MEAD_BOTTLES.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_SOUL_GEMS_LIGHT.get());
        nordicRecipe(output, ingredientA, ingredientB, ModBlocks.NORDIC_SOUL_GEMS_DARK.get());

        // All Royal furniture: quartz_block + red_wool + binding_agent (gold_nugget-backed tag)
        Ingredient royalA = Ingredient.of(Items.QUARTZ_BLOCK);
        Ingredient royalB = Ingredient.of(Items.RED_WOOL);

        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_WOOL.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_CARPET.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_WALL_LIGHT.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_FLOOR_LIGHT.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_CHANDELIER_LIGHT.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_TABLE.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_CHAIR.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_BENCH.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_STOOL.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_CUSHION.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_SHELF.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_SOFA.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_DRAWER.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_LOCKBOX.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_DESK_LEFT.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_DESK_RIGHT.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_DRESSER.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_COUNTER.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_CHEST.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_WARDROBE_BOTTOM.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_WARDROBE_TOP.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_BOOKSHELF.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_BED_SINGLE.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_BED_DOUBLE.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_DOOR_SINGLE.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_DOOR_DOUBLE.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_PAINTING_SMALL.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_PAINTING_WIDE.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_OVEN.get());

        // Royal decorations
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_CROWN.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_CUSHIONED_CROWN.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_CANDELABRA.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_CHALICES.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_PLATTER.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_FOOD.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_FLOOR_CUSHION.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_WALL_MIRROR.get());
        royalRecipe(output, royalA, royalB, ModBlocks.ROYAL_WALL_MIRROR_TALL.get());
    }

    private void nordicRecipe(RecipeOutput recipeOutput, Ingredient ingredientA, Ingredient ingredientB, ItemLike result) {
        furnitureSetRecipe(recipeOutput, "furniture_set/nordic", ingredientA, ingredientB, result);
    }

    private void royalRecipe(RecipeOutput recipeOutput, Ingredient ingredientA, Ingredient ingredientB, ItemLike result) {
        furnitureSetRecipe(recipeOutput, "furniture_set/royal", ingredientA, ingredientB, result);
    }

    private void furnitureSetRecipe(RecipeOutput recipeOutput, String group, Ingredient ingredientA, Ingredient ingredientB, ItemLike result) {
        ResourceLocation id = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(result.asItem());
        FurnitureStationRecipe recipe = new FurnitureStationRecipe(
                group,
                ingredientA,
                ingredientB,
                ModTags.FURNITURE_STATION_BINDING_AGENT,
                new ItemStack(result)
        );
        recipeOutput.accept(id, recipe, null);
    }
}
