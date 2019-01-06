package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelBismuth;
import mod.amalgam.client.render.layers.LayerBismuthItem;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityBismuth;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBismuth extends RenderGemBase<EntityBismuth> {
	public RenderBismuth(RenderManager manager) {
        super(manager, new ModelBismuth(), 0.25F);
        this.addLayer(new LayerBismuthItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerNoDyeOverlay(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerGemPlacement(this));
    }

	@Override
	protected void preRenderCallback(EntityBismuth gem, float partialTickTime) {
		if (gem.isDefective()) {
			GlStateManager.scale(0.67F, 0.9F, 0.8F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBismuth entity) {
		return new ResourceLocation("kagic:textures/entities/bismuth/bismuth.png");
	}
}
