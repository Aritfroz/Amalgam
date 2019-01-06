package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelZircon;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.client.render.layers.LayerZirconHair;
import mod.amalgam.client.render.layers.LayerZirconItem;
import mod.amalgam.gem.EntityZircon;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderZircon extends RenderGemBase<EntityZircon> {
	public RenderZircon(RenderManager manager) {
        super(manager, new ModelZircon(), 0.25F);
        this.addLayer(new LayerZirconItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerNoDyeOverlay(this));
        this.addLayer(new LayerZirconHair(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerGemPlacement(this));
        this.addLayer(new LayerVisor(this));
    }
	
	@Override
	protected void preRenderCallback(EntityZircon gem, float partialTickTime) {
		//float[] afloat = EntitySheep.getDyeRgb(EnumDyeColor.values()[((EntityZircon) gem).getInsigniaColor()]);
		//GlStateManager.color(afloat[0], afloat[1], afloat[2]);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityZircon entity) {
		return new ResourceLocation("kagic:textures/entities/zircon/zircon_" + entity.getSpecialSkin() + ".png");
	}
}
