package mod.amalgam.client.render.layers;

import java.util.ArrayList;

import mod.amalgam.entity.EntityGem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerUniform implements LayerRenderer<EntityGem> {
	private final RenderLivingBase<?> renderer;
	private final ArrayList<ResourceLocation> uniforms;
	public LayerUniform(RenderLivingBase<?> renderer, ArrayList<ResourceLocation> uniforms) {
		this.renderer = renderer;
		this.uniforms = uniforms;
	}
	
	@Override
	public void doRenderLayer(EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.renderer.bindTexture(this.uniforms.get(gem.getUniformVariant()));
		int uniform = gem.getUniformColor();
		float r = ((uniform & 16711680) >> 16) / 255f;
		float g = ((uniform & 65280) >> 8) / 255f;
		float b = ((uniform & 255) >> 0) / 255f;
		GlStateManager.color(r, g, b);
        this.renderer.getMainModel().render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	}
	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
}
