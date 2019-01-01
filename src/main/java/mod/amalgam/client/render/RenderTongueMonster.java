package mod.amalgam.client.render;

import mod.akrivus.kagic.entity.gem.corrupted.EntityTongueMonster;
import mod.amalgam.client.model.corrupted.ModelTongueMonster;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerSkin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class RenderTongueMonster extends RenderGemBase<EntityTongueMonster> {
	
	public RenderTongueMonster() {
		super(Minecraft.getMinecraft().getRenderManager(), new ModelTongueMonster(), 0.75F);

		
		this.addLayer(new LayerSkin(this, 0F, "corrupted/tongue_monster"));
		this.addLayer(new LayerGemPlacement(this, "corrupted/tongue_monster"));
		/*
		if (KAGIC.isBirthday()) {
			this.addLayer(new LayerBirthdayHat(this));
		} else if (KAGIC.isHalloween()) {
			this.addLayer(new LayerWitchHat(this));
		}*/
	}
			
	@Override
	protected void preRenderCallback(EntityTongueMonster tongueMonster, float partialTickTime) {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityTongueMonster tongueMonster) {
		return new ResourceLocation("kagic:textures/entities/corrupted/tongue_monster/tongue_monster.png");
	}
}
