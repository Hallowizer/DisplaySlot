package com.hallowizer.displaySlot.plugin.bungee;

import com.hallowizer.displaySlot.plugin.DisplaySlotMain;
import com.hallowizer.displaySlot.plugin.IPlayer;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public final class PlayerJoinListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PostLoginEvent event) {
		IPlayer player = new IPlayer() {};
		DisplaySlotPlugin.getInstance().getPlatform().getPlayerMap().put(player, event.getPlayer());
		DisplaySlotPlugin.getInstance().getPlatform().getReversePlayerMap().put(event.getPlayer(), player);
		DisplaySlotMain.onPlayerJoin(player);
	}
}
