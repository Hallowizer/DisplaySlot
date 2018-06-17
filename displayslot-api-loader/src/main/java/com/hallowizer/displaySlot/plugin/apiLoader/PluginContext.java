package com.hallowizer.displaySlot.apiLoader;

import java.util.UUID;

public interface PluginContext {
	public void sendPluginMessage(UUID uuid, String channel, byte[] data);
	public int getScreenWidth(UUID uuid);
	public int getScreenHeight(UUID uuid);
}
