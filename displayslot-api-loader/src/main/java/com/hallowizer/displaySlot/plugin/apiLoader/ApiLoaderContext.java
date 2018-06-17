package com.hallowizer.displaySlot.plugin.apiLoader;

import java.util.logging.Logger;

import com.hallowizer.displaySlot.api.PluginContext;

public interface ApiLoaderContext {
	public Logger getLogger();
	public void disablePlugin();
	public String getPlatformName();
	public PluginContext newPluginContext();
}
