package mod.amalgam.client.render.layers;

import mod.amalgam.client.render.RenderAmalgamGem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerOpalItem implements LayerRenderer<EntityGem> {
	protected final RenderAmalgamGem<?> livingEntityRenderer;
	
	public LayerOpalItem(RenderAmalgamGem<?> livingEntityRenderer) {
		this.livingEntityRenderer = livingEntityRenderer;
	}
	
	@Override
	public void doRenderLayer(EntityGem entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		boolean righty = entity.getPrimaryHand() == EnumHandSide.RIGHT;
		ItemStack mainItem = righty ? entity.getHeldItemMainhand() : entity.getHeldItemOffhand();
		ItemStack secondItem = righty ? entity.getHeldItemOffhand() : entity.getHeldItemMainhand();
		
		if (!secondItem.isEmpty() || !mainItem.isEmpty()) {
			GlStateManager.pushMatrix();
			this.renderHeldItem(entity, mainItem, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
			this.renderHeldItem(entity, secondItem, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
			GlStateManager.popMatrix();
		}
	}
	
	private void renderHeldItem(EntityGem entity, ItemStack stack, ItemCameraTransforms.TransformType camera, EnumHandSide handSide) {
		if (!stack.isEmpty()) {
			GlStateManager.pushMatrix();
			if (entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			this.setSide(handSide);
			GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			boolean lefty = handSide == EnumHandSide.LEFT;
			GlStateManager.translate((lefty ? -1 : 1) / 3F, 0.125F, -0.45F);
			Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, stack, camera, lefty);
			GlStateManager.popMatrix();
		}
	}
	
	protected void setSide(EnumHandSide side) {
		((ModelBiped) this.livingEntityRenderer.getMainModel()).postRenderArm(0.0625F, side);
	}
	
	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}
