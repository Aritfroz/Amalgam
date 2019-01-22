package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelRuby;
import mod.amalgam.client.render.layers.LayerCrossFusionGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.fusion.EntityFusedPyrite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFusedPyrite extends RenderGemBase<EntityFusedPyrite> {
	public RenderFusedPyrite(RenderManager manager) {
        super(manager, new ModelRuby(), 0.25F);
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerHair(this));
		this.addLayer(new LayerCrossFusionGemPlacement(this));
    }
	@Override
	protected void preRenderCallback(EntityFusedPyrite gem, float partialTickTime) {
		GlStateManager.scale(gem.getFusionCount(), gem.getFusionCount(), gem.getFusionCount());
		this.shadowSize = 0.25F * gem.getFusionCount();
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityFusedPyrite entity) {
		return new ResourceLocation("amalgam:textures/entities/fused_pyrite/pyrite.png");
	}
}