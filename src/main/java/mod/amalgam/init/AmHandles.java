package mod.amalgam.init;

import mod.amalgam.handles.HandleBubbling;
import mod.amalgam.handles.HandleCapturing;
import mod.amalgam.handles.HandleGuarding;
import mod.amalgam.handles.HandleShattering;
import net.minecraftforge.common.MinecraftForge;

public class AmHandles {
	public static void register() {
		registerHandle(new HandleBubbling());
		registerHandle(new HandleCapturing());
		registerHandle(new HandleShattering());
		registerHandle(new HandleGuarding());
	}
	public static void registerHandle(Object handler) {
		MinecraftForge.EVENT_BUS.register(handler);
	}
}
