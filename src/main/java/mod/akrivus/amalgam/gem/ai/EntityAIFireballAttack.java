package mod.akrivus.amalgam.gem.ai;

import mod.akrivus.kagic.entity.EntityGem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class EntityAIFireballAttack extends EntityAIBase {
    private final EntityGem gem;
    private int attackStep;
    private int attackTime;
    
    public EntityAIFireballAttack(EntityGem gem) {
        this.gem = gem;
        this.setMutexBits(3);
    }
    public boolean shouldExecute() {
        EntityLivingBase entitylivingbase = this.gem.getAttackTarget();
        return entitylivingbase != null && entitylivingbase.isEntityAlive();
    }
    public void startExecuting() {
        this.attackStep = 0;
    }
    public void updateTask() {
        EntityLivingBase entity = this.gem.getAttackTarget();
        double distance = this.gem.getDistanceSq(entity);
        if (distance < 4.0D) {
            if (this.attackTime <= 0) {
            	this.gem.attackEntityAsMob(entity);
                this.attackTime = 20;
            }
            this.gem.getMoveHelper().setMoveTo(entity.posX, entity.posY, entity.posZ, 1.0D);
        }
        else if (distance < 256) {
            double dX = entity.posX - this.gem.posX;
            double dY = entity.getEntityBoundingBox().minY + (double)(entity.height / 2.0F) - (this.gem.posY + (double)(this.gem.height / 2.0F));
            double dZ = entity.posZ - this.gem.posZ;
            if (this.attackTime <= 0) {
                ++this.attackStep;
                if (this.attackStep == 1) {
                    this.attackTime = 60;
                }
                else if (this.attackStep <= 4) {
                    this.attackTime = 6;
                }
                else {
                    this.attackTime = 100;
                    this.attackStep = 0;
                }
                if (this.attackStep > 1) {
                    float dS = MathHelper.sqrt(MathHelper.sqrt(distance)) * 0.5F;
                    if (this.gem.isDefective()) {
	                    this.gem.createFireworks();
                    }
                    else {
	                    this.gem.world.playSound(null, this.gem.getPosition(), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
	                    for (int i = 0; i < 1; ++i) {
	                    	if (this.gem.isFusion()) {
	                    		EntityLargeFireball fireball = new EntityLargeFireball(this.gem.world, this.gem, dX + this.gem.getRNG().nextGaussian() * dS, dY, dZ + this.gem.getRNG().nextGaussian() * dS);
	                    		fireball.posY = this.gem.posY + (double)(this.gem.height / 2.0F) + 0.5D;
	                    		fireball.explosionPower = this.gem.getFusionCount();
		                        this.gem.world.spawnEntity(fireball);
	                    	}
	                    	else {
		                    	EntitySmallFireball fireball = new EntitySmallFireball(this.gem.world, this.gem, dX + this.gem.getRNG().nextGaussian() * dS, dY, dZ + this.gem.getRNG().nextGaussian() * dS);
		                        fireball.posY = this.gem.posY + (double)(this.gem.height / 2.0F) + 0.5D;
		                        this.gem.world.spawnEntity(fireball);
	                    	}
	                    }
                    }
                }
            }
            this.gem.getLookHelper().setLookPositionWithEntity(entity, 10.0F, 10.0F);
        }
        else {
            this.gem.getNavigator().clearPath();
            this.gem.getMoveHelper().setMoveTo(entity.posX, entity.posY, entity.posZ, 1.0D);
        }
        --this.attackTime;
        super.updateTask();
    }
}
