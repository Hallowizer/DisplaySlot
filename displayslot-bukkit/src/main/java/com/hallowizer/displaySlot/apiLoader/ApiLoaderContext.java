package com.hallowizer.displaySlot.apiLoader;

import java.io.File;
import java.util.logging.Logger;

public interface ApiLoaderContext {
	public Logger getLogger();
	public void disablePlugin();
	public String getPlatformName();
	public PluginContext newPluginContext();
	public File getDataFolder();
}
