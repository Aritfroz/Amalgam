package mod.amalgam.items;

import mod.akrivus.kagic.entity.EntityGem;
import mod.amalgam.entity.EntityAmalgamGem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemAmalgamGem extends mod.akrivus.kagic.items.ItemGem {
	public Class<? extends EntityAmalgamGem> gemClass;
	public ItemAmalgamGem(Class<? extends EntityAmalgamGem> gemClass, String name, boolean cracked) {
		super(name, cracked);
		this.gemClass = gemClass;
	}
	public ItemAmalgamGem(Class<? extends EntityAmalgamGem> gemClass, String name) {
		super(name);
		this.gemClass = gemClass;
	}
	@Override
	public boolean spawnGem(World world, EntityPlayer player, BlockPos pos, ItemStack stack) {
		if (!world.isRemote) {
			if (this.isCracked) {
				return false;
			}
			else {
				try {
					EntityGem newGem = (EntityAmalgamGem)(this.gemClass.getConstructors()[0].newInstance(world));
					newGem.readFromNBT(stack.getTagCompound());
					newGem.setUniqueId(MathHelper.getRandomUUID(world.rand));
					newGem.setPosition(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
	            	newGem.onInitialSpawn(world.getDifficultyForLocation(pos), null);
	            	newGem.setRestPosition(newGem.getPosition());
					newGem.setHealth(newGem.getMaxHealth());
					newGem.setAttackTarget(null);
					newGem.extinguish();
					newGem.clearActivePotions();
					world.spawnEntity(newGem);
				}
				catch (Exception e) {
					e.printStackTrace();
					System.out.println("Error creating gem: " + e.getMessage());
				}
			}
		}
		return false;
	}
}
