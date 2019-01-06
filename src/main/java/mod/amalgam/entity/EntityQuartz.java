package mod.amalgam.entity;

import mod.akrivus.kagic.entity.ai.EntityAICommandGems;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtByTarget;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtTarget;
import mod.akrivus.kagic.entity.ai.EntityAIFollowDiamond;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityQuartz extends EntityAmalgam {
	private static final DataParameter<Boolean> CHARGED = EntityDataManager.<Boolean>createKey(EntityQuartz.class, DataSerializers.BOOLEAN);
	private boolean chargedByTakingDamageNotDelivering;
	private int ticksCharged = 0;
	private int hitCount = 0;
	public EntityQuartz(World world) {
		super(world);
		this.setSize(0.9F, 2.3F);
		this.isSoldier = true;
		this.stayAI = new EntityAIStay(this);
		this.tasks.addTask(1, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(4, new EntityAICommandGems(this, 0.6D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIDiamondHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIDiamondHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
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
	public boolean hasUniformVariant(GemPlacements placement) {
		return placement == GemPlacements.BELLY;
	}
	public void generateGemPositions(GemCuts cut) {
		this.setCutPlacement(cut, GemPlacements.BACK_OF_HEAD);
		this.setCutPlacement(cut, GemPlacements.FOREHEAD);
		this.setCutPlacement(cut, GemPlacements.LEFT_EYE);
		this.setCutPlacement(cut, GemPlacements.RIGHT_EYE);
		this.setCutPlacement(cut, GemPlacements.LEFT_CHEEK);
		this.setCutPlacement(cut, GemPlacements.RIGHT_CHEEK);
		this.setCutPlacement(cut, GemPlacements.LEFT_SHOULDER);
		this.setCutPlacement(cut, GemPlacements.RIGHT_SHOULDER);
		this.setCutPlacement(cut, GemPlacements.LEFT_HAND);
		this.setCutPlacement(cut, GemPlacements.RIGHT_HAND);
		this.setCutPlacement(cut, GemPlacements.BACK);
		this.setCutPlacement(cut, GemPlacements.CHEST);
		this.setCutPlacement(cut, GemPlacements.BELLY);
		this.setCutPlacement(cut, GemPlacements.LEFT_THIGH);
		this.setCutPlacement(cut, GemPlacements.RIGHT_THIGH);
		this.setCutPlacement(cut, GemPlacements.LEFT_KNEE);
		this.setCutPlacement(cut, GemPlacements.RIGHT_KNEE);
	}
	public void generateVariant() {
		
	}
	public QuartzVariety getVariant() {
		return null;
	}
	public static class QuartzVariety {
		private final int identifier;
		private final String name;
		private final int nativeColor;
		private final int gemColor;
		private final int[] skinColor;
		private int[] bandColor;
		private int[] markColor;
		private int bandIndex;
		private int[] bandRandom;
		public QuartzVariety(int identifier, String name, int nativeColor, int gemColor, int...skinColor) {
			this.identifier = identifier;
			this.name = name;
			this.nativeColor = nativeColor;
			this.gemColor = gemColor;
			this.skinColor = skinColor;
		}
		public QuartzVariety bands(int...bandColor) {
			this.bandColor = bandColor;
			return this;
		}
		public QuartzVariety marks(int...markColor) {
			this.markColor = markColor;
			return this;
		}
		public QuartzVariety type(int bandIndex, int...bandRandom) {
			this.bandIndex = bandIndex;
			if (bandRandom.length == 0) {
				this.bandRandom = new int[] {0};
			}
			else {
				this.bandRandom = bandRandom;
			}
			return this;
		}
		public int getID() {
			return this.identifier;
		}
		public String getName() {
			return this.name;
		}
		public int getNativeColor() {
			return this.nativeColor;
		}
		public int getGemColor() {
			return this.gemColor;
		}
		public int[] getSkinColor() {
			return this.skinColor;
		}
		public int[] getBandColor() {
			return this.bandColor;
		}
		public int[] getMarkColor() {
			return this.markColor;
		}
		public int getBandIndex() {
			return this.bandIndex;
		}
		public int[] getBandRandom() {
			return this.bandRandom;
		}
	}
}
