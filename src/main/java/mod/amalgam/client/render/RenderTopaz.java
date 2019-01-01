package mod.amalgam.client.render;

import mod.amalgam.client.model.ModelTopaz;
import mod.amalgam.client.render.layers.LayerFusionPlacement;
import mod.amalgam.client.render.layers.LayerGemPlacement;
import mod.amalgam.client.render.layers.LayerHair;
import mod.amalgam.client.render.layers.LayerInsignia;
import mod.amalgam.client.render.layers.LayerSkin;
import mod.amalgam.client.render.layers.LayerTopazItem;
import mod.amalgam.client.render.layers.LayerUniform;
import mod.amalgam.client.render.layers.LayerVisor;
import mod.amalgam.gem.EntityTopaz;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderTopaz extends RenderGemBase<EntityTopaz> {
	public RenderTopaz() {
        super(Minecraft.getMinecraft().getRenderManager(), new ModelTopaz(), 0.625F);
        this.addLayer(new LayerTopazItem(this));
        this.addLayer(new LayerSkin(this));
        this.addLayer(new LayerUniform(this));
        this.addLayer(new LayerInsignia(this));
        this.addLayer(new LayerHair(this));
        this.addLayer(new LayerVisor(this));
        this.addLayer(new LayerGemPlacement(this));
        this.addLayer(new LayerFusionPlacement(this));

		LayerBipedArmor topazArmor = new LayerBipedArmor(this) {
			@Override
			protected void initArmor() {
				this.modelLeggings = new ModelTopaz(0.5F, true);
				this.modelArmor = new ModelTopaz(1F, true);
			}
		};
		this.addLayer(topazArmor);
	}
	
	@Override
	protected void preRenderCallback(EntityTopaz gem, float partialTickTime) {
		GlStateManager.scale(gem.getFusionCount(), gem.getFusionCount(), gem.getFusionCount());
		this.shadowSize = 0.625F * gem.getFusionCount();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityTopaz entity) {
		return new ResourceLocation("kagic:textures/entities/topaz/topaz.png");
	}
}
