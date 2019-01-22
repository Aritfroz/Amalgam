package mod.amalgam.gem;

import java.util.ArrayList;

import mod.amalgam.entity.EntityQuartz;
import mod.amalgam.init.AmGems;
import mod.amalgam.init.AmItems;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.World;

public class EntityAquaAuraQuartz extends EntityQuartz implements IAnimals {
	public static final int SKIN_COLOR_BEGIN = 0x00AADF; 
	public static final int SKIN_COLOR_END = 0x9796C0; 
	public static final int HAIR_COLOR_BEGIN = 0x73C6DD;
	public static final int HAIR_COLOR_MID = 0xB6ECEF;
	public static final int HAIR_COLOR_END = 0xF9CEFC;
	public EntityAquaAuraQuartz(World world) {
		super(world);
		this.nativeColor = AmGems.BASIC_LIGHT_BLUE;
		this.chargedByTakingDamageNotDelivering = true;
        this.droppedGemItem = AmItems.AQUA_AURA_QUARTZ_GEM;
		this.droppedCrackedGemItem = AmItems.CRACKED_AQUA_AURA_QUARTZ_GEM;
	}
    @Override
    public int generateGemColor() {
    	return 0x0CC2EA;
    }
	@Override
	public int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntityAquaAuraQuartz.SKIN_COLOR_BEGIN);
		skinColors.add(EntityAquaAuraQuartz.SKIN_COLOR_END);
		return Colors.arbiLerp(skinColors);
	}
	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		hairColors.add(EntityAquaAuraQuartz.HAIR_COLOR_BEGIN);
		hairColors.add(EntityAquaAuraQuartz.HAIR_COLOR_MID);
		hairColors.add(EntityAquaAuraQuartz.HAIR_COLOR_END);
		return Colors.arbiLerp(hairColors);
	}
}