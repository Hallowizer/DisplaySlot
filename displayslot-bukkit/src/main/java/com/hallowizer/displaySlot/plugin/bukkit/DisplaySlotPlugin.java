package com.hallowizer.displaySlot.plugin.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.hallowizer.displaySlot.plugin.DisplaySlotMain;

import lombok.Getter;

public final class DisplaySlotPlugin extends JavaPlugin {
	@Getter
	private static DisplaySlotPlugin instance;
	
	@Override
	public void onLoad() {
		instance = this;
		DisplaySlotMain.onLoad(new BukkitDisplaySlotPlatform());
		
		getLogger().info("DisplaySlot has been loaded!");
	}
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		DisplaySlotMain.onEnable();
		getLogger().info("DisplaySlot has been enabled!");
	}
	
	@Override
	public void onDisable() {
		DisplaySlotMain.onDisable();
		getLogger().info("DisplaySlot has been disabled!");
	}
	
	public BukkitDisplaySlotPlatform getPlatform() {
		return (BukkitDisplaySlotPlatform)DisplaySlotMain.getPlatform();
	}
}
