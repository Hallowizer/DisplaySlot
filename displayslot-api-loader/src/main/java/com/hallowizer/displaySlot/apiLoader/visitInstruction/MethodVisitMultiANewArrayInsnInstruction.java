package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitMultiANewArrayInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final String descriptor;
	private final int numDimensions;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitMultiANewArrayInsn(descriptor, numDimensions);
	}
}
