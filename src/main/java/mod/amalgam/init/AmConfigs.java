package mod.amalgam.init;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Amalgam.MODID)
@Config.LangKey("amalgam.config.title")
@Mod.EventBusSubscriber
public class AmConfigs {
	@Config.Name("Grow Agates?")
	public static boolean enableAgate = true;
	@Config.Name("Grow Amethysts?")
	public static boolean enableAmethyst = true;
	@Config.Name("Grow Aqua Aura Quartzes")
	public static boolean enableAquaAuraQuartz = true;
	@Config.Name("Grow Aquamarines?")
	public static boolean enableAquamarine = true;
	@Config.Name("Grow Bismuths?")
	public static boolean enableBismuth = true;
	@Config.Name("Grow Carnelians?")
	public static boolean enableCarnelian = true;
	@Config.Name("Grow Citrines?")
	public static boolean enableCitrine = true;
	@Config.Name("Grow Emeralds?")
	public static boolean enableEmerald = true;
	@Config.Name("Grow Ender Pearls?")
	public static boolean enableEnderPearl = true;
	@Config.Name("Grow Hessonites?")
	public static boolean enableHessonite = true;
	@Config.Name("Grow Jaspers?")
	public static boolean enableJasper = true;
	@Config.Name("Grow Lapis Lazulis?")
	public static boolean enableLapisLazuli = true;
	@Config.Name("Grow Melanites?")
	public static boolean enableMelanite = true;
	@Config.Name("Grow Mother of Pearls?")
	public static boolean enableNacre = true;
	@Config.Name("Grow Nephrites?")
	public static boolean enableNephrite = true;
	@Config.Name("Grow Pearls?")
	public static boolean enablePearl = true;
	@Config.Name("Grow Peridots?")
	public static boolean enablePeridot = true;
	@Config.Name("Grow Pyrites?")
	public static boolean enablePyrite = true;
	@Config.Name("Grow Rose Quartzes?")
	public static boolean enableRoseQuartz = true;
	@Config.Name("Grow Rubies?")
	public static boolean enableRuby = true;
	@Config.Name("Grow Rutiles?")
	public static boolean enableRutile = true;
	@Config.Name("Grow Sapphires?")
	public static boolean enableSapphire = true;
	@Config.Name("Grow Topazes?")
	public static boolean enableTopaz = true;
	@Config.Name("Grow Watermelon Tourmalines?")
	public static boolean enableWatermelonTourmaline = true;
	@Config.Name("Grow Zircons?")
	public static boolean enableZircon = true;
	
	@Config.Name("Spawn Meteor Gems?")
	public static boolean enableMeteorGems = true;
	@Config.Name("Spawn Crystal Gems?")
	public static boolean enableCrystalGems = true;
	@Config.Name("Spawn Beach City?")
	public static boolean enableBeachCity = true;
	@Config.Name("Spawn Rebellions?")
	public static boolean enableRebellions = true;
	
	@Config.Name("Show descriptors?")
	public static boolean showDescriptors = true;
	
	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e) {
		if (e.getModID().equals(Amalgam.MODID)) {
			ConfigManager.sync(Amalgam.MODID, Config.Type.INSTANCE);
			AmConfigs.register();
		}
	}
	public static void register() {
    	ModConfigs.displayNames = AmConfigs.showDescriptors;
    	ModConfigs.spawnMeteorRubies = false;
    	ModConfigs.canRebel = false;
	}
}
