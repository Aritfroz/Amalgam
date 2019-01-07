package mod.amalgam.gem;

import java.util.ArrayList;

import mod.amalgam.entity.EntityQuartz;
import mod.amalgam.init.AmItems;
import mod.heimrarnadalr.kagic.util.Colors;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityCitrine extends EntityQuartz implements IAnimals {
	private static final DataParameter<Integer> DEFECTIVE_COLOR = EntityDataManager.<Integer>createKey(EntityCitrine.class, DataSerializers.VARINT);
	public static final int SKIN_COLOR_BEGIN = 0xFFF37C; 
	public static final int SKIN_COLOR_END = 0xFF9400; 
	public static final int HAIR_COLOR_BEGIN = 0xF1DA8F;
	public static final int HAIR_COLOR_END = 0xFFFF01;
	public EntityCitrine(World world) {
		super(world);
		this.chargedByTakingDamageNotDelivering = false;
        this.droppedGemItem = AmItems.CITRINE_GEM;
		this.droppedCrackedGemItem = AmItems.CRACKED_CITRINE_GEM;
        this.dataManager.register(DEFECTIVE_COLOR, 0);
	}
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		if (this.isDefective()) {
			this.setCustomNameTag(new TextComponentTranslation("entity.ametrine.name").getUnformattedComponentText());
			this.setSpecial(this.rand.nextInt(2));
			this.generateDefectiveColor();
		}
		this.nativeColor = this.getSpecial() == 1 ? 8 : 12;
		this.dataManager.set(DEFECTIVE_COLOR, this.generateDefectiveColor());
		return super.onInitialSpawn(difficulty, livingdata);
    }
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("defectiveColor", this.dataManager.get(DEFECTIVE_COLOR));
    }
    @Override
	public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.dataManager.set(DEFECTIVE_COLOR, compound.getInteger("defectiveColor"));
    }
    @Override
    public int generateGemColor() {
    	return this.getSpecial() == 1 ? 0xDC64FD : 0xEBFD64;
    }
	@Override
	public int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		if (this.getSpecial() == 1) {
			skinColors.add(EntityAmethyst.SKIN_COLOR_BEGIN);
			skinColors.add(EntityAmethyst.SKIN_COLOR_END);
		}
		else {
			skinColors.add(EntityCitrine.SKIN_COLOR_BEGIN);
			skinColors.add(EntityCitrine.SKIN_COLOR_END);
		}
		return Colors.arbiLerp(skinColors);
	}
	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		if (this.getSpecial() == 1) {
			hairColors.add(EntityAmethyst.HAIR_COLOR_BEGIN);
			hairColors.add(EntityAmethyst.HAIR_COLOR_END);
		}
		else {
			hairColors.add(EntityCitrine.HAIR_COLOR_BEGIN);
			hairColors.add(EntityCitrine.HAIR_COLOR_END);
		}
		return Colors.arbiLerp(hairColors);
	}
	public int generateDefectiveColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		if (this.getSpecial() == 0) {
			skinColors.add(EntityAmethyst.SKIN_COLOR_BEGIN);
			skinColors.add(EntityAmethyst.SKIN_COLOR_END);
		}
		else {
			skinColors.add(EntityCitrine.SKIN_COLOR_BEGIN);
			skinColors.add(EntityCitrine.SKIN_COLOR_END);
		}
		return Colors.arbiLerp(skinColors);
	}
	public int getDefectiveColor() {
		return this.dataManager.get(DEFECTIVE_COLOR);
	}
}