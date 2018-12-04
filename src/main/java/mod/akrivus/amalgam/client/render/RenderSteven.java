package mod.akrivus.amalgam.client.render;

import mod.akrivus.amalgam.client.model.ModelSteven;
import mod.akrivus.amalgam.client.render.layers.LayerCheeseburgerBackpack;
import mod.akrivus.amalgam.client.render.layers.LayerStevenItem;
import mod.akrivus.amalgam.gem.EntitySteven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.util.ResourceLocation;

public class RenderSteven extends RenderLivingBase<EntitySteven> {
	public RenderSteven() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelSteven(), 0.25F);
        this.addLayer(new LayerStevenItem(this));
        this.addLayer(new LayerCheeseburgerBackpack(this));
        this.addLayer(new LayerArrow(this));
    }
	@Override
	protected ResourceLocation getEntityTexture(EntitySteven entity) {
		return new ResourceLocation("amalgam:textures/entities/steven/steven.png");
	}
}