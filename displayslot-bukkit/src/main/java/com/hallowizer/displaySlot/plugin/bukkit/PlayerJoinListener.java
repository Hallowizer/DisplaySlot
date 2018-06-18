package com.hallowizer.displaySlot.plugin.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.hallowizer.displaySlot.plugin.DisplaySlotMain;
import com.hallowizer.displaySlot.plugin.IPlayer;

public final class PlayerJoinListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		IPlayer player = new IPlayer() {};
		DisplaySlotPlugin.getInstance().getPlatform().getPlayerMap().put(player, event.getPlayer());
		DisplaySlotPlugin.getInstance().getPlatform().getReversePlayerMap().put(event.getPlayer(), player);
		DisplaySlotMain.onPlayerJoin(player);
	}
}
