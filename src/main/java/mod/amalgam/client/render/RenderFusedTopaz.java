package mod.amalgam.client.render;

import mod.akrivus.kagic.client.model.ModelTopaz;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.LayerBirthdayHat;
import mod.akrivus.kagic.client.render.layers.LayerCrossFusionGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerHair;
import mod.akrivus.kagic.client.render.layers.LayerInsignia;
import mod.akrivus.kagic.client.render.layers.LayerSantaHat;
import mod.akrivus.kagic.client.render.layers.LayerSkin;
import mod.akrivus.kagic.client.render.layers.LayerUniform;
import mod.akrivus.kagic.client.render.layers.LayerVisor;
import mod.akrivus.kagic.client.render.layers.LayerWitchHat;
import mod.amalgam.gem.fusion.EntityFusedTopaz;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFusedTopaz extends RenderGemBase<EntityFusedTopaz> {
	public RenderFusedTopaz(RenderManager manager) {
        super(manager, new ModelTopaz(), 0.25F);
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerVisor(this));
		this.addLayer(new LayerCrossFusionGemPlacement(this));
		if (Amalgic.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (Amalgic.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		} else if (Amalgic.isChristmas()) {
			this.addLayer(new LayerSantaHat(this));
		}
    }
	@Override
	protected void preRenderCallback(EntityFusedTopaz gem, float partialTickTime) {
		GlStateManager.scale(2.0F, 2.0F, 2.0F);
		this.shadowSize = 1.25F;
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityFusedTopaz entity) {
		return new ResourceLocation("amalgam:textures/entities/fused_topaz/topaz.png");
	}
}