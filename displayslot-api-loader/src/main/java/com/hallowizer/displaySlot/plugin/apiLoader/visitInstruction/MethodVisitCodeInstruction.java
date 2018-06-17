package com.hallowizer.displaySlot.plugin.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

public final class MethodVisitCodeInstruction implements VisitInstruction<MethodVisitor> {
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitCode();
	}
}
