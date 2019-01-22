package mod.amalgam.init;

import mod.amalgam.handles.HandleBubbling;
import mod.amalgam.handles.HandleCapturing;
import mod.amalgam.handles.HandleGemShards;
import mod.amalgam.handles.HandleGuarding;
import net.minecraftforge.common.MinecraftForge;

public class AmHandles {
	public static void register() {
		registerHandler(new HandleBubbling());
		registerHandler(new HandleCapturing());
		registerHandler(new HandleGemShards());
		registerHandler(new HandleGuarding());
	}
	public static void registerHandler(Object handler) {
		MinecraftForge.EVENT_BUS.register(handler);
	}
}
