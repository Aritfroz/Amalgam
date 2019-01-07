package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelPearl;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerPearlDress;
import mod.amalgam.client.render.layers.LayerPearlHair;
import mod.amalgam.client.render.layers.LayerPearlItem;
import mod.amalgam.client.render.layers.LayerPearlVisor;
import mod.amalgam.gem.EntityPearl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPearl extends RenderGemBase<EntityPearl> {
	public RenderPearl(RenderManager manager) {
        super(manager, new ModelPearl(), 0.25F);
        this.addLayer(new LayerPearlItem(this));
        this.addLayer(new LayerNoDyeOverlay(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerPearlHair(this));
        this.addLayer(new LayerPearlDress(this));
        this.addLayer(new LayerPearlVisor(this));
        this.addLayer(new LayerGemPlacement(this));
	}
	@Override
	protected void preRenderCallback(EntityPearl gem, float partialTickTime) {
		if (gem.getSpecialSkin().equals("_0")) {
			int skin = gem.generateSkinColor();
			float r = ((skin & 16711680) >> 16) / 255.0F;
			float g = ((skin & 65280) >> 8) / 255.0F;
			float b = ((skin & 255) >> 0) / 255.0F;
			GlStateManager.color(r, g, b, 1.0F);
		}
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityPearl entity) {
		return new ResourceLocation("kagic:textures/entities/pearl/pearl" + entity.getSpecialSkin() + ".png");
	}
}
