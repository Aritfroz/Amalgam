package mod.amalgam.client.render.layers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;

public class LayerFusionPlacement implements LayerRenderer<EntityGem> {
	private final RenderLivingBase<?> gemRenderer;
	private final ModelBase gemModel;
	public LayerFusionPlacement(RenderLivingBase<?> gemRenderer) {
		this.gemRenderer = gemRenderer;
		this.gemModel = gemRenderer.getMainModel();
	}
	@Override
	public void doRenderLayer(EntityGem gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (gem.isFusion()) {
			String[] placements = gem.getFusionPlacements().split(" ");
			for (String placement : placements) {
				this.gemRenderer.bindTexture(this.getTexture(gem, placement));
				int color = gem.getGemColor();
				float r = ((color & 16711680) >> 16) / 255f;
		        float g = ((color & 65280) >> 8) / 255f;
		        float b = ((color & 255) >> 0) / 255f;
				GlStateManager.color(r, g, b);
				this.gemModel.setModelAttributes(this.gemRenderer.getMainModel());
		        this.gemModel.render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			}
		}
	}
	public ResourceLocation getTexture(EntityGem gem, String placement) {
		ResourceLocation loc = EntityList.getKey(gem);
		return new ResourceLocation(loc.getResourceDomain() + ":textures/entities/" + this.getName(gem) + "/gems/" + placement + ".png");
	}
	public String getName(EntityGem gem) {
		ResourceLocation loc = EntityList.getKey(gem);
		if (loc.getResourceDomain().equals("kagic")) {
	        return loc.getResourcePath().replaceFirst("kagic.", "");
		}
		else {
	        return loc.getResourcePath();
		}
	}
	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
}
