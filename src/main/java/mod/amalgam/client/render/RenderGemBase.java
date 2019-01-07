package mod.amalgam.client.render;

import java.util.Iterator;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.init.ModConfigs;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;

public class RenderGemBase<T extends EntityGem> extends RenderBiped<T> {
	public RenderGemBase(RenderManager manager, ModelBiped model, float shadowSize) {
		super(manager, model, shadowSize);
		for (Iterator<LayerRenderer<T>> it = this.layerRenderers.iterator(); it.hasNext();) {
			LayerRenderer layer = it.next();
			if (layer instanceof LayerHeldItem) {
				it.remove();
			}
		}
	}
	@Override
	protected void renderEntityName(T entity, double x, double y, double z, String name, double distanceSq) {
		if (ModConfigs.displayNames) {
			this.renderLivingLabel(entity, name, x, y+0.25, z, 64);
			this.renderLivingLabel(entity, "(" + entity.getSpecificName() + ")", x, y, z, 64);
		}
		else {
			this.renderLivingLabel(entity, name, x, y, z, 64);
		}
    }
}
