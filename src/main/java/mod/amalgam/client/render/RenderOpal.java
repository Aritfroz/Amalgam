package mod.amalgam.client.render;

import mod.amalgam.client.model.fusions.ModelOpal;
import mod.amalgam.client.render.layers.LayerCrossFusionGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerOpalItem;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.gem.fusion.EntityOpal;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderOpal extends RenderGemBase<EntityOpal> {

	public RenderOpal() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelOpal(), 1F);
		this.addLayer(new LayerOpalItem(this));
		this.addLayer(new LayerSkin(this, 0.4F));
		this.addLayer(new LayerHair(this));
		this.addLayer(new LayerNoDyeOverlay(this));
		this.addLayer(new LayerCrossFusionGemPlacement(this));
	}

	@Override
	protected void preRenderCallback(EntityOpal opal, float partialTickTime) {
		GlStateManager.scale(1.8F * opal.getSizeFactor(), 1.8F * opal.getSizeFactor(), 1.8F * opal.getSizeFactor());
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityOpal entity) {
		return new ResourceLocation("kagic:textures/entities/opal/opal.png");
	}
}
