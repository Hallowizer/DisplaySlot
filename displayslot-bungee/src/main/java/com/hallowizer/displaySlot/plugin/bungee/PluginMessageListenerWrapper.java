package com.hallowizer.displaySlot.plugin.bungee;

import com.hallowizer.displaySlot.plugin.IPluginMessageListener;
import com.hallowizer.displaySlot.plugin.annotation.NonNull;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@RequiredArgsConstructor
public final class PluginMessageListenerWrapper implements Listener {
	@NonNull
	private final IPluginMessageListener listener;
	
	@EventHandler
	public void onPluginMessageReceived(PluginMessageEvent event) {
		listener.onPluginMessageReceived(event.getTag(), DisplaySlotPlugin.getInstance().getPlatform().getReversePlayerMap().get((ProxiedPlayer) event.getSender()), event.getData());
	}
}
