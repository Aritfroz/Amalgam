package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelRuby;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerHeldItem;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityPyrite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPyrite extends RenderAmalgamGem<EntityPyrite> {
	public RenderPyrite(RenderManager manager) {
        super(manager, new ModelRuby(), 0.25F);
		this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerHair(this, EntityPyrite.HAIRSTYLES));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerGemPlacement(this));
    }
	@Override
	protected void preRenderCallback(EntityPyrite gem, float partialTickTime) {
		if (gem.isFusion()) {
			GlStateManager.scale(gem.getFusionCount(), gem.getFusionCount(), gem.getFusionCount());
		}
		else if (gem.isDefective()) {
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
		}
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityPyrite entity) {
		return new ResourceLocation("amalgam:textures/entities/pyrite/pyrite.png");
	}
}