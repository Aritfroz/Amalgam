package mod.amalgam.client.render.layers;

import mod.amalgam.gem.EntityNacre;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerNacreItem implements LayerRenderer<EntityNacre> {
	protected final RenderGemBase<EntityNacre> renderer;
	public LayerNacreItem(RenderGemBase<EntityNacre> renderer) {
		this.renderer = renderer;
	}
	@Override
	public void doRenderLayer(EntityNacre nacre, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		boolean flag = nacre.getPrimaryHand() == EnumHandSide.RIGHT;
		ItemStack itemstack = flag ? nacre.getHeldItemOffhand() : nacre.getHeldItemMainhand();
		ItemStack itemstack1 = flag ? nacre.getHeldItemMainhand() : nacre.getHeldItemOffhand();
		if (!itemstack.isEmpty() || !itemstack1.isEmpty()) {
			GlStateManager.pushMatrix();
			this.renderHeldItem(nacre, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
			this.renderHeldItem(nacre, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
			GlStateManager.popMatrix();
		}
	}
	private void renderHeldItem(EntityLivingBase entity, ItemStack stack, ItemCameraTransforms.TransformType camera, EnumHandSide handSide) {
		if (!stack.isEmpty()) {
			GlStateManager.pushMatrix();
			if (entity.isSneaking()) {
				GlStateManager.translate(0.0F, 0.2F, 0.0F);
			}
			this.setSide(handSide);
			GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			boolean flag = handSide == EnumHandSide.LEFT;
			GlStateManager.translate((flag ? -1 : 1) / 16.0F, 0.125F, -0.7F);
			Minecraft.getMinecraft().getItemRenderer().renderItemSide(entity, stack, camera, flag);
			GlStateManager.popMatrix();
		}
	}
	protected void setSide(EnumHandSide side) {
		((ModelBiped)(this.renderer.getMainModel())).postRenderArm(0.04F, side);
	}
	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}