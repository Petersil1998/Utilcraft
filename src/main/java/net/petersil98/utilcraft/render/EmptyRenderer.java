package net.petersil98.utilcraft.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.petersil98.utilcraft.Utilcraft;
import net.petersil98.utilcraft.entities.EmptyEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class EmptyRenderer <T extends EmptyEntity> extends EntityRenderer<T> {

    public static final ResourceLocation RES_NOTHING = new ResourceLocation(Utilcraft.MOD_ID, "textures/entity/nothing.png");

    public static ModelLayerLocation MODEL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Utilcraft.MOD_ID, "empty_entity"), "cube_layer");

    private final ModelPart part;

    public EmptyRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.part = context.bakeLayer(MODEL_LAYER_LOCATION);
    }

    public void render(@Nonnull T entity, float entityYaw, float partialTicks, @Nonnull PoseStack matrixStack, @Nonnull MultiBufferSource buffer, int packedLight) {
        part.render(matrixStack, buffer.getBuffer(RenderType.LINES), 0,0);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@Nonnull T entity) {
        return RES_NOTHING;
    }
}
