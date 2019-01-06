package mod.amalgam.client.render;

import mod.amalgam.client.model.fusions.ModelRainbowQuartz;
import mod.amalgam.client.render.layers.LayerCrossFusionGemPlacement;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerRainbowQuartzItem;
import mod.amalgam.client.render.layers.LayerRainbowQuartzShawl;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.gem.fusion.EntityRainbowQuartz;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRainbowQuartz extends RenderGemBase<EntityRainbowQuartz> {

	public RenderRainbowQuartz(RenderManager manager) {
		super(manager, new ModelRainbowQuartz(), 0.75F);
		this.addLayer(new LayerRainbowQuartzItem(this));
		this.addLayer(new LayerSkin(this));
		this.addLayer(new LayerNoDyeOverlay(this));
		this.addLayer(new LayerCrossFusionGemPlacement(this));
		this.addLayer(new LayerRainbowQuartzShawl(this));
	}

	@Override
	protected void preRenderCallback(EntityRainbowQuartz rainbowQuartz, float partialTickTime) {
		GlStateManager.scale(1.8F * rainbowQuartz.getSizeFactor(), 1.8F * rainbowQuartz.getSizeFactor(), 1.8F * rainbowQuartz.getSizeFactor());
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRainbowQuartz rainbowQuartz) {
		return new ResourceLocation("kagic:textures/entities/rainbow_quartz/rainbow_quartz.png");
	}
}
