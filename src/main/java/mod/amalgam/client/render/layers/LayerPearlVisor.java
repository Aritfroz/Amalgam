package mod.amalgam.client.render.layers;

import mod.amalgam.gem.EntityPearl;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class LayerPearlVisor implements LayerRenderer<EntityPearl> {
	private final RenderLivingBase<?> pearlRenderer;
	private final ModelBase pearlModel;
	
	public LayerPearlVisor(RenderLivingBase<?> pearlRenderer) {
		this.pearlRenderer = pearlRenderer;
		this.pearlModel = pearlRenderer.getMainModel();
	}
	public void doRenderLayer(EntityPearl entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (entity.hasVisor()) {
			float[] afloat = EntitySheep.getDyeRgb(EnumDyeColor.values()[entity.getVisorColor()]);
			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
			GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			this.pearlRenderer.bindTexture(this.getTexture(entity));
			this.pearlModel.setModelAttributes(this.pearlRenderer.getMainModel());
	        this.pearlModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	        GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
		}
	}
	public ResourceLocation getTexture(EntityPearl pearl) {
		return new ResourceLocation("kagic:textures/entities/pearl/visor.png");
	}
	public boolean shouldCombineTextures() {
		return true;
	}
}
