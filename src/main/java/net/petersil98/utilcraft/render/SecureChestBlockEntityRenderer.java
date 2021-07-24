package net.petersil98.utilcraft.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.core.Direction;
import com.mojang.math.Vector3f;
import net.minecraft.world.level.Level;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.SecureChest;
import net.petersil98.utilcraft.block_entities.SecureChestBlockEntity;

import javax.annotation.Nonnull;
import java.util.Calendar;

public class SecureChestBlockEntityRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T> {
    private final ModelPart singleLid;
    private final ModelPart singleBottom;
    private final ModelPart singleLatch;
    private boolean isChristmas;

    public SecureChestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26) {
            this.isChristmas = true;
        }
        ModelPart modelpart = context.bakeLayer(ModelLayers.CHEST);
        this.singleBottom = modelpart.getChild("bottom");
        this.singleLid = modelpart.getChild("lid");
        this.singleLatch = modelpart.getChild("lock");
    }

    public void render(@Nonnull T blockEntity, float partialTicks, @Nonnull PoseStack matrixStack, @Nonnull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Level world = blockEntity.getLevel();
        boolean flag = world != null;
        BlockState blockstate = flag ? blockEntity.getBlockState() : UtilcraftBlocks.SECURE_CHEST.defaultBlockState().setValue(SecureChest.FACING, Direction.SOUTH);
        Block block = blockstate.getBlock();
        if (block instanceof SecureChest) {
            matrixStack.pushPose();
            float f = blockstate.getValue(SecureChest.FACING).toYRot();
            matrixStack.translate(0.5D, 0.5D, 0.5D);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(-f));
            matrixStack.translate(-0.5D, -0.5D, -0.5D);
            DoubleBlockCombiner.NeighborCombineResult<? extends SecureChestBlockEntity> icallbackwrapper = DoubleBlockCombiner.Combiner::acceptNone;
            float f1 = icallbackwrapper.apply(SecureChest.getLidRotationCallback(blockEntity)).get(partialTicks);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = icallbackwrapper.apply(new BrightnessCombiner<>()).applyAsInt(combinedLight);
            Material rendermaterial = this.getMaterial(blockEntity);
            VertexConsumer ivertexbuilder = rendermaterial.buffer(buffer, RenderType::entityCutout);
            this.renderModels(matrixStack, ivertexbuilder, this.singleLid, this.singleLatch, this.singleBottom, f1, i, combinedOverlay);

            matrixStack.popPose();
        }
    }

    private void renderModels(PoseStack matrixStack, VertexConsumer buffer, @Nonnull ModelPart chestLid, @Nonnull ModelPart chestLatch, @Nonnull ModelPart chestBottom, float lidAngle, int combinedLight, int combinedOverlay) {
        chestLid.xRot = -(lidAngle * ((float)Math.PI / 2F));
        chestLatch.xRot = chestLid.xRot;
        chestLid.render(matrixStack, buffer, combinedLight, combinedOverlay);
        chestLatch.render(matrixStack, buffer, combinedLight, combinedOverlay);
        chestBottom.render(matrixStack, buffer, combinedLight, combinedOverlay);
    }

    protected Material getMaterial(T blockEntity) {
        return Sheets.chooseMaterial(blockEntity, ChestType.SINGLE, this.isChristmas);
    }
}
