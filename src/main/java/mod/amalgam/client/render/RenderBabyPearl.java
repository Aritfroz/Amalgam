package mod.amalgam.client.render;

import mod.amalgam.gem.EntityPearl;
import net.minecraft.client.renderer.GlStateManager;

public class RenderBabyPearl extends RenderPearl {
	@Override
	protected void preRenderCallback(EntityPearl gem, float partialTickTime) {
		super.preRenderCallback(gem, partialTickTime);
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
	}
}