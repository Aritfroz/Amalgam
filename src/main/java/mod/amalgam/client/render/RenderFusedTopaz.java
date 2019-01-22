package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelTopaz;
import mod.amalgam.client.render.layers.LayerCrossFusionGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.fusion.EntityFusedTopaz;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFusedTopaz extends RenderGemBase<EntityFusedTopaz> {
	public RenderFusedTopaz(RenderManager manager) {
        super(manager, new ModelTopaz(), 0.25F);
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerVisor(this));
		this.addLayer(new LayerCrossFusionGemPlacement(this));
    }
	@Override
	protected void preRenderCallback(EntityFusedTopaz gem, float partialTickTime) {
		GlStateManager.scale(2.0F, 2.0F, 2.0F);
		this.shadowSize = 1.25F;
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityFusedTopaz entity) {
		return new ResourceLocation("amalgam:textures/entities/fused_topaz/topaz.png");
	}
}