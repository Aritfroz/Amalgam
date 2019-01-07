package mod.amalgam.init;

import java.util.List;
import java.util.Random;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.entity.ai.EntityAIFollowTopaz;
import mod.akrivus.kagic.event.TimeGlassEvent;
import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.items.ItemGem;
import mod.amalgam.enchant.EnchantShard;
import mod.amalgam.entity.EntityBubble;
import mod.amalgam.entity.EntityGemShard;
import mod.amalgam.entity.EntityPalanquin;
import mod.amalgam.human.EntitySteven;
import mod.amalgam.items.ItemGemShard;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class AmEvents {
	public static void register() {
		MinecraftForge.EVENT_BUS.register(new AmEvents());
	}
	@SubscribeEvent
	public boolean onLivingUpdate(LivingUpdateEvent e) {
		if (e.getEntityLiving().ticksExisted % 80 == 0) {
			for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
				ItemStack stack = e.getEntityLiving().getItemStackFromSlot(slot);
				NBTTagList enchantments = stack.getEnchantmentTagList();
				for (int i = 0; i < enchantments.tagCount(); i++) {
					if (Enchantment.getEnchantmentByID(enchantments.getCompoundTagAt(i).getInteger("id")) instanceof EnchantShard) {
						EnchantShard en = (EnchantShard)(Enchantment.getEnchantmentByID(enchantments.getCompoundTagAt(i).getInteger("id")));
						e.getEntityLiving().addPotionEffect(en.getBuff(en.color));
					}
				}
			}
		}
		return false;
	}
	@SubscribeEvent
	public boolean onTimeGlass(TimeGlassEvent e) {
		if (AmConfigs.enableBeachCity && e.player.world.rand.nextInt(40) == 0 && !e.player.world.isRemote) {
			EntitySteven steven = new EntitySteven(e.player.world);
			steven.setPosition(e.player.posX, e.player.posY, e.player.posZ);
			steven.world.spawnEntity(steven);
			return true;
		}
		return false;
	}
	@SubscribeEvent
	public void onItemExpire(ItemExpireEvent e) {
		ItemStack stack = e.getEntityItem().getItem();
		if (stack.isItemEnchanted()) {
			NBTTagList enchantments = stack.getEnchantmentTagList();
			for (int i = 0; i < enchantments.tagCount(); i++) {
				if (Enchantment.getEnchantmentByID(enchantments.getCompoundTagAt(i).getInteger("id")) instanceof EnchantShard) {
					if (!e.getEntityItem().world.isRemote) {
						EnchantShard en = (EnchantShard)(Enchantment.getEnchantmentByID(enchantments.getCompoundTagAt(i).getInteger("id")));
						EntityGemShard shard = new EntityGemShard(e.getEntityItem().world);
						shard.setPositionAndRotation(e.getEntityItem().posX, e.getEntityItem().posY, e.getEntityItem().posZ, e.getEntityItem().rotationYaw, e.getEntityItem().rotationPitch);
						shard.setColor(en.color);
						shard.setItem(stack);
						e.getEntityItem().world.spawnEntity(shard);
						e.getEntityItem().setDead();
					}
				}
			}
		}
	}
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent e) {
		if (e.getEntity() instanceof EntityAnimal) {
			EntityAnimal animal = (EntityAnimal)(e.getEntity());
			animal.targetTasks.addTask(3, new EntityAIFollowTopaz(animal, 0.9D));
		}
		if (e.getEntity() instanceof EntityMob) {
			EntityMob mob = (EntityMob)(e.getEntity());
			mob.tasks.addTask(0, new EntityAIAvoidEntity<EntityPalanquin>(mob, EntityPalanquin.class, 16, 0.2D, 0.8D));
		}
		if (e.getEntity() instanceof EntityGem) {
			EntityGem gem = (EntityGem)(e.getEntity());
			AmTweaks.applyGemTweaks(gem);
			AmTweaks.Gem.applyWailingStoneTweaks(gem);
			AmTweaks.Gem.applyRenameTweaks(gem);
			AmTweaks.Gem.applyFusionTweaks(gem);
		}
	}
	@SubscribeEvent
	public void onPlayerLogged(PlayerLoggedInEvent e) {
		e.player.sendMessage(new TextComponentString("Amalgam " + Amalgam.VERSION).setStyle(new Style().setColor(TextFormatting.RED)));
	}
	@SubscribeEvent
	public void onAnvilRepair(AnvilRepairEvent e) {
		if (e.getIngredientInput().getItem() instanceof ItemGemShard) {
			ItemStack stack = e.getIngredientInput().copy();
			stack.shrink(1);
			boolean added = e.getEntityPlayer().addItemStackToInventory(stack);
			if (!added) {
				e.getEntityPlayer().dropItem(stack, true);
			}
		}
		if (e.getItemInput().getItem() instanceof ItemGem) {
			ItemGem gem = (ItemGem)(e.getItemInput().getItem());
			if (gem.isCracked) {
				if (e.getIngredientInput().getItem().getToolClasses(e.getIngredientInput()).contains("pickaxe")) {
					ItemStack stack = e.getIngredientInput().copy();
					stack.damageItem(1, e.getEntityPlayer());
					boolean added = e.getEntityPlayer().addItemStackToInventory(stack);
					if (!added) {
						e.getEntityPlayer().dropItem(stack, true);
					}
				}
			}
		}
	}
	@SubscribeEvent
	public void onAnvilUpdate(AnvilUpdateEvent e) {
		Random rand = new Random(e.getRight().hashCode());
		if (e.getRight().getItem() instanceof ItemGemShard) {
			ItemGemShard gem = (ItemGemShard)(e.getRight().getItem());
			if (e.getLeft().getItem() instanceof ItemGemShard) {
				ItemGem[] SHARDS = new ItemGem[] {
					ModItems.HANDBODY_GEM,
					ModItems.FOOTARM_GEM,
					ModItems.MOUTHTORSO_GEM
				};
				e.setOutput(new ItemStack(SHARDS[rand.nextInt(SHARDS.length)]));
			}
			else {
				boolean enchantItem = true;
				NBTTagList enchantments = e.getLeft().getEnchantmentTagList();
				for (int i = 0; i < enchantments.tagCount(); i++) {
					if (Enchantment.getEnchantmentByID(enchantments.getCompoundTagAt(i).getInteger("id")) instanceof EnchantShard) {
						enchantItem = true;
					}
				}
				if (enchantItem) {
					ItemStack stack = e.getLeft().copy();
					stack.addEnchantment(EnchantShard.ENCHANTS.get(gem.getUnlocalizedName().replaceAll("item\\.", "")), 1);
					e.setOutput(stack);
				}
			}
			if (!e.getOutput().isEmpty()) {
				e.setResult(Result.ALLOW);
				e.setCost(1);
			}
		}
		if (e.getLeft().getItem() instanceof ItemGem) {
			boolean canBreak = false;
			if (e.getRight().getItem() instanceof ItemPickaxe) {
				ItemPickaxe tool = (ItemPickaxe)(e.getRight().getItem());
				ToolMaterial mat = ToolMaterial.valueOf(tool.getToolMaterialName());
				canBreak = mat.getHarvestLevel() > 1;
			}
			if (canBreak) {
				ItemGem gem = (ItemGem)(e.getLeft().getItem());
				if (gem.isCracked) {
				    int gemColor = rand.nextInt(16777215);
				    if (e.getLeft().hasTagCompound()) {
				    	NBTTagCompound nbt = e.getLeft().getTagCompound();
				    	if (nbt.hasKey("gemColor")) {
				    		gemColor = nbt.getInteger("gemColor");
				    	}
				    }
				    double maxDist = Double.MAX_VALUE;
				    int dyeColor = 0;
				    float r = (gemColor & 16711680) >> 16;
			        float g = (gemColor & 65280) >> 8;
			        float b = (gemColor & 255) >> 0;
				    for (int i = 0; i < EntityGemShard.PARTICLE_COLORS.length; ++i) {
				    	int color = EntityGemShard.PARTICLE_COLORS[i];
						float r1 = (color & 16711680) >> 16;
				        float g1 = (color & 65280) >> 8;
				        float b1 = (color & 255) >> 0;
						double dist = Math.sqrt(Math.pow(r1-r, 2)+Math.pow(g1-g, 2)+Math.pow(b1-b, 2));
						if (dist < maxDist) {
							maxDist = dist;
							dyeColor = i;
						}
				    }
				    e.setOutput(new ItemStack(ItemGemShard.SHARD_COLORS.get(dyeColor), 9));
				    if (!e.getOutput().isEmpty()) {
						e.setResult(Result.ALLOW);
						e.setCost(1);
					}
				}
			}
		}
	}
	@SubscribeEvent
	public void onBlockInteract(PlayerInteractEvent.RightClickBlock e) {
		if (!e.getWorld().isRemote) {
			List<EntityItem> items = e.getEntityPlayer().world.<EntityItem>getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(e.getPos()).grow(1, 1, 1));
			for (EntityItem item : items) {
				if (!item.isDead) {
					if (e.getItemStack().getItem() == ModItems.GEM_STAFF || e.getItemStack().getItem() == ModItems.COMMANDER_STAFF) {
						List<EntityGem> list = e.getEntityPlayer().world.<EntityGem>getEntitiesWithinAABB(EntityGem.class, e.getEntityPlayer().getEntityBoundingBox().grow(4, 4, 4));
						double distance = Double.MAX_VALUE;
						EntityGem gem = null;
						for (EntityGem testedGem : list) {
							if (testedGem.isOwnedBy(e.getEntityPlayer())) {
								double newDistance = e.getEntityPlayer().getDistanceSq(testedGem);
								if (newDistance <= distance) {
									distance = newDistance;
									gem = testedGem;
								}
							}
						}
						if (gem != null) {
							EntityBubble bubble = new EntityBubble(e.getWorld());
							bubble.setColor(gem == null ? e.getWorld().rand.nextInt(0xFFFFFF) : gem.getGemColor());
							bubble.setItem(item.getItem());
							bubble.setPosition(item.posX, item.posY, item.posZ);
							bubble.setHealth(0.5F);
							bubble.motionY = e.getWorld().rand.nextDouble() / 2;
							bubble.playBubbleSound();
							item.setDead();
							e.getWorld().spawnEntity(bubble);
						}
					}
				}
			}
		}
	}
}