package mod.amalgam.client.render;

import mod.akrivus.kagic.entity.pepo.EntityStrawberry;
import mod.amalgam.client.model.ModelPepo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.util.ResourceLocation;

public class RenderStrawberry extends RenderLivingBase<EntityStrawberry> {
	public RenderStrawberry() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelPepo(), 0.25F);
        this.addLayer(new LayerArrow(this));
    }
	protected void preRenderCallback(EntityStrawberry entity, float partialTickTime) {
		
	}
	protected ResourceLocation getEntityTexture(EntityStrawberry entity) {
		return new ResourceLocation("kagic:textures/entities/pepo/strawberry.png");
	}
}
