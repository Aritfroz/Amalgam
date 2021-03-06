package mod.amalgam.items;

import java.util.ArrayList;

import mod.amalgam.init.Amalgam;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;

public class ItemGemShard extends Item {
	public static final ArrayList<Item> SHARD_COLORS = new ArrayList<Item>();
	public int color;
	public ItemGemShard(int index) {
		String name = EnumDyeColor.byMetadata(index).toString().toLowerCase();
		this.setUnlocalizedName(name + "_gem_shard");
		this.setMaxStackSize(64);
		this.setCreativeTab(Amalgam.CREATIVE_TAB);
		ItemGemShard.SHARD_COLORS.add(this);
		this.color = index;
	}
}
