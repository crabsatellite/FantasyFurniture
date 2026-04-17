package xyz.apex.minecraft.fantasyfurniture.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import xyz.apex.minecraft.fantasyfurniture.registry.ModRecipeTypes;
import xyz.apex.minecraft.fantasyfurniture.registry.ModTags;

public class FurnitureStationRecipe implements Recipe<RecipeInput> {
    private final String group;
    private final Ingredient ingredientA;
    private final Ingredient ingredientB;
    private final TagKey<Item> bindingAgent;
    private final ItemStack result;

    public FurnitureStationRecipe(String group, Ingredient ingredientA, Ingredient ingredientB, TagKey<Item> bindingAgent, ItemStack result) {
        this.group = group;
        this.ingredientA = ingredientA;
        this.ingredientB = ingredientB;
        this.bindingAgent = bindingAgent;
        this.result = result;
    }

    @Override
    public boolean matches(RecipeInput input, Level level) {
        return ingredientA.test(input.getItem(0))
                && ingredientB.test(input.getItem(1))
                && input.getItem(2).is(bindingAgent);
    }

    @Override
    public ItemStack assemble(RecipeInput input, HolderLookup.Provider registries) {
        return result.copy();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public String getGroup() {
        return group;
    }

    public Ingredient getIngredientA() {
        return ingredientA;
    }

    public Ingredient getIngredientB() {
        return ingredientB;
    }

    public TagKey<Item> getBindingAgent() {
        return bindingAgent;
    }

    public ItemStack getResult() {
        return result;
    }

    @Override
    public RecipeSerializer<FurnitureStationRecipe> getSerializer() {
        return ModRecipeTypes.FURNITURE_STATION_SERIALIZER.get();
    }

    @Override
    public RecipeType<FurnitureStationRecipe> getType() {
        return ModRecipeTypes.FURNITURE_STATION_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<FurnitureStationRecipe> {
        public static final MapCodec<FurnitureStationRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        com.mojang.serialization.Codec.STRING.optionalFieldOf("group", "").forGetter(FurnitureStationRecipe::getGroup),
                        Ingredient.CODEC.fieldOf("ingredientA").forGetter(FurnitureStationRecipe::getIngredientA),
                        Ingredient.CODEC.fieldOf("ingredientB").forGetter(FurnitureStationRecipe::getIngredientB),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(FurnitureStationRecipe::getResult)
                ).apply(instance, (group, a, b, result) ->
                        new FurnitureStationRecipe(group, a, b, ModTags.FURNITURE_STATION_BINDING_AGENT, result))
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, FurnitureStationRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        ByteBufCodecs.STRING_UTF8, FurnitureStationRecipe::getGroup,
                        Ingredient.CONTENTS_STREAM_CODEC, FurnitureStationRecipe::getIngredientA,
                        Ingredient.CONTENTS_STREAM_CODEC, FurnitureStationRecipe::getIngredientB,
                        ItemStack.STREAM_CODEC, FurnitureStationRecipe::getResult,
                        (group, a, b, result) ->
                                new FurnitureStationRecipe(group, a, b, ModTags.FURNITURE_STATION_BINDING_AGENT, result)
                );

        @Override
        public MapCodec<FurnitureStationRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, FurnitureStationRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
