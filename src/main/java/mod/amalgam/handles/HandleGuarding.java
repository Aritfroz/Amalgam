package mod.amalgam.handles;

import com.google.common.base.Predicate;

import mod.amalgam.entity.EntityQuartz;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandleGuarding {
	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent e) {
		if (e.getEntity() instanceof EntityMob) {
			EntityMob mob = (EntityMob)(e.getEntity());
			double height = mob.height;
			mob.tasks.addTask(0, new EntityAIAvoidEntity<EntityQuartz>(mob, EntityQuartz.class, new Predicate<EntityQuartz>() {
				@Override
				public boolean apply(EntityQuartz applicant) {
					return applicant.chargedByTakingDamageNotDelivering && height < applicant.height;
				}
			}, 16, 0.2D, 0.8D));
		}
	}
}
