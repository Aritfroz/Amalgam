package mod.amalgam.blocks;

import java.util.Random;

import mod.amalgam.init.AmBlocks;
import mod.amalgam.init.Amalgam;
import mod.amalgam.injection.InjectorResult;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDrainLily extends BlockBush {
	public BlockDrainLily() {
		super(Material.PLANTS, MapColor.CYAN);
		this.setUnlocalizedName("drain_lily");
		this.setLightLevel(4.0F);
		this.setTickRandomly(true);
		this.setCreativeTab(Amalgam.CREATIVE_TAB);
	}
	@Override
	protected boolean canSustainBush(IBlockState state) {
        return state.isTopSolid();
    }
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return world.isSideSolid(pos.down(), EnumFacing.UP);
    }
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
		IBlockState down = world.getBlockState(pos.down());
		if (down.isSideSolid(world, pos, EnumFacing.UP)) {
			super.updateTick(world, pos, state, random);
			if (down.getMaterial() == BlockDrainBlock.DRAINED) {
				BlockPos check = pos.add(world.rand.nextInt(3) - 1, 0, world.rand.nextInt(3) - 1);
				if (world.isAirBlock(check) && world.isSideSolid(check.down(), EnumFacing.UP)) {
					world.setBlockState(check, AmBlocks.DRAIN_LILY.getDefaultState());
				}
			}
			else {
				InjectorResult.drain(world, pos.down());
			}
		}
		else {
			world.destroyBlock(pos, true);
		}
	}
}
