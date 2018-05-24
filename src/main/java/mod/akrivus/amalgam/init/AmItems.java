package mod.akrivus.amalgam.init;

import mod.akrivus.kagic.init.ModItems;
import mod.akrivus.kagic.items.ItemGem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;

public class AmItems {
	public static final ItemGem CITRINE_GEM = new ItemGem("citrine");
	public static final ItemGem AMETRINE_GEM = new ItemGem("citrine_1");
	public static final ItemGem PYRITE_GEM = new ItemGem("pyrite");
	public static final ItemGem ENDER_PEARL_GEM = new ItemGem("ender_pearl");
	public static final ItemGem NACRE_GEM = new ItemGem("nacre");
	
	public static final ItemGem CRACKED_CITRINE_GEM = new ItemGem("citrine", true);
	public static final ItemGem CRACKED_AMETRINE_GEM = new ItemGem("citrine_1", true);
	public static final ItemGem CRACKED_PYRITE_GEM = new ItemGem("pyrite", true);
	public static final ItemGem CRACKED_ENDER_PEARL_GEM = new ItemGem("ender_pearl", true);
	public static final ItemGem CRACKED_NACRE_GEM = new ItemGem("nacre", true);
	
	public static void register(RegistryEvent.Register<Item> event) {
		ModItems.registerExternalGem(CITRINE_GEM, CRACKED_CITRINE_GEM, "amalgam", event);
		ModItems.registerExternalGem(AMETRINE_GEM, CRACKED_AMETRINE_GEM, "amalgam", event);
		ModItems.registerExternalGem(PYRITE_GEM, CRACKED_PYRITE_GEM, "amalgam", event);
		ModItems.registerExternalGem(ENDER_PEARL_GEM, CRACKED_ENDER_PEARL_GEM, "amalgam", event);
		ModItems.registerExternalGem(NACRE_GEM, CRACKED_NACRE_GEM, "amalgam", event);
	}
}
