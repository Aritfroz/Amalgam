package mod.amalgam.client.render;

import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.LayerBirthdayHat;
import mod.akrivus.kagic.client.render.layers.LayerGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerHair;
import mod.akrivus.kagic.client.render.layers.LayerInsignia;
import mod.akrivus.kagic.client.render.layers.LayerSantaHat;
import mod.akrivus.kagic.client.render.layers.LayerSkin;
import mod.akrivus.kagic.client.render.layers.LayerUniform;
import mod.akrivus.kagic.client.render.layers.LayerWitchHat;
import mod.akrivus.kagic.init.Amalgic;
import mod.amalgam.client.model.ModelNephrite;
import mod.amalgam.client.render.layers.LayerNephriteItem;
import mod.amalgam.gem.EntityNephrite;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderNephrite extends RenderGemBase<EntityNephrite> {
	public RenderNephrite(RenderManager manager) {
        super(manager, new ModelNephrite(), 0.5F);
        this.addLayer(new LayerNephriteItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerGemPlacement(this));
    }	
	@Override
	protected ResourceLocation getEntityTexture(EntityNephrite entity) {
		return new ResourceLocation("amalgam:textures/entities/nephrite/nephrite.png");
	}
}