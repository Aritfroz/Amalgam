package mod.amalgam.client.render.layers;

import mod.amalgam.client.model.ModelZircon;
import mod.amalgam.client.render.RenderZircon;
import mod.amalgam.gem.EntityZircon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;

public class LayerZirconHair implements LayerRenderer<EntityZircon> {
	private final RenderZircon zirconRenderer;
	private final ModelZircon zirconModel = new ModelZircon();
	public LayerZirconHair(RenderZircon zirconRenderer) {
		this.zirconRenderer = zirconRenderer;
	}
	public void doRenderLayer(EntityZircon gem, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (gem.getSpecialSkin().equals("0")) {
			this.zirconRenderer.bindTexture(EntityZircon.ZIRCON_HAIR_STYLES.get(gem.getHairStyle()));
			float[] afloat = EntitySheep.getDyeRgb(EnumDyeColor.values()[gem.getInsigniaColor()]);
			GlStateManager.color(afloat[0] + 0.25F, afloat[1] + 0.25F, afloat[2] + 0.25F);
			this.zirconModel.setModelAttributes(this.zirconRenderer.getMainModel());
			this.zirconModel.render(gem, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		}
	}
	public boolean shouldCombineTextures() {
		return true;
	}
}
