package com.hallowizer.displaySlot.plugin.bungee;

import com.hallowizer.displaySlot.plugin.DisplaySlotMain;

import lombok.Getter;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class DisplaySlotPlugin extends Plugin {
	@Getter
	private static DisplaySlotPlugin instance;
	
	@Override
	public void onLoad() {
		instance = this;
		DisplaySlotMain.onLoad(new BungeeDisplaySlotPlatform());
		
		getLogger().info("DisplaySlot has been loaded!");
	}
	
	@Override
	public void onEnable() {
		ProxyServer.getInstance().getPluginManager().registerListener(this, new PlayerJoinListener());
		DisplaySlotMain.onEnable();
		getLogger().info("DisplaySlot has been enabled!");
	}
	
	@Override
	public void onDisable() {
		DisplaySlotMain.onDisable();
		getLogger().info("DisplaySlot has been disabled!");
	}
	
	public BungeeDisplaySlotPlatform getPlatform() {
		return (BungeeDisplaySlotPlatform) DisplaySlotMain.getPlatform();
	}
}
