package mod.amalgam.client.render.layers;

import mod.akrivus.kagic.entity.EntitySlag;
import mod.amalgam.client.render.RenderSlag;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerSlagVariants implements LayerRenderer<EntitySlag> {
	private final RenderSlag slagRenderer;
	private final ModelBase slagModel;
	public LayerSlagVariants(RenderSlag slagRenderer) {
		this.slagRenderer = slagRenderer;
		this.slagModel = slagRenderer.getMainModel();
	}
	public void doRenderLayer(EntitySlag slag, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		String[] placements = Integer.toString(slag.getVariant()).split("");
		for (String placement : placements) {
            this.slagRenderer.bindTexture(new ResourceLocation("kagic:textures/entities/slag/slag_" + placement + ".png"));
			this.slagModel.setModelAttributes(this.slagRenderer.getMainModel());
	        this.slagModel.render(slag, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	        GlStateManager.disableBlend();
		}
	}
	public boolean shouldCombineTextures() {
		return true;
	}
}
