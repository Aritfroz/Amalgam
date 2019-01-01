package mod.amalgam.client.render;

import mod.akrivus.kagic.entity.EntitySlag;
import mod.amalgam.client.render.layers.LayerSlagVariants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderSlag extends RenderLivingBase<EntitySlag> {
	public RenderSlag() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelSilverfish(), 0.25F);
        this.layerRenderers.add(new LayerSlagVariants(this));
    }
	protected void preRenderCallback(EntitySlag entity, float partialTickTime) {
        GlStateManager.scale(entity.getCount(), entity.getCount(), entity.getCount());
    }
	protected ResourceLocation getEntityTexture(EntitySlag entity) {
		return new ResourceLocation("kagic:textures/entities/slag/slag.png");
	}
}
