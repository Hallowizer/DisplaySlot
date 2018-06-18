package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitLdcInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final Object value;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitLdcInsn(value);
	}
}
