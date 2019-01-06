package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelQuartz;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerJasperMark1;
import mod.amalgam.client.render.layers.LayerJasperMark2;
import mod.amalgam.client.render.layers.LayerQuartzCape;
import mod.amalgam.client.render.layers.LayerQuartzItem;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityJasper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderJasper extends RenderGemBase<EntityJasper> {
	public RenderJasper(RenderManager manager) {
		super(manager, new ModelQuartz(), 0.25F);		
		this.addLayer(new LayerQuartzItem(this));
		this.addLayer(new LayerSkin(this));
		this.addLayer(new LayerJasperMark1(this));
		this.addLayer(new LayerJasperMark2(this));
		this.addLayer(new LayerUniform(this));
		this.addLayer(new LayerInsignia(this));
		this.addLayer(new LayerHair(this));
		this.addLayer(new LayerVisor(this));
		this.addLayer(new LayerQuartzCape(this));
		this.addLayer(new LayerGemPlacement(this));
	}
		
	@Override
	protected void preRenderCallback(EntityJasper jasper, float partialTickTime) {
		if (jasper.isDefective()) {
			GlStateManager.scale(0.7F, 1.0F, 0.7F);
		} else if (jasper.isPrimary()) {
			GlStateManager.scale(1.1F, 1.1F, 1.1F);
		}
	}	
	
	@Override
	protected ResourceLocation getEntityTexture(EntityJasper entity) {
		return new ResourceLocation("kagic:textures/entities/jasper/" + entity.getSpecialSkin() + "jasper.png");
	}
}
