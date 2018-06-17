package com.hallowizer.displaySlot.plugin.apiLoader;

import static org.objectweb.asm.Opcodes.ASM5;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.jar.Manifest;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import lombok.SneakyThrows;
import sun.misc.Resource;
import sun.misc.URLClassPath;

@SuppressWarnings("restriction")
public final class ApiClassLoader extends URLClassLoader {
	private ApiLoaderContext ctx;
	
	public ApiClassLoader(URL[] urls, ApiLoaderContext ctx) {
		super(urls);
		this.ctx = ctx;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] originalData = loadUrlClass(name);
		
		ClassWriter cw = new ClassWriter(ASM5);
		ClassVisitor tapMethodAnnotation = new TapMethodAnnotationClassAdapter(ctx, cw);
		ClassReader cr = new ClassReader(originalData);
		cr.accept(tapMethodAnnotation, 0);
		byte[] data = cw.toByteArray();
		
		return defineClass(name, data, 0, data.length);
	}
	
	private byte[] loadUrlClass(final String name) throws ClassNotFoundException { // Majority of method written by Oracle, but the return type was changed to byte[]. Changes have been outlined in comments.
		final byte[] result;
		try {
			result = AccessController.doPrivileged(
				new PrivilegedExceptionAction<byte[]>() {
					@SneakyThrows
					public byte[] run() {
						String path = name.replace('.', '/').concat(".class");
						Resource res = getUcp().getResource(path, false); // ucp replaced by getUcp()
						if (res != null) {
							try {
								return toBytes(name, res); // defineClass() replaced by toBytes()
							} catch (IOException e) {
								throw new ClassNotFoundException(name, e);
							}
						} else {
							return null;
						}
					}
				}, getAcc());
		} catch (java.security.PrivilegedActionException pae) {
			throw (ClassNotFoundException) pae.getException();
		}
		if (result == null) {
			throw new ClassNotFoundException(name);
		}
		return result;
	}
	
	private byte[] toBytes(String name, Resource resource) throws Exception { // Majority of method written by Oracle, but the return type was changed to byte[]. Changes have been outlined in comments.
		int i = name.lastIndexOf('.');
		URL url = resource.getCodeSourceURL();
		if (i != -1) {
			String pkgname = name.substring(0, i);
			Manifest man = resource.getManifest();
			definePackageInternal(pkgname, man, url); // Invokes reflection method instead of original method directly.
		}
		java.nio.ByteBuffer bb = resource.getByteBuffer();
		if (bb != null) {
			return bb.array(); // defineClass() replaced by bb.array();
		} else {
			return resource.getBytes(); // defineClass() replaced by resource.getBytes();
		}
	}
	
	private void definePackageInternal(String name, Manifest manifest, URL url) throws Exception {
		Method method = URLClassLoader.class.getDeclaredMethod("definePackageInternal", String.class, Manifest.class, URL.class);
		method.setAccessible(true);
		method.invoke(this, name, manifest, url);
	}
	
	@SneakyThrows
	private AccessControlContext getAcc() {
		Field field = URLClassLoader.class.getDeclaredField("acc");
		field.setAccessible(true);
		return (AccessControlContext) field.get(this);
	}
	
	@SneakyThrows
	private URLClassPath getUcp() {
		Field field = URLClassLoader.class.getDeclaredField("ucp");
		field.setAccessible(true);
		return (URLClassPath) field.get(this);
	}
}
