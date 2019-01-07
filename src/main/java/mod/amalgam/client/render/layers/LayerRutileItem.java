package mod.amalgam.client.render.layers;

import mod.amalgam.client.render.RenderRutile;
import mod.amalgam.gem.EntityRutile;
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
public class LayerRutileItem implements LayerRenderer<EntityRutile> {
	protected final RenderRutile livingEntityRenderer;
	
	public LayerRutileItem(RenderRutile renderRutile) {
		this.livingEntityRenderer = renderRutile;
	}

	@Override
	public void doRenderLayer(EntityRutile entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		boolean flag = entity.getPrimaryHand() == EnumHandSide.RIGHT;
		ItemStack itemstack = flag ? entity.getHeldItemOffhand() : entity.getHeldItemMainhand();
		ItemStack itemstack1 = flag ? entity.getHeldItemMainhand() : entity.getHeldItemOffhand();
		if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
			GlStateManager.pushMatrix();
			this.renderHeldItem(entity, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
			this.renderHeldItem(entity, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
			GlStateManager.popMatrix();
		}
	}
	
	private void renderHeldItem(EntityRutile entity, ItemStack stack, ItemCameraTransforms.TransformType camera, EnumHandSide handSide) {
		if (entity.isDefective()) {
			if (!stack.isEmpty()) {
				GlStateManager.pushMatrix();
				if (entity.isSneaking()) {
					GlStateManager.translate(0.0F, 0.2F, 0.0F);
				}
				this.setSide(handSide);
				GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
				boolean flag = handSide == EnumHandSide.LEFT;
				GlStateManager.translate((flag ? -1 : 1) / 5.5F, 0.1F, -0.9F);
				Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, stack, camera, flag);
				GlStateManager.popMatrix();
			}
		}
		else {
			if (!stack.isEmpty()) {
				GlStateManager.pushMatrix();
				if (entity.isSneaking()) {
					GlStateManager.translate(0.0F, 0.2F, 0.0F);
				}
				this.setSide(handSide);
				GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
				boolean flag = handSide == EnumHandSide.LEFT;
				GlStateManager.translate((flag ? -1 : 1) / 16.0F, 0.125F, -0.75F);
				Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, stack, camera, flag);
				GlStateManager.popMatrix();
			}
			
		}
	}
	
	protected void setSide(EnumHandSide side) {
		((ModelBiped) this.livingEntityRenderer.getMainModel()).postRenderArm(0.04F, side);
	}
	
	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}