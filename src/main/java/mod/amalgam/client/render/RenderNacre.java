package mod.amalgam.client.render;

import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.LayerGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerHair;
import mod.akrivus.kagic.client.render.layers.LayerInsignia;
import mod.akrivus.kagic.client.render.layers.LayerNoDyeOverlay;
import mod.akrivus.kagic.client.render.layers.LayerSkin;
import mod.akrivus.kagic.client.render.layers.LayerUniform;
import mod.amalgam.client.model.ModelNacre;
import mod.amalgam.client.render.layers.LayerNacreColor1;
import mod.amalgam.client.render.layers.LayerNacreColor2;
import mod.amalgam.client.render.layers.LayerNacreColor3;
import mod.amalgam.client.render.layers.LayerNacreColor4;
import mod.amalgam.client.render.layers.LayerNacreItem;
import mod.amalgam.client.render.layers.LayerNacreShell;
import mod.amalgam.gem.EntityNacre;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderNacre extends RenderGemBase<EntityNacre> {
	public RenderNacre(RenderManager manager) {
        super(manager, new ModelNacre(), 0.5F);
        this.addLayer(new LayerNacreItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerNacreColor1(this));
        this.addLayer(new LayerNacreColor2(this));
        this.addLayer(new LayerNacreColor3(this));
        this.addLayer(new LayerNacreColor4(this));
        this.addLayer(new LayerNacreShell(this));
        this.addLayer(new LayerNoDyeOverlay(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerGemPlacement(this));
    }
	@Override
	protected void preRenderCallback(EntityNacre gem, float partialTickTime) {
		GlStateManager.translate(0F, -0.4F, 0F);
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityNacre entity) {
		return new ResourceLocation("amalgam:textures/entities/nacre/nacre.png");
	}
}