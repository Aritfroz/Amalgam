package mod.amalgam.entity;

import java.util.UUID;

import com.google.common.base.Optional;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityGem extends EntityLiving implements IEntityOwnable, IRangedAttackMob, IEntityAdditionalSpawnData {
	protected static final DataParameter<Optional<UUID>> GEM_GLOBAL_ID 	= EntityDataManager.<Optional<UUID>>createKey(EntityGem.class, DataSerializers.OPTIONAL_UNIQUE_ID);	
	protected static final DataParameter<Optional<UUID>> GEM_AUTHORITY 	= EntityDataManager.<Optional<UUID>>createKey(EntityGem.class, DataSerializers.OPTIONAL_UNIQUE_ID);	
	protected static final DataParameter<Optional<UUID>> GEM_LEADER_ID	= EntityDataManager.<Optional<UUID>>createKey(EntityGem.class, DataSerializers.OPTIONAL_UNIQUE_ID);	
	protected static final DataParameter<Boolean> 	IS_SWINGING_ARMS 	= EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> 	IS_HIGHLIGHTED 		= EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<BlockPos> 	ORIGINAL_POS 		= EntityDataManager.<BlockPos>createKey(EntityGem.class, DataSerializers.BLOCK_POS);
	protected static final DataParameter<Integer> 	ORIGINAL_DIM 		= EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer>	COLOR_DYE_INSIGNIA 	= EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer>	COLOR_RGB_UNIFORM 	= EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer>	COLOR_RGB_SKIN 		= EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer>	COLOR_RGB_HAIR 		= EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer>	COLOR_RGB_GEMSTONE 	= EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer>	VARIANT_HAIR		= EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
	protected static final DataParameter<Integer>	VARIANT_SKIN		= EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
	protected static final DataParameter<BlockPos>	GEMSTONE_POS 		= EntityDataManager.<BlockPos>createKey(EntityGem.class, DataSerializers.BLOCK_POS);
	protected static final DataParameter<Integer>	GEMSTONE_CUT 		= EntityDataManager.<Integer>createKey(EntityGem.class, DataSerializers.VARINT);
	protected static final DataParameter<Boolean>	HAS_VISOR 			= EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);	
	protected static final DataParameter<Boolean>	IS_DEFECTIVE		= EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean>	IS_PERFECT			= EntityDataManager.<Boolean>createKey(EntityGem.class, DataSerializers.BOOLEAN);
	protected boolean changesScaleBasedOnCondition = true;
	protected int defaultUniformColor = 0;
	public EntityGem(World world) {
		super(world);
		this.dataManager.register(GEM_GLOBAL_ID, Optional.absent());
		this.dataManager.register(GEM_AUTHORITY, Optional.absent());
		this.dataManager.register(GEM_LEADER_ID, Optional.absent());
		this.dataManager.register(IS_SWINGING_ARMS, false);
		this.dataManager.register(IS_HIGHLIGHTED, false);
		this.dataManager.register(ORIGINAL_POS, BlockPos.ORIGIN);
		this.dataManager.register(ORIGINAL_DIM, 0);
		this.dataManager.register(COLOR_DYE_INSIGNIA, -1);
		this.dataManager.register(COLOR_RGB_UNIFORM, 0);
		this.dataManager.register(COLOR_RGB_SKIN, 0);
		this.dataManager.register(COLOR_RGB_HAIR, 0);
		this.dataManager.register(COLOR_RGB_GEMSTONE, 0);
		this.dataManager.register(VARIANT_HAIR, 0);
		this.dataManager.register(VARIANT_SKIN, 0);
		this.dataManager.register(GEMSTONE_POS, BlockPos.ORIGIN);
		this.dataManager.register(GEMSTONE_CUT, 0);
		this.dataManager.register(HAS_VISOR, false);
		this.dataManager.register(IS_DEFECTIVE, false);
		this.dataManager.register(IS_PERFECT, false);
	}
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData data) {
		this.setGemGlobalID(UUID.randomUUID());
		if (data instanceof GemSpawnData) {
			GemSpawnData gemSpawnData = (GemSpawnData)(data);
			this.setGemAuthority(gemSpawnData.getAuthority());
			this.setInsigniaColor(gemSpawnData.getColor());
			this.setDefective(gemSpawnData.isDefective());
			this.setPerfect(gemSpawnData.isPerfect());
		}
		this.setOriginalPosition(this.getPosition());
		this.setOriginalDimension(this.dimension);
		this.setUniformColor(this.defaultUniformColor);
		this.setSkinColor(this.generateSkinColor());
		this.setHairColor(this.generateHairColor());
		this.setGemstoneColor(this.generateGemstoneColor());
		this.setHairVariant(this.generateHairVariant());
		this.setSkinVariant(this.generateSkinVariant());
		this.setGemstonePosition(this.generateGemstonePosition());
		this.setGemstoneCut(this.generateGemstoneCut());
		this.setHasVisor(this.generateVisor());
		this.setHealth(this.getMaxHealth());
		return data;
	}
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		if (compound.hasKey("GemGlobalID")) {
			this.setGemGlobalID(UUID.fromString(compound.getString("GemGlobalID")));
		}
		if (compound.hasKey("GemAuthority")) {
			this.setGemAuthority(UUID.fromString(compound.getString("GemAuthority")));
		}
		if (compound.hasKey("GemLeader")) {
			this.setGemLeader(UUID.fromString(compound.getString("GemLeader")));
		}
		this.setSwingingArms(compound.getBoolean("SwingingArms"));
		this.setHighlighted(compound.getBoolean("Highlighted"));
		this.setOriginalPosition(BlockPos.fromLong(compound.getLong("OriginalPosition")));
		this.setOriginalDimension(compound.getInteger("OriginalDimension"));
		this.setInsigniaColor(compound.getInteger("InsigniaColor"));
		this.setUniformColor(compound.getInteger("UniformColor"));
		this.setSkinColor(compound.getInteger("SkinColor"));
		this.setHairColor(compound.getInteger("HairColor"));
		this.setGemstoneColor(compound.getInteger("GemstoneColor"));
		this.setHairVariant(compound.getInteger("HairVariant"));
		this.setSkinVariant(compound.getInteger("SkinVariant"));
		this.setGemstonePosition(BlockPos.fromLong(compound.getLong("GemstonePosition")));
		this.setGemstoneCut(compound.getInteger("GemstoneCut"));
		this.setHasVisor(compound.getBoolean("HasVisor"));
		this.setDefective(compound.getBoolean("Defective"));
		this.setPerfect(compound.getBoolean("Perfect"));
	}
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		if (this.getGemGlobalID() != null) {
			compound.getString("GemGlobalID", this.getGemGlobal().toString());
		}
		if (this.getGemAuthority() != null) {
			compound.getString("GemAuthority", this.getGemAuthority().toString());
		}
		if (this.getGemLeader() != null) {
			compound.getString("GemLeader", this.getGemLeader().toString());
		}
		compound.setBoolean("SwingingArms", this.getSwingingArms());
		compound.setBoolean("Highlighted", this.isHighlighted());
		compound.setLong("OriginalPosition", this.getOriginalPosition().toLong());
		compound.setInteger("OriginalDimension", this.getOriginalDimension());
		compound.setInteger("InsigniaColor", this.getInsigniaColor());
		compound.setInteger("UniformColor", this.getUniformColor());
		compound.setInteger("SkinColor", this.getSkinColor());
		compound.setInteger("HairColor", this.getHairColor());
		compound.setInteger("GemstoneColor", this.getGemstoneColor());
		compound.setInteger("HairVariant", this.getHairVariant());
		compound.setInteger("SkinVariant", this.getSkinVariant());
		compound.setLong("GemstonePosition", this.getGemstonePosition().toLong());
		compound.setInteger("GemstoneCut", this.getGemstoneCut());
		compound.setBoolean("HasVisor", this.getHasVisor());
		compound.setBoolean("Defective", this.isDefective());
		compound.setBoolean("Perfect", this.isPerfect());
	}
	@Override
	public void onUpdate() {
        super.onUpdate();
        if (this.world.getDifficulty().equals(EnumDifficulty.PEACEFUL)) {
            this.dead = this.getHealth() > 0;
        }
    }
	public void setDefective(boolean defective) {
		super.setDefective(defective);
		if (this.isDefective() && this.changesScaleBasedOnCondition) {
			this.setSize(this.width * 0.5F, this.height * 0.5F);
		}
	}
	public boolean isDefective() {
		return this.dataManager.get(IS_DEFECTIVE);
	}
	public void setPerfect(boolean perfect) {
		super.setPrimary(primary);
		if (primary) {
			this.setSize(this.width * 1.5F, this.height * 1.5F);
		}
	}
	@Override
	public String getSpecificName() {
		return super.getSpecificName().replaceAll("Cut", this.getTitle());
	}
	public String getTitle() {
		switch (this.getGemCut()) {
		case CABOCHON: case TEARDROP:
			return "Cabochon";
		case BISMUTH:
			return "Cell";
		default:
			return "Cut";
		}
	}
	public boolean isOverwritten() {
		return false;
	}
	public float[] getGemPosition() {
		return new float[] {0, 0, 0};
	}
	public static class GemSpawnData implements IEntityLivingData {
		protected final UUID authority;
		protected final int color;
		protected final boolean defective;
		protected final boolean perfect;
		public GemSpawnData(UUID authority, int color, boolean defective, boolean perfect) {
			this.authority = authority;
			this.color = color;
			this.defective = defective;
			this.perfect = perfect;
		}
		public UUID getAuthority() {
			return this.authority;
		}
		public int getColor() {
			return this.color;
		}
		public boolean isDefective() {
			return this.defective;
		}
		public boolean isPerfect() {
			return this.perfect;
		}
	}
	public static interface IGemVariant {
		public int[] getSkinColor();
		public int[] getHairColor();
		public int[] getGemColor();
		public String getName();
		public int getDamage();
	}
}
