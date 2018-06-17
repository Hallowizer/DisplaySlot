package com.hallowizer.displaySlot.plugin.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.hallowizer.displaySlot.plugin.IPluginMessageListener;
import com.hallowizer.displaySlot.plugin.annotation.NonNull;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PluginMessageListenerWrapper implements PluginMessageListener {
	@NonNull
	private final IPluginMessageListener listener;
	
	@Override
	public void onPluginMessageReceived(String name, Player player, byte[] data) {
		listener.onPluginMessageReceived(name, DisplaySlotPlugin.getInstance().getPlatform().getReversePlayerMap().get(player), data);
	}
}
