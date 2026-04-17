package xyz.apex.minecraft.fantasyfurniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import xyz.apex.minecraft.fantasyfurniture.entity.SeatEntity;

public class SeatFurnitureBlock extends FurnitureBlock {

    public SeatFurnitureBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (player.isPassenger() || player.isShiftKeyDown()) {
            return InteractionResult.PASS;
        }

        return SeatEntity.create(level, pos, getSeatYOffset(), player, state.getValue(FACING));
    }

    protected double getSeatYOffset() {
        // Default for seats whose top voxel face is at pixel Y=7 (stool/cushion/bench).
        // Seat entity uses passengerAttachments(0), so this value is the player's feet Y
        // relative to the block origin — set it to the voxel seat-surface height.
        return 7.0 / 16.0;
    }
}
