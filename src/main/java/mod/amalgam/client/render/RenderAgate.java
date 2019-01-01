package mod.amalgam.client.render;

import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.amalgam.client.model.ModelAgate;
import mod.amalgam.client.render.layers.LayerAgateBand;
import mod.amalgam.client.render.layers.LayerAgateHair;
import mod.amalgam.client.render.layers.LayerAgateItem;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerQuartzCape;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityAgate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderAgate extends RenderGemBase<EntityAgate> {
	private static final float OFFSET = .0f;

	public RenderAgate() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelAgate(), 0.25F);
		this.addLayer(new LayerAgateItem(this));
        this.addLayer(new LayerAgateHair(this));
        this.addLayer(new LayerNoDyeOverlay(this));
        this.addLayer(new LayerAgateBand(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerQuartzCape(this));
        this.addLayer(new LayerGemPlacement(this));
	}
	
	@Override
	protected void preRenderCallback(EntityAgate agate, float partialTickTime) {
		if (!agate.isHolly()) {
			float[] afloat = EntityAgate.AGATECOLORS[agate.getColor()];
			GlStateManager.color(afloat[0] + OFFSET, afloat[1] + OFFSET, afloat[2] + OFFSET);
		}
		if (agate.isDefective()) {
			GlStateManager.scale(0.8F, 0.8F, 0.8F);
		} else if (agate.isPrimary()) {
			GlStateManager.scale(1.1F, 1.1F, 1.1F);
		}
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityAgate entity) {
		if (entity.getGemPlacement() == GemPlacements.BELLY) {
			return new ResourceLocation("kagic:textures/entities/agate/agate_belly.png");
		} else if (entity.isHolly()) {
			return new ResourceLocation("kagic:textures/entities/agate/holly_blue_agate.png");
		} else {
			return new ResourceLocation("kagic:textures/entities/agate/agate.png");
		}
	}
}
