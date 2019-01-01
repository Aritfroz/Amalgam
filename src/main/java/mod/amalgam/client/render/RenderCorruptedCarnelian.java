package mod.amalgam.client.render;

import mod.akrivus.kagic.entity.gem.corrupted.EntityCorruptedCarnelian;
import mod.amalgam.client.model.corrupted.ModelCorruptedQuartz;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerSkin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.util.ResourceLocation;

public class RenderCorruptedCarnelian extends RenderLiving<EntityCorruptedCarnelian> {
	
	public RenderCorruptedCarnelian() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelCorruptedQuartz(), 2F);

		this.addLayer(new LayerSkin(this, 0F, "corrupted/carnelian"));
		this.addLayer(new LayerHair(this, 0F, "corrupted/carnelian"));
		this.addLayer(new LayerGemPlacement(this, "corrupted/carnelian"));
		/*		
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		}*/
	}
			
	@Override
	protected void preRenderCallback(EntityCorruptedCarnelian carnelian, float partialTickTime) {
		GlStateManager.scale(2F, 2F, 2F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityCorruptedCarnelian carnelian) {
		return new ResourceLocation("kagic:textures/entities/corrupted/carnelian/carnelian.png");
	}
}
