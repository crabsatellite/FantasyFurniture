package xyz.apex.minecraft.fantasyfurniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import xyz.apex.minecraft.fantasyfurniture.block.bone.BoneSkullBlossomsBlock;
import xyz.apex.minecraft.fantasyfurniture.block.entity.SkullBlossomsBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.client.renderer.model.SkullBlossomsModel;
import xyz.apex.minecraft.fantasyfurniture.registry.ModBlocks;

public final class SkullBlossomsBlockEntityRenderer implements BlockEntityRenderer<SkullBlossomsBlockEntity> {
    private final SkullBlossomsModel model;

    public SkullBlossomsBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new SkullBlossomsModel(ctx.bakeLayer(SkullBlossomsModel.LAYER_LOCATION));
    }

    @Override
    public void render(SkullBlossomsBlockEntity blockEntity, float partialTick, PoseStack pose, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        var blockState = blockEntity.getBlockState();
        var texture = blockState.is(ModBlocks.BONE_SKELETON_SKULL_BLOSSOMS.get())
                ? SkullBlossomsModel.TEXTURE_SKELETON
                : SkullBlossomsModel.TEXTURE_WITHER;
        var renderType = model.renderType(texture);
        var modelBuffer = buffer.getBuffer(renderType);

        pose.pushPose();

        if (blockEntity.hasLevel()) {
            Direction facing = blockState.getValue(BoneSkullBlossomsBlock.FACING);
            pose.translate(0.5D, 0.5D, 0.5D);
            pose.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()));
            pose.mulPose(Axis.XP.rotationDegrees(180F));
            pose.translate(0D, -1D, 0D);
        } else {
            pose.translate(0.5D, 0.5D, 0.5D);
            pose.mulPose(Axis.ZP.rotationDegrees(180F));
        }

        model.renderToBuffer(pose, modelBuffer, packedLight, packedOverlay, 0xFFFFFFFF);

        pose.popPose();
    }
}
