package mod.akrivus.amalgam.client.render;

import mod.akrivus.amalgam.client.model.ModelConnie;
import mod.akrivus.amalgam.client.render.layers.LayerConnieBackpack;
import mod.akrivus.amalgam.client.render.layers.LayerConnieClothing;
import mod.akrivus.amalgam.client.render.layers.LayerConnieHair;
import mod.akrivus.amalgam.client.render.layers.LayerConnieItem;
import mod.akrivus.amalgam.gem.EntityConnie;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.util.ResourceLocation;

public class RenderConnie extends RenderLiving<EntityConnie> {
	public RenderConnie() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelConnie(), 0.25F);
        this.addLayer(new LayerConnieItem(this));
        this.addLayer(new LayerConnieClothing(this));
        this.addLayer(new LayerConnieHair(this));
        this.addLayer(new LayerConnieBackpack(this));
        this.addLayer(new LayerArrow(this));
    }
	@Override
	protected ResourceLocation getEntityTexture(EntityConnie entity) {
		return new ResourceLocation("amalgam:textures/entities/connie/connie.png");
	}
}