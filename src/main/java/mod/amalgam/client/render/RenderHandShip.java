package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelHandShip;
import mod.amalgam.entity.vehicle.EntityHandShip;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHandShip extends RenderLiving<EntityHandShip> {
	public RenderHandShip(RenderManager manager) {
		super(manager, new ModelHandShip(), 0.0F);
	}
	@Override
	protected void preRenderCallback(EntityHandShip ship, float partialTickTime) {
		super.preRenderCallback(ship, partialTickTime);
		GlStateManager.scale(32.0D, 32.0D, 32.0D);
		GlStateManager.translate(0, -0.5, 0);
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityHandShip entity) {
		return new ResourceLocation("amalgam:textures/entities/gem_warship.png");
	}
}
