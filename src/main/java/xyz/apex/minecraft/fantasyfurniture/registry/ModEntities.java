package xyz.apex.minecraft.fantasyfurniture.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.apex.minecraft.fantasyfurniture.FantasyFurniture;
import xyz.apex.minecraft.fantasyfurniture.entity.SeatEntity;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, FantasyFurniture.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<SeatEntity>> SEAT =
            ENTITY_TYPES.register("seat",
                    () -> EntityType.Builder.<SeatEntity>of(SeatEntity::new, MobCategory.MISC)
                            .sized(0.25F, 0.35F)
                            // Default PASSENGER attachment is AT_HEIGHT (0, height, 0), which would
                            // add +0.35 to the rider's Y. Override to zero so getSeatYOffset() values
                            // directly map to the seat voxel's top Y (block-fraction units).
                            .passengerAttachments(0.0F)
                            .noSummon()
                            .noSave()
                            .build(ResourceLocation.fromNamespaceAndPath(FantasyFurniture.MOD_ID, "seat").toString()));

    private ModEntities() {}
}
