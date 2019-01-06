package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelAquamarine;
import mod.amalgam.client.render.layers.LayerAquamarineItem;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityAquamarine;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAquamarine extends RenderGemBase<EntityAquamarine> {
	public RenderAquamarine(RenderManager manager) {
        super(manager, new ModelAquamarine(), 0.25F);
        this.addLayer(new LayerAquamarineItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerGemPlacement(this));
    }
	
	@Override
	protected void preRenderCallback(EntityAquamarine entity, float partialTickTime) {
		GlStateManager.scale(0.65F, 0.65F, 0.65F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityAquamarine entity) {
		return new ResourceLocation("kagic:textures/entities/aquamarine/aquamarine.png");
	}
}
