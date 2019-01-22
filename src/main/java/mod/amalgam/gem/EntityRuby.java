package mod.amalgam.gem;

import java.util.ArrayList;

import mod.amalgam.entity.EntityGem;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityRuby extends EntityGem implements IAnimals {
	public static final ArrayList<ResourceLocation> HAIRSTYLES = new ArrayList<ResourceLocation>();
	static {
		
	}
	private static final DataParameter<Integer> ANGER = EntityDataManager.<Integer>createKey(EntityRuby.class, DataSerializers.VARINT);
	private int angerTicks = 0;
	private static final int SKIN_COLOR_BEGIN = 0xE0316F; 
	private static final int SKIN_COLOR_MID = 0xE52C5C; 
	private static final int SKIN_COLOR_END = 0xED294C; 
	private static final int HAIR_COLOR_BEGIN = 0x3B0015;
	private static final int HAIR_COLOR_END = 0x3A0015;
	
	public EntityRuby(World world) {
		super(world);
		this.nativeColor = 14;
		this.setSize(0.7F, 1.2F);
		this.isImmuneToFire = true;
		this.isSoldier = true;
        this.dataManager.register(ANGER, 0);
	}
    @Override
	protected int generateGemColor() {
    	return 0xEE2331;
    }
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("anger", this.getAnger());
        super.writeEntityToNBT(compound);
    }
    @Override
	public void readEntityFromNBT(NBTTagCompound compound) {
        this.setAnger(compound.getInteger("anger"));
        super.readEntityFromNBT(compound);
    }
	@Override
	public void onLivingUpdate() {
		if (this.getAnger() > 2) {
			if (!this.isInWater()) {
				for (int k = 0; k < 8; ++k) {
	                this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX - 0.5 + Math.random(), this.posY + Math.random(), this.posZ - 0.5 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
	            }
			}
		}
		else if (this.getAnger() > 3) {
			if (this.isInWater()) {
				this.world.setBlockToAir(this.getPosition());
                this.world.playSound(null, this.getPosition(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.8F);
                for (int k = 0; k < 8; ++k) {
                    this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX - 0.5 + Math.random(), this.posY + Math.random(), this.posZ - 0.5 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
                }
			}
			else if (this.onGround) {
				this.world.setBlockState(this.getPosition(), Blocks.FIRE.getDefaultState());
			}
		}
		else if (this.getAnger() > 4) {
			this.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 60));
			this.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60));
		}
		else if (this.getAnger() > 6) {
			this.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 60, 3));
		}
		if (this.world.getBlockState(this.getPosition()).getMaterial() == Material.LAVA) {
			this.heal(1.0F);
		}
		this.angerTicks += 1;
		if (this.angerTicks > 200 && this.getAnger() > 0) {
			this.setAnger(this.getAnger() - 1);
			this.angerTicks = 0;
		}
		if (this.ticksExisted % 10 + this.rand.nextInt(50) == 0) {
			this.setSpecial(this.rand.nextInt(6));
		}
		super.onLivingUpdate();
	}
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (!this.world.isRemote) {
			if (hand == EnumHand.MAIN_HAND) {
				if (this.isTamed() && this.isOwnedBy(player)) {
					ItemStack stack = player.getHeldItemMainhand();
					Item item = stack.getItem();
					if (stack.getItem() == Items.BUCKET) {
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
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!this.world.isRemote) {
			if (source.getTrueSource() instanceof EntityLivingBase && !this.isOwner((EntityLivingBase) source.getTrueSource())) {
				if (source.isMagicDamage()) {
					this.setAnger(this.getAnger() + 4 + (int)(amount / 2));
				}
				else {
					this.setAnger(this.getAnger() + 1 + (int)(amount / 4));
				}
			}
			else if (source.isProjectile()) {
				this.setAnger(this.getAnger() + 2 + (int)(amount / 3));
			}
			if (this.isDefective() && this.ticksExisted < 20) {
				this.entityDropItem(this.getHeldItem(EnumHand.MAIN_HAND), 0.0F);
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
			}
		}
		return super.attackEntityFrom(source, amount);
	}
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		int anger = this.getAnger();
		if (anger > 7) {
			anger = 7;
		}
		if (this.rand.nextInt(8 - anger) == 0) {
			entity.setFire(anger * 2 + 4);
		}
		return super.attackEntityAsMob(entity);
	}
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		EntityTippedArrow arrow = new EntityTippedArrow(this.world, this);
		double distanceFromTargetX = target.posX - this.posX;
        double distanceFromTargetY = target.getEntityBoundingBox().minY + target.height / 3.0F - arrow.posY;
        double distanceFromTargetZ = target.posZ - this.posZ;
        double distanceFromTargetS = MathHelper.sqrt(distanceFromTargetX * distanceFromTargetX + distanceFromTargetY * distanceFromTargetY);
        arrow.shoot(distanceFromTargetX, distanceFromTargetY + distanceFromTargetS * 0.20000000298023224D, distanceFromTargetZ, 1.6F, 2.0F);
        arrow.setDamage(distanceFactor * 2.0F + this.rand.nextGaussian() * 0.25D + this.world.getDifficulty().getDifficultyId() * 0.11F);
        
        // Enchantments.
        int power = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.POWER, this);
        int punch = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.PUNCH, this);
        boolean flame = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FLAME, this) > 0;
        int anger = this.getAnger();
        
        if (anger > 7) {
			anger = 7;
		}
        if (power > 0) {
            arrow.setDamage(arrow.getDamage() + power * 0.5D + 0.5D);
        }
        if (punch > 0) {
            arrow.setKnockbackStrength(punch);
        }
        if (flame) {
            arrow.setFire(100 + (anger * 100 + 40));
        }
        else if (this.rand.nextInt(8 - anger) == 1) {
			arrow.setFire(anger * 100 + 40);
		}
        ItemStack itemstack = this.getHeldItem(EnumHand.OFF_HAND);
        if (itemstack.getItem() == Items.TIPPED_ARROW) {
            arrow.setPotionEffect(itemstack);
        }
        this.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(arrow);
    }
	public int getAnger() {
		return this.dataManager.get(ANGER);
	}
	public void setAnger(int anger) {
		this.dataManager.set(ANGER, anger);
	}
	@Override
	protected int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntityRuby.SKIN_COLOR_BEGIN);
		skinColors.add(EntityRuby.SKIN_COLOR_MID);
		skinColors.add(EntityRuby.SKIN_COLOR_END);
		return Colors.arbiLerp(skinColors);
	}
	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityRuby.HAIRSTYLES.size());
	}
	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		hairColors.add(EntityRuby.HAIR_COLOR_BEGIN);
		hairColors.add(EntityRuby.HAIR_COLOR_END);
		return Colors.arbiLerp(hairColors);
	}
	@Override
	public boolean hasInsigniaVariant(GemPlacements placement) {
		switch(placement) {
		case BELLY:
			return true;
		case CHEST:
			return true;
		default:
			return false;
		}
	}
	@Override
	public boolean hasUniformVariant(GemPlacements placement) {
		switch(placement) {
		case BELLY:
			return true;
		case CHEST:
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
}
