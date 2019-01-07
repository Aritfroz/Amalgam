package mod.amalgam.items;

import mod.amalgam.entity.EntityAmalgamGem;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemColoredAmalgamGem extends ItemAmalgamGem implements IItemColor {
	public ItemColoredAmalgamGem(Class<? extends EntityAmalgamGem> gemClass, String name, boolean cracked) {
		super(gemClass, name, cracked);
	}
	public ItemColoredAmalgamGem(Class<? extends EntityAmalgamGem> gemClass, String name) {
		super(gemClass, name);
	}
	@Override
	public int colorMultiplier(ItemStack stack, int tint) {
		if (stack.hasTagCompound()) {
			NBTTagCompound compound = stack.getTagCompound();
			return compound.getInteger("gemColor");
		}
		return 0xFFFFFF;
	}
}
