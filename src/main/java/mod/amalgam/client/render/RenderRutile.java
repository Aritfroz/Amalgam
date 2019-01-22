package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelNothing;
import mod.amalgam.client.model.ModelRutile;
import mod.amalgam.client.model.ModelTwinRutile;
import mod.amalgam.client.render.layers.LayerRutileGemPlacement;
import mod.amalgam.client.render.layers.LayerRutileHair;
import mod.amalgam.client.render.layers.LayerRutileInsignia;
import mod.amalgam.client.render.layers.LayerRutileItem;
import mod.amalgam.client.render.layers.LayerRutileModel;
import mod.amalgam.client.render.layers.LayerRutileSkin;
import mod.amalgam.client.render.layers.LayerRutileUniform;
import mod.amalgam.client.render.layers.LayerRutileVisor;
import mod.amalgam.gem.EntityRutile;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderRutile extends RenderAmalgamGem<EntityRutile> {
	private ModelBiped normalRutile = new ModelRutile();
	private ModelBiped twinRutile = new ModelTwinRutile();
	public RenderRutile(RenderManager manager) {
        super(manager, new ModelNothing(), 0.25F);
        this.addLayer(new LayerRutileModel(this));
        this.addLayer(new LayerRutileItem(this));
        this.addLayer(new LayerRutileSkin(this));
        this.addLayer(new LayerRutileUniform(this));
        this.addLayer(new LayerRutileInsignia(this));
        this.addLayer(new LayerRutileHair(this));
        this.addLayer(new LayerRutileVisor(this));
        this.addLayer(new LayerRutileGemPlacement(this));
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityRutile entity) {
		return new ResourceLocation("kagic:textures/entities/rutile/rutile.png");
	}
	
	public ModelBiped getModel(boolean defective) {
		if (defective) {
			return this.twinRutile;
		}
		return this.normalRutile;
	}
}
