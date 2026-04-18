package xyz.apex.minecraft.fantasyfurniture.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import xyz.apex.minecraft.fantasyfurniture.block.venthyr.VenthyrWidowBloomBlock;
import xyz.apex.minecraft.fantasyfurniture.block.entity.WidowBloomBlockEntity;
import xyz.apex.minecraft.fantasyfurniture.client.renderer.model.WidowBloomModel;

public final class WidowBloomBlockEntityRenderer implements BlockEntityRenderer<WidowBloomBlockEntity> {
    private final WidowBloomModel model;

    public WidowBloomBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.model = new WidowBloomModel(ctx.bakeLayer(WidowBloomModel.LAYER_LOCATION));
    }

    @Override
    public void render(WidowBloomBlockEntity blockEntity, float partialTick, PoseStack pose, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        var renderType = model.renderType(WidowBloomModel.TEXTURE);
        var modelBuffer = buffer.getBuffer(renderType);

        pose.pushPose();

        if (blockEntity.hasLevel()) {
            Direction facing = blockEntity.getBlockState().getValue(VenthyrWidowBloomBlock.FACING);
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
