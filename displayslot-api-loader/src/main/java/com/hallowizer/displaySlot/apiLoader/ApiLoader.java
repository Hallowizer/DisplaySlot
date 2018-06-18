package com.hallowizer.displaySlot.apiLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URL;

import com.hallowizer.http.client.HttpClient;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiLoader {
	@SuppressWarnings("resource")
	@SneakyThrows
	public void loadAPI(ApiLoaderContext ctx) {
		String data = HttpClient.get("https://hallowizer.com/resources/DisplaySlotAPI.jar");
		boolean error = false;
		if (!data.startsWith("Êþº¾")) { // Êþº¾ is 0xCA 0xFE 0xBA 0xBE
			ctx.getLogger().warning("Failed to download api. If this happens on a regular basis, please check The Opal Games website and/or your internet connection.");
			error = true;
		}
		
		File file = new File(ctx.getDataFolder(), "api.jar");
		
		if (!file.exists()) {
			if (error) {
				ctx.getLogger().severe("No downloaded api is available. Disabling the plugin.");
				ctx.disablePlugin();
				return;
			}
			
			file.createNewFile();
		}
		
		if (!error)
			try (FileOutputStream out = new FileOutputStream(file); PrintWriter pout = new PrintWriter(out)) {
				pout.write(data);
			}
		
		ApiClassLoader loader = new ApiClassLoader(new URL[] {new URL(".api.jar")}, ctx);
		Class<?> apiMain = loader.loadClass("com.hallowizer.displaySlot.api.DisplaySlot");
		Class<?> apiCtxClazz = Class.forName("com.hallowizer.displaySlot.api.DefaultApiContext");
		
		Object apiCtx = apiCtxClazz.newInstance();
		
		Field ctxField = apiMain.getDeclaredField("ctx");
		ctxField.setAccessible(true);
		ctxField.set(null, apiCtx);
		ctxField.setAccessible(false);
		
		Field pluginCtxField = apiCtxClazz.getDeclaredField("ctx");
		pluginCtxField.set(apiCtx, ctx.newPluginContext());
	}
}
