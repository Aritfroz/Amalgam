package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelQuartz;
import mod.amalgam.client.render.layers.LayerAmetrineHalf;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerHeldItem;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerQuartzCape;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.entity.EntityQuartz;
import mod.amalgam.gem.EntityCitrine;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCitrine extends RenderGemBase<EntityCitrine> {
	public RenderCitrine(RenderManager manager) {
        super(manager, new ModelQuartz(), 0.5F);
		this.addLayer(new LayerHeldItem(this, 0.625F, 8.0F, 0.125F, -0.6F));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerHair(this, EntityQuartz.HAIRSTYLES));
        this.addLayer(new LayerAmetrineHalf(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerQuartzCape(this));
        this.addLayer(new LayerGemPlacement(this));
    }
	@Override
	protected void preRenderCallback(EntityCitrine citrine, float partialTickTime) {
		if (citrine.isPrimary()) {
			GlStateManager.scale(1.1F, 1.1F, 1.1F);
		}
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityCitrine entity) {
		if (entity.isDefective()) {
			return new ResourceLocation("amalgam:textures/entities/citrine/ametrine_" + entity.getSpecial() + ".png");
		}
		return new ResourceLocation("amalgam:textures/entities/citrine/citrine.png");
	}
}