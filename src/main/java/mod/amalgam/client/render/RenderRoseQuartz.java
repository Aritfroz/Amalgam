package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelQuartz;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerHeldItem;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerQuartzCape;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.entity.EntityQuartz;
import mod.amalgam.gem.EntityRoseQuartz;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRoseQuartz extends RenderAmalgamGem<EntityRoseQuartz> {
	public RenderRoseQuartz(RenderManager manager) {
        super(manager, new ModelQuartz(), 0.25F);
		this.addLayer(new LayerHeldItem(this, 0.625F, 8.0F, 0.125F, -0.6F));
        this.addLayer(new LayerHair(this, EntityQuartz.HAIRSTYLES));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerQuartzCape(this));
        this.addLayer(new LayerGemPlacement(this));
    }
	
	@Override
	protected void preRenderCallback(EntityRoseQuartz roseQuartz, float partialTickTime) {
		if (roseQuartz.isDefective()) {
			GlStateManager.scale(0.7F, 1.0F, 0.7F);
		} else if (roseQuartz.isPrimary()) {
			GlStateManager.scale(1.1F, 1.1F, 1.1F);
		}
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityRoseQuartz entity) {
		return new ResourceLocation("kagic:textures/entities/rose_quartz/rose_quartz.png");
	}
}
