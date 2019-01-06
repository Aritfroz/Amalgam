package mod.amalgam.client.render;

import mod.akrivus.kagic.client.model.ModelHessonite;
import mod.akrivus.kagic.client.render.RenderGemBase;
import mod.akrivus.kagic.client.render.layers.LayerGemPlacement;
import mod.akrivus.kagic.client.render.layers.LayerHair;
import mod.akrivus.kagic.client.render.layers.LayerInsignia;
import mod.akrivus.kagic.client.render.layers.LayerQuartzItem;
import mod.akrivus.kagic.client.render.layers.LayerSkin;
import mod.akrivus.kagic.client.render.layers.LayerUniform;
import mod.akrivus.kagic.client.render.layers.LayerVisor;
import mod.amalgam.client.render.layers.LayerMelaniteCape;
import mod.amalgam.gem.EntityMelanite;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMelanite extends RenderGemBase<EntityMelanite> {
    public RenderMelanite(RenderManager manager) {
        super(manager, new ModelHessonite(), 0.5F);
        this.addLayer(new LayerQuartzItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerMelaniteCape(this, true, true));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerGemPlacement(this));
    }
    @Override
    protected void preRenderCallback(EntityMelanite gem, float partialTickTime) {
        if (gem.isDefective()) {
            GlStateManager.scale(0.8F, 0.7F, 0.8F);
        }
    }
    @Override
    protected ResourceLocation getEntityTexture(EntityMelanite entity) {
        return new ResourceLocation("amalgam:textures/entities/melanite/melanite.png");
    }
}
