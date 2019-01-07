package mod.amalgam.gem;

import java.util.ArrayList;
import java.util.Random;

import com.google.common.base.Predicate;

import mod.akrivus.kagic.entity.EntityCorruptedGem;
import mod.akrivus.kagic.entity.EntityFusionGem;
import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAICommandGems;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtByTarget;
import mod.akrivus.kagic.entity.ai.EntityAIDiamondHurtTarget;
import mod.akrivus.kagic.entity.ai.EntityAIFollowDiamond;
import mod.akrivus.kagic.entity.ai.EntityAIScareMobs;
import mod.akrivus.kagic.entity.ai.EntityAISitStill;
import mod.akrivus.kagic.entity.ai.EntityAIStay;
import mod.akrivus.kagic.entity.gem.GemCuts;
import mod.akrivus.kagic.entity.gem.GemPlacements;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.items.ItemGem;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityMelanite extends EntityGem implements IAnimals {
    private static final int SKIN_COLOR_BEGIN = 0x2F2F2F;
    private static final int SKIN_COLOR_END = 0x404040;
    private static final int NUM_HAIRSTYLES = 1;
    private static final int HAIR_COLOR_BEGIN = 0x0F0F0F;
    private static final int HAIR_COLOR_END = 0x1F1F1F;

    public EntityMelanite(World world) {
        super(world);
        this.nativeColor = 15;
        this.setSize(0.9F, 2.3F);
        this.isSoldier = true;
        this.canTalk = true;
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK_OF_HEAD);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.RIGHT_EYE);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BACK);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.CHEST);
        this.setCutPlacement(GemCuts.CABOCHON, GemPlacements.BELLY);
        this.stayAI = new EntityAIStay(this);
        this.tasks.addTask(1, new EntityAIFollowDiamond(this, 1.0D));
        this.tasks.addTask(1, new EntityAICommandGems(this, 0.6D));
        this.tasks.addTask(2, new EntityAISitStill(this, 1.0D));
        this.tasks.addTask(3, new EntityAIScareMobs(this));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityMob.class, 16.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIDiamondHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIDiamondHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityLiving>(this, EntityLiving.class, 10, true, false, new Predicate<EntityLiving>() {
            @Override
			public boolean apply(EntityLiving input) {
                return input != null && IMob.VISIBLE_MOB_SELECTOR.apply(input);
            }
        }));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<EntityGem>(this, EntityGem.class, 10, true, false, new Predicate<EntityGem>() {
            @Override
			public boolean apply(EntityGem input) {
                return input != null && (input.isDefective() || input instanceof EntityFusionGem || input instanceof EntityCorruptedGem);
            }
        }));
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
    }
    @Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (hand == EnumHand.MAIN_HAND && !this.world.isRemote) {
            ItemStack stack = player.getHeldItemMainhand();
            if (this.isTamed() && this.isOwner(player)) {
                if (stack.getItem() instanceof ItemGem) {
                    if (!((ItemGem) stack.getItem()).isCracked) {
                        Item gem = stack.getItem();
                        ItemStack result = new ItemStack(ModItems.GEM_TABLE.get(gem));
                        result.setTagCompound(stack.getTagCompound());
                        this.entityDropItem(result, 1);
                    }
                    else {
                        Random random = new Random();
                        ItemStack result = new ItemStack(ModItems.ACTIVATED_GEM_SHARD, (random.nextInt(3) + 2));
                        this.entityDropItem(result, 1);
                    }
                    if (!player.capabilities.isCreativeMode) {
                        stack.shrink(1);
                    }
                    return true;
                }
            }
        }
        return super.processInteract(player, hand);
    }
    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (super.attackEntityAsMob(entity)) {
            if (entity instanceof EntityLivingBase) {
                if(!this.isDefective()) {
                    if(this.isPrimary()) {
                        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.WITHER, 200));
                    }
                    else {
                        ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100));
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }
    @Override
    protected int generateSkinColor() {
        ArrayList<Integer> skinColors = new ArrayList<Integer>();
        skinColors.add(EntityMelanite.SKIN_COLOR_BEGIN);
        skinColors.add(EntityMelanite.SKIN_COLOR_END);
        return Colors.arbiLerp(skinColors);
    }
    @Override
    protected int generateHairStyle() {
        return this.rand.nextInt(EntityMelanite.NUM_HAIRSTYLES);
    }
    @Override
    protected int generateHairColor() {
        ArrayList<Integer> hairColors = new ArrayList<Integer>();
        hairColors.add(EntityMelanite.HAIR_COLOR_BEGIN);
        hairColors.add(EntityMelanite.HAIR_COLOR_END);
        return Colors.arbiLerp(hairColors);
    }
    @Override
    public boolean hasCape() {
        return true;
    }
}
