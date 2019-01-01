package mod.amalgam.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelInjector extends ModelBase {
    public ModelRenderer base1;
    public ModelRenderer girdle;
    public ModelRenderer base2;
    public ModelRenderer body;
    public ModelRenderer drillBit1;
    public ModelRenderer miniDrill;
    public ModelRenderer drillBit2;
    public ModelRenderer drillBit3;
    public ModelRenderer neck;
    public ModelRenderer incubator;
    public ModelRenderer incubatorCap;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer foot1;
    public ModelRenderer foot2;
    public ModelRenderer foot3;
    public ModelRenderer foot4;
    public ModelInjector() {
        this.textureWidth = 96; this.textureHeight = 96;
        this.foot1 = new ModelRenderer(this, 0, 68);
        this.foot1.setRotationPoint(0.0F, -13.0F, 2.0F);
        this.foot1.addBox(-1.5F, 0.0F, 0.0F, 3, 20, 2, 0.0F);
        this.foot1.rotateAngleX = -0.8726646259971648F;
        this.foot2 = new ModelRenderer(this, 0, 68);
        this.foot2.setRotationPoint(0.0F, -13.0F, 2.0F);
        this.foot2.addBox(-1.5F, 0.0F, 0.0F, 3, 20, 2, 0.0F);
        this.foot2.rotateAngleX = -0.8726646259971648F;
        this.foot3 = new ModelRenderer(this, 0, 68);
        this.foot3.setRotationPoint(0.0F, -13.0F, 2.0F);
        this.foot3.addBox(-1.5F, 0.0F, 0.0F, 3, 20, 2, 0.0F);
        this.foot3.rotateAngleX = -0.8726646259971648F;
        this.foot4 = new ModelRenderer(this, 0, 68);
        this.foot4.setRotationPoint(0.0F, -13.0F, 2.0F);
        this.foot4.addBox(-1.5F, 0.0F, 0.0F, 3, 20, 2, 0.0F);
        this.foot4.rotateAngleX = -0.8726646259971648F;
        this.leg1 = new ModelRenderer(this, 0, 42);
        this.leg1.setRotationPoint(0.0F, 0.0F, -9.0F);
        this.leg1.addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2, 0.0F);
        this.leg1.rotateAngleX = 0.7853981633974483F;
        this.leg2 = new ModelRenderer(this, 0, 42);
        this.leg2.setRotationPoint(0.0F, 0.0F, 9.0F);
        this.leg2.addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2, 0.0F);
        this.leg2.rotateAngleX = 0.7853981633974483F;
        this.leg2.rotateAngleY = 3.141592653589793F;
        this.leg3 = new ModelRenderer(this, 0, 42);
        this.leg3.setRotationPoint(-9.0F, 0.0F, 0.0F);
        this.leg3.addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2, 0.0F);
        this.leg3.rotateAngleX = 0.7853981633974483F;
        this.leg3.rotateAngleY = 1.5707963267948966F;
        this.leg4 = new ModelRenderer(this, 0, 42);
        this.leg4.setRotationPoint(9.0F, 0.0F, 0.0F);
        this.leg4.addBox(-1.0F, -10.0F, -1.0F, 2, 10, 2, 0.0F);
        this.leg4.rotateAngleX = 0.7853981633974483F;
        this.leg4.rotateAngleY = -1.5707963267948966F;
        this.neck = new ModelRenderer(this, 0, 32);
        this.neck.setRotationPoint(0.0F, -35.0F, 0.0F);
        this.neck.addBox(-3.5F, -3.0F, -3.5F, 7, 3, 7, 0.0F);
        this.neck.rotateAngleY = 0.7853981633974483F;
        this.incubator = new ModelRenderer(this, 0, 42);
        this.incubator.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.incubator.addBox(-6.0F, -14.0F, -6.0F, 12, 14, 12, 0.0F);
        this.incubatorCap = new ModelRenderer(this, 38, 58);
        this.incubatorCap.setRotationPoint(0.0F, -14.0F, 0.0F);
        this.incubatorCap.addBox(-5.0F, -5.0F, -5.0F, 10, 5, 10, 0.0F);
        this.base1 = new ModelRenderer(this, 0, 0);
        this.base1.setRotationPoint(0.0F, 10.0F, 0.0F);
        this.base1.addBox(-8.0F, 0.0F, -8.0F, 16, 3, 16, 0.0F);
        this.base2 = new ModelRenderer(this, 48, 0);
        this.base2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.base2.addBox(-5.0F, 0.0F, -5.0F, 10, 3, 10, 0.0F);
        this.drillBit1 = new ModelRenderer(this, 0, 19);
        this.drillBit1.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.drillBit1.addBox(-3.0F, 0.0F, -3.0F, 6, 3, 6, 0.0F);
        this.drillBit1.rotateAngleY = 0.7853981633974483F;
        this.drillBit2 = new ModelRenderer(this, 78, 0);
        this.drillBit2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.drillBit2.addBox(-2.0F, 0.0F, -2.0F, 4, 3, 4, 0.0F);
        this.drillBit3 = new ModelRenderer(this, 48, 0);
        this.drillBit3.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.drillBit3.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2, 0.0F);
        this.miniDrill = new ModelRenderer(this, 15, 19);
        this.miniDrill.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.miniDrill.addBox(-4.5F, 0.0F, -4.5F, 9, 4, 9, 0.0F);
        this.body = new ModelRenderer(this, 54, 13);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-5.0F, -35.0F, -5.0F, 10, 35, 10, 0.0F);
        this.girdle = new ModelRenderer(this, 0, 0);
        this.girdle.setRotationPoint(0.0F, 10.6F, 0.0F);
        this.girdle.addBox(-2.0F, 0.0F, -2.0F, 4, 0, 4, 0.0F);
        this.girdle.rotateAngleY = 0.7853981633974483F;
        this.leg1.addChild(this.foot1);
        this.leg2.addChild(this.foot2);
        this.leg3.addChild(this.foot4);
        this.leg4.addChild(this.foot4);
        this.girdle.addChild(this.leg1);
        this.girdle.addChild(this.leg2);
        this.girdle.addChild(this.leg3);
        this.girdle.addChild(this.leg4);
        this.base2.addChild(this.drillBit1);
        this.base2.addChild(this.miniDrill);
        this.drillBit1.addChild(this.drillBit2);
        this.drillBit2.addChild(this.drillBit3);
        this.incubator.addChild(this.incubatorCap);
        this.neck.addChild(this.incubator);
        this.base1.addChild(this.base2);
        this.base1.addChild(this.body);
        this.body.addChild(this.neck);
    }
    public void resetRotationAngles() {
        this.leg1.rotateAngleX = 0.7853981633974483F;
        this.leg1.rotateAngleY = 0;
        this.leg1.rotateAngleZ = 0;
        this.leg2.rotateAngleX = 0.7853981633974483F;
        this.leg2.rotateAngleY = 3.141592653589793F;
        this.leg2.rotateAngleZ = 0;
        this.leg3.rotateAngleX = 0.7853981633974483F;
        this.leg3.rotateAngleY = 1.5707963267948966F;
        this.leg3.rotateAngleZ = 0;
        this.leg4.rotateAngleX = 0.7853981633974483F;
        this.leg4.rotateAngleY =-1.5707963267948966F;
        this.leg4.rotateAngleZ = 0;
    }
    @Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
    	this.resetRotationAngles();
    	this.leg1.rotateAngleY +=  Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.14F) * 0.8F) * limbSwingAmount;
    	this.leg2.rotateAngleY += -Math.abs(MathHelper.sin(limbSwing * 0.6662F + 3.14F) * 0.8F) * limbSwingAmount;
    	this.leg3.rotateAngleY += -Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.8F) * limbSwingAmount;
    	this.leg4.rotateAngleY +=  Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.8F) * limbSwingAmount;
       	this.leg1.rotateAngleZ +=  MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.14F) * 0.8F * limbSwingAmount;
    	this.leg2.rotateAngleZ += -MathHelper.cos(limbSwing * 0.6662F * 2.0F + 3.14F) * 0.8F * limbSwingAmount;
    	this.leg3.rotateAngleZ +=  MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.8F * limbSwingAmount;
    	this.leg4.rotateAngleZ += -MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.8F * limbSwingAmount;
    }
    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headYaw, float scale) {
    	this.base1.render(scale);
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.girdle.offsetX, this.girdle.offsetY, this.girdle.offsetZ);
        GlStateManager.translate(this.girdle.rotationPointX * scale, this.girdle.rotationPointY * scale, this.girdle.rotationPointZ * scale);
        GlStateManager.scale(1.0D, 1.5D, 1.0D);
        GlStateManager.translate(-this.girdle.offsetX, -this.girdle.offsetY, -this.girdle.offsetZ);
        GlStateManager.translate(-this.girdle.rotationPointX * scale, -this.girdle.rotationPointY * scale, -this.girdle.rotationPointZ * scale);
        this.girdle.render(scale);
        GlStateManager.popMatrix();
    }
}