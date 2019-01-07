package mod.amalgam.gem;

import java.util.ArrayList;

import mod.amalgam.entity.EntityQuartz;
import mod.amalgam.init.AmGems;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.entity.INpc;
import net.minecraft.world.World;

public class EntityRoseQuartz extends EntityQuartz implements INpc {	
	public static final int SKIN_COLOR_BEGIN = 0xFDDAC9;
	public static final int SKIN_COLOR_END = 0xFDDCCB;
	public static final int HAIR_COLOR_BEGIN = 0xFDAECB;
	public static final int HAIR_COLOR_END = 0xFF5EEC;
	public EntityRoseQuartz(World world) {
		super(world);
		this.nativeColor = AmGems.BASIC_PINK;
	}
	@Override
	protected int generateGemColor() {
		return 0xFFA2E6;
	}
	@Override
	protected int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntityRoseQuartz.SKIN_COLOR_BEGIN);
		skinColors.add(EntityRoseQuartz.SKIN_COLOR_END);
		return Colors.arbiLerp(skinColors);
	}
	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		hairColors.add(EntityRoseQuartz.HAIR_COLOR_BEGIN);
		hairColors.add(EntityRoseQuartz.HAIR_COLOR_END);
		return Colors.arbiLerp(hairColors);
	}
}
