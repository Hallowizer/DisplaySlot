package com.hallowizer.displaySlot.plugin.bungee;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import com.hallowizer.displaySlot.plugin.IDisplaySlotPlatform;
import com.hallowizer.displaySlot.plugin.IPlayer;
import com.hallowizer.displaySlot.plugin.IPluginMessageListener;
import com.hallowizer.displaySlot.plugin.Platform;

import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public final class BungeeDisplaySlotPlatform implements IDisplaySlotPlatform {
	@Getter
	private final Map<IPlayer,ProxiedPlayer> playerMap = new HashMap<>();
	@Getter
	private final Map<ProxiedPlayer,IPlayer> reversePlayerMap = new HashMap<>();
	
	@Override
	public Platform getPlatform() {
		return Platform.BUNGEE;
	}

	@Override
	public void registerIncomingPluginChannel(String name, IPluginMessageListener listener) {
		if (!ProxyServer.getInstance().getChannels().contains(name))
			ProxyServer.getInstance().registerChannel(name);
		
		ProxyServer.getInstance().getPluginManager().registerListener(DisplaySlotPlugin.getInstance(), new PluginMessageListenerWrapper(listener));
	}

	@Override
	public void registerOutgoingPluginChannel(String name) {
		if (!ProxyServer.getInstance().getChannels().contains(name))
			ProxyServer.getInstance().registerChannel(name);
	}

	@Override
	public void sendPluginMessage(IPlayer player, String channel, byte[] data) {
		playerMap.get(player).sendData(channel, data);
	}

	@Override
	public IPlayer getPlayer(UUID uuid) {
		return reversePlayerMap.get(ProxyServer.getInstance().getPlayer(uuid));
	}

	@Override
	public Logger getLogger() {
		return DisplaySlotPlugin.getInstance().getLogger();
	}

	@Override
	public void disablePlugin() {
		ProxyServer.getInstance().getPluginManager().unregisterListeners(DisplaySlotPlugin.getInstance());
		DisplaySlotPlugin.getInstance().onDisable();
	}

	@Override
	public File getDataFolder() {
		return DisplaySlotPlugin.getInstance().getDataFolder();
	}
}
