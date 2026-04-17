package xyz.apex.minecraft.fantasyfurniture.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import xyz.apex.minecraft.fantasyfurniture.registry.ModEntities;
import xyz.apex.minecraft.fantasyfurniture.registry.ModTags;

import java.util.List;

public class SeatEntity extends Entity {
    private static final EntityDataAccessor<BlockPos> DATA_POS = SynchedEntityData.defineId(
            SeatEntity.class, EntityDataSerializers.BLOCK_POS
    );

    public SeatEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        noPhysics = true;
        blocksBuilding = false;
    }

    public void setSittingPos(BlockPos pos) {
        entityData.set(DATA_POS, pos);
    }

    public BlockPos getSittingPos() {
        return entityData.get(DATA_POS);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_POS, BlockPos.ZERO);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide()) {
            BlockPos sittingPos = getSittingPos();

            if (getPassengers().isEmpty() || level().isEmptyBlock(sittingPos)) {
                discard();
            }
        }
    }

    @Override
    public boolean isPickable() {
        return false;
    }

    @Override
    protected double getDefaultGravity() {
        return 0.0;
    }

    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return getPassengers().isEmpty() && !passenger.getType().is(ModTags.SEAT_BLACKLIST);
    }

    public static InteractionResult create(Level level, BlockPos pos, double yOffset, Player player, Direction direction) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if (player.isPassenger() || player.getType().is(ModTags.SEAT_BLACKLIST)) {
            return InteractionResult.PASS;
        }

        // Check for existing seat entity at this position
        List<SeatEntity> existingSeats = level.getEntitiesOfClass(SeatEntity.class,
                new AABB(pos).inflate(0.25), seat -> seat.getSittingPos().equals(pos));
        if (!existingSeats.isEmpty()) {
            // Seat already exists - try to ride it instead
            SeatEntity existing = existingSeats.getFirst();
            if (existing.getPassengers().isEmpty()) {
                player.startRiding(existing);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }

        SeatEntity seat = new SeatEntity(ModEntities.SEAT.get(), level);
        seat.setSittingPos(pos);
        seat.setPos(pos.getX() + 0.5, pos.getY() + yOffset, pos.getZ() + 0.5);
        seat.setYRot(direction.toYRot());
        level.addFreshEntity(seat);
        player.startRiding(seat);

        return InteractionResult.SUCCESS;
    }
}
