package com.hallowizer.displaySlot.apiLoader.visitInstruction;

import org.objectweb.asm.MethodVisitor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class MethodVisitInsnInstruction implements VisitInstruction<MethodVisitor> {
	private final int opcode;
	
	@Override
	public void execute(MethodVisitor mv) {
		mv.visitInsn(opcode);
	}
}
