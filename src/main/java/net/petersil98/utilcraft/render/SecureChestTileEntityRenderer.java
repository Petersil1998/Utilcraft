package net.petersil98.utilcraft.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.petersil98.utilcraft.blocks.UtilcraftBlocks;
import net.petersil98.utilcraft.blocks.SecureChest;
import net.petersil98.utilcraft.tile_entities.SecureChestTileEntity;

import javax.annotation.Nonnull;
import java.util.Calendar;

public class SecureChestTileEntityRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<T> {
    private final ModelRenderer singleLid;
    private final ModelRenderer singleBottom;
    private final ModelRenderer singleLatch;
    private boolean isChristmas;

    public SecureChestTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcher) {
        super(rendererDispatcher);
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26) {
            this.isChristmas = true;
        }

        this.singleBottom = new ModelRenderer(64, 64, 0, 19);
        this.singleBottom.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
        this.singleLid = new ModelRenderer(64, 64, 0, 0);
        this.singleLid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
        this.singleLid.y = 9.0F;
        this.singleLid.z = 1.0F;
        this.singleLatch = new ModelRenderer(64, 64, 0, 0);
        this.singleLatch.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
        this.singleLatch.y = 8.0F;
    }

    public void render(@Nonnull T tileEntity, float partialTicks, @Nonnull MatrixStack matrixStack, @Nonnull IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        World world = tileEntity.getLevel();
        boolean flag = world != null;
        BlockState blockstate = flag ? tileEntity.getBlockState() : UtilcraftBlocks.SECURE_CHEST.get().defaultBlockState().setValue(SecureChest.FACING, Direction.SOUTH);
        Block block = blockstate.getBlock();
        if (block instanceof SecureChest) {
            matrixStack.pushPose();
            float f = blockstate.getValue(SecureChest.FACING).toYRot();
            matrixStack.translate(0.5D, 0.5D, 0.5D);
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(-f));
            matrixStack.translate(-0.5D, -0.5D, -0.5D);
            TileEntityMerger.ICallbackWrapper<? extends SecureChestTileEntity> icallbackwrapper = TileEntityMerger.ICallback::acceptNone;
            float f1 = icallbackwrapper.apply(SecureChest.getLidRotationCallback(tileEntity)).get(partialTicks);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = icallbackwrapper.apply(new DualBrightnessCallback<>()).applyAsInt(combinedLight);
            RenderMaterial rendermaterial = this.getMaterial(tileEntity);
            IVertexBuilder ivertexbuilder = rendermaterial.buffer(buffer, RenderType::entityCutout);
            this.renderModels(matrixStack, ivertexbuilder, this.singleLid, this.singleLatch, this.singleBottom, f1, i, combinedOverlay);

            matrixStack.popPose();
        }
    }

    private void renderModels(MatrixStack matrixStack, IVertexBuilder buffer, @Nonnull ModelRenderer chestLid, @Nonnull ModelRenderer chestLatch, @Nonnull ModelRenderer chestBottom, float lidAngle, int combinedLight, int combinedOverlay) {
        chestLid.xRot = -(lidAngle * ((float)Math.PI / 2F));
        chestLatch.xRot = chestLid.xRot;
        chestLid.render(matrixStack, buffer, combinedLight, combinedOverlay);
        chestLatch.render(matrixStack, buffer, combinedLight, combinedOverlay);
        chestBottom.render(matrixStack, buffer, combinedLight, combinedOverlay);
    }

    protected RenderMaterial getMaterial(T tileEntity) {
        return Atlases.chooseMaterial(tileEntity, ChestType.SINGLE, this.isChristmas);
    }
}
