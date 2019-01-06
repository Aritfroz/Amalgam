package mod.amalgam.init;

import java.util.ArrayList;
import java.util.HashMap;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.items.ItemGem;
import mod.amalgam.injection.CruxEntry;
import net.minecraft.util.ResourceLocation;

public class AmGems {
	public static final HashMap<ResourceLocation, Class<? extends EntityGem>> GEM_LIST = new HashMap<ResourceLocation, Class<? extends EntityGem>>();
	public static final HashMap<ResourceLocation, ArrayList<CruxEntry>> CRUXES = new HashMap<ResourceLocation, ArrayList<CruxEntry>>();
	public static final ArrayList<ItemGem> GEM_ITEMS = new ArrayList<ItemGem>();
	public static final HashMap<ItemGem, ItemGem> NORMAL_TO_CRACKED = new HashMap<ItemGem, ItemGem>();
	public static final HashMap<Integer, ItemGem> ROUND_GEMS = new HashMap<Integer, ItemGem>();
	public static final HashMap<Integer, ItemGem> PEARY_GEMS = new HashMap<Integer, ItemGem>();
	public static final HashMap<Integer, ItemGem> FACET_GEMS = new HashMap<Integer, ItemGem>();
	public static final int BASIC_WHITE = 0;
	public static final int BASIC_ORANGE = 1;
	public static final int BASIC_MAGENTA = 2;
	public static final int BASIC_LIGHT_BLUE = 3;
	public static final int BASIC_YELLOW = 4;
	public static final int BASIC_LIME = 5;
	public static final int BASIC_PINK = 6;
	public static final int BASIC_GRAY = 7;
	public static final int BASIC_SILVER = 8;
	public static final int BASIC_CYAN = 9;
	public static final int BASIC_PURPLE = 10;
	public static final int BASIC_BLUE = 11;
	public static final int BASIC_BROWN = 12;
	public static final int BASIC_GREEN = 13;
	public static final int BASIC_RED = 14;
	public static final int BASIC_BLACK = 15;
	
	public enum GemShape {
		ROUND("round"), PEARY("peary"), FACET("facet");
		private final String name;
		GemShape(String name) {
			this.name = name;
		}
		public String toString() {
			return this.name;
		}
	}
	
	public static void registerGemEntity(ResourceLocation loc, Class<? extends EntityGem> entity, ArrayList<CruxEntry> cruxes) {
		GEM_LIST.put(loc, entity);
		CRUXES.put(loc, cruxes);
	}
	public static void setGemItem(GemShape shape, int facets) {
		ItemGem gem = AmGems.setGemItem(shape.toString() + "_" + facets);
		switch (shape) {
		case ROUND:
			ROUND_GEMS.put(facets, gem); break;
		case PEARY:
			PEARY_GEMS.put(facets, gem); break;
		case FACET:
			FACET_GEMS.put(facets, gem); break;
		}
	}
	public static ItemGem setGemItem(String name) {
		ItemGem normal = new ItemGem(name); ItemGem cracked = new ItemGem(name, true);
		AmGems.GEM_ITEMS.add(normal); AmGems.GEM_ITEMS.add(cracked);
		AmGems.NORMAL_TO_CRACKED.put(normal, cracked);
		return normal;
	}
	public static void getGemItem(GemShape shape, int facet) {
		
	}
}
