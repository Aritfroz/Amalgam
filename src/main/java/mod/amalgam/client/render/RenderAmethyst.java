package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelQuartz;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerQuartzCape;
import mod.amalgam.client.render.layers.LayerQuartzItem;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityAmethyst;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderAmethyst extends RenderGemBase<EntityAmethyst> {
	public RenderAmethyst() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelQuartz(), 0.5F);
		this.addLayer(new LayerQuartzItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerQuartzCape(this));
        this.addLayer(new LayerGemPlacement(this));
    }
	
	@Override
	protected void preRenderCallback(EntityAmethyst amethyst, float partialTickTime) {
		if (amethyst.isDefective()) {
			GlStateManager.scale(0.9F, 0.72F, 0.9F);
		} else if (amethyst.isPrimary()) {
			GlStateManager.scale(1.1F, 1.1F, 1.1F);
		}
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityAmethyst entity) {
		return new ResourceLocation("kagic:textures/entities/amethyst/amethyst.png");
	}
}
