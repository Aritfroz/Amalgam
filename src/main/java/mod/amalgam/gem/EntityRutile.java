package mod.amalgam.gem;

import java.util.ArrayList;

import mod.amalgam.entity.EntityGem;
import net.minecraft.entity.INpc;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityRutile extends EntityGem implements INpc {
	public static final ArrayList<ResourceLocation> HAIRSTYLES = new ArrayList<ResourceLocation>();
	static {
		
	}
	private static final int SKIN_COLOR_BEGIN = 0x5F243F; 
	private static final int SKIN_COLOR_END = 0xC4695C; 
	private static final int HAIR_COLOR_BEGIN = 0x0E0005;
	private static final int HAIR_COLOR_END = 0x832C13;
	public EntityRutile(World world) {
		super(world);
		this.nativeColor = 14;
		this.setSize(0.6F, 1.9F);
		this.seePastDoors();
	}
	@Override
	protected int generateGemColor() {
    	return 0xB7513D;
    }
	@Override
	protected int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntityRutile.SKIN_COLOR_BEGIN);
		skinColors.add(EntityRutile.SKIN_COLOR_END);
		return Colors.arbiLerp(skinColors);
	}
	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityRutile.HAIRSTYLES.size());
	}
	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		hairColors.add(EntityRutile.HAIR_COLOR_BEGIN);
		hairColors.add(EntityRutile.HAIR_COLOR_END);
		return Colors.arbiLerp(hairColors);
	}
}
