package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelAgate;
import mod.amalgam.client.render.layers.LayerAgateBand;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerHeldItem;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerQuartzCape;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.entity.EntityQuartz;
import mod.amalgam.gem.EntityAgate;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAgate extends RenderAmalgamGem<EntityAgate> {
	private static final ResourceLocation EYES = new ResourceLocation("amalgam:textures/entities/quartz/eyes.png"); 
	public RenderAgate(RenderManager manager) {
        super(manager, new ModelAgate(), 0.25F);
		this.addLayer(new LayerHeldItem(this, 0.625F, 8.0F, 0.125F, -0.6F));
        this.addLayer(new LayerHair(this, EntityQuartz.HAIRSTYLES));
        this.addLayer(new LayerNoDyeOverlay(this));
        this.addLayer(new LayerAgateBand(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerQuartzCape(this));
        this.addLayer(new LayerGemPlacement(this));
	}
	@Override
	protected void preRenderCallback(EntityAgate agate, float partialTickTime) {
		int color = agate.getHairColor();
        float r = ((color & 16711680) >> 16) / 255f;
        float g = ((color & 65280) >> 8) / 255f;
        float b = ((color & 255) >> 0) / 255f;
		GlStateManager.color(r, g, b);
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityAgate entity) {
		return EYES;
	}
}
