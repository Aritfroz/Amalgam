package mod.amalgam.client.render;

import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.LayerGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerHair;
import mod.akrivus.kagic.client.render.layers.LayerInsignia;
import mod.akrivus.kagic.client.render.layers.LayerSkin;
import mod.akrivus.kagic.client.render.layers.LayerUniform;
import mod.akrivus.kagic.client.render.layers.LayerVisor;
import mod.amalgam.client.model.ModelEmerald;
import mod.amalgam.client.render.layers.LayerEmeraldItem;
import mod.amalgam.gem.EntityEmerald;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEmerald extends RenderGemBase<EntityEmerald> {
	public RenderEmerald(RenderManager manager) {
		super(manager, new ModelEmerald(), 0.5F);
		this.addLayer(new LayerEmeraldItem(this));
		this.addLayer(new LayerSkin(this));
		this.addLayer(new LayerHair(this));
		this.addLayer(new LayerVisor(this));
		this.addLayer(new LayerUniform(this));
		this.addLayer(new LayerInsignia(this));
		this.addLayer(new LayerGemPlacement(this));
	}
	@Override
	protected void preRenderCallback(EntityEmerald gem, float partialTicks) {
		if (gem.isDefective()) {
			GlStateManager.scale(0.8F, 0.8F, 0.8F);
		}
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityEmerald entity) {
		return new ResourceLocation("amalgam:textures/entities/emerald/emerald.png");
	}
}
