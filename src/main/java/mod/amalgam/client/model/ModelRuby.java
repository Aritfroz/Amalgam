package mod.amalgam.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelRuby extends ModelBiped {
	public ModelRuby() {
		super(0.0F, 0.0F, 64, 64);
		// Head.
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.addBox(-6F, -4F, -4F, 12, 12, 8);
	    this.bipedHead.setRotationPoint(0F, 0F, 0F);
		
	    // Body.
	    this.bipedBody = new ModelRenderer(this, 16, 20);
	    this.bipedBody.addBox(-4F, 8F, -2F, 8, 8, 4);
	    this.bipedBody.setRotationPoint(0F, 0F, 0F);
	    // Right arm.
	    this.bipedRightArm = new ModelRenderer(this, 40, 32);
	    this.bipedRightArm.addBox(-3F, 0F, -2F, 4, 8, 4);
	    this.bipedRightArm.setRotationPoint(0F, 8F, 0F);
	    // Left arm.
	    this.bipedLeftArm = new ModelRenderer(this, 40, 20);
	    this.bipedLeftArm.addBox(-1F, 0F, -2F, 4, 8, 4);
	    this.bipedLeftArm.setRotationPoint(0F, 8F, 0.0F);
	    // Right leg.
	    this.bipedRightLeg = new ModelRenderer(this, 0, 20);
	    this.bipedRightLeg.addBox(-4F, 4F, -2F, 4, 8, 4);
	    this.bipedRightLeg.setRotationPoint(0F, 0F, 0F);
	    // Left leg.
	    this.bipedLeftLeg = new ModelRenderer(this, 0, 32);
	    this.bipedLeftLeg.addBox(0F, 4F, -2F, 4, 8, 4);
	    this.bipedLeftLeg.setRotationPoint(0F, 0F, 0F);
    }
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		this.bipedHead.render(scale);
		this.bipedBody.render(scale);
		this.bipedRightArm.render(scale);
		this.bipedLeftArm.render(scale);
		this.bipedRightLeg.render(scale);
		this.bipedLeftLeg.render(scale);
	}
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
	    super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entity);
	}
	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwingAmount, float ageInTicks, float partialTickTime) {
		this.rightArmPose = ModelBiped.ArmPose.EMPTY;
		this.leftArmPose = ModelBiped.ArmPose.EMPTY;
		if (entity instanceof EntityGem) {
			ItemStack itemstack = entity.getHeldItem(EnumHand.MAIN_HAND);
			EntityGem gem = (EntityGem) entity;
			if (itemstack != null && itemstack.getItem() == Items.BOW && gem.isSwingingArms()) {
				if (entity.getPrimaryHand() == EnumHandSide.RIGHT) {
					this.rightArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
				}
				else {
					this.leftArmPose = ModelBiped.ArmPose.BOW_AND_ARROW;
				}
			}
			if (gem.getRidingEntity() instanceof AbstractHorse) {
				this.bipedRightLeg.offsetX = 0.1F;
				this.bipedRightLeg.offsetY = 0.33F;
				this.bipedRightLeg.offsetZ = 0.3F;
				this.bipedLeftLeg.offsetX = -0.1F;
				this.bipedLeftLeg.offsetY = 0.33F;
				this.bipedLeftLeg.offsetZ = 0.3F;
			}
			else {
				this.bipedRightLeg.offsetX = 0.0F;
				this.bipedRightLeg.offsetY = 0.0F;
				this.bipedRightLeg.offsetZ = 0.0F;
				this.bipedLeftLeg.offsetX = 0.0F;
				this.bipedLeftLeg.offsetY = 0.0F;
				this.bipedLeftLeg.offsetZ = 0.0F;
			}
		}
		super.setLivingAnimations(entity, limbSwingAmount, ageInTicks, partialTickTime);
	}
}