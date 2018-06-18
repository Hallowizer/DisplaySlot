package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitFrameInstruction implements VisitInstruction<MethodVisitor> {
	private final int type;
	private final int nLocal;
	private final Object[] local;
	private final int nStack;
	private final Object[] stack;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitFrame(type, nLocal, local, nStack, stack);
	}
}
