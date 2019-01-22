package mod.amalgam.client.render.layers;

import java.util.ArrayList;
import java.util.HashMap;

import mod.amalgam.client.render.RenderAmalgamGem;
import mod.amalgam.entity.EntityGem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerHair implements LayerRenderer<EntityGem> {
	private final RenderAmalgamGem<?> renderer;
	private final ArrayList<ResourceLocation> hairstyles;
	private HashMap<GemPlacements, ResourceLocation> variants;
	public LayerHair(RenderAmalgamGem<?> renderer, ArrayList<ResourceLocation> hairstyles, HashMap<GemPlacements, ResourceLocation> variants) {
		this.renderer = renderer;
		this.hairstyles = hairstyles;
		this.variants = variants;
	}
	public LayerHair(RenderAmalgamGem<?> renderer, ArrayList<ResourceLocation> hairstyles) {
		this(renderer, hairstyles, null);
	}
	@Override
	public void doRenderLayer(EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.renderer.bindTexture(this.getHairStyle(gem, gem.getHairStyle()));
		int color = gem.getHairColor();
        float r = ((color & 16711680) >> 16) / 255f;
        float g = ((color & 65280) >> 8) / 255f;
        float b = ((color & 255) >> 0) / 255f;
		GlStateManager.color(r, g, b);
		this.renderer.getMainModel().render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		GlStateManager.disableBlend();
	}
	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
	public ResourceLocation getHairStyle(EntityGem gem, int hairstyle) {
		if (gem.hasHairVariant(gem.getGemPlacement())) {
			return this.variants.get(gem.getGemPlacement());
		}
		else {
			return this.hairstyles.get(gem.getHairStyle());
		}
	}
}
