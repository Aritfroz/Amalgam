package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelRuby;
import mod.amalgam.client.render.layers.LayerFusionPlacement;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerRubyItem;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityRuby;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRuby extends RenderGemBase<EntityRuby> {
	public RenderRuby(RenderManager manager) {
        super(manager, new ModelRuby(), 0.3F);
        this.addLayer(new LayerRubyItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerGemPlacement(this));
        this.addLayer(new LayerFusionPlacement(this));
    }
	
	@Override
	protected void preRenderCallback(EntityRuby gem, float partialTickTime) {
		if (gem.isFusion()) {
			GlStateManager.scale(0.8F * gem.getFusionCount(), 0.8F * gem.getFusionCount(), 0.8F * gem.getFusionCount());
		}
		else if (gem.isDefective()) {
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
		}
		else {
			GlStateManager.scale(0.8F, 0.8F, 0.8F);
		}
		this.shadowSize = 0.3F * gem.getFusionCount();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityRuby entity) {
		return new ResourceLocation("kagic:textures/entities/ruby/ruby.png");
	}
}
