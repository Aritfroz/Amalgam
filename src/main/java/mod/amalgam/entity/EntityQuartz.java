package mod.amalgam.entity;

import java.util.ArrayList;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityQuartz extends EntityGem {
	private static final DataParameter<Boolean> CHARGED = EntityDataManager.<Boolean>createKey(EntityQuartz.class, DataSerializers.BOOLEAN);
	public static final ArrayList<ResourceLocation> HAIRSTYLES = new ArrayList<ResourceLocation>();
	static {
		
	}
	public EntityAITarget guardDogAttack = new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 10, true, false, new Predicate<EntityLiving>() {
        @Override
		public boolean apply(EntityLiving input) {
            return input != null && IMob.VISIBLE_MOB_SELECTOR.apply(input);
        }
    });
	public boolean chargedByTakingDamageNotDelivering;
	private int ticksCharged = 0;
	private int hitCount = 0;
	public EntityQuartz(World world) {
		super(world);
		this.setSize(0.9F, 2.3F);
		this.isSoldier = true;
		this.dataManager.register(CHARGED, false);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
	}
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
    	if (this.isPrimary()) {
    		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
    		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(120.0D);
    	}
    	else if (this.isDefective()) {
    		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
    	}
    	else {
    		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
    		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
    	}
        return super.onInitialSpawn(difficulty, livingdata);
    }
    @Override
    public void setAttackAI() {
    	super.setAttackAI();
    	this.targetTasks.removeTask(this.guardDogAttack);
    	if (this.chargedByTakingDamageNotDelivering) {
            this.targetTasks.addTask(4, this.guardDogAttack);
    	}
    }
    @Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (!this.world.isRemote) {
			if (hand == EnumHand.MAIN_HAND) {
				if (this.isTamed() && this.isOwnedBy(player)) {
					ItemStack stack = player.getHeldItemMainhand();
					Item item = stack.getItem();
					if (item instanceof ItemArmor) {
						this.playEquipSound(stack);
						this.setItemStackToSlot(((ItemArmor)item).armorType, stack.copy());
						if (!player.isCreative()) {
							stack.shrink(1);
						}
						this.playObeySound();
						return true;
					}
					else if (stack.getItem() == Items.BUCKET) {
						this.entityDropItem(this.getHeldItemMainhand(), 0.0F);
						this.setHeldItem(EnumHand.MAIN_HAND, stack);
						return true;
					}
					else if (player.isSneaking() && this.getHeldItemMainhand().getItem() instanceof ItemBucket) {
						this.entityDropItem(this.getHeldItemMainhand(), 0.0F);
						this.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
						return true;
					}
				}
			}
		}
		return super.processInteract(player, hand);
    }
    @Override
	public void onLivingUpdate() {
		if (this.ticksCharged < 0) {
			this.setCharged(false);
		}
		else if (!this.isCharged() && this.hitCount > 6) {
			this.setCharged(true);
		}
		else {
			this.ticksCharged -= 1;
		}
		super.onLivingUpdate();
	}
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.chargedByTakingDamageNotDelivering) {
			this.ticksCharged += 20 + this.world.rand.nextInt(20);
			this.hitCount += 1;
		}
		return super.attackEntityFrom(source, amount);
	}
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		if (!this.world.isRemote) {
			this.attackEntityAsMob(target);
		}
		super.attackEntityWithRangedAttack(target, distanceFactor);
	}
	@Override
	public boolean attackEntityAsMob(Entity target) {
		if (!this.chargedByTakingDamageNotDelivering) {
			this.ticksCharged += 20 + this.world.rand.nextInt(20);
			this.hitCount += 1;
		}
		return super.attackEntityAsMob(target);
	}
	@Override
	public boolean hasUniformVariant(GemPlacements placement) {
		return placement == GemPlacements.BELLY;
	}
	@Override
	public boolean hasHairVariant(GemPlacements placement) {
		return placement == GemPlacements.FOREHEAD;
	}
	@Override
	public boolean hasCape() {
		return true;
	}
	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(HAIRSTYLES.size());
	}
	@Override
	@SideOnly(Side.CLIENT)
    public int getBrightnessForRender() {
        return isCharged() ? 0xFFFFFF : super.getBrightnessForRender();
	}
    @Override
	public float getBrightness() {
        return isCharged() ? 1.0F : super.getBrightness();
    }
	public QuartzVariety getVariant() {
		return null;
	}
	public void setCharged(boolean charged) {
		this.dataManager.set(CHARGED, charged);
	}
	public boolean isCharged() {
		return this.dataManager.get(CHARGED);
	}
	public static interface QuartzVariety extends GemVariety {
		@Override
		public int[] getSkinColor();
		@Override
		public int[] getHairColor();
		@Override
		public int[] getGemColor();
		public int[] getBandColor();
		public int[] getBandIndex();
		public int[] getMarkColor();
		public int[] getMarkIndex();
		@Override
		public String getName();
		@Override
		public int getDamage();
	}
}