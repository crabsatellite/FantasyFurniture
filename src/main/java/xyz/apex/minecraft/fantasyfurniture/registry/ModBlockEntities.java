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
import xyz.apex.minecraft.fantasyfurniture.block.entity.SmallContainerBlockEntity;

public final class ModBlockEntities {
    // Reserved for future main-mod BE types. Currently empty; kept so FantasyFurniture.java
    // can still call .register(modEventBus) uniformly without null checks.
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FantasyFurniture.MOD_ID);

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
                            ModBlocks.NORDIC_DESK_RIGHT.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MediumContainerBlockEntity>> MEDIUM_CONTAINER =
            NORDIC_BLOCK_ENTITY_TYPES.register("medium_container",
                    () -> BlockEntityType.Builder.of(
                            MediumContainerBlockEntity::new,
                            ModBlocks.NORDIC_DRESSER.get(),
                            ModBlocks.NORDIC_COUNTER.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LargeContainerBlockEntity>> LARGE_CONTAINER =
            NORDIC_BLOCK_ENTITY_TYPES.register("large_container",
                    () -> BlockEntityType.Builder.of(
                            LargeContainerBlockEntity::new,
                            ModBlocks.NORDIC_CHEST.get(),
                            ModBlocks.NORDIC_WARDROBE_BOTTOM.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BookshelfBlockEntity>> BOOKSHELF =
            NORDIC_BLOCK_ENTITY_TYPES.register("bookshelf",
                    () -> BlockEntityType.Builder.of(
                            BookshelfBlockEntity::new,
                            ModBlocks.NORDIC_BOOKSHELF.get()
                    ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FurnaceBlockEntity>> FURNACE =
            NORDIC_BLOCK_ENTITY_TYPES.register("furnace",
                    () -> BlockEntityType.Builder.of(
                            FurnaceBlockEntity::new,
                            ModBlocks.NORDIC_OVEN.get()
                    ).build(null));

    private ModBlockEntities() {}
}
