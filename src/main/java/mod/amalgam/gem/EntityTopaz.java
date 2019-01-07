package mod.amalgam.gem;

import java.util.ArrayList;
import java.util.HashMap;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAICommandGems;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtByTarget;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtTarget;
import mod.akrivus.kagic.entity.ai.EntityAIFollowDiamond;
import mod.akrivus.kagic.entity.ai.EntityAISitStill;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModSounds;
import mod.amalgam.entity.EntityAmalgamGem;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityTopaz extends EntityAmalgamGem implements INpc {
	public static final ArrayList<ResourceLocation> HAIRSTYLES = new ArrayList<ResourceLocation>();
	static {
		
	}
	private static final int SKIN_COLOR = 0xF6E83E; 
	private static final int HAIR_COLOR = 0xFFF564;
	private static final DataParameter<Boolean> HOLDING = EntityDataManager.<Boolean>createKey(EntityAmethyst.class, DataSerializers.BOOLEAN);
	private ArrayList<EntityLivingBase> heldEntities = new ArrayList<EntityLivingBase>();
	public EntityTopaz(World world) {
		super(world);
		this.setSize(0.9F, 2.3F);
		this.isSoldier = true;
		this.dataManager.register(HOLDING, false);
	}
	@Override
	protected int generateGemColor() {
		if (this.getSpecial() == 1) {
			return 0x5D73FF;
		}
		else if (this.getSpecial() == 2) {
			return 0x48DAA8;
		}
    	return 0xFAFF5D;
    }
	public String getColor() {
		switch (this.getSpecial()) {
		case 1:
			return "blue_";
		case 2:
			return "green_";
		default:
			return "";
		}
	}
	public void setColor(int color) {
		this.setSpecial(color);
	}
	@Override
	public void convertGems(int placement) {
    	this.setGemCut(GemCuts.DRUM.id);
    	switch (placement) {
    	case 0:
    		this.setGemPlacement(GemPlacements.LEFT_CHEEK.id);
    		break;
    	case 1:
    		this.setGemPlacement(GemPlacements.RIGHT_CHEEK.id);
    		break;
    	}
    }
	
	/*********************************************************
	 * Methods related to entity loading.                    *
	 *********************************************************/
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }
    @Override
	public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }
    @Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
    	this.setSpecial(this.rand.nextInt(7) == 0 ? 1 : 0);
    	this.itemDataToGemData(this.getSpecial());
		return super.onInitialSpawn(difficulty, livingdata);
    }
    @Override
	public void itemDataToGemData(int data) {
        this.setSpecial(data);
        this.setSkinColor(this.generateSkinColor());
		this.setHairStyle(this.generateHairStyle());
		this.setHairColor(this.generateHairColor());
		this.setGemColor(this.generateGemColor());
		if (this.getSpecial() == 1) {
    		this.nativeColor = 11;
    	}
    	else {
    		this.nativeColor = 14;
    	}
	}
    /*********************************************************
     * Methods related to entity interaction.                *
     *********************************************************/
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (!this.world.isRemote) {
			if (hand == EnumHand.MAIN_HAND) {
				if (this.isTamed() && this.isOwnedBy(player)) {
					ItemStack stack = player.getHeldItemMainhand();
					Item item = stack.getItem();
					if (item instanceof ItemArmor && ((ItemArmor)item).armorType == EntityEquipmentSlot.HEAD || player.isSneaking() && stack.isEmpty()) {
						this.playEquipSound(stack);
						this.entityDropItem(this.getItemStackFromSlot(EntityEquipmentSlot.HEAD), 0.0F);
						this.setItemStackToSlot(EntityEquipmentSlot.HEAD, stack.copy());
						if (!player.isCreative()) {
							stack.shrink(1);
						}
						this.playObeySound();
						return true;
					}
				}
			}
		}
		return super.processInteract(player, hand);
    }
    
    @Override
    public boolean alternateInteract(EntityPlayer player) {
    	super.alternateInteract(player);
    	if (!this.getHeldEntities().isEmpty()) {
    		this.addHeldEntity(null);
    	}
    	else {
    		this.wantsToFuse = true;
    	}
    	return true;
    }
    
    public void whenFused() {
		this.setSize(0.9F * this.getFusionCount(), 2.3F * this.getFusionCount());
    }
    
    public ArrayList<EntityLivingBase> getHeldEntities() {
    	return this.heldEntities;
    }
    public boolean addHeldEntity(EntityLivingBase entity) {
    	if (entity != null) {
	    	if (this.isFusion()) {
		    	this.heldEntities.add(entity);
		    	return true;
	    	}
	    	else if (this.heldEntities.isEmpty()) {
	    		this.dataManager.set(HOLDING, true);
	    		this.heldEntities.add(entity);
	    		return true;
	    	}
	    	this.dataManager.set(HOLDING, false);
	    	return false;
    	}
    	else {
    		this.dataManager.set(HOLDING, false);
    		this.heldEntities.clear();
    		return false;
    	}
    }
    public boolean isHolding() {
    	return this.dataManager.get(HOLDING);
    }
    
    /*********************************************************
	 * Methods related to living.                            *
	 *********************************************************/
	@Override
	public void onLivingUpdate() {
		if (this.isFusion()) {
			this.whenFused();
		}
		if (!this.world.isRemote) {
			for (int i = 0; i < this.heldEntities.size(); ++i) {
				EntityLivingBase entity = this.heldEntities.get(i);
				if (entity != null && entity.isEntityAlive() && entity.getDistanceSq(this) < 16) {
					double[] offset = new double[] {0, this.height, 0};
					if (this.isFusion()) {
						switch (i) {
						case 0:
							offset = new double[] {1, 1, -1};
							break;
						case 1:
							offset = new double[] {-1, 1, -1};
							break;
						case 2:
							offset = new double[] {1, 1, 1};
							break;
						case 3:
							offset = new double[] {-1, 1, 1};
							break;
						case 4:
							offset = new double[] {1, 2, -1};
							break;
						case 5:
							offset = new double[] {-1, 2, -1};
							break;
						case 6:
							offset = new double[] {1, 2, 1};
							break;
						case 7:
							offset = new double[] {-1, 2, 1};
							break;
						case 8:
							offset = new double[] {0, 1, -1};
							break;
						case 9:
							offset = new double[] {0, 1, 1};
							break;
						case 10:
							offset = new double[] {-1, 1, 0};
							break;
						case 11:
							offset = new double[] {1, 1, 0};
							break;
						case 12:
							offset = new double[] {0, 2, -1};
							break;
						case 13:
							offset = new double[] {0, 2, 1};
							break;
						case 14:
							offset = new double[] {-1, 2, 0};
							break;
						case 15:
							offset = new double[] {1, 2, 0};
							break;
						default:
							offset = new double[] {0, 1.5, 0};
							if (entity.ticksExisted % 10 == 0) {
								entity.attackEntityFrom(DamageSource.IN_WALL, 1.0F);
							}
						}
					}
					entity.setPositionAndRotation(this.posX + offset[0], this.posY + offset[1], this.posZ + offset[2], this.rotationYaw, this.rotationPitch);
					entity.motionX = 0;
					entity.motionY = 0;
					entity.motionZ = 0;
				}
				else {
					this.heldEntities.remove(i);
					--i;
				}
			}
			if (this.heldEntities.isEmpty()) {
				this.dataManager.set(HOLDING, false);
			}
			else if (this.isFusion()) {
				this.motionX = 0;
				this.motionZ = 0;
			}
		}
		super.onLivingUpdate();
	}
	@Override
	protected void collideWithEntity(Entity entity) {
		if (this.heldEntities.isEmpty()) {
			super.collideWithEntity(entity);
		}
	}
	public boolean canFuseWith(EntityTopaz other) {
		if (this.getHealth() < 0.0f || other.getHealth() < 0.0f) {
			return false;
		}
		if (this.canFuse() && other.canFuse() && this.getServitude() == other.getServitude() && this.getGemPlacement() != other.getGemPlacement()) {
			if ((this.getServitude() == EntityGem.SERVE_HUMAN && this.getOwnerId().equals(other.getOwnerId())) || this.getServitude() != EntityGem.SERVE_HUMAN) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean canFuse() {
		return super.canFuse() && this.wantsToFuse && !this.isFusion();
	}
	public EntityTopaz fuse(EntityTopaz other) {
		EntityTopaz topaz = new EntityTopaz(this.world);
		NBTTagCompound primeCompound = new NBTTagCompound();
		this.writeToNBT(primeCompound);
		topaz.fusionMembers.add(primeCompound);
		NBTTagCompound otherCompound = new NBTTagCompound();
		other.writeToNBT(otherCompound);
		topaz.fusionMembers.add(otherCompound);
		if (this.getServitude() == EntityGem.SERVE_HUMAN) {
			topaz.setOwnerId(this.getOwnerId());
			topaz.setLeader(this.getOwner());
		}
		topaz.setServitude(this.getServitude());
		topaz.setFusionCount(this.getFusionCount() + other.getFusionCount());
		topaz.generateFusionPlacements();
		topaz.setPosition((this.posX + other.posX) / 2, (this.posY + other.posY) / 2, (this.posZ + other.posZ) / 2);
		topaz.setSpecial(this.getSpecial() == other.getSpecial() ? this.getSpecial() : 2);
		topaz.setSkinColor(topaz.generateSkinColor());
		topaz.setHairStyle(topaz.generateHairStyle());
		topaz.setHairColor(topaz.generateHairColor());
		topaz.setInsigniaColor(this.getInsigniaColor());
		topaz.setUniformColor(this.getUniformColor());
		topaz.setGemColor(topaz.generateGemColor());
		topaz.setHasVisor(this.hasVisor());
		topaz.setAttackTarget(this.getAttackTarget());
		topaz.setRevengeTarget(this.getAttackingEntity());
		
		topaz.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D * topaz.getFusionCount());
		topaz.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D * topaz.getFusionCount());
		topaz.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D / topaz.getFusionCount());
		topaz.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0F);
		topaz.stepHeight = topaz.getFusionCount();
		topaz.setHealth(topaz.getMaxHealth());

    	ItemStack weapon = this.getHeldItem(EnumHand.MAIN_HAND);
    	if (weapon.getItem() == Items.AIR) {
    		weapon = other.getHeldItem(EnumHand.MAIN_HAND);
    	}
    	ItemStack second = this.getHeldItem(EnumHand.OFF_HAND);
    	if (second.getItem() == Items.AIR) {
    		second = other.getHeldItem(EnumHand.OFF_HAND);
    	}
    	topaz.setFusionWeapon(weapon);
    	topaz.setFusionWeapon(second);

    	topaz.setSize(0.9F * topaz.getFusionCount(), 2.3F * topaz.getFusionCount());
		return topaz;
	}
	@Override
	public void unfuse() {
		for (int i = 0; i < this.fusionMembers.size(); ++i) {
			EntityTopaz topaz = new EntityTopaz(this.world);
			topaz.readFromNBT(this.fusionMembers.get(i));
			topaz.setUniqueId(MathHelper.getRandomUUID(this.world.rand));
			topaz.setHealth(topaz.getMaxHealth());
			topaz.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			topaz.setRevengeTarget(null);
			topaz.setAttackTarget(null);
			topaz.wantsToFuse = false;
			this.world.spawnEntity(topaz);
		}
		this.world.removeEntity(this);
	}

	/*********************************************************
	 * Methods related to rendering.                         *
	 *********************************************************/
	@Override
	protected int generateSkinColor() {
		return 0;
	}
	
	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityTopaz.HAIRSTYLES.size());
	}
	
	@Override
	protected int generateHairColor() {
		return 0;
	}

	@Override
	public boolean hasUniformVariant(GemPlacements placement) {
		switch(placement) {
		case BELLY:
			return true;
		default:
			return false;
		}
	}
	@Override
	public boolean hasHairVariant(GemPlacements placement) {
		switch(placement) {
		case FOREHEAD:
			return true;
		default:
			return false;
		}
	}
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		super.attackEntityWithRangedAttack(target, distanceFactor);
	}
}
