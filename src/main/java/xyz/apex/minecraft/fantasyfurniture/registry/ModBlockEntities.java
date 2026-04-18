package xyz.apex.minecraft.fantasyfurniture.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.block.entity.BookshelfBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.block.entity.FurnaceBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.block.entity.LargeContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.block.entity.MediumContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.block.entity.SkullBlossomsBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.block.entity.SmallContainerBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.block.entity.WidowBloomBlockEntity;

public final class ModBlockEntities {
    // Main mod namespace — houses decoration BE types (widow_bloom, skull_blossoms)
    // that aren't tied to a single furniture set's save namespace.
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FantasyFurniture.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WidowBloomBlockEntity>> VENTHYR_WIDOW_BLOOM =
            BLOCK_ENTITY_TYPES.register("venthyr_widow_bloom",
                    () -> BlockEntityType.Builder.of(
                            WidowBloomBlockEntity::new,
                            ModBlocks.VENTHYR_WIDOW_BLOOM.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SkullBlossomsBlockEntity>> BONE_SKULL_BLOSSOMS =
            BLOCK_ENTITY_TYPES.register("bone_skull_blossoms",
                    () -> BlockEntityType.Builder.of(
                            SkullBlossomsBlockEntity::new,
                            ModBlocks.BONE_SKELETON_SKULL_BLOSSOMS.get(),
                            ModBlocks.BONE_WITHER_SKULL_BLOSSOMS.get()
                    ).build(null));

    // All Nordic BE types — matches 1.20.x upstream fantasyfurniture_nordic namespace.
    public static final DeferredRegister<BlockEntityType<?>> NORDIC_BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FantasyFurniture.NORDIC_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SmallContainerBlockEntity>> SMALL_CONTAINER =
            NORDIC_BLOCK_ENTITY_TYPES.register("small_container",
                    () -> BlockEntityType.Builder.of(
                            SmallContainerBlockEntity::new,
                            ModBlocks.NORDIC_DRAWER.get(),
                            ModBlocks.NORDIC_LOCKBOX.get(),
                            ModBlocks.NORDIC_DESK_LEFT.get(),
                            ModBlocks.NORDIC_DESK_RIGHT.get(),
                            ModBlocks.ROYAL_DRAWER.get(),
                            ModBlocks.ROYAL_LOCKBOX.get(),
                            ModBlocks.ROYAL_DESK_LEFT.get(),
                            ModBlocks.ROYAL_DESK_RIGHT.get(),
                            ModBlocks.DUNMER_DRAWER.get(),
                            ModBlocks.DUNMER_LOCKBOX.get(),
                            ModBlocks.DUNMER_DESK_LEFT.get(),
                            ModBlocks.DUNMER_DESK_RIGHT.get(),
                            ModBlocks.VENTHYR_DRAWER.get(),
                            ModBlocks.VENTHYR_LOCKBOX.get(),
                            ModBlocks.VENTHYR_DESK_LEFT.get(),
                            ModBlocks.VENTHYR_DESK_RIGHT.get(),
                            ModBlocks.NECROLORD_DRAWER.get(),
                            ModBlocks.NECROLORD_LOCKBOX.get(),
                            ModBlocks.NECROLORD_DESK_LEFT.get(),
                            ModBlocks.NECROLORD_DESK_RIGHT.get(),
                            ModBlocks.BONE_SKELETON_DRAWER.get(),
                            ModBlocks.BONE_SKELETON_LOCKBOX.get(),
                            ModBlocks.BONE_SKELETON_DESK_LEFT.get(),
                            ModBlocks.BONE_SKELETON_DESK_RIGHT.get(),
                            ModBlocks.BONE_WITHER_DRAWER.get(),
                            ModBlocks.BONE_WITHER_LOCKBOX.get(),
                            ModBlocks.BONE_WITHER_DESK_LEFT.get(),
                            ModBlocks.BONE_WITHER_DESK_RIGHT.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MediumContainerBlockEntity>> MEDIUM_CONTAINER =
            NORDIC_BLOCK_ENTITY_TYPES.register("medium_container",
                    () -> BlockEntityType.Builder.of(
                            MediumContainerBlockEntity::new,
                            ModBlocks.NORDIC_DRESSER.get(),
                            ModBlocks.NORDIC_COUNTER.get(),
                            ModBlocks.ROYAL_DRESSER.get(),
                            ModBlocks.ROYAL_COUNTER.get(),
                            ModBlocks.DUNMER_DRESSER.get(),
                            ModBlocks.DUNMER_COUNTER.get(),
                            ModBlocks.VENTHYR_DRESSER.get(),
                            ModBlocks.VENTHYR_COUNTER.get(),
                            ModBlocks.NECROLORD_DRESSER.get(),
                            ModBlocks.NECROLORD_COUNTER.get(),
                            ModBlocks.BONE_SKELETON_DRESSER.get(),
                            ModBlocks.BONE_SKELETON_COUNTER.get(),
                            ModBlocks.BONE_WITHER_DRESSER.get(),
                            ModBlocks.BONE_WITHER_COUNTER.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LargeContainerBlockEntity>> LARGE_CONTAINER =
            NORDIC_BLOCK_ENTITY_TYPES.register("large_container",
                    () -> BlockEntityType.Builder.of(
                            LargeContainerBlockEntity::new,
                            ModBlocks.NORDIC_CHEST.get(),
                            ModBlocks.NORDIC_WARDROBE_BOTTOM.get(),
                            ModBlocks.ROYAL_CHEST.get(),
                            ModBlocks.ROYAL_WARDROBE_BOTTOM.get(),
                            ModBlocks.DUNMER_CHEST.get(),
                            ModBlocks.DUNMER_WARDROBE_BOTTOM.get(),
                            ModBlocks.VENTHYR_CHEST.get(),
                            ModBlocks.VENTHYR_WARDROBE_BOTTOM.get(),
                            ModBlocks.NECROLORD_CHEST.get(),
                            ModBlocks.NECROLORD_WARDROBE_BOTTOM.get(),
                            ModBlocks.BONE_SKELETON_CHEST.get(),
                            ModBlocks.BONE_SKELETON_WARDROBE_BOTTOM.get(),
                            ModBlocks.BONE_WITHER_CHEST.get(),
                            ModBlocks.BONE_WITHER_WARDROBE_BOTTOM.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BookshelfBlockEntity>> BOOKSHELF =
            NORDIC_BLOCK_ENTITY_TYPES.register("bookshelf",
                    () -> BlockEntityType.Builder.of(
                            BookshelfBlockEntity::new,
                            ModBlocks.NORDIC_BOOKSHELF.get(),
                            ModBlocks.ROYAL_BOOKSHELF.get(),
                            ModBlocks.DUNMER_BOOKSHELF.get(),
                            ModBlocks.VENTHYR_BOOKSHELF.get(),
                            ModBlocks.NECROLORD_BOOKSHELF.get(),
                            ModBlocks.BONE_SKELETON_BOOKSHELF.get(),
                            ModBlocks.BONE_WITHER_BOOKSHELF.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FurnaceBlockEntity>> FURNACE =
            NORDIC_BLOCK_ENTITY_TYPES.register("furnace",
                    () -> BlockEntityType.Builder.of(
                            FurnaceBlockEntity::new,
                            ModBlocks.NORDIC_OVEN.get(),
                            ModBlocks.ROYAL_OVEN.get(),
                            ModBlocks.DUNMER_OVEN.get(),
                            ModBlocks.VENTHYR_OVEN.get(),
                            ModBlocks.NECROLORD_OVEN.get(),
                            ModBlocks.BONE_SKELETON_OVEN.get(),
                            ModBlocks.BONE_WITHER_OVEN.get()
                    ).build(null));

    private ModBlockEntities() {}
}
