package mod.amalgam.items;

import mod.amalgam.entity.EntityAmalgam;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemColoredGem extends ItemGem implements IItemColor {
	public ItemColoredGem(Class<? extends EntityAmalgam> gemClass, String name, boolean cracked) {
		super(gemClass, name, cracked);
	}
	public ItemColoredGem(Class<? extends EntityAmalgam> gemClass, String name) {
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
