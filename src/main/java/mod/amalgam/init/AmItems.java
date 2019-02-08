package mod.amalgam.init;

import mod.amalgam.items.ItemGem;
import mod.amalgam.items.ItemGemDestabilizer;
import mod.amalgam.items.ItemGemDust;
import mod.amalgam.items.ItemGemShard;
import mod.amalgam.items.ItemGemStaff;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class AmItems {
	public static final ItemGemStaff GEM_STAFF = new ItemGemStaff();

	public static final ItemGemDestabilizer WHITE_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_WHITE);
	public static final ItemGemShard WHITE_GEM_SHARD = new ItemGemShard(AmGems.BASIC_WHITE);
	public static final ItemGemDust WHITE_GEM_DUST = new ItemGemDust(AmGems.BASIC_WHITE);
	public static final ItemGemDestabilizer ORANGE_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_ORANGE);
	public static final ItemGemShard ORANGE_GEM_SHARD = new ItemGemShard(AmGems.BASIC_ORANGE);
	public static final ItemGemDust ORANGE_GEM_DUST = new ItemGemDust(AmGems.BASIC_ORANGE);
	public static final ItemGemDestabilizer MAGENTA_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_MAGENTA);
	public static final ItemGemShard MAGENTA_GEM_SHARD = new ItemGemShard(AmGems.BASIC_MAGENTA);
	public static final ItemGemDust MAGENTA_GEM_DUST = new ItemGemDust(AmGems.BASIC_MAGENTA);
	public static final ItemGemDestabilizer LIGHT_BLUE_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_LIGHT_BLUE);
	public static final ItemGemShard LIGHT_BLUE_GEM_SHARD = new ItemGemShard(AmGems.BASIC_LIGHT_BLUE);
	public static final ItemGemDust LIGHT_BLUE_GEM_DUST = new ItemGemDust(AmGems.BASIC_LIGHT_BLUE);
	public static final ItemGemDestabilizer YELLOW_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_YELLOW);
	public static final ItemGemShard YELLOW_GEM_SHARD = new ItemGemShard(AmGems.BASIC_YELLOW);
	public static final ItemGemDust YELLOW_GEM_DUST = new ItemGemDust(AmGems.BASIC_YELLOW);
	public static final ItemGemDestabilizer LIME_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_LIME);
	public static final ItemGemShard LIME_GEM_SHARD = new ItemGemShard(AmGems.BASIC_LIME);
	public static final ItemGemDust LIME_GEM_DUST = new ItemGemDust(AmGems.BASIC_LIME);
	public static final ItemGemDestabilizer PINK_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_PINK);
	public static final ItemGemShard PINK_GEM_SHARD = new ItemGemShard(AmGems.BASIC_PINK);
	public static final ItemGemDust PINK_GEM_DUST = new ItemGemDust(AmGems.BASIC_PINK);
	public static final ItemGemDestabilizer GRAY_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_GRAY);
	public static final ItemGemShard GRAY_GEM_SHARD = new ItemGemShard(AmGems.BASIC_GRAY);
	public static final ItemGemDust GRAY_GEM_DUST = new ItemGemDust(AmGems.BASIC_GRAY);
	public static final ItemGemDestabilizer SILVER_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_SILVER);
	public static final ItemGemShard SILVER_GEM_SHARD = new ItemGemShard(AmGems.BASIC_SILVER);
	public static final ItemGemDust SILVER_GEM_DUST = new ItemGemDust(AmGems.BASIC_SILVER);
	public static final ItemGemDestabilizer CYAN_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_CYAN);
	public static final ItemGemShard CYAN_GEM_SHARD = new ItemGemShard(AmGems.BASIC_CYAN);
	public static final ItemGemDust CYAN_GEM_DUST = new ItemGemDust(AmGems.BASIC_CYAN);
	public static final ItemGemDestabilizer PURPLE_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_PURPLE);
	public static final ItemGemShard PURPLE_GEM_SHARD = new ItemGemShard(AmGems.BASIC_PURPLE);
	public static final ItemGemDust PURPLE_GEM_DUST = new ItemGemDust(AmGems.BASIC_PURPLE);
	public static final ItemGemDestabilizer BLUE_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_BLUE);
	public static final ItemGemShard BLUE_GEM_SHARD = new ItemGemShard(AmGems.BASIC_BLUE);
	public static final ItemGemDust BLUE_GEM_DUST = new ItemGemDust(AmGems.BASIC_BLUE);
	public static final ItemGemDestabilizer BROWN_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_BROWN);
	public static final ItemGemShard BROWN_GEM_SHARD = new ItemGemShard(AmGems.BASIC_BROWN);
	public static final ItemGemDust BROWN_GEM_DUST = new ItemGemDust(AmGems.BASIC_BROWN);
	public static final ItemGemDestabilizer GREEN_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_GREEN);
	public static final ItemGemShard GREEN_GEM_SHARD = new ItemGemShard(AmGems.BASIC_GREEN);
	public static final ItemGemDust GREEN_GEM_DUST = new ItemGemDust(AmGems.BASIC_GREEN);
	public static final ItemGemDestabilizer RED_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_RED);
	public static final ItemGemShard RED_GEM_SHARD = new ItemGemShard(AmGems.BASIC_RED);
	public static final ItemGemDust RED_GEM_DUST = new ItemGemDust(AmGems.BASIC_RED);
	public static final ItemGemDestabilizer BLACK_GEM_DESTABILIZER = new ItemGemDestabilizer(AmGems.BASIC_BLACK);
	public static final ItemGemShard BLACK_GEM_SHARD = new ItemGemShard(AmGems.BASIC_BLACK);
	public static final ItemGemDust BLACK_GEM_DUST = new ItemGemDust(AmGems.BASIC_BLACK);
	
	public static void register(RegistryEvent.Register<Item> event) {
		registerItem(GEM_STAFF, new ResourceLocation("amalgam:gem_staff"), event);
		
		registerItem(WHITE_GEM_DESTABILIZER, new ResourceLocation("amalgam:white_gem_destabilizer"), event);
		registerItem(WHITE_GEM_SHARD, new ResourceLocation("amalgam:white_gem_shard"), event);
		registerItem(WHITE_GEM_DUST, new ResourceLocation("amalgam:white_gem_dust"), event);
		registerItem(ORANGE_GEM_DESTABILIZER, new ResourceLocation("amalgam:orange_gem_destabilizer"), event);
		registerItem(ORANGE_GEM_SHARD, new ResourceLocation("amalgam:orange_gem_shard"), event);
		registerItem(ORANGE_GEM_DUST, new ResourceLocation("amalgam:orange_gem_dust"), event);
		registerItem(MAGENTA_GEM_DESTABILIZER, new ResourceLocation("amalgam:magenta_gem_destabilizer"), event);
		registerItem(MAGENTA_GEM_SHARD, new ResourceLocation("amalgam:magenta_gem_shard"), event);
		registerItem(MAGENTA_GEM_DUST, new ResourceLocation("amalgam:magenta_gem_dust"), event);
		registerItem(LIGHT_BLUE_GEM_DESTABILIZER, new ResourceLocation("amalgam:light_blue_gem_destabilizer"), event);
		registerItem(LIGHT_BLUE_GEM_SHARD, new ResourceLocation("amalgam:light_blue_gem_shard"), event);
		registerItem(LIGHT_BLUE_GEM_DUST, new ResourceLocation("amalgam:light_blue_gem_dust"), event);
		registerItem(YELLOW_GEM_DESTABILIZER, new ResourceLocation("amalgam:yellow_gem_destabilizer"), event);
		registerItem(YELLOW_GEM_SHARD, new ResourceLocation("amalgam:yellow_gem_shard"), event);
		registerItem(YELLOW_GEM_DUST, new ResourceLocation("amalgam:yellow_gem_dust"), event);
		registerItem(LIME_GEM_DESTABILIZER, new ResourceLocation("amalgam:lime_gem_destabilizer"), event);
		registerItem(LIME_GEM_SHARD, new ResourceLocation("amalgam:lime_gem_shard"), event);
		registerItem(LIME_GEM_DUST, new ResourceLocation("amalgam:lime_gem_dust"), event);
		registerItem(PINK_GEM_DESTABILIZER, new ResourceLocation("amalgam:pink_gem_destabilizer"), event);
		registerItem(PINK_GEM_SHARD, new ResourceLocation("amalgam:pink_gem_shard"), event);
		registerItem(PINK_GEM_DUST, new ResourceLocation("amalgam:pink_gem_dust"), event);
		registerItem(GRAY_GEM_DESTABILIZER, new ResourceLocation("amalgam:gray_gem_destabilizer"), event);
		registerItem(GRAY_GEM_SHARD, new ResourceLocation("amalgam:gray_gem_shard"), event);
		registerItem(GRAY_GEM_DUST, new ResourceLocation("amalgam:gray_gem_dust"), event);
		registerItem(SILVER_GEM_DESTABILIZER, new ResourceLocation("amalgam:silver_gem_destabilizer"), event);
		registerItem(SILVER_GEM_SHARD, new ResourceLocation("amalgam:silver_gem_shard"), event);
		registerItem(SILVER_GEM_DUST, new ResourceLocation("amalgam:silver_gem_dust"), event);
		registerItem(CYAN_GEM_DESTABILIZER, new ResourceLocation("amalgam:cyan_gem_destabilizer"), event);
		registerItem(CYAN_GEM_SHARD, new ResourceLocation("amalgam:cyan_gem_shard"), event);
		registerItem(CYAN_GEM_DUST, new ResourceLocation("amalgam:cyan_gem_dust"), event);
		registerItem(PURPLE_GEM_DESTABILIZER, new ResourceLocation("amalgam:purple_gem_destabilizer"), event);
		registerItem(PURPLE_GEM_SHARD, new ResourceLocation("amalgam:purple_gem_shard"), event);
		registerItem(PURPLE_GEM_DUST, new ResourceLocation("amalgam:purple_gem_dust"), event);
		registerItem(BLUE_GEM_DESTABILIZER, new ResourceLocation("amalgam:blue_gem_destabilizer"), event);
		registerItem(BLUE_GEM_SHARD, new ResourceLocation("amalgam:blue_gem_shard"), event);
		registerItem(BLUE_GEM_DUST, new ResourceLocation("amalgam:blue_gem_dust"), event);
		registerItem(BROWN_GEM_DESTABILIZER, new ResourceLocation("amalgam:brown_gem_destabilizer"), event);
		registerItem(BROWN_GEM_SHARD, new ResourceLocation("amalgam:brown_gem_shard"), event);
		registerItem(BROWN_GEM_DUST, new ResourceLocation("amalgam:brown_gem_dust"), event);
		registerItem(GREEN_GEM_DESTABILIZER, new ResourceLocation("amalgam:green_gem_destabilizer"), event);
		registerItem(GREEN_GEM_SHARD, new ResourceLocation("amalgam:green_gem_shard"), event);
		registerItem(GREEN_GEM_DUST, new ResourceLocation("amalgam:green_gem_dust"), event);
		registerItem(RED_GEM_DESTABILIZER, new ResourceLocation("amalgam:red_gem_destabilizer"), event);
		registerItem(RED_GEM_SHARD, new ResourceLocation("amalgam:red_gem_shard"), event);
		registerItem(RED_GEM_DUST, new ResourceLocation("amalgam:red_gem_dust"), event);
		registerItem(BLACK_GEM_DESTABILIZER, new ResourceLocation("amalgam:black_gem_destabilizer"), event);
		registerItem(BLACK_GEM_SHARD, new ResourceLocation("amalgam:black_gem_shard"), event);
		registerItem(BLACK_GEM_DUST, new ResourceLocation("amalgam:black_gem_dust"), event);
		
		for (Item item : AmBlocks.ITEMS) {
			registerItem(item, null, event);
		}
	}
	public static void registerGemItem(ItemGem normal, ItemGem cracked, ResourceLocation name, RegistryEvent.Register<Item> event) {
		AmItems.registerItem(cracked, new ResourceLocation(name.getResourcePath(), "cracked_" + name.getResourceDomain()), event);
		AmItems.registerItem(normal, name, event);
		AmGems.NORMAL_TO_CRACKED.put(normal, cracked);
		AmGems.CRACKED_TO_NORMAL.put(cracked, normal);
	}
	public static void registerItem(Item item, ResourceLocation name, RegistryEvent.Register<Item> event, String...meta) {
		event.getRegistry().register(name == null ? (item.getRegistryName() != null ? item : item.setRegistryName("amalgam:" + ((ItemBlock)(item)).getBlock().getUnlocalizedName().replaceAll("tile\\.", ""))) : item.setRegistryName(name));
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			for (int i = 0; i < meta.length; ++i) {
				ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(item.getRegistryName(), meta[i]));
			}
		}
	}
	public static void registerItem(Item item, ResourceLocation name, RegistryEvent.Register<Item> event) {
		AmItems.registerItem(item, name, event, "inventory");
	}
}