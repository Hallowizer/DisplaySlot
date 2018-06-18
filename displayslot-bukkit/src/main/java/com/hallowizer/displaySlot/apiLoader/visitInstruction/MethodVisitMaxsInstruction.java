package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitMaxsInstruction implements VisitInstruction<MethodVisitor> {
	private final int maxStack;
	private final int maxLocals;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitMaxs(maxStack, maxLocals);
	}
}
