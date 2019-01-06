package mod.amalgam.items;

import java.util.ArrayList;

import mod.akrivus.kagic.init.ModCreativeTabs;
import net.minecraft.item.Item;

public class ItemShard extends Item {
	public static final ArrayList<Item> SHARD_COLORS = new ArrayList<Item>();
	public int color;
	public ItemShard(int index) {
		this.setUnlocalizedName("gem_shard_" + index);
		this.setMaxStackSize(64);
		this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
		ItemShard.SHARD_COLORS.add(this);
		this.color = index;
	}
}
