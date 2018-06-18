package com.hallowizer.displaySlot.plugin.bukkit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.hallowizer.displaySlot.plugin.IDisplaySlotPlatform;
import com.hallowizer.displaySlot.plugin.IPlayer;
import com.hallowizer.displaySlot.plugin.IPluginMessageListener;
import com.hallowizer.displaySlot.plugin.Platform;

import lombok.Getter;

public final class BukkitDisplaySlotPlatform implements IDisplaySlotPlatform {
	@Getter
	private final Map<IPlayer,Player> playerMap = new HashMap<>();
	@Getter
	private final Map<Player,IPlayer> reversePlayerMap = new HashMap<>();
	
	@Override
	public Platform getPlatform() {
		return Platform.BUKKIT;
	}

	@Override
	public void registerIncomingPluginChannel(String name, IPluginMessageListener listener) {
		Bukkit.getMessenger().registerIncomingPluginChannel(DisplaySlotPlugin.getInstance(), name, new PluginMessageListenerWrapper(listener));
	}

	@Override
	public void registerOutgoingPluginChannel(String name) {
		Bukkit.getMessenger().registerOutgoingPluginChannel(DisplaySlotPlugin.getInstance(), name);
	}

	@Override
	public void sendPluginMessage(IPlayer player, String channel, byte[] data) {
		playerMap.get(player).sendPluginMessage(DisplaySlotPlugin.getInstance(), channel, data);
	}

	@Override
	public IPlayer getPlayer(UUID uuid) {
		return reversePlayerMap.get(Bukkit.getPlayer(uuid));
	}

	@Override
	public Logger getLogger() {
		return DisplaySlotPlugin.getInstance().getLogger();
	}

	@Override
	public void disablePlugin() {
		Bukkit.getPluginManager().disablePlugin(DisplaySlotPlugin.getInstance());
	}

	@Override
	public File getDataFolder() {
		return DisplaySlotPlugin.getInstance().getDataFolder();
	}
}
