package mod.akrivus.amalgam.tileentity;

import java.util.List;

import mod.akrivus.amalgam.init.AmSounds;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityWailingStone extends TileEntity implements ITickable {
	private boolean wailing = false;
	private int ticksExisted = 0;
	@Override
	public void update() {
		++this.ticksExisted;
		if (this.isWailing() && !this.world.isRemote) {
			if (this.ticksExisted % 6 == 0) {
				this.world.playSound(null, this.pos, AmSounds.WAILING_STONE, SoundCategory.BLOCKS, 1.0F, 1.0F);
				List<EntityLivingBase> entities = this.world.<EntityLivingBase>getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(this.pos).grow(8.0D));
				for (EntityLivingBase entity : entities) {
					if (entity instanceof EntityPlayer) {
						if (entity.getDistanceSq(this.pos) < 8) {
							entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
						}
					}
					if (entity instanceof EntitySlime) {
						entity.attackEntityFrom(DamageSource.FALLING_BLOCK, 2.0F);
					}
				}
			}
		}
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		compound.setBoolean("wailing", this.wailing);
		return compound;
	}
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.wailing = compound.getBoolean("wailing");
	}
	public boolean isWailing() {
		return this.wailing || this.world.isBlockPowered(this.pos);
	}
	public void setWailing(boolean wailing) {
		this.wailing = wailing;
	}
	public void toggleActivate() {
		this.wailing = !this.wailing;
		this.markDirty();
	}
}
