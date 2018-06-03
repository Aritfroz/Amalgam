package mod.akrivus.amalgam.init;

import mod.akrivus.amalgam.gem.EntityCitrine;
import mod.akrivus.amalgam.gem.EntityConnie;
import mod.akrivus.amalgam.gem.EntityEnderPearl;
import mod.akrivus.amalgam.gem.EntityNacre;
import mod.akrivus.amalgam.gem.EntityPyrite;
import mod.akrivus.amalgam.gem.EntitySteven;
import mod.akrivus.kagic.init.ModEntities;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class AmGems {
	private static int currentID = 4;
	public static void register() {
		ModEntities.registerExternalGem("amalgam", "citrine", EntityCitrine.class, "mod/akrivus/amalgam/client/render/RenderCitrine", 0xECF404, 0xEBFD64, false);
		ModEntities.registerExternalGem("amalgam", "pyrite", EntityPyrite.class, "mod/akrivus/amalgam/client/render/RenderPyrite", 0xEADC73, 0x71512B, false);
		ModEntities.registerExternalGem("amalgam", "ender_pearl", EntityEnderPearl.class, "mod/akrivus/amalgam/client/render/RenderEnderPearl", 0x000000, 0xFF00FF, false);
		ModEntities.registerExternalGem("amalgam", "nacre", EntityNacre.class, "mod/akrivus/amalgam/client/render/RenderNacre", 0xEDEFF4, 0xFFCF96, false);
		
		registerMob("steven", EntitySteven.class, 0xFD6270, 0xFFD248);
		registerMob("connie", EntityConnie.class, 0x99D3CD, 0xAF4E3D);
	}
	public static <T extends Entity> void registerMob(String name, Class<T> entity, int back, int fore) {
		EntityRegistry.registerModEntity(new ResourceLocation("amalgam:" + name), entity, name, currentID, Amalgam.instance, 256, 1, true, back, fore);
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			try {
				Class<Render<? extends T>> render = (Class<Render<? extends T>>) Amalgam.class.getClassLoader().loadClass("mod/akrivus/amalgam/client/render/" + entity.getName().replaceAll(".+?Entity", "Render"));
				RenderingRegistry.registerEntityRenderingHandler(entity, render.newInstance());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		++currentID;
	}
}
