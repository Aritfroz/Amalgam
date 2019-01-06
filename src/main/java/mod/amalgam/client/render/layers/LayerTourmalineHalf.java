package mod.amalgam.client.render.layers;

import mod.amalgam.client.render.RenderWatermelonTourmaline;
import mod.amalgam.gem.EntityWatermelonTourmaline;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerTourmalineHalf implements LayerRenderer<EntityWatermelonTourmaline> {
	private final RenderWatermelonTourmaline renderer;
	public LayerTourmalineHalf(RenderWatermelonTourmaline renderer) {
		this.renderer = renderer;
	}
	@Override
	public void doRenderLayer(EntityWatermelonTourmaline gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.renderer.bindTexture(this.getTexture(gem));
		int skin = gem.getLowerColor();
		float red = ((skin & 16711680) >> 16) / 255f;
		float green = ((skin & 65280) >> 8) / 255f;
		float blue = ((skin & 255) >> 0) / 255f;
		GlStateManager.color(red, green, blue);
        this.renderer.getMainModel().render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		GlStateManager.disableBlend();
	}
	public ResourceLocation getTexture(EntityWatermelonTourmaline gem) {
		return new ResourceLocation("amalgam:textures/entities/wtourmaline/half.png");
	}
	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}