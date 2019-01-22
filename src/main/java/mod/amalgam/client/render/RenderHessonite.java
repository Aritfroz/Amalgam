package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelHessonite;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerHeldItem;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerQuartzCape;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityHessonite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHessonite extends RenderAmalgamGem<EntityHessonite> {

	public RenderHessonite(RenderManager manager) {
		super(manager, new ModelHessonite(), 0.5F);
		this.addLayer(new LayerHeldItem(this, 0.625F, 8.0F, 0.125F, -0.6F));
        this.addLayer(new LayerHair(this, EntityHessonite.HAIRSTYLES));
		this.addLayer(new LayerSkin(this));
		this.addLayer(new LayerUniform(this));
		//this.addLayer(new LayerInsignia(this));
		this.addLayer(new LayerNoDyeOverlay(this));
		this.addLayer(new LayerQuartzCape(this));
		this.addLayer(new LayerQuartzCape(this, true, true));
		this.addLayer(new LayerVisor(this));
		this.addLayer(new LayerGemPlacement(this));
	}

	@Override
	protected void preRenderCallback(EntityHessonite gem, float partialTickTime) {
		if (gem.isDefective()) {
			GlStateManager.scale(0.8F, 0.7F, 0.8F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHessonite entity) {
		return new ResourceLocation("kagic:textures/entities/hessonite/hessonite.png");
	}
}
