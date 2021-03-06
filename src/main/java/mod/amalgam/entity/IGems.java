package mod.amalgam.entity;

import net.minecraft.entity.INpc;

public interface IGems extends INpc {
	public int generateSkinColor();
	public int generateHairColor();
	public int generateGemstoneColor();
	public int generateInsigniaVariant();
	public int generateUniformVariant();
	public int generateHairVariant();
	public int generateSkinVariant();
	public int generateGemstonePosition();
	public int generateGemstoneCut();
	public boolean generateVisor();
}
