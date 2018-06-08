package mod.akrivus.amalgam.gem.ai;

import mod.akrivus.amalgam.entity.EntitySpitball;
import mod.akrivus.amalgam.gem.EntityNephrite;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityAIRemoveHazards extends EntityAIMoveGemToBlock {
	private final EntityNephrite gem;
	private final World world;
	private double distance = 0;
	private int blockTime = 0;
	private int delay = 0;
	public EntityAIRemoveHazards(EntityNephrite gem, double speed) {
		super(gem, speed, 4);
		this.gem = gem;
		this.world = gem.world;
	}
	public boolean shouldExecute() {
		if (this.world.getGameRules().getBoolean("mobGriefing")) {
			if (delay > 20 + this.gem.getRNG().nextInt(20)) {
				this.runDelay = 0;
				return super.shouldExecute();
			}
			else {
				++this.delay;
			}
			return false;
		}
		return false;
	}
	public boolean shouldContinueExecuting() {
		return super.shouldContinueExecuting() && this.gem.getAttackTarget() == null && !this.gem.getNavigator().noPath();
	}
	public void startExecuting() {
		super.startExecuting();
	}
	public void resetTask() {
		this.distance = 0;
		super.resetTask();
	}
	public void updateTask() {
		this.gem.getLookHelper().setLookPosition((double) this.destinationBlock.getX() + 0.5D, (double)(this.destinationBlock.getY() + 1), (double) this.destinationBlock.getZ() + 0.5D, 10.0F, (float) this.gem.getVerticalFaceSpeed());
		if (this.blockTime > 10 && this.distance < 3) {
			this.gem.world.destroyBlock(this.destinationBlock, true);
			this.blockTime = 0;
		}
		++this.blockTime;
		super.updateTask();
	}
	protected boolean shouldMoveTo(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		if (block == Blocks.MAGMA || block == Blocks.TNT || block == Blocks.WEB
				|| state.getMaterial() == Material.CACTUS || state.getMaterial() == Material.FIRE
				|| state.getMaterial() == Material.LAVA) {
			return this.hasAir(pos);
		}
		return false;
	}
	protected boolean hasAir(BlockPos pos) {
		if (this.gem.world.isAirBlock(pos.up())) {
			return true;
		}
		return false;
	}
}