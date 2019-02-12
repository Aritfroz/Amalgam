package mod.amalgam.init;

import java.util.ArrayList;

import mod.amalgam.client.render.RenderBubble;
import mod.amalgam.client.render.RenderGemShard;
import mod.amalgam.client.render.RenderHandShip;
import mod.amalgam.client.render.RenderInjector;
import mod.amalgam.client.render.RenderPalanquin;
import mod.amalgam.entity.EntityGem;
import mod.amalgam.entity.EntityGemShard;
import mod.amalgam.entity.machine.EntityBubble;
import mod.amalgam.entity.machine.EntityInjector;
import mod.amalgam.entity.vehicle.EntityHandShip;
import mod.amalgam.entity.vehicle.EntityPalanquin;
import mod.amalgam.injection.CruxEntry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class AmEntities {
	private static int currentID = 0;
	public static void register(int pass) {
		registerEntity("injector", EntityInjector.class, RenderInjector::new, pass);
		registerEntity("gem_shard", EntityGemShard.class, RenderGemShard::new, pass);
		registerEntity("bubble", EntityBubble.class, RenderBubble::new, pass);
		registerEntity("palanquin", EntityPalanquin.class, RenderPalanquin::new, pass);
		registerEntity("hand_ship", EntityHandShip.class, RenderHandShip::new, pass);
	}
	public static <T extends EntityGem> void registerGem(String name, Class<T> entity, IRenderFactory<T> renderer, int back, int fore, ArrayList<CruxEntry> cruxes, int pass) {
		AmEntities.registerMob(name, entity, renderer, back, fore, pass);
		if (pass > 0) {
			AmGems.registerGemEntity(new ResourceLocation("amalgam:" + name), entity, cruxes);
		}
	}
	public static <T extends EntityGem> void registerGem(String name, Class<T> entity, IRenderFactory<T> renderer, int pass) {
		AmEntities.registerEntity(name, entity, renderer, pass);
	}
	public static <T extends EntityLiving> void registerMob(String name, Class<T> entity, IRenderFactory<T> renderer, int back, int fore, int pass) {
		AmEntities.registerEntity(name, entity, renderer, pass);
		if (pass > 0) {
			EntityRegistry.registerEgg(new ResourceLocation("amalgam:" + name), back, fore);
		}
	}
	public static <T extends Entity> void registerEntity(String name, Class<T> entity, IRenderFactory<T> renderer, int pass) {
		if (pass > 0) {
			EntityRegistry.registerModEntity(new ResourceLocation("amalgam:" + name), entity, name, currentID, Amalgam.instance, 256, 1, true);
			++AmEntities.currentID;
		}
		else if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			RenderingRegistry.registerEntityRenderingHandler(entity, renderer);
		}
	}
}
