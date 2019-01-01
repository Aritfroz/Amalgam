package mod.amalgam.client.render;

import mod.akrivus.kagic.entity.gem.EntityHoloPearl;
import mod.amalgam.client.model.ModelPearl;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHoloPearlDress;
import mod.amalgam.client.render.layers.LayerHoloPearlHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerNoDyeOverlay;
import mod.amalgam.client.render.layers.LayerPearlItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class RenderHoloPearl extends RenderGemBase<EntityHoloPearl> {
	private static final float OFFSET = .75f;

	public RenderHoloPearl() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelPearl(), 0.25F);

        this.addLayer(new LayerPearlItem(this));
        this.addLayer(new LayerNoDyeOverlay(this, "pearl"));
        this.addLayer(new LayerInsignia(this, "pearl"));
        this.addLayer(new LayerHoloPearlHair(this));
        this.addLayer(new LayerHoloPearlDress(this));
        this.addLayer(new LayerGemPlacement(this, "pearl"));
	}

	
	@Override
	protected void preRenderCallback(EntityHoloPearl gem, float partialTickTime) {
		float[] afloat = EntitySheep.getDyeRgb(EnumDyeColor.values()[((EntityHoloPearl) gem).getColor()]);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(afloat[0] + OFFSET, afloat[1] + OFFSET, afloat[2] + OFFSET, 0.75F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityHoloPearl entity) {
		return new ResourceLocation("kagic:textures/entities/pearl/pearl_0.png");
	}
}
