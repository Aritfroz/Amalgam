package mod.amalgam.gem;

import java.util.ArrayList;

import mod.amalgam.entity.EntityQuartz;
import mod.amalgam.init.AmItems;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityAgate extends EntityQuartz implements IAnimals {
	public static final ArrayList<QuartzVariety> VARIETIES = new ArrayList<QuartzVariety>();
	static {
		VARIETIES.add(new QuartzVariety() {
			@Override
			public int[] getSkinColor() { return null; }
			@Override
			public int[] getHairColor() { return null; }
			@Override
			public int[] getGemColor() 	{ return null; }
			@Override
			public int[] getBandColor() { return null; }
			@Override
			public int[] getBandIndex() { return null; }
			@Override
			public int[] getMarkColor() { return null; }
			@Override
			public int[] getMarkIndex() { return null; }
			@Override
			public String getName() 	{ return null; }
			@Override
			public int getDamage() 		{ return 0; }
		});
	}
	/**
	 * static {
		VARIETIES.add(new QuartzVariety( 0, "White", AmGems.BASIC_WHITE, 0xffffff, 0x9367f8));
		VARIETIES.add(new QuartzVariety( 1, "Apache", AmGems.BASIC_RED, 0xcb7226, 0x9f512b, 0xe76c1f).bands(0x5b3b2c, 0xc6b0a2).type(0, 0));
		VARIETIES.add(new QuartzVariety( 2, "Crazy Lace", AmGems.BASIC_GRAY, 0xcea69e, 0xdfa671, 0xd0ad9a).bands(0xfffbcf, 0xd7811e, 0x8a4b44).type(5, 0));
		VARIETIES.add(new QuartzVariety( 3, "Persian", AmGems.BASIC_CYAN, 0x15709c, 0x073c70, 0x1d759b).bands(0x586a5c, 0x96baba, 0x7eabc2).type(8, 0));
		VARIETIES.add(new QuartzVariety( 4, "Honey", AmGems.BASIC_BROWN, 0xe5c77e, 0xd7d0a6, 0xd49838));
		VARIETIES.add(new QuartzVariety( 5, "Moss", AmGems.BASIC_GRAY, 0x042b16, 0x0b3126, 0x255346).bands(0xc0dbd2, 0xfbfdf0, 0xb3ad73).type(5, 0));
		VARIETIES.add(new QuartzVariety( 6, "Pink", AmGems.BASIC_PURPLE, 0xff046b, 0xc50248, 0xfd1680).bands(0xff53ac, 0xee94c8, 0x8d6d84).type(8, 0));
		VARIETIES.add(new QuartzVariety( 7, "Enhydro", AmGems.BASIC_RED, 0x5e595f, 0x3b2531, 0x573c33).bands(0x392f3a, 0x9aa1a9).type(6, 0));
		VARIETIES.add(new QuartzVariety( 8, "Cloud", AmGems.BASIC_LIGHT_BLUE, 0xbcbeca, 0xafb0b5, 0x9097aa).bands(0x7d8496, 0xa5abbb).type(6, 0));
		VARIETIES.add(new QuartzVariety( 9, "Polyhedroid", AmGems.BASIC_BLUE, 0x556680, 0x37415a, 0x869bb0).bands(0x757654, 0xf7d6a0).type(8, 0));
		VARIETIES.add(new QuartzVariety(10, "Agua Nueva", AmGems.BASIC_MAGENTA, 0x8d4a76, 0x8f637c, 0x5c327c).bands(0xc8aaf0, 0x926c9d).type(1, 0));
		VARIETIES.add(new QuartzVariety(11, "Blue Lace", AmGems.BASIC_LIGHT_BLUE, 0x1b2886, 0x233096, 0x3e51a0).bands(0x6f7ebf, 0x929acb).type(5, 0));
		VARIETIES.add(new QuartzVariety(12, "Zimbabwe", AmGems.BASIC_BLACK, 0x393b38, 0x757456, 0x666853).bands(0xdead8d, 0xefe4c4).type(7, 0));
		VARIETIES.add(new QuartzVariety(13, "Tree", AmGems.BASIC_GREEN, 0xdee9d8, 0x99b59f, 0xc3d2a9).bands(0x244c41, 0x669777).type(8, 0));
		VARIETIES.add(new QuartzVariety(14, "Lake Superior", AmGems.BASIC_RED, 0xd83428, 0x820900, 0xff5227).bands(0xf4ffe4, 0xd8a0c5).type(8, 0));
		VARIETIES.add(new QuartzVariety(15, "Black", AmGems.BASIC_WHITE, 0x090909, 0x000000, 0x323635).bands(0x3f342e, 0x34281a, 0x20130d).type(1, 0));
		VARIETIES.add(new QuartzVariety(16, "Holly Blue", AmGems.BASIC_BLUE, 0x9367f8, 0x8191c7, 0x5d6da5, 0x8394c9));
		VARIETIES.add(new QuartzVariety(17, "Fire", AmGems.BASIC_RED, 0xaa340e, 0x1e100d, 0x633b2f, 0x75291c).bands(0xe4fe05, 0x5cff46).type(1, 0));
		VARIETIES.add(new QuartzVariety(18, "Peach", AmGems.BASIC_BROWN, 0xe7a281, 0xe1b38f, 0xfcc99c, 0xe4ab9a).bands(0xc73f25, 0xcd785b).type(1, 0));
		VARIETIES.add(new QuartzVariety(19, "Feather", AmGems.BASIC_BLACK, 0x3f3a34, 0x594739, 0x201f1a).bands(0xcbbfa9, 0xf0e4d6).type(2, 0));
		VARIETIES.add(new QuartzVariety(20, "Luna", AmGems.BASIC_SILVER, 0x9899ab, 0x7a7f95, 0xa99ebe).bands(0xa5888c, 0xcfcde2).type(1, 0));
		VARIETIES.add(new QuartzVariety(21, "Spider", AmGems.BASIC_SILVER, 0xcbbfa9, 0xf0e4d6).bands(0x3f3a34, 0x594739, 0x201f1a).type(1, 0));
	}
	 */
	public EntityAgate(World world) {
		super(world);
		this.chargedByTakingDamageNotDelivering = true;
		this.tasks.addTask(2, new EntityAISitStill(this, 1.0D));
		this.tasks.addTask(3, new EntityAIScareMobs(this));
        this.droppedGemItem = AmItems.AGATE_GEM;
		this.droppedCrackedGemItem = AmItems.CRACKED_AGATE_GEM;
	}
	@Override
	public boolean isOverwritten() {
		return true;
	}
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        return super.onInitialSpawn(difficulty, livingdata);
    }
	@Override
	public boolean hasCape() {
		return true;
	}
}