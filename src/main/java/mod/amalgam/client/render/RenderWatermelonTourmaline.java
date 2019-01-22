package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelTourmaline;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerTourmalineHalf;
import mod.amalgam.client.render.layers.LayerTourmalineItem;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityWatermelonTourmaline;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderWatermelonTourmaline extends RenderGemBase<EntityWatermelonTourmaline> {
	public RenderWatermelonTourmaline(RenderManager manager) {
        super(manager, new ModelTourmaline(), 0.25F);
		this.addLayer(new LayerTourmalineItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerTourmalineHalf(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerGemPlacement(this));
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityWatermelonTourmaline entity) {
		return new ResourceLocation("amalgam:textures/entities/wtourmaline/tourmaline.png");
	}
}