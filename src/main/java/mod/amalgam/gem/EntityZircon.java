package mod.amalgam.gem;

import java.util.ArrayList;
import java.util.Map;

import com.google.common.base.Predicate;

import mod.amalgam.entity.EntityGem;
import mod.amalgam.init.AmItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class EntityZircon extends EntityGem implements INpc {
	public static final ArrayList<ResourceLocation> HAIRSTYLES = new ArrayList<ResourceLocation>();
	static {
		
	}
	public EntityZircon(World world) {
		super(world);
		this.setSize(0.6F, 1.9F);
		this.visorChanceReciprocal = 1;
		this.seePastDoors();
		
		//Define valid gem cuts and placements
		this.setCutPlacement(GemCuts.PILLOW, GemPlacements.BACK_OF_HEAD);
		this.setCutPlacement(GemCuts.PILLOW, GemPlacements.FOREHEAD);
		this.setCutPlacement(GemCuts.PILLOW, GemPlacements.RIGHT_EYE);
		this.setCutPlacement(GemCuts.PILLOW, GemPlacements.BACK);
		this.setCutPlacement(GemCuts.PILLOW, GemPlacements.CHEST);
		this.setCutPlacement(GemCuts.PILLOW, GemPlacements.BELLY);

		// Apply entity AI.
		this.stayAI = new EntityAIStay(this);
		this.tasks.addTask(1, new EntityAIAvoidEntity<EntityCreeper>(this, EntityCreeper.class, new Predicate<EntityCreeper>() {
			@Override
			public boolean apply(EntityCreeper input) {
				return input.getCreeperState() == 1;
			}
		}, 6.0F, 1.0D, 1.2D));
		this.tasks.addTask(1, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(1, new EntityAICommandGems(this, 0.6D));
		this.tasks.addTask(3, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
		this.tasks.addTask(6, new EntityAIStandGuard(this, 0.6D));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		
		// Apply entity attributes.
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.droppedGemItem = AmItems.ZIRCON_GEM;
		this.droppedCrackedGemItem = AmItems.CRACKED_ZIRCON_GEM;
	}

	@Override
	protected int generateGemColor() {
		switch (this.getInsigniaColor()) {
    	case 0:
    		return 0xFFFFFF;
    	case 1:
    		return 0xCB7226;
    	case 2:
    		return 0xAE48D4;
    	case 3:
    		return 0x215493;
    	case 4:
    		return 0xFEFE4C;
    	case 5:
    		return 0x469300;
    	case 6:
    		return 0xE8759B;
    	case 7:
    		return 0x939393;
    	case 8:
    		return 0x8F8F8F;
    	case 9:
    		return 0x6699B3;
    	case 10:
    		return 0x7B3BAE;
    	case 11:
    		return 0x3B54BA;
    	case 12:
    		return 0x4E341B;
    	case 13:
    		return 0x4C6519;
    	case 14:
    		return 0x963030;
    	case 15:
    		return 0x333333;
    	}
		return 0x074464;
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		int color = this.rand.nextInt(16);
		this.itemDataToGemData(color);
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	@Override
	public void itemDataToGemData(int data) {
		this.setInsigniaColor(data);
		this.nativeColor = this.getInsigniaColor();
		this.setUniformColor(data);
		this.setSkinColor(this.generateSkinColor());
		this.setGemColor(this.generateGemColor());
		if (data == 14) {
			this.setCustomNameTag(new TextComponentTranslation("entity.kagic.zircon_14.name").getUnformattedComponentText());
		} else {
			this.setCustomNameTag(new TextComponentTranslation("entity.kagic.zircon.name").getUnformattedComponentText());
		}
	}
	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityZircon.HAIRSTYLES.size());
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.ZIRCON_HURT;
	}

	@Override
	protected SoundEvent getObeySound() {
		return ModSounds.ZIRCON_OBEY;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.ZIRCON_DEATH;
	}

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		if (!this.world.isRemote && hand == EnumHand.MAIN_HAND) {
			if (this.isTamed() && this.isOwnedBy(player)) {
				ItemStack playerStack = player.getHeldItemMainhand();
				if (playerStack.getItem() instanceof ItemEnchantedBook) {
					this.entityDropItem(this.getHeldItemMainhand(), 0.5F);
					this.setHeldItem(EnumHand.MAIN_HAND, playerStack.copy());
					if (!player.isCreative()) {
						playerStack.shrink(1);
					}
				}
				else if (playerStack.isEmpty()) {
					this.entityDropItem(this.getHeldItemMainhand(), 0.5F);
					this.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
				}
				else if (!(playerStack.getItem() instanceof ItemGemStaff) && !(playerStack.getItem() instanceof ItemGem) && this.getHeldItemMainhand().getItem() instanceof ItemEnchantedBook) {
					ItemStack enchantmentResult = this.getEnchantedItem(playerStack.copy());
					if (playerStack.getCount() == 1) {
						player.setHeldItem(EnumHand.MAIN_HAND, enchantmentResult);
					}
					else {
						if (!player.addItemStackToInventory(enchantmentResult)) {
							this.entityDropItem(enchantmentResult, 0.5F);
						}
						playerStack.shrink(1);
					}
				}
			}
		}
		return super.processInteract(player, hand);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}
	
	public String getSpecialSkin() {
		return "0";
	}
	
	private ItemStack getEnchantedItem(ItemStack playerStack) {
		ItemStack holdingStack = this.getHeldItemMainhand();
		if (!(holdingStack.getItem() instanceof ItemEnchantedBook)) {
			return playerStack;
		}
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(holdingStack);
		if (enchantments.isEmpty()) {
			// I'm not sure if it can ever be the case that an enchanted book has no
			// enchantments, but better safe than sorry
			return playerStack;
		}
		Map<Enchantment, Integer> existingEnchantments = EnchantmentHelper.getEnchantments(playerStack);
		Enchantment enchantment = KAGICEnchantmentUtils.getFirstNonconflicting(enchantments, existingEnchantments);
		if (enchantment == null) {
			return playerStack;
		}
		int level = enchantments.get(enchantment);
		if (level <= 0) {
			return playerStack;
		}
		if (existingEnchantments.containsKey(enchantment)) {
			existingEnchantments.put(enchantment, existingEnchantments.get(enchantment) + 1);
		}
		else {
			existingEnchantments.put(enchantment, 1);
		}
		if (playerStack.getCount() > 1) {
			playerStack.setCount(1);
		}
		EnchantmentHelper.setEnchantments(existingEnchantments, playerStack);
		this.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, this.rand.nextFloat() * 0.1F + 0.9F);
		level -= 1;
		if (level == 0) {
			enchantments.remove(enchantment);
		}
		else {
			enchantments.put(enchantment, level);
		}
		if (!enchantments.isEmpty()) {
			ItemStack newBookStack = new ItemStack(Items.ENCHANTED_BOOK);
			EnchantmentHelper.setEnchantments(enchantments, newBookStack);
			this.setHeldItem(EnumHand.MAIN_HAND, newBookStack);
		}
		else {
			this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOOK));
		}
		return playerStack;
	}
	@Override
	protected int generateSkinColor() {
		int colorIndex = this.getInsigniaColor();
		EnumDyeColor color = EnumDyeColor.values()[colorIndex];
		int colorValue = 0;
		try {
			colorValue = ReflectionHelper.getPrivateValue(EnumDyeColor.class, color, "colorValue", "field_193351_w", "w");
		}
		catch (Exception e) {}
		return colorValue;
	}
}
