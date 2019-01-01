package mod.amalgam.client.render;

import mod.akrivus.kagic.entity.shardfusion.EntityHandBody;
import mod.amalgam.client.model.shardfusions.ModelHandBody;
import mod.amalgam.client.render.layers.LayerShardPlacement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderHandBody extends RenderLivingBase<EntityHandBody> {

	public RenderHandBody() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelHandBody(), 0.25F);
        this.addLayer(new LayerShardPlacement(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHandBody entity) {
		return new ResourceLocation("kagic:textures/entities/shardfusions/handbody/handbody.png");
	}
}
