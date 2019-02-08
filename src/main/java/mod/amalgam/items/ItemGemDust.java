package mod.amalgam.items;

import java.util.ArrayList;

import mod.amalgam.init.Amalgam;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;

public class ItemGemDust extends Item {
	public static final ArrayList<Item> DUST_COLORS = new ArrayList<Item>();
	public int color;
	public ItemGemDust(int index) {
		String name = EnumDyeColor.byMetadata(index).toString().toLowerCase();
		this.setUnlocalizedName(name + "_gem_dust");
		this.setMaxStackSize(64);
		this.setCreativeTab(Amalgam.CREATIVE_TAB);
		ItemGemDust.DUST_COLORS.add(this);
		this.color = index;
	}
}
