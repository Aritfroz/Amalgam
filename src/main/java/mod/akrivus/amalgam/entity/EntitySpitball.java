package mod.akrivus.amalgam.entity;

import mod.akrivus.amalgam.gem.EntityNephrite;
import mod.akrivus.kagic.entity.EntityGem;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySpitball extends Entity {
    public EntityLivingBase shootingEntity;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    public EntitySpitball(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.0F);
    }
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
        double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
        if (Double.isNaN(d0)) {
            d0 = 4.0D;
        }
        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }
    public EntitySpitball(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn);
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        this.accelerationX = accelX;
        this.accelerationY = accelY;
        this.accelerationZ = accelZ;
    }
    public EntitySpitball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn);
        this.shootingEntity = shooter;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(shooter.posX, shooter.posY, shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.accelerationX = accelX;
        this.accelerationY = accelY;
        this.accelerationZ = accelZ;
    }
    public void onUpdate() {
        if (this.world.isRemote || (this.shootingEntity == null || !this.shootingEntity.isDead || this.shootingEntity.getDistance(this) > 64) && this.world.isBlockLoaded(new BlockPos(this))) {
            super.onUpdate();
            RayTraceResult raytraceresult = ProjectileHelper.forwardsRaycast(this, true, false, this.shootingEntity);
            if (raytraceresult != null) {
                this.onImpact(raytraceresult);
            }
            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            ProjectileHelper.rotateTowardsMovement(this, 0.1F);
            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= this.getMotionFactor();
            this.motionY *= this.getMotionFactor();
            this.motionZ *= this.getMotionFactor();
            this.setPosition(this.posX, this.posY, this.posZ);
        }
        else {
            this.setDead();
        }
    }
    protected EnumParticleTypes getParticleType() {
        return EnumParticleTypes.SMOKE_NORMAL;
    }
    protected double getMotionFactor() {
        return 0.1D;
    }
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
           	if (result.entityHit != null) {
           		boolean shoot = true;
           		if (this.shootingEntity instanceof EntityNephrite) {
           			EntityNephrite neph = (EntityNephrite) this.shootingEntity;
           			if (result.entityHit instanceof EntityLivingBase) {
            			EntityLivingBase entity = (EntityLivingBase)(result.entityHit);
            			shoot = !neph.isOwnedBy(entity);
            			if (entity instanceof EntityGem) {
            				EntityGem gem = (EntityGem)(entity);
            				shoot = !neph.isOwnedBySamePeople(gem);
            			}
           			}
           		}
           		if (shoot) {
	        		result.entityHit.attackEntityFrom(DamageSource.WITHER, 10.0F);
	        		if (result.entityHit instanceof EntityLivingBase) {
	        			EntityLivingBase entity = (EntityLivingBase)(result.entityHit);
	        			entity.addPotionEffect(new PotionEffect(MobEffects.WITHER, 20));
	            	}
	        		this.makeAreaOfEffectCloud(new BlockPos(result.hitVec));
           		}
            }
            else {
            	for (int x = -2; x < 2; ++x) {
            		for (int y = -3; y < 3; ++y) {
            			for (int z = -2; z < 2; ++z) {
            				BlockPos newp = new BlockPos(new BlockPos(result.hitVec).add(x, y, z));
                    		IBlockState block = this.world.getBlockState(newp);
                    		Material mat = block.getMaterial();
                    		if (this.world.isAirBlock(newp.up())) {
	                    		if (block.getBlock() == Blocks.LAVA) {
	                    			this.world.setBlockState(newp, Blocks.OBSIDIAN.getDefaultState());
	                    		}
	                    		else if (mat == Material.CACTUS || mat == Material.CAKE || mat == Material.CARPET
	                    				|| mat == Material.CLOTH || mat == Material.CORAL || mat == Material.FIRE
	                    				|| mat == Material.GOURD || mat == Material.GRASS || mat == Material.GROUND
	                    				|| mat == Material.LAVA || mat == Material.LEAVES || mat == Material.PLANTS
	                    				|| mat == Material.VINE || mat == Material.WOOD) {
	                    			this.world.setBlockToAir(newp);
	                    		}
                    		}
                    	}
                	}
            	}
            	this.makeAreaOfEffectCloud(new BlockPos(result.hitVec));
            }
			this.setDead();
        }
    }
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setTag("direction", this.newDoubleNBTList(new double[] {this.motionX, this.motionY, this.motionZ}));
        compound.setTag("power", this.newDoubleNBTList(new double[] {this.accelerationX, this.accelerationY, this.accelerationZ}));
    }
    public void readEntityFromNBT(NBTTagCompound compound) {
        if (compound.hasKey("power", 9)) {
            NBTTagList nbttaglist = compound.getTagList("power", 6);
            if (nbttaglist.tagCount() == 3) {
                this.accelerationX = nbttaglist.getDoubleAt(0);
                this.accelerationY = nbttaglist.getDoubleAt(1);
                this.accelerationZ = nbttaglist.getDoubleAt(2);
            }
        }
        if (compound.hasKey("direction", 9) && compound.getTagList("direction", 6).tagCount() == 3) {
            NBTTagList nbttaglist1 = compound.getTagList("direction", 6);
            this.motionX = nbttaglist1.getDoubleAt(0);
            this.motionY = nbttaglist1.getDoubleAt(1);
            this.motionZ = nbttaglist1.getDoubleAt(2);
        }
        else {
            this.setDead();
        }
    }
    public boolean canBeCollidedWith() {
        return true;
    }
    public float getCollisionBorderSize() {
        return 1.0F;
    }
    public float getBrightness(float partialTicks) {
        return 1.0F;
    }
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float partialTicks) {
        return 15728880;
    }
	protected void entityInit() { }
    private void makeAreaOfEffectCloud(BlockPos pos) {
        EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(this.world, pos.getX(), pos.getY(), pos.getZ());
        cloud.setOwner(this.shootingEntity);
        cloud.setRadius(3.0F);
        cloud.setRadiusOnUse(-0.5F);
        cloud.setWaitTime(10);
        cloud.setDuration(100);
        cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());
        cloud.addEffect(new PotionEffect(MobEffects.WITHER));
        cloud.setColor(0x80F67A);
        this.world.spawnEntity(cloud);
    }
}
