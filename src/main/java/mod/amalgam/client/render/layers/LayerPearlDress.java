package mod.amalgam.client.render.layers;

import mod.amalgam.client.model.ModelPearl;
import mod.amalgam.client.render.RenderAmalgamGem;
import mod.amalgam.gem.EntityPearl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;

public class LayerPearlDress implements LayerRenderer<EntityPearl> {
	private final RenderAmalgamGem pearlRenderer;
	private final ModelPearl pearlModel = new ModelPearl();
	
	public LayerPearlDress(RenderAmalgamGem pearlRenderer) {
		this.pearlRenderer = pearlRenderer;
	}
	
	@Override
	public void doRenderLayer(EntityPearl gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (gem.getSpecialSkin().equals("_0") && !gem.isNaked()) {
			this.pearlRenderer.bindTexture(EntityPearl.PEARL_DRESS_STYLES.get(gem.getDressStyle()));
			float[] afloat = EntitySheep.getDyeRgb(EnumDyeColor.values()[gem.getInsigniaColor()]);
			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
			GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			this.pearlModel.setModelAttributes(this.pearlRenderer.getMainModel());
			this.pearlModel.render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
		}
	}
	
	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
}
