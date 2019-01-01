package mod.amalgam.client.render;

import mod.akrivus.kagic.entity.gem.EntityPadparadscha;
import mod.amalgam.client.model.ModelNothing;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class RenderPadparadscha extends RenderGemBase<EntityPadparadscha> {
	public RenderPadparadscha() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelNothing(), 0.25F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPadparadscha entity) {
		return new ResourceLocation("kagic:textures/entities/sapphire/sapphire.png");
	}
}
