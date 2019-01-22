package mod.amalgam.client.render;

import mod.amalgam.client.model.fusions.ModelGarnet;
import mod.amalgam.client.render.layers.LayerCrossFusionGemPlacement;
import mod.amalgam.client.render.layers.LayerFusionColor;
import mod.amalgam.client.render.layers.LayerGarnetItem;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.gem.fusion.EntityGarnet;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderGarnet extends RenderAmalgamGem<EntityGarnet> {

	public RenderGarnet(RenderManager manager) {
		super(manager, new ModelGarnet(), 0.5F);
		
		this.addLayer(new LayerGarnetItem(this));
		this.addLayer(new LayerSkin(this));
		this.addLayer(new LayerHair(this));
		this.addLayer(new LayerNoDyeOverlay(this));
		this.addLayer(new LayerFusionColor(this, 0.25F));
		//this.addLayer(new LayerVisor(this));
		this.addLayer(new LayerCrossFusionGemPlacement(this));
	}

	@Override
	protected void preRenderCallback(EntityGarnet garnet, float partialTickTime) {
		GlStateManager.scale(1F * garnet.getSizeFactor(), 1F * garnet.getSizeFactor(), 1F * garnet.getSizeFactor());
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGarnet entity) {
		return new ResourceLocation("kagic:textures/entities/garnet/garnet.png");
	}
}