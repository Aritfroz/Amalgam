package mod.amalgam.gem;

import java.util.ArrayList;

import mod.amalgam.entity.EntityQuartz;
import mod.amalgam.init.AmGems;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.World;

public class EntityJasper extends EntityQuartz implements IAnimals {
	public static final ArrayList<QuartzVariety> VARIETIES = new ArrayList<QuartzVariety>();
	static {
		VARIETIES.add(new QuartzVariety(0, "Noreena", AmGems.BASIC_ORANGE, 0xff3f01, 0x9367f8));
		VARIETIES.add(new QuartzVariety(1, "Ocean", AmGems.BASIC_CYAN, 0x58d3cf, 0x9f512b, 0xe76c1f).bands(0x5b3b2c, 0xc6b0a2).type(0, 0));
		VARIETIES.add(new QuartzVariety(2, "Biggs", AmGems.BASIC_BROWN, 0xd48768, 0xdfa671, 0xd0ad9a).bands(0xfffbcf, 0xd7811e, 0x8a4b44).type(5, 0));
		VARIETIES.add(new QuartzVariety(3, "Green", AmGems.BASIC_GREEN, 0xbad1b5, 0x073c70, 0x1d759b).bands(0x586a5c, 0x96baba, 0x7eabc2).type(8, 0));
		VARIETIES.add(new QuartzVariety(4, "Bruneau", AmGems.BASIC_BROWN, 0xffc583, 0xd7d0a6, 0xd49838));
		VARIETIES.add(new QuartzVariety(5, "Royal Plume", AmGems.BASIC_PURPLE, 0xd7a3e6, 0x0b3126, 0x255346).bands(0xc0dbd2, 0xfbfdf0, 0xb3ad73).type(5, 0));
		VARIETIES.add(new QuartzVariety(6, "Flame", AmGems.BASIC_RED, 0xc78873, 0xc50248, 0xfd1680).bands(0xff53ac, 0xee94c8, 0x8d6d84).type(8, 0));
		VARIETIES.add(new QuartzVariety(7, "Picture", AmGems.BASIC_SILVER, 0xf3f2f9, 0x3b2531, 0x573c33).bands(0x392f3a, 0x9aa1a9).type(6, 0));
		VARIETIES.add(new QuartzVariety(8, "Zebra", AmGems.BASIC_WHITE, 0xb02e26, 0xafb0b5, 0x9097aa).bands(0x7d8496, 0xa5abbb).type(6, 0));
	}
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
	}
}