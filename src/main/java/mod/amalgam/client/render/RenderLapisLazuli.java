package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelLapisLazuli;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerLapisLazuliItem;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityLapisLazuli;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderLapisLazuli extends RenderGemBase<EntityLapisLazuli> {
	public RenderLapisLazuli(RenderManager manager) {
        super(manager, new ModelLapisLazuli(), 0.25F);
        this.addLayer(new LayerLapisLazuliItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerGemPlacement(this));
    }
	
	@Override
	protected void preRenderCallback(EntityLapisLazuli entity, float partialTickTime) {
		if (entity.isBeingRidden() && entity.canBeSteered()) {
			GlStateManager.translate(0F, -1F, 1.25F);
			GlStateManager.rotate(90.0F, 1, 0, 0);
		}
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLapisLazuli entity) {
		return new ResourceLocation("kagic:textures/entities/lapis_lazuli/lapis_lazuli.png");
	}
}
