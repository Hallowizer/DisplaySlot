package com.hallowizer.displaySlot.plugin.apiLoader;

import static org.objectweb.asm.Opcodes.ASM5;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public final class TapMethodAnnotationClassAdapter extends ClassVisitor {
	private ApiLoaderContext ctx;
	
	public TapMethodAnnotationClassAdapter(ApiLoaderContext ctx, ClassVisitor cv) {
		super(ASM5, cv);
		this.ctx = ctx;
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
		return new TapMethodAnnotationMethodAdapter(ctx, cv, access, name, descriptor, signature, exceptions);
	}
}
