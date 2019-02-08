package mod.amalgam.init;

import mod.amalgam.command.CommandGetCrux;
import mod.amalgam.proxies.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = Amalgam.MODID, version = Amalgam.VERSION, acceptedMinecraftVersions = Amalgam.MCVERSION, dependencies="after:kagic")
public class Amalgam {
    public static final String VERSION = "@version";
    public static final String MCVERSION = "1.12.2";
	public static final String MODID = "amalgam";
	public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(CreativeTabs.getNextID(), Amalgam.MODID) {
		@Override
		public ItemStack getTabIconItem() {
			int index = (int)(System.currentTimeMillis() / 1000L) % 16;
			switch (index) {
			case AmGems.BASIC_WHITE:
				return new ItemStack(AmItems.WHITE_GEM_SHARD);
			case AmGems.BASIC_ORANGE:
				return new ItemStack(AmItems.ORANGE_GEM_SHARD);
			case AmGems.BASIC_MAGENTA:
				return new ItemStack(AmItems.MAGENTA_GEM_SHARD);
			case AmGems.BASIC_LIGHT_BLUE:
				return new ItemStack(AmItems.LIGHT_BLUE_GEM_SHARD);
			case AmGems.BASIC_YELLOW:
				return new ItemStack(AmItems.YELLOW_GEM_SHARD);
			case AmGems.BASIC_LIME:
				return new ItemStack(AmItems.LIME_GEM_SHARD);
			case AmGems.BASIC_PINK:
				return new ItemStack(AmItems.PINK_GEM_SHARD);
			case AmGems.BASIC_GRAY:
				return new ItemStack(AmItems.GRAY_GEM_SHARD);
			case AmGems.BASIC_SILVER:
				return new ItemStack(AmItems.SILVER_GEM_SHARD);
			case AmGems.BASIC_CYAN:
				return new ItemStack(AmItems.CYAN_GEM_SHARD);
			case AmGems.BASIC_PURPLE:
				return new ItemStack(AmItems.PURPLE_GEM_SHARD);
			case AmGems.BASIC_BLUE:
				return new ItemStack(AmItems.BLUE_GEM_SHARD);
			case AmGems.BASIC_BROWN:
				return new ItemStack(AmItems.BROWN_GEM_SHARD);
			case AmGems.BASIC_GREEN:
				return new ItemStack(AmItems.GREEN_GEM_SHARD);
			case AmGems.BASIC_RED:
				return new ItemStack(AmItems.RED_GEM_SHARD);
			default:
				return new ItemStack(AmItems.BLACK_GEM_SHARD);
			}
		}
	};

	@SidedProxy(clientSide = "mod.amalgam.proxies.ClientProxy", serverSide = "mod.amalgam.proxies.ServerProxy")
	public static CommonProxy proxy;
	
    @Instance
    public static Amalgam instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	AmWorldGen.register();
    	AmRecipes.register();
    	AmEntities.register(0);
    }
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	AmHandles.register();
    	AmTileEntities.register();
    	AmEntities.register(1);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	AmConfigs.register();
    	AmSkills.register();
    	AmCruxes.register();
    }
    @EventHandler
    public void serverStarting(FMLServerStartingEvent e) {
    	e.registerServerCommand(new CommandGetCrux());
    }
    
    @Mod.EventBusSubscriber(modid = Amalgam.MODID)
	public static class RegistrationHandler {
    	@SubscribeEvent
		public static void registerEnchants(RegistryEvent.Register<Enchantment> event) {
			AmEnchants.register(event);
		}
    	@SubscribeEvent
		public static void registerItems(RegistryEvent.Register<Item> event) {
			AmItems.register(event);
		}
    	@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			AmBlocks.register(event);
		}
		@SubscribeEvent
		public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
			AmSounds.register(event);
		}
	}
}
