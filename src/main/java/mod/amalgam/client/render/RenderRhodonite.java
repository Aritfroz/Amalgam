package mod.amalgam.client.render;

import mod.amalgam.client.model.fusions.ModelRhodonite;
import mod.amalgam.client.render.layers.LayerCrossFusionGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerRhodoniteItem;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.gem.fusion.EntityRhodonite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRhodonite extends RenderAmalgamGem<EntityRhodonite> {

	public RenderRhodonite(RenderManager manager) {
		super(manager, new ModelRhodonite(), 0.5F);
		this.addLayer(new LayerRhodoniteItem(this));
		this.addLayer(new LayerSkin(this));
		this.addLayer(new LayerHair(this));
		this.addLayer(new LayerNoDyeOverlay(this));
		this.addLayer(new LayerCrossFusionGemPlacement(this));
	}

	@Override
	protected void preRenderCallback(EntityRhodonite rhodonite, float partialTickTime) {
		GlStateManager.scale(1F * rhodonite.getSizeFactor(), 1F * rhodonite.getSizeFactor(), 1F * rhodonite.getSizeFactor());
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityRhodonite entity) {
		return new ResourceLocation("kagic:textures/entities/rhodonite/rhodonite.png");
	}
}
