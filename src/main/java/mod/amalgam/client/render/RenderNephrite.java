package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelNephrite;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerHeldItem;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.gem.EntityNephrite;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderNephrite extends RenderAmalgamGem<EntityNephrite> {
	public RenderNephrite(RenderManager manager) {
        super(manager, new ModelNephrite(), 0.5F);
		this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerHair(this, EntityNephrite.HAIRSTYLES));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerGemPlacement(this));
    }	
	@Override
	protected ResourceLocation getEntityTexture(EntityNephrite entity) {
		return new ResourceLocation("amalgam:textures/entities/nephrite/nephrite.png");
	}
}