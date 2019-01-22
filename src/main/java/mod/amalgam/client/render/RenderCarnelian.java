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
import mod.amalgam.gem.EntityCarnelian;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCarnelian extends RenderAmalgamGem<EntityCarnelian> {
	public RenderCarnelian(RenderManager manager) {
        super(manager, new ModelQuartz(), 0.5F);
		this.addLayer(new LayerHeldItem(this, 0.625F, 8.0F, 0.125F, -0.6F));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerHair(this, EntityQuartz.HAIRSTYLES));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerQuartzCape(this));
        this.addLayer(new LayerGemPlacement(this));
    }
	@Override
	protected void preRenderCallback(EntityCarnelian carnelian, float partialTickTime) {
		if (carnelian.isDefective()) {
			GlStateManager.scale(0.9F, 0.75F, 0.9F);
		}
		else if (carnelian.isPrimary()) {
			GlStateManager.scale(1.1F, 1.1F, 1.1F);
		}
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityCarnelian entity) {
		return new ResourceLocation("kagic:textures/entities/carnelian/carnelian.png");
	}
}
