package mod.amalgam.gem;

import java.util.ArrayList;

import mod.amalgam.entity.EntityQuartz;
import mod.amalgam.init.AmGems;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.World;

public class EntityCarnelian extends EntityQuartz implements IAnimals {
	public static final int SKIN_COLOR_BEGIN = 0xE1764D;
	public static final int SKIN_COLOR_END = 0xC5307D;
	public static final int HAIR_COLOR_BEGIN = 0xF24807;
	public static final int HAIR_COLOR_END = 0x4D0043;
	public EntityCarnelian(World world) {
		super(world);
		this.nativeColor = AmGems.BASIC_RED;
		this.isImmuneToFire = true;
	}
	@Override
	protected int generateGemColor() {
    	return 0xFF2D5D;
    }
	@Override
	protected int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntityCarnelian.SKIN_COLOR_BEGIN);
		skinColors.add(EntityCarnelian.SKIN_COLOR_END);
		return Colors.arbiLerp(skinColors);
	}
	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		hairColors.add(EntityCarnelian.HAIR_COLOR_BEGIN);
		hairColors.add(EntityCarnelian.HAIR_COLOR_END);
		return Colors.arbiLerp(hairColors);
	}
}
