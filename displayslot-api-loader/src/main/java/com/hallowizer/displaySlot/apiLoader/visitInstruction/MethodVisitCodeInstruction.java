package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

public final class MethodVisitCodeInstruction implements VisitInstruction<MethodVisitor> {
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitCode();
	}
}
