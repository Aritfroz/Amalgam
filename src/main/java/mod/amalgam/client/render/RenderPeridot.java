package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelPeridot;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerPeridotItem;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityPeridot;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPeridot extends RenderGemBase<EntityPeridot> {
	public RenderPeridot(RenderManager manager) {
        super(manager, new ModelPeridot(), 0.25F);
        this.addLayer(new LayerPeridotItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerGemPlacement(this));
    }

	@Override
	protected ResourceLocation getEntityTexture(EntityPeridot entity) {
		return new ResourceLocation("kagic:textures/entities/peridot/peridot.png");
	}
}
