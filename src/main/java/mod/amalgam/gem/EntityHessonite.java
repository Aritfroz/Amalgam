package mod.amalgam.gem;

import java.util.ArrayList;

import mod.akrivus.kagic.init.ModSounds;
import mod.amalgam.entity.EntityAmalgamGem;
import mod.heimrarnadalr.kagic.util.Colors;
import mod.heimrarnadalr.kagic.util.GemPlayerLoot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityHessonite extends EntityAmalgamGem implements IAnimals {
	public static final ArrayList<ResourceLocation> HAIRSTYLES = new ArrayList<ResourceLocation>();
	static {
		
	}
	private static final int SKIN_COLOR_BEGIN = 0xF0A100;
	private static final int SKIN_COLOR_END = 0xF0A100;
	private static final int HAIR_COLOR_BEGIN = 0xF8F299;
	private static final int HAIR_COLOR_END = 0xF8F299;
	public EntityHessonite(World world) {
		super(world);
		this.nativeColor = 14;
		this.setSize(0.9F, 2.3F);
		this.isSoldier = true;
		this.visorChanceReciprocal = 1;
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	}
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source instanceof EntityDamageSourceIndirect && this.teleportToEntity(source.getTrueSource())) {
			return true;
		}
		return super.attackEntityFrom(source, amount);
	}
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		boolean attacked = super.attackEntityAsMob(entity);
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase target = (EntityLivingBase) entity;
			if (attacked && this.rand.nextFloat() < this.getDisarmChance()) {
				GemPlayerLoot.dropEntityMainHand(target);
			}
		}
		return attacked;
	}
	private float getDisarmChance() {
		return this.isPrimary() ? 1.0F : this.isDefective() ? 0.0F : 0.5F;
	}
	private boolean teleportToEntity(Entity target) {
		Vec3d vec3d = new Vec3d(this.posX - target.posX, this.getEntityBoundingBox().minY + this.height / 2.0F - target.posY + target.getEyeHeight(), this.posZ - target.posZ);
		if (vec3d.lengthVector() < 4F) {
			return false;
		}
		double dX = target.posX + (this.rand.nextDouble() - 0.5D) * 2.0D;
		double dY = target.posY + 1;
		double dZ = target.posZ + (this.rand.nextDouble() - 0.5D) * 2.0D;
		return this.teleportTo(dX, dY, dZ);
	}
	private boolean teleportTo(double x, double y, double z) {
		boolean success = this.attemptTeleport(x, y, z);
		if (success) {
			this.world.playSound(null, this.prevPosX, this.prevPosY, this.prevPosZ, ModSounds.HESSONITE_TELEPORT_START, this.getSoundCategory(), 1.0F, 1.0F);
			this.playSound(ModSounds.HESSONITE_TELEPORT_END, 1.0F, 1.0F);
		}
		return success;
	}
	@Override
	protected boolean canEquipItem(ItemStack stack) {
		Item weapon = stack.getItem();
		if (weapon instanceof ItemSword || weapon instanceof ItemTool || weapon instanceof ItemBow) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public boolean canPickUpLoot() {
		return true;
	}
	@Override
	public void onItemPickup(Entity item, int quantity) {
		this.setAttackAI();
	}
	@Override
	protected SoundEvent getAmbientSound() {
		if (this.rand.nextInt(3) == 0) {
			return ModSounds.HESSONITE_LIVING;
		}
		else {
			return null;
		}
	}
	@Override
	protected int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntityHessonite.SKIN_COLOR_BEGIN);
		skinColors.add(EntityHessonite.SKIN_COLOR_END);
		return Colors.arbiLerp(skinColors);
	}
	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityHessonite.HAIRSTYLES.size());
	}
	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		hairColors.add(EntityHessonite.HAIR_COLOR_BEGIN);
		hairColors.add(EntityHessonite.HAIR_COLOR_END);
		return Colors.arbiLerp(hairColors);
	}
	@Override
	public boolean hasCape() {
		return true;
	}
}