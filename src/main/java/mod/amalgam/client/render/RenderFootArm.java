package mod.amalgam.client.render;

import mod.akrivus.kagic.entity.shardfusion.EntityFootArm;
import mod.amalgam.client.model.shardfusions.ModelFootArm;
import mod.amalgam.client.render.layers.LayerShardPlacement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderFootArm extends RenderLivingBase<EntityFootArm> {

	public RenderFootArm() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelFootArm(), 0.25F);
        this.addLayer(new LayerShardPlacement(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFootArm entity) {
		return new ResourceLocation("kagic:textures/entities/shardfusions/footarm/footarm.png");
	}
}
