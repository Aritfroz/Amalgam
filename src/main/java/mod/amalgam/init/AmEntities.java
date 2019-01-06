package mod.amalgam.init;

import java.util.ArrayList;

import mod.akrivus.kagic.entity.EntityGem;
import mod.akrivus.kagic.init.ModEntities;
import mod.amalgam.client.render.RenderAgate;
import mod.amalgam.client.render.RenderAmethyst;
import mod.amalgam.client.render.RenderAquaAuraQuartz;
import mod.amalgam.client.render.RenderBismuth;
import mod.amalgam.client.render.RenderBubble;
import mod.amalgam.client.render.RenderCarnelian;
import mod.amalgam.client.render.RenderCitrine;
import mod.amalgam.client.render.RenderConnie;
import mod.amalgam.client.render.RenderEmerald;
import mod.amalgam.client.render.RenderEnderPearl;
import mod.amalgam.client.render.RenderFusedPyrite;
import mod.amalgam.client.render.RenderFusedRuby;
import mod.amalgam.client.render.RenderFusedTopaz;
import mod.amalgam.client.render.RenderGarnet;
import mod.amalgam.client.render.RenderGemShard;
import mod.amalgam.client.render.RenderHessonite;
import mod.amalgam.client.render.RenderInjector;
import mod.amalgam.client.render.RenderJasper;
import mod.amalgam.client.render.RenderLapisLazuli;
import mod.amalgam.client.render.RenderMalachite;
import mod.amalgam.client.render.RenderMelanite;
import mod.amalgam.client.render.RenderNacre;
import mod.amalgam.client.render.RenderNephrite;
import mod.amalgam.client.render.RenderOpal;
import mod.amalgam.client.render.RenderPalanquin;
import mod.amalgam.client.render.RenderPearl;
import mod.amalgam.client.render.RenderPeridot;
import mod.amalgam.client.render.RenderPyrite;
import mod.amalgam.client.render.RenderRainbowQuartz;
import mod.amalgam.client.render.RenderRhodonite;
import mod.amalgam.client.render.RenderRoseQuartz;
import mod.amalgam.client.render.RenderRuby;
import mod.amalgam.client.render.RenderRutile;
import mod.amalgam.client.render.RenderSapphire;
import mod.amalgam.client.render.RenderSpitball;
import mod.amalgam.client.render.RenderSteven;
import mod.amalgam.client.render.RenderStevonnie;
import mod.amalgam.client.render.RenderTopaz;
import mod.amalgam.client.render.RenderWatermelonTourmaline;
import mod.amalgam.client.render.RenderZircon;
import mod.amalgam.entity.EntityBubble;
import mod.amalgam.entity.EntityGemShard;
import mod.amalgam.entity.EntityInjector;
import mod.amalgam.entity.EntityPalanquin;
import mod.amalgam.entity.EntitySpitball;
import mod.amalgam.gem.EntityAgate;
import mod.amalgam.gem.EntityAmethyst;
import mod.amalgam.gem.EntityAquaAuraQuartz;
import mod.amalgam.gem.EntityBismuth;
import mod.amalgam.gem.EntityCarnelian;
import mod.amalgam.gem.EntityCitrine;
import mod.amalgam.gem.EntityEmerald;
import mod.amalgam.gem.EntityEnderPearl;
import mod.amalgam.gem.EntityHessonite;
import mod.amalgam.gem.EntityJasper;
import mod.amalgam.gem.EntityLapisLazuli;
import mod.amalgam.gem.EntityMelanite;
import mod.amalgam.gem.EntityNacre;
import mod.amalgam.gem.EntityNephrite;
import mod.amalgam.gem.EntityPearl;
import mod.amalgam.gem.EntityPeridot;
import mod.amalgam.gem.EntityPyrite;
import mod.amalgam.gem.EntityRoseQuartz;
import mod.amalgam.gem.EntityRuby;
import mod.amalgam.gem.EntityRutile;
import mod.amalgam.gem.EntitySapphire;
import mod.amalgam.gem.EntityTopaz;
import mod.amalgam.gem.EntityWatermelonTourmaline;
import mod.amalgam.gem.EntityZircon;
import mod.amalgam.gem.fusion.EntityFusedPyrite;
import mod.amalgam.gem.fusion.EntityFusedRuby;
import mod.amalgam.gem.fusion.EntityFusedTopaz;
import mod.amalgam.gem.fusion.EntityGarnet;
import mod.amalgam.gem.fusion.EntityMalachite;
import mod.amalgam.gem.fusion.EntityOpal;
import mod.amalgam.gem.fusion.EntityRainbowQuartz;
import mod.amalgam.gem.fusion.EntityRhodonite;
import mod.amalgam.human.EntityConnie;
import mod.amalgam.human.EntitySteven;
import mod.amalgam.human.EntityStevonnie;
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
		registerGem("agate", EntityAgate.class, RenderAgate::new, 0xB3DEFF, 0x1B50D0, AmCruxes.AGATE, pass);
		registerGem("amethyst", EntityAmethyst.class, RenderAmethyst::new, 0xEAE2FF, 0xC49EDB, AmCruxes.AGATE, pass);
		registerGem("aqua_aura_quartz", EntityAquaAuraQuartz.class, RenderAquaAuraQuartz::new, 0x179FC4, 0xBB85D9, AmCruxes.AGATE, pass);
		registerGem("bismuth", EntityBismuth.class, RenderBismuth::new, 0x91A8CF, 0x9C5867, AmCruxes.AGATE, pass);
		registerGem("carnelian", EntityCarnelian.class, RenderCarnelian::new, 0xC13178, 0x510245, AmCruxes.AGATE, pass);
		registerGem("citrine", EntityCitrine.class, RenderCitrine::new, 0xECF404, 0xEBFD64, AmCruxes.AGATE, pass);
		registerGem("emerald", EntityEmerald.class, RenderEmerald::new, 0x2AC861, 0xB2F731, AmCruxes.AGATE, pass);
		registerGem("ender_pearl", EntityEnderPearl.class, RenderEnderPearl::new, 0x000000, 0xFF00FF, AmCruxes.AGATE, pass);
		registerGem("hessonite", EntityHessonite.class, RenderHessonite::new, 0xBE331C, 0xEDCC41, AmCruxes.AGATE, pass);
		registerGem("jasper", EntityJasper.class, RenderJasper::new, 0xF89E57, 0xE13941, AmCruxes.AGATE, pass);
		registerGem("lapis_lazuli", EntityLapisLazuli.class, RenderLapisLazuli::new, 0x4FEEFB, 0x1B6AD6, AmCruxes.AGATE, pass);
		registerGem("melanite", EntityMelanite.class, RenderMelanite::new, 0x343434, 0x2B2F34, AmCruxes.AGATE, pass);
		registerGem("nacre", EntityNacre.class, RenderNacre::new, 0xEDEFF4, 0xFFCF96, AmCruxes.AGATE, pass);
		registerGem("nephrite", EntityNephrite.class, RenderNephrite::new, 0x428F33, 0x0E0D09, AmCruxes.AGATE, pass);
		registerGem("pearl", EntityPearl.class, RenderPearl::new, 0xFCCCB1, 0x92EAD9, AmCruxes.AGATE, pass);
		registerGem("peridot", EntityPeridot.class, RenderPeridot::new, 0x98FF72, 0x13BA54, AmCruxes.AGATE, pass);
		registerGem("pyrite", EntityPyrite.class, RenderPyrite::new, 0xEADC73, 0x71512B, AmCruxes.AGATE, pass);
		registerGem("rose_quartz", EntityRoseQuartz.class, RenderRoseQuartz::new, 0xFEDED3, 0xE99CBE, AmCruxes.AGATE, pass);
		registerGem("ruby", EntityRuby.class, RenderRuby::new, 0xE52C5C, 0x3A0015, AmCruxes.AGATE, pass);
		registerGem("rutile", EntityRutile.class, RenderRutile::new, 0xD2508C, 0x23020D, AmCruxes.AGATE, pass);
		registerGem("sapphire", EntitySapphire.class, RenderSapphire::new, 0xBAF5FD, 0x7298EC, AmCruxes.AGATE, pass);
		registerGem("topaz", EntityTopaz.class, RenderTopaz::new, 0xF5FC51, 0xFDFEA4, AmCruxes.AGATE, pass);
		registerGem("watermelon_tourmaline", EntityWatermelonTourmaline.class, RenderWatermelonTourmaline::new, 0x45E79F, 0xFFC9E2, AmCruxes.AGATE, pass);
		registerGem("zircon", EntityZircon.class, RenderZircon::new, 0x458FBE, 0x57C7CF, AmCruxes.AGATE, pass);
		
		registerGem("fused_pyrite", EntityFusedPyrite.class, RenderFusedPyrite::new, pass);
		registerGem("fused_ruby", EntityFusedRuby.class, RenderFusedRuby::new, pass);
		registerGem("fused_topaz", EntityFusedTopaz.class, RenderFusedTopaz::new, pass);
		registerGem("garnet", EntityGarnet.class, RenderGarnet::new, pass);
		registerGem("malachite", EntityMalachite.class, RenderMalachite::new, pass);
		registerGem("opal", EntityOpal.class, RenderOpal::new, pass);
		registerGem("rainbow_quartz", EntityRainbowQuartz.class, RenderRainbowQuartz::new, pass);
		registerGem("rhodonite", EntityRhodonite.class, RenderRhodonite::new, pass);
		
		registerMob("steven", EntitySteven.class, RenderSteven::new, 0xFD6270, 0xFFD248, pass);
		registerMob("connie", EntityConnie.class, RenderConnie::new, 0x99D3CD, 0xAF4E3D, pass);
		registerEntity("gem_shard", EntityGemShard.class, RenderGemShard::new, pass);
		registerEntity("stevonnie", EntityStevonnie.class, RenderStevonnie::new, pass);
		registerEntity("spitball", EntitySpitball.class, RenderSpitball::new, pass);
		registerEntity("bubble", EntityBubble.class, RenderBubble::new, pass);
		registerEntity("injector", EntityInjector.class, RenderInjector::new, pass);
		registerEntity("palanquin", EntityPalanquin.class, RenderPalanquin::new, pass);
	}
	public static <T extends EntityGem> void registerGem(String name, Class<T> entity, IRenderFactory<T> renderer, int back, int fore, ArrayList<CruxEntry> cruxes, int pass) {
		AmEntities.registerMob(name, entity, renderer, back, fore, pass);
		if (pass > 0) {
			AmGems.registerGemEntity(new ResourceLocation("amalgam:" + name), entity, cruxes);
			ModEntities.GEMS.put("amalgam." + name, entity);
		}
	}
	public static <T extends EntityGem> void registerGem(String name, Class<T> entity, IRenderFactory<T> renderer, int pass) {
		AmEntities.registerEntity(name, entity, renderer, pass);
		if (pass > 0) {
			ModEntities.GEMS.put("amalgam." + name, entity);
		}
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
