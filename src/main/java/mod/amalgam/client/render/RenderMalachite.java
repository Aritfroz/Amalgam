package mod.amalgam.client.render;

import mod.amalgam.client.model.fusions.ModelMalachite;
import mod.amalgam.client.render.layers.LayerCrossFusionGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerMalachiteItem;
import mod.amalgam.client.render.layers.LayerMalachiteMark;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.gem.fusion.EntityMalachite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMalachite extends RenderGemBase<EntityMalachite> {

	public RenderMalachite(RenderManager manager) {
		super(manager, new ModelMalachite(), 3F);
		
		this.addLayer(new LayerMalachiteItem(this));
		this.addLayer(new LayerSkin(this));
		this.addLayer(new LayerMalachiteMark(this));
		this.addLayer(new LayerUniform(this));
		this.addLayer(new LayerInsignia(this));
		this.addLayer(new LayerHair(this));
		this.addLayer(new LayerCrossFusionGemPlacement(this));
	}

	@Override
	protected void preRenderCallback(EntityMalachite malachite, float partialTickTime) {
		GlStateManager.scale(4F * malachite.getSizeFactor(), 4F * malachite.getSizeFactor(), 4F * malachite.getSizeFactor());
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMalachite malachite) {
		return new ResourceLocation("kagic:textures/entities/malachite/malachite.png");
	}
}