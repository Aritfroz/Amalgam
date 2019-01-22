package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelPearl;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerHeldItem;
import mod.amalgam.client.render.layers.LayerPearlDress;
import mod.amalgam.gem.EntityEnderPearl;
import mod.amalgam.gem.EntityPearl;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEnderPearl extends RenderAmalgamGem<EntityEnderPearl> {
	public RenderEnderPearl(RenderManager manager) {
        super(manager, new ModelPearl(), 0.25F);
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new LayerHair(this, EntityPearl.HAIRSTYLES));
        this.addLayer(new LayerPearlDress(this));
        this.addLayer(new LayerGemPlacement(this));
        this.addLayer(new LayerDiamondGlow(this));
    }
	@Override
	protected ResourceLocation getEntityTexture(EntityEnderPearl entity) {
		return new ResourceLocation("amalgam:textures/entities/ender_pearl/ender_pearl.png");
	}
}
