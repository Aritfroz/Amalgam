package mod.amalgam.gem;

import java.util.ArrayList;
import java.util.HashMap;

import mod.amalgam.entity.EntityQuartz;
import mod.amalgam.init.AmGems;
import mod.amalgam.init.AmItems;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityAmethyst extends EntityQuartz implements IAnimals {
	public static final HashMap<Integer, ResourceLocation> AMETHYST_HAIR_STYLES = new HashMap<Integer, ResourceLocation>();
	public static final int SKIN_COLOR_BEGIN = 0xD2BFE1; 
	public static final int SKIN_COLOR_MID = 0xB899CA; 
	public static final int SKIN_COLOR_END = 0xB5367D; 
	public static final int HAIR_COLOR_BEGIN = 0xBD79C9;
	public static final int HAIR_COLOR_MID_1 = 0x9877B1;
	public static final int HAIR_COLOR_MID_2 = 0xF9E4FF;
	public static final int HAIR_COLOR_MID_3 = 0xFFFDFF; 
	public static final int HAIR_COLOR_MID_4 = 0xDCD3EF; 
	public static final int HAIR_COLOR_MID_5 = 0xAD859F;
	public static final int HAIR_COLOR_END = 0xC47DA3; 
	public EntityAmethyst(World world) {
		super(world);
		this.nativeColor = AmGems.BASIC_GRAY;
		this.chargedByTakingDamageNotDelivering = true;
        this.droppedGemItem = AmItems.AMETHYST_GEM;
		this.droppedCrackedGemItem = AmItems.CRACKED_AMETHYST_GEM;
	}
    @Override
    protected int generateGemColor() {
    	return 0xDC64FD;
    }
	@Override
	public int generateSkinColor() {
		return Colors.triLerp(EntityAmethyst.SKIN_COLOR_BEGIN, EntityAmethyst.SKIN_COLOR_MID, EntityAmethyst.SKIN_COLOR_END);
	}
	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		hairColors.add(EntityAmethyst.HAIR_COLOR_BEGIN);
		hairColors.add(EntityAmethyst.HAIR_COLOR_MID_1);
		hairColors.add(EntityAmethyst.HAIR_COLOR_MID_2);
		hairColors.add(EntityAmethyst.HAIR_COLOR_MID_3);
		hairColors.add(EntityAmethyst.HAIR_COLOR_MID_4);
		hairColors.add(EntityAmethyst.HAIR_COLOR_MID_5);
		hairColors.add(EntityAmethyst.HAIR_COLOR_END);
		return Colors.arbiLerp(hairColors);
	}
}
