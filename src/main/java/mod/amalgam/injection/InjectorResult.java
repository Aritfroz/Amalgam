package mod.amalgam.injection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.event.DrainBlockEvent;
import mod.akrivus.kagic.init.ModBlocks;
import mod.akrivus.kagic.util.injector.ExitHole;
import mod.amalgam.init.AmBlocks;
import mod.amalgam.init.AmGems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class InjectorResult {
	private final EntityGem gem;
	private final BlockPos position;
	private final boolean isDefective;
	private final boolean isPrimary;
	private final ExitHole exitHole;
	private final GemSpawnData data;
	
	public InjectorResult(EntityGem gem, BlockPos position, boolean isDefective, boolean isPrimary, ExitHole exitHole, GemSpawnData data) {
		this.gem = gem;
		this.position = position;
		this.isDefective = isDefective;
		this.isPrimary = isPrimary;
		this.exitHole = exitHole;
		this.data = data; 
	}
	public EntityGem getGem() {
		return this.gem;
	}
	public String getName() {
		return this.gem.getName();
	}
	public BlockPos getPosition() {
		return this.position;
	}
	public boolean isPrimary() {
		return this.isPrimary;
	}
	public boolean isDefective() {
		return this.isDefective;
	}
	public ExitHole getExitHole() {
		return this.exitHole;
	}
	public void generate(World world) {
		this.exitHole.emerge(world);
		if (this.isDefective()) {
			this.gem.setDefective(true);
		}
		if (this.isPrimary()) {
			this.gem.setPrimary(true);
		}
		world.spawnEntity(this.gem);
		this.gem.onInitialSpawn(world.getDifficultyForLocation(this.getPosition()), null);
	}
	public static InjectorResult create(World world, BlockPos pos, boolean generate) {
		HashMap<ResourceLocation, ArrayList<CruxEntry>> cruxes = AmGems.CRUXES;
		ArrayList<IBlockState> image = new ArrayList<IBlockState>();
		for (int y = -2; y < 2; ++y) {
			for (int x = -2; x < 2; ++x) {
				for (int z = -2; z < 2; ++z) {
					image.add(world.getBlockState(pos.add(x, y, z)));
				}
			}
		}
		double volume = 64.0F;
		HashMap<ResourceLocation, Double> yields = new HashMap<ResourceLocation, Double>();
		Iterator<ResourceLocation> gits = cruxes.keySet().iterator();
		while (gits.hasNext()) {
			ResourceLocation gem = gits.next();
			Iterator<CruxEntry> crits = cruxes.get(gem).iterator();
			yields.put(gem, 0.0);
			while (crits.hasNext()) {
				CruxEntry crit = crits.next();
				Iterator<IBlockState> bits = image.iterator();
				while (bits.hasNext()) {
					if (bits.next().equals(crit.getState())) {
						double yield = crit.getYield() * (1.0D - (pos.getY() / crit.getLimit()));
						yields.put(gem, yields.get(gem) + yield);
					}
				}
			}
		}
		double random = Math.random() * volume;
		ResourceLocation key = null;
		Iterator<ResourceLocation> yits = yields.keySet().iterator();
		while (yits.hasNext()) {
			ResourceLocation gem = yits.next();
			double yield = yields.get(gem);
			random -= yield;
			if (random <= 0.0) {
				random = yield;
				key = gem;
				break;
			}
		}
		EntityGem gem = null;
		try {
			gem = (EntityGem)(AmGems.GEM_LIST.get(key).getConstructors()[0].newInstance(world));
		} catch (Exception e) {
			System.out.println("Gem called '" + key + "' failed to load!");
			return null;
		}
		ExitHole exit = null;
		if (generate) {
			exit = ExitHole.create(world, pos, gem.height, random > (volume * 0.8));
			exit.emerge(world);
			for (int y = -2; y < 2; ++y) {
				for (int x = -2; x < 2; ++x) {
					for (int z = -2; z < 2; ++z) {
						InjectorResult.drain(world, pos.add(x, y, z));
					}
				}
			}
		}
		return new InjectorResult(gem, pos, random < (volume * 0.1), random > (volume * 0.8), exit, null);
	}
	public static void drain(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();
		Material material = state.getMaterial();
		if (MinecraftForge.EVENT_BUS.post(new DrainBlockEvent(world, pos, state, block))) return;
		if (state.getBlockHardness(world, pos) >= 0) {
			if (material == Material.ROCK || state.isFullCube()) {
				if (pos.getY() % 6 == 0 || pos.getY() % 6 == 1) {
					world.setBlockState(pos, ModBlocks.DRAINED_BLOCK_2.getDefaultState());
				}
				else if (pos.getY() % 5 == 0) {
					world.setBlockState(pos, ModBlocks.DRAINED_BANDS.getDefaultState());
				}
				else {
					world.setBlockState(pos, ModBlocks.DRAINED_BLOCK.getDefaultState());
				}
			}
			if (material == Material.SAND) {
				world.setBlockState(pos, ModBlocks.DRAINED_GRAVEL.getDefaultState());
			}
			if (material == Material.PLANTS) {
				world.setBlockState(pos, AmBlocks.DRAIN_LILY.getDefaultState());
			}
		}
	}
}