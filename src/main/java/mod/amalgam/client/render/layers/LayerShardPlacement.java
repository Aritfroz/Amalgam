package mod.amalgam.client.render.layers;

import mod.akrivus.kagic.entity.EntityGem;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;

public class LayerShardPlacement extends LayerGemPlacement {

	public LayerShardPlacement(RenderLivingBase<?> gemRenderer) {
		super(gemRenderer);
	}

	@Override
	public ResourceLocation getTexture(EntityGem gem) {
		ResourceLocation loc = EntityList.getKey(gem);
		return new ResourceLocation(loc.getResourceDomain() + ":textures/entities/shardfusions/" + this.getName(gem) + "/gems/" + gem.getGemPlacement().id + "_" + gem.getGemCut().id + ".png");
	}
}
