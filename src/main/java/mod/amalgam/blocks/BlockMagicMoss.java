package mod.amalgam.blocks;

import java.util.Random;

import mod.amalgam.init.AmBlocks;
import mod.amalgam.init.Amalgam;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMagicMoss extends Block {
	protected static final AxisAlignedBB MAGIC_MOSS_SHORT_SELECTION = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);
	protected static final AxisAlignedBB MAGIC_MOSS_BLOCK_SELECTION = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB MAGIC_MOSS_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public static final PropertyBool SHORT = PropertyBool.create("short");
	public BlockMagicMoss() {
		super(Material.GROUND, MapColor.GREEN);
		this.setUnlocalizedName("magic_moss");
		this.setHardness(0.4F);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SHORT, false));
		this.setTickRandomly(true);
		this.setCreativeTab(Amalgam.CREATIVE_TAB);
	}
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			entity.attackEntityFrom(DamageSource.IN_WALL, 0.1F);
		}
	    entity.motionX = 0.0D;
	    entity.motionY = 0.0D;
	    entity.motionZ = 0.0D;
    }
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
		super.updateTick(world, pos, state, random);
		if (world.canSeeSky(pos) && world.isDaytime()) {
			BlockPos check = pos.add((random.nextBoolean() ? -1 : random.nextInt(2)), 0, (random.nextBoolean() ? -1 : random.nextInt(2)));
			if (world.getBlockState(check).getMaterial() == Material.WATER) {
				world.setBlockState(check, state);
				return;
			}
			else if (world.isAirBlock(pos.up())) {
				if (world.rand.nextBoolean()) {
					world.setBlockState(pos.up(), AmBlocks.MOSS_ROSE.getDefaultState());
				}
			}
		}
	}
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    	boolean set = world.getBlockState(pos.up()).getBlock() == AmBlocks.MOSS_ROSE || world.isAirBlock(pos.up());
		return this.getDefaultState().withProperty(SHORT, set);
    }
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		boolean set = world.getBlockState(pos.up()).getBlock() == AmBlocks.MOSS_ROSE || world.isAirBlock(pos.up());
		world.setBlockState(pos, state.withProperty(SHORT, set));
		super.neighborChanged(state, world, pos, block, fromPos);
    }
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		boolean set = world.getBlockState(pos.up()).getBlock() == AmBlocks.MOSS_ROSE || world.isAirBlock(pos.up());
		world.setBlockState(pos, state.withProperty(SHORT, set));
    	super.onBlockAdded(world, pos, state);
    }
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(SHORT)) {
			return MAGIC_MOSS_SHORT_SELECTION;
		}
		else {
			return MAGIC_MOSS_BLOCK_SELECTION;
		}
    }
	@Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return MAGIC_MOSS_AABB;
    }
	@Override
	public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
	@Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
	@Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        return true;
    }
	@Override
	public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(SHORT, meta == 0);
    }
	@Override
    public int getMetaFromState(IBlockState state) {
		if (state.getValue(SHORT)) {
			return 0;
		}
        return 1;
    }
	@Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] { SHORT });
    }
}
