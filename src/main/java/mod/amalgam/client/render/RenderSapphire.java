package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelSapphire;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerSapphireItem;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.gem.EntitySapphire;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSapphire extends RenderGemBase<EntitySapphire> {
	public RenderSapphire(RenderManager manager) {
        super(manager, new ModelSapphire(), 0.25F);
        this.addLayer(new LayerSapphireItem(this));
		this.addLayer(new LayerSkin(this, 0.25F));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerNoDyeOverlay(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this, 0.5F));
        this.addLayer(new LayerGemPlacement(this));
	}
	
	@Override
	protected void preRenderCallback(EntitySapphire gem, float partialTickTime) {
		GlStateManager.scale(0.85F, 0.85F, 0.85F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntitySapphire entity) {
		return new ResourceLocation("kagic:textures/entities/sapphire/sapphire.png");
	}
}
