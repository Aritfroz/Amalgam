package mod.amalgam.entity;

import mod.akrivus.kagic.entity.EntityGem;
import net.minecraft.world.World;

public class EntityAmalgamGem extends EntityGem {
	public EntityAmalgamGem(World world) {
		super(world);
	}
	public boolean isOverwritten() {
		return false;
	}
	public float[] getGemPosition() {
		return new float[] {0, 0, 0};
	}
}
