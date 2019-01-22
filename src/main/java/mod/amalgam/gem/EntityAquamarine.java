package mod.amalgam.gem;

import java.util.ArrayList;

import mod.amalgam.entity.EntityGem;
import mod.amalgam.init.AmGems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityFlyHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAquamarine extends EntityGem implements IAnimals {
	public static final ArrayList<ResourceLocation> HAIRSTYLES = new ArrayList<ResourceLocation>();
	static {
		
	}
	public static final int SKIN_COLOR_BEGIN = 0x8BFFFE;
	public static final int SKIN_COLOR_END = 0x3AE2E4;
	public static final int HAIR_COLOR_BEGIN = 0x13A9DF;
	public static final int HAIR_COLOR_END = 0x137EDF;
	public EntityAquamarine(World world) {
		super(world);
		this.setSize(0.4F, 0.8F);
		this.nativeColor = AmGems.BASIC_BLUE;
		this.moveHelper = new EntityFlyHelper(this);
		this.seePastDoors();
	}
	@Override
	protected PathNavigate createNavigator(World world) {
        return new PathNavigateFlying(this, world);
    }
	@Override
	protected int generateGemColor() {
    	return 0x4FFCE7;
    }
	@Override
	public void convertGems(int placement) {
    	this.setGemCut(GemCuts.TINY.id);
    	switch (placement) {
    	case 0:
    		this.setGemPlacement(GemPlacements.LEFT_CHEEK.id);
    		break;
    	case 1:
    		this.setGemPlacement(GemPlacements.RIGHT_CHEEK.id);
    		break;
    	case 2:
    		this.setGemPlacement(GemPlacements.FOREHEAD.id);
    		break;
    	}
    }
	
	@Override
	public void setDefective(boolean defective) {
		super.setDefective(defective);
		if (defective) {
			this.moveHelper = new EntityMoveHelper(this);
		}
		else {
			this.moveHelper = new EntityFlyHelper(this);
		}
	}
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
    }
	/********************************************************
	 * Methods related to entity flight.                    *
	 ********************************************************/
	@Override
	public void fall(float distance, float damageMultiplier) {
		super.fall(distance, damageMultiplier);
    }
    @Override
	protected void updateFallState(double y, boolean onGround, IBlockState state, BlockPos pos) {
    	super.updateFallState(y, onGround, state, pos);
    }
    @Override
	public boolean isOnLadder() {
        return false;
    }
	@Override
	protected int generateSkinColor() {
		ArrayList<Integer> skinColors = new ArrayList<Integer>();
		skinColors.add(EntityAquamarine.SKIN_COLOR_BEGIN);
		skinColors.add(EntityAquamarine.SKIN_COLOR_END);
		return Colors.arbiLerp(skinColors);
	}
	@Override
	protected int generateHairStyle() {
		return this.rand.nextInt(EntityAquamarine.HAIRSTYLES.size());
	}
	@Override
	protected int generateHairColor() {
		ArrayList<Integer> hairColors = new ArrayList<Integer>();
		hairColors.add(EntityAquamarine.HAIR_COLOR_BEGIN);
		hairColors.add(EntityAquamarine.HAIR_COLOR_END);
		return Colors.arbiLerp(hairColors);
	}
}
