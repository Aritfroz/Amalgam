package mod.amalgam.gem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import mod.amalgam.entity.EntityQuartz;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityJasper extends EntityQuartz implements IAnimals {
	public static final HashMap<Integer, ResourceLocation> JASPER_HAIR_STYLES = new HashMap<Integer, ResourceLocation>();
	private static final DataParameter<Integer> MARK_1_COLOR = EntityDataManager.<Integer>createKey(EntityJasper.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> MARK_1 = EntityDataManager.<Integer>createKey(EntityJasper.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> MARK_2_COLOR = EntityDataManager.<Integer>createKey(EntityJasper.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> MARK_2 = EntityDataManager.<Integer>createKey(EntityJasper.class, DataSerializers.VARINT);
	private static final int NUM_HAIRSTYLES = 5;
	private static final Map<Integer, Integer> MARK1S = new LinkedHashMap<Integer, Integer>();
	static {
		MARK1S.put(0, 6);
		MARK1S.put(1, 1);
		MARK1S.put(2, 1);
		MARK1S.put(3, 1);
		MARK1S.put(4, 1);
		MARK1S.put(5, 1);
		MARK1S.put(6, 1);
		MARK1S.put(7, 1);
		MARK1S.put(8, 1);
	}

	private static final Map<Integer, Integer> MARK2S = new LinkedHashMap<Integer, Integer>();
	static {
		MARK2S.put(0, 0);
		MARK2S.put(1, 1);
		MARK2S.put(2, 1);
		MARK2S.put(3, 0);
		MARK2S.put(4, 1);
		MARK2S.put(5, 0);
		MARK2S.put(6, 1);
		MARK2S.put(7, 1);
		MARK2S.put(8, 2);
	}

	private static final Map<Integer, ArrayList<Integer>> SKIN_COLORS = new LinkedHashMap<Integer, ArrayList<Integer>>();
	static {
		ArrayList<Integer> normal = new ArrayList<Integer>();
		normal.add(0xFFD69D);
		normal.add(0xFC9C6F);
		normal.add(0xFA8669);
		normal.add(0xFFA351);
		normal.add(0xF58059);
		normal.add(0xE8745B);
		SKIN_COLORS.put(0, normal);

		ArrayList<Integer> ocean = new ArrayList<Integer>();
		ocean.add(0x70BCC7);
		ocean.add(0x478397);
		SKIN_COLORS.put(1, ocean);

		ArrayList<Integer> biggs = new ArrayList<Integer>();
		biggs.add(0xBC6A68);
		SKIN_COLORS.put(2, biggs);
		
		ArrayList<Integer> green = new ArrayList<Integer>();
		green.add(0x4E7E6D);
		SKIN_COLORS.put(3, green);

		ArrayList<Integer> bruneau = new ArrayList<Integer>();
		bruneau.add(0xB36935);
		SKIN_COLORS.put(4, bruneau);
		
		ArrayList<Integer> purple = new ArrayList<Integer>();
		purple.add(0x8A6087);
		SKIN_COLORS.put(5, purple);
		
		ArrayList<Integer> flame = new ArrayList<Integer>();
		flame.add(0xA84B3D);
		SKIN_COLORS.put(6, flame);
		
		ArrayList<Integer> picture = new ArrayList<Integer>();
		picture.add(0xDF8A69);
		SKIN_COLORS.put(7, picture);

		ArrayList<Integer> candyCane = new ArrayList<Integer>();
		candyCane.add(0xF9FFFE);
		SKIN_COLORS.put(8, candyCane);
	}
	
	private static final Map<Integer, ArrayList<Integer>> HAIR_COLORS = new LinkedHashMap<Integer, ArrayList<Integer>>();
	static {
		ArrayList<Integer> normal = new ArrayList<Integer>();
		normal.add(0xFEFFEC);
		normal.add(0xFFFCE8);
		normal.add(0xFFF0D4);
		normal.add(0xFDE7D9);
		normal.add(0xFFD69D);
		HAIR_COLORS.put(0, normal);

		ArrayList<Integer> ocean = new ArrayList<Integer>();
		ocean.add(0xFaF9Fc);
		HAIR_COLORS.put(1, ocean);

		ArrayList<Integer> biggs = new ArrayList<Integer>();
		biggs.add(0xFDF8EA);
		HAIR_COLORS.put(2, biggs);
		
		ArrayList<Integer> green = new ArrayList<Integer>();
		green.add(0xDADCCC);
		HAIR_COLORS.put(3, green);

		ArrayList<Integer> bruneau = new ArrayList<Integer>();
		bruneau.add(0xFACDA1);
		HAIR_COLORS.put(4, bruneau);

		ArrayList<Integer> purple = new ArrayList<Integer>();
		purple.add(0xDECDD1);
		HAIR_COLORS.put(5, purple);

		ArrayList<Integer> flame = new ArrayList<Integer>();
		flame.add(0xF0D2C6);
		HAIR_COLORS.put(6, flame);

		ArrayList<Integer> picture = new ArrayList<Integer>();
		picture.add(0xEBDCCA);
		HAIR_COLORS.put(7, picture);

		ArrayList<Integer> candyCane = new ArrayList<Integer>();
		candyCane.add(0xF9FFFE);
		HAIR_COLORS.put(8, candyCane);
	}
	
	private static final Map<Integer, ArrayList<Integer>> MARK_1_COLORS = new LinkedHashMap<Integer, ArrayList<Integer>>();
	static {
		ArrayList<Integer> normal = new ArrayList<Integer>();
		normal.add(0xF58059);
		normal.add(0xED4A3C);
		normal.add(0xF62F46);
		normal.add(0xF1162C);
		normal.add(0xD01729);
		normal.add(0xAC0522);
		MARK_1_COLORS.put(0, normal);

		ArrayList<Integer> ocean = new ArrayList<Integer>();
		ocean.add(0x478397);
		ocean.add(0x173F55);
		MARK_1_COLORS.put(1, ocean);

		ArrayList<Integer> biggs = new ArrayList<Integer>();
		biggs.add(0xD7AB85);
		biggs.add(0xC68976);
		MARK_1_COLORS.put(2, biggs);
		
		ArrayList<Integer> green = new ArrayList<Integer>();
		green.add(0x6dab98);
		MARK_1_COLORS.put(3, green);

		ArrayList<Integer> bruneau = new ArrayList<Integer>();
		bruneau.add(0xE59F54);
		MARK_1_COLORS.put(4, bruneau);

		ArrayList<Integer> purple = new ArrayList<Integer>();
		purple.add(0x82838A);
		MARK_1_COLORS.put(5, purple);

		ArrayList<Integer> flame = new ArrayList<Integer>();
		flame.add(0xE07769);
		MARK_1_COLORS.put(6, flame);

		ArrayList<Integer> picture = new ArrayList<Integer>();
		picture.add(0xCA6C63);
		MARK_1_COLORS.put(7, picture);

		ArrayList<Integer> candyCane = new ArrayList<Integer>();
		candyCane.add(0xB02E26);
		MARK_1_COLORS.put(8, candyCane);
	}
	
	private static final Map<Integer, ArrayList<Integer>> MARK_2_COLORS = new LinkedHashMap<Integer, ArrayList<Integer>>();
	static {
		ArrayList<Integer> normal = new ArrayList<Integer>();
		normal.add(0);
		MARK_2_COLORS.put(0, normal);

		ArrayList<Integer> ocean = new ArrayList<Integer>();
		ocean.add(0xF47B95);
		ocean.add(0xFFC3D3);
		MARK_2_COLORS.put(1, ocean);

		ArrayList<Integer> biggs = new ArrayList<Integer>();
		biggs.add(0xA2504E);
		MARK_2_COLORS.put(2, biggs);
		
		ArrayList<Integer> green = new ArrayList<Integer>();
		green.add(0);
		MARK_2_COLORS.put(3, green);

		ArrayList<Integer> bruneau = new ArrayList<Integer>();
		bruneau.add(0x8A511B);
		MARK_2_COLORS.put(4, bruneau);
		
		ArrayList<Integer> purple = new ArrayList<Integer>();
		purple.add(0);
		MARK_2_COLORS.put(5, purple);
		
		ArrayList<Integer> flame = new ArrayList<Integer>();
		flame.add(0xFFAD92);
		MARK_2_COLORS.put(6, flame);
		
		ArrayList<Integer> picture = new ArrayList<Integer>();
		picture.add(0x91818B);
		MARK_2_COLORS.put(7, picture);

		ArrayList<Integer> candyCane = new ArrayList<Integer>();
		candyCane.add(0x5E7C16);
		MARK_2_COLORS.put(8, candyCane);
	}
	public EntityJasper(World world) {
		super(world);
		this.dataManager.register(MARK_1_COLOR, 0);
		this.dataManager.register(MARK_1, 0);
		this.dataManager.register(MARK_2_COLOR, 0);
		this.dataManager.register(MARK_2, 0);
	}
	@Override
	protected int generateGemColor() {
		switch (this.getSpecial()) {
		case 1:
			return 0x58D3CF;
		case 2:
			return 0xD48768;
		case 3:
			return 0xBAD1B5;
		case 4:
			return 0xFFC583;
		case 5:
			return 0xD7A3E6;
		case 6:
			return 0xC78873;
		case 7:
			return 0xF3F2F9;
		case 8:
			return 0xB02E26;
		default:
			return 0xFF3F01;
		}
	}
	public String getSpecialSkin() {
		switch (this.getSpecial()) {
		case 0:
			return "";
		case 1:
			return "ocean_";
		case 2:
			return "biggs_";
		case 3:
			return "green_";
		case 4:
			return "bruneau_";
		case 5:
			return "purple_";
		case 6:
			return "flame_";
		case 7:
			return "picture_";
		case 8:
			return "candy_cane_";
		}
		return null;
	}
}