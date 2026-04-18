package xyz.apex.minecraft.fantasyfurniture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public abstract class StackedFurnitureBlock extends FurnitureBlock {

    public StackedFurnitureBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(getStackSizeProperty(), getMinValue()));
    }

    public abstract IntegerProperty getStackSizeProperty();

    public int getMinValue() {
        return getStackSizeProperty().getPossibleValues().stream().mapToInt(i -> i).min().orElse(0);
    }

    public int getMaxValue() {
        return getStackSizeProperty().getPossibleValues().stream().mapToInt(i -> i).max().orElse(0);
    }

    protected boolean isForStack(ItemStack stack) {
        return stack.is(asItem());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(getStackSizeProperty());
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState placement = super.getStateForPlacement(context);
        if (placement == null) return null;

        BlockState existing = context.getLevel().getBlockState(context.getClickedPos());
        IntegerProperty property = getStackSizeProperty();

        if (existing.is(this) && existing.hasProperty(property)) {
            int current = existing.getValue(property);
            int next = Math.min(getMaxValue(), current + 1);
            if (next != current) {
                spawnPlaceParticles(context.getLevel(), context.getClickedPos(), existing);
                return placement.setValue(property, next);
            }
        }
        return placement;
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        IntegerProperty property = getStackSizeProperty();
        if (!state.hasProperty(property)) return super.canBeReplaced(state, context);
        return !context.isSecondaryUseActive()
                && isForStack(context.getItemInHand())
                && state.getValue(property) < getMaxValue()
                || super.canBeReplaced(state, context);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        IntegerProperty property = getStackSizeProperty();
        int count = state.getValue(property);
        int min = getMinValue();
        int next = count - 1;

        if (next < min) {
            level.destroyBlock(pos, false, player);
            if (!player.isCreative()) {
                popResource(level, pos, new ItemStack(this));
            }
            return InteractionResult.sidedSuccess(level.isClientSide());
        }

        BlockState newState = state.setValue(property, next);
        SoundType sound = state.getSoundType();
        level.playSound(player, pos, sound.getBreakSound(), net.minecraft.sounds.SoundSource.BLOCKS,
                (sound.getVolume() + 1F) / 2F, sound.getPitch() * 0.8F);

        if (!player.isCreative()) {
            popResource(level, pos, new ItemStack(this));
        }

        spawnPlaceParticles(level, pos, newState);
        level.setBlockAndUpdate(pos, newState);
        return InteractionResult.sidedSuccess(level.isClientSide());
    }

    private void spawnPlaceParticles(Level level, BlockPos pos, BlockState state) {
        for (int i = 0; i < 5; i++) {
            level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, state),
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    0, 0, 0);
        }
    }
}
