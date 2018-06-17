package com.hallowizer.displaySlot.plugin.apiLoader;

import static org.objectweb.asm.Opcodes.ASM5;

import org.objectweb.asm.AnnotationVisitor;

public final class TapMethodAnnotationAnnotationAdapter extends AnnotationVisitor {
	private TapMethodAnnotationMethodAdapter mv;
	private ApiLoaderContext ctx;
	
	public TapMethodAnnotationAnnotationAdapter(AnnotationVisitor av, TapMethodAnnotationMethodAdapter mv, ApiLoaderContext ctx) {
		super(ASM5, av);
		this.mv = mv;
		this.ctx = ctx;
	}
	
	@Override
	public void visitEnum(String name, String descriptor, String value) {
		if (name.equals("value")) {
			if (!value.equals(ctx.getPlatformName()))
				mv.invalid = true;
		}
	}
}
