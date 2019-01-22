package mod.amalgam.items;

import java.util.List;

import javax.annotation.Nullable;

import mod.amalgam.init.AmSounds;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemConnieBracelet extends ModRecord {
	public ItemConnieBracelet() {
		super("connie_bracelet", AmSounds.RECORD_LOVE_LIKE_YOU, false);
		this.setCreativeTab(ModCreativeTabs.CREATIVE_TAB_OTHER);
	}
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		EnumActionResult result = super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
		if (result == EnumActionResult.PASS && !world.isRemote) {
			ITextComponent message = new TextComponentTranslation(String.format("command.amalgam.connie_doesnt_spawn"));
			message.getStyle().setColor(TextFormatting.GRAY);
			player.sendMessage(message);
	       	return EnumActionResult.SUCCESS;
		}
		return result;
	}
	@Override
	public boolean onEntityItemUpdate(EntityItem entity) {
		entity.isDead = false;
		entity.setEntityInvulnerable(true);
		entity.extinguish();
        return false;
    }
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
		
    }
    @Override
	public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.COMMON;
    }
}