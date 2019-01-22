package mod.amalgam.client.render.layers;

import mod.amalgam.client.render.RenderRutile;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class LayerRutileUniform implements LayerRenderer<EntityGem> {
	private final RenderRutile gemRenderer;
	
	public LayerRutileUniform(RenderRutile gemRenderer) {
		this.gemRenderer = gemRenderer;
	}
	
	@Override
	public void doRenderLayer(EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.gemRenderer.bindTexture(this.getTexture(gem));
		float[] afloat = EntitySheep.getDyeRgb(EnumDyeColor.values()[gem.getUniformColor()]);
		GlStateManager.color(afloat[0], afloat[1], afloat[2]/*, 0.99f*/);
		//GlStateManager.enableBlend();
		//GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
		this.gemRenderer.getModel(gem.isDefective()).setModelAttributes(this.gemRenderer.getMainModel());
        this.gemRenderer.getModel(gem.isDefective()).render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		GlStateManager.disableBlend();
	}
	
	public ResourceLocation getTexture(EntityGem gem) {
		return new ResourceLocation("kagic:textures/entities/rutile/uniform.png");
	}
	
	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
}
